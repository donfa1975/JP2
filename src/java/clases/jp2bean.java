/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author fabricio.diaz
 */
@Named(value = "jp2bean")
@SessionScoped
public class jp2bean implements Serializable {

	/**
	 * Creates a new instance of jp2bean
	 */
	private clsUsuario myUser;
	private String usuario;
	private String pwd;
	private clsTablero tablero;
	
	public jp2bean() {
		
	}
	
	public String login()
	{
		myUser=new clsUsuario(usuario,pwd);
		if(myUser.getId()==0)
		{
			return "";
		}
		else
		{
			setTablero(new clsTablero());
			
			getTablero().update();
			
			return "index?faces-redirect=true"; 
		}
		
	}
	
//<editor-fold desc="propiedades">
	public clsUsuario getMyUser() {
		return myUser;
	}

	public void setMyUser(clsUsuario myUser) {
		this.myUser = myUser;
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

	public clsTablero getTablero() {
		return tablero;
	}

	public void setTablero(clsTablero tablero) {
		this.tablero = tablero;
	}
}
