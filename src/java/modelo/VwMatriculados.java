/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabricio.diaz
 */
@Entity
@Table(name = "vwMatriculados")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VwMatriculados.findAll", query = "SELECT v FROM VwMatriculados v")
	, @NamedQuery(name = "VwMatriculados.findByIdPaciente", query = "SELECT v FROM VwMatriculados v WHERE v.idPaciente = :idPaciente")
	, @NamedQuery(name = "VwMatriculados.findByCedula", query = "SELECT v FROM VwMatriculados v WHERE v.cedula = :cedula")
	, @NamedQuery(name = "VwMatriculados.findByApellido", query = "SELECT v FROM VwMatriculados v WHERE v.apellido = :apellido")
	, @NamedQuery(name = "VwMatriculados.findByNombre", query = "SELECT v FROM VwMatriculados v WHERE v.nombre = :nombre")
	, @NamedQuery(name = "VwMatriculados.findBySexo", query = "SELECT v FROM VwMatriculados v WHERE v.sexo = :sexo")
	, @NamedQuery(name = "VwMatriculados.findByArea", query = "SELECT v FROM VwMatriculados v WHERE v.area = :area")
	, @NamedQuery(name = "VwMatriculados.findByEdad", query = "SELECT v FROM VwMatriculados v WHERE v.edad = :edad")
	, @NamedQuery(name = "VwMatriculados.findByAnio", query = "SELECT v FROM VwMatriculados v WHERE v.anio = :anio")
	, @NamedQuery(name = "VwMatriculados.findByNumMat", query = "SELECT v FROM VwMatriculados v WHERE v.numMat = :numMat")
	, @NamedQuery(name = "VwMatriculados.findByDireccion", query = "SELECT v FROM VwMatriculados v WHERE v.direccion = :direccion")
	, @NamedQuery(name = "VwMatriculados.findByTelefono", query = "SELECT v FROM VwMatriculados v WHERE v.telefono = :telefono")})
public class VwMatriculados implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
    @NotNull
    @Column(name = "idPaciente")
	private int idPaciente;
	@Size(max = 10)
    @Column(name = "cedula")
	private String cedula;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Apellido")
	private String apellido;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Nombre")
	private String nombre;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
	private String sexo;
	@Size(max = 350)
    @Column(name = "Area")
	private String area;
	@Column(name = "edad")
	private Integer edad;
	@Basic(optional = false)
    @NotNull
    @Column(name = "anio")
	private int anio;
	@Id
	@Size(max = 20)
    @Column(name = "numMat")
	private String numMat;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Direccion")
	private String direccion;
	@Size(max = 50)
    @Column(name = "Telefono")
	private String telefono;

	public VwMatriculados() {
	}

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

	public String getNumMat() {
		return numMat;
	}

	public void setNumMat(String numMat) {
		this.numMat = numMat;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
}
