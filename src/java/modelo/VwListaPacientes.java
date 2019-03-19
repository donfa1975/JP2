/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fabricio.diaz
 */
@Entity
@Table(name = "vwListaPacientes")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VwListaPacientes.findAll", query = "SELECT v FROM VwListaPacientes v")
	, @NamedQuery(name = "VwListaPacientes.findByIdPaciente", query = "SELECT v FROM VwListaPacientes v WHERE v.idPaciente = :idPaciente")
	, @NamedQuery(name = "VwListaPacientes.findByCedula", query = "SELECT v FROM VwListaPacientes v WHERE v.cedula = :cedula")
	, @NamedQuery(name = "VwListaPacientes.findByApellido", query = "SELECT v FROM VwListaPacientes v WHERE v.apellido = :apellido")
	, @NamedQuery(name = "VwListaPacientes.findByNombre", query = "SELECT v FROM VwListaPacientes v WHERE v.nombre = :nombre")
	, @NamedQuery(name = "VwListaPacientes.findByFechaNace", query = "SELECT v FROM VwListaPacientes v WHERE v.fechaNace = :fechaNace")
	, @NamedQuery(name = "VwListaPacientes.findByCedRepresentante", query = "SELECT v FROM VwListaPacientes v WHERE v.cedRepresentante = :cedRepresentante")
	, @NamedQuery(name = "VwListaPacientes.findByNombreRepresentante", query = "SELECT v FROM VwListaPacientes v WHERE v.nombreRepresentante = :nombreRepresentante")
	, @NamedQuery(name = "VwListaPacientes.findByNombrePadre", query = "SELECT v FROM VwListaPacientes v WHERE v.nombrePadre = :nombrePadre")
	, @NamedQuery(name = "VwListaPacientes.findByNombreMadre", query = "SELECT v FROM VwListaPacientes v WHERE v.nombreMadre = :nombreMadre")
	, @NamedQuery(name = "VwListaPacientes.findBySexo", query = "SELECT v FROM VwListaPacientes v WHERE v.sexo = :sexo")
	, @NamedQuery(name = "VwListaPacientes.findByDireccion", query = "SELECT v FROM VwListaPacientes v WHERE v.direccion = :direccion")
	, @NamedQuery(name = "VwListaPacientes.findByTelefono", query = "SELECT v FROM VwListaPacientes v WHERE v.telefono = :telefono")
	, @NamedQuery(name = "VwListaPacientes.findByOrigen", query = "SELECT v FROM VwListaPacientes v WHERE v.origen = :origen")
	, @NamedQuery(name = "VwListaPacientes.findByCanton", query = "SELECT v FROM VwListaPacientes v WHERE v.canton = :canton")
	, @NamedQuery(name = "VwListaPacientes.findByArea", query = "SELECT v FROM VwListaPacientes v WHERE v.area = :area")
	, @NamedQuery(name = "VwListaPacientes.findByZona", query = "SELECT v FROM VwListaPacientes v WHERE v.zona = :zona")
	, @NamedQuery(name = "VwListaPacientes.findByEdad", query = "SELECT v FROM VwListaPacientes v WHERE v.edad = :edad")
	, @NamedQuery(name = "VwListaPacientes.findByPaciente", query = "SELECT v FROM VwListaPacientes v WHERE v.paciente = :paciente")})
public class VwListaPacientes implements Serializable {

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
    @Column(name = "FechaNace")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaNace;
	@Size(max = 10)
    @Column(name = "cedRepresentante")
	private String cedRepresentante;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombreRepresentante")
	private String nombreRepresentante;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombrePadre")
	private String nombrePadre;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombreMadre")
	private String nombreMadre;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
	private String sexo;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "Direccion")
	private String direccion;
	@Size(max = 50)
    @Column(name = "Telefono")
	private String telefono;
	@Size(max = 150)
    @Column(name = "Origen")
	private String origen;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "canton")
	private String canton;
	@Size(max = 350)
    @Column(name = "Area")
	private String area;
	@Size(max = 150)
    @Column(name = "Zona")
	private String zona;
	@Column(name = "edad")
	private Integer edad;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 201)
    @Column(name = "Paciente")
	private String paciente;

	public VwListaPacientes() {
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

	public Date getFechaNace() {
		return fechaNace;
	}

	public void setFechaNace(Date fechaNace) {
		this.fechaNace = fechaNace;
	}

	public String getCedRepresentante() {
		return cedRepresentante;
	}

	public void setCedRepresentante(String cedRepresentante) {
		this.cedRepresentante = cedRepresentante;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getNombrePadre() {
		return nombrePadre;
	}

	public void setNombrePadre(String nombrePadre) {
		this.nombrePadre = nombrePadre;
	}

	public String getNombreMadre() {
		return nombreMadre;
	}

	public void setNombreMadre(String nombreMadre) {
		this.nombreMadre = nombreMadre;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getCanton() {
		return canton;
	}

	public void setCanton(String canton) {
		this.canton = canton;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}
	
}
