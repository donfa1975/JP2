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
public class clsItemTab {
	private String titulo;
	private int cuenta;

	public clsItemTab(String titulo, int cuenta) {
		this.titulo = titulo;
		this.cuenta = cuenta;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}
	
	
	
}
