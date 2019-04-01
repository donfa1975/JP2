/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.TblUsuarios;

/**
 *
 * @author fabricio.diaz
 */
public class clsUsuario {
	
//<editor-fold desc="propiedades">
	private int id;
	private String usuario;
	private String pwd;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
//</editor-fold>
	
	public clsUsuario(String usuario, String pwd) {
		this.usuario = usuario;
		this.pwd = pwd;
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JP2PU");
        EntityManager em = emf.createEntityManager();
		
		Query q1=em.createQuery("select c from TblUsuarios c where c.usuario=:user");
		q1.setParameter("user", usuario);
		try{
			TblUsuarios tu=(TblUsuarios)q1.getSingleResult();
			if(tu.getClave().equals(pwd))
			{
				this.id=tu.getIdUsuario();
			}
			else
			{
				this.id=0;
			}
			
		}
		catch(Exception ex)
		{
			this.id=0;
		}
		
	}
	
	
}
