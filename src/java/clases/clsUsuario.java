/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

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
	}
	
	
}
