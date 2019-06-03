/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import facade.TblCitasFacade;
import java.util.Date;
import javax.faces.event.ActionEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.UserTransaction;
import javax.validation.ConstraintViolation;
import modelo.*;

/**
 *
 * @author fabricio.diaz
 */
@Named(value = "nCitasController")
@ViewScoped
public class nCitasController {
	
	@Inject
	private TblPacientesController idPacienteController;
	
	private String nombre;
	
	private Date fecha;
	
	private Date hora;
	
	private TblAcciones accion; 
	
	/**
	 * Creates a new instance of nCitasController
	 */
	public nCitasController() {
		
		
	}
	
		public void prepareIdPaciente(ActionEvent event) {
			if(idPacienteController.getSelected()!=null)
			{
				nombre=idPacienteController.getSelected().getApellido() +" "+ idPacienteController.getSelected().getNombre();
			}
			else
			{
				nombre="Seleccione paciente";
			}
		}
	
	public String getNombre() {
		/*if(idPacienteController.getSelected()!=null){
		return idPacienteController.getSelected().getApellido();}
		else
		{
			return "";
		}*/
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}

	public TblAcciones getAccion() {
		return accion;
	}

	public void setAccion(TblAcciones accion) {
		this.accion = accion;
	}
	
	public void saveNew()
	{
		
		TblCitas nc=new TblCitas();
		
		TblPacientes mp=this.idPacienteController.getSelected();
		
		nc.setEjecutada(Boolean.FALSE);
		nc.setFecha(fecha);
		nc.setFechaReg(new Date());
		nc.setHora(String.format("%d:%d", hora.getHours(),hora.getMinutes()) );
		nc.setIdAccion(accion);
		nc.setIdCita(0);
		nc.setIdPaciente(mp);
		nc.setIdusuario(1);
		nc.setNumeroCita("001");
		
		//TblCitasFacade tbc=new TblCitasFacade();
		
		try
		{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("JP2PU");
			EntityManager em = emf.createEntityManager();
			UserTransaction t = (UserTransaction)new InitialContext().lookup("java:comp/UserTransaction");
			t.begin();
			em.joinTransaction();
			//q.executeUpdate();
			em.persist(nc);
			t.commit();
			
		}
		catch(javax.validation.ConstraintViolationException  e)
			{
				//e.printStackTrace();
				for(ConstraintViolation cv:e.getConstraintViolations())
				{
					cv.getMessage();
				}
				
			}
		catch(Exception e1)
		{
			
			
		}
		
	}
}
