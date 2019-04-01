/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.*;

/**
 *
 * @author fabricio.diaz
 */
public class clsTablero {
	
	private List<clsItemTab> datos;
	//////////////////////////////

	public clsTablero() {
	}
	public void update()
	{
		String msg;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JP2PU");
        EntityManager em = emf.createEntityManager();
		
		String titulo;
		int valor;
		
		List<VwCitas> citas;
		List<TblPacientes> pacientes;
		
		datos=new ArrayList<>();
		
		clsItemTab item;
		
		Query q1=em.createQuery("select c from TblPacientes c where c.idOrigen IS NOT NULL");
		
		Query q2=em.createQuery("select c from VwCitas c");
		
		pacientes=q1.getResultList();
		
		citas=q2.getResultList();
		try
		{
		titulo="Total Pacientes";valor=pacientes.size();
		datos.add(new clsItemTab(titulo,valor));
		
		titulo="Pacientes Asociacion Nuestra familia"; 
		valor=(int)pacientes.stream().filter(x->x.getIdOrigen().getIdorigen()==1).count();
		datos.add(new clsItemTab(titulo,valor));
		
		titulo="Pacientes Instituto Juan Pablo Segundo"; 
		valor=(int)pacientes.stream().filter(x->x.getIdOrigen().getIdorigen()==2).count();
		datos.add(new clsItemTab(titulo,valor));
		
		titulo="Pacientes Externos"; 
		valor=(int)pacientes.stream().filter(x->x.getIdOrigen().getIdorigen()==3).count();
		datos.add(new clsItemTab(titulo,valor));
		
		titulo="Consultas"; 
		valor=citas.size();
		datos.add(new clsItemTab(titulo,valor));
		}
		catch(Exception ex)
		{
			msg=ex.getMessage();
			
		}
		em.close();
		emf.close();
	}
	//////////////////////////////
	
	//////////////////////////////
//<editor-fold desc="propiedades">
	public List<clsItemTab> getDatos() {
		return datos;
	}

	public void setDatos(List<clsItemTab> datos) {
		this.datos = datos;
	}
//</editor-fold>	

	
	
}
