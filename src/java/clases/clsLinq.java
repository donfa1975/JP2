/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import facade.TblMatriculasFacade;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.TblAreas;
import modelo.TblMatriculas;
import modelo.TblPacientes;

/**
 *
 * @author fabricio.diaz
 */
public class clsLinq {
	
	public String matricular(TblPacientes p, TblAreas a, int anio)
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JP2PU");
        EntityManager em = emf.createEntityManager();
		
		Query q1=em.createQuery("select c from TblMatriculas c where c.anio=:anio");
		//q1.setParameter("p", p.getIdPaciente());
		q1.setParameter("anio", anio);
		
		List<TblMatriculas> l=q1.getResultList();
		
		Query q2=em.createQuery("Update TblPacientes c set c.idArea=:ar where c.idPaciente=:id");
		q2.setParameter("ar", a);
		q2.setParameter("id", p.getIdPaciente());
		q2.executeUpdate();
		
		Optional<TblMatriculas> mat=l.stream().filter(x->x.getIdPaciente().getIdPaciente()==p.getIdPaciente()).findFirst();
		
		String numMat="";
		
		if(mat.isPresent()) // ya existe la matricula
		{
			return mat.get().getNumMat();
		}
		
		int contar=l.size()+1;
		String pred="";
		if(contar<10)
		{
			pred="00";
		}
		else if(contar<100)
		{
			pred="0";
		}
		
		numMat=String.format("%s%s-%s", pred,String.valueOf(contar),String.valueOf(anio));
		
		TblMatriculasFacade matC;
		matC = new TblMatriculasFacade();
		
	//	matC.create(entity);
		
		return "ok";
	}
}
