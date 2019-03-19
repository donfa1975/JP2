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
@Table(name = "vwCitas")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VwCitas.findAll", query = "SELECT v FROM VwCitas v")
	, @NamedQuery(name = "VwCitas.findByFecha", query = "SELECT v FROM VwCitas v WHERE v.fecha = :fecha")
	, @NamedQuery(name = "VwCitas.findByAccion", query = "SELECT v FROM VwCitas v WHERE v.accion = :accion")
	, @NamedQuery(name = "VwCitas.findByIdCita", query = "SELECT v FROM VwCitas v WHERE v.idCita = :idCita")
	, @NamedQuery(name = "VwCitas.findByIdPaciente", query = "SELECT v FROM VwCitas v WHERE v.idPaciente = :idPaciente")
	, @NamedQuery(name = "VwCitas.findByCedula", query = "SELECT v FROM VwCitas v WHERE v.cedula = :cedula")
	, @NamedQuery(name = "VwCitas.findByPaciente", query = "SELECT v FROM VwCitas v WHERE v.paciente = :paciente")
	, @NamedQuery(name = "VwCitas.findBySexo", query = "SELECT v FROM VwCitas v WHERE v.sexo = :sexo")
	, @NamedQuery(name = "VwCitas.findByFechaNace", query = "SELECT v FROM VwCitas v WHERE v.fechaNace = :fechaNace")
	, @NamedQuery(name = "VwCitas.findByEdad", query = "SELECT v FROM VwCitas v WHERE v.edad = :edad")})
public class VwCitas implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "fecha")
    @Temporal(TemporalType.DATE)
	private Date fecha;
	@Size(max = 450)
    @Column(name = "Accion")
	private String accion;
	@Id
	@Basic(optional = false)
    @NotNull
    @Column(name = "idCita")
	private int idCita;
	@Basic(optional = false)
    @NotNull
    @Column(name = "idPaciente")
	private int idPaciente;
	@Size(max = 10)
    @Column(name = "cedula")
	private String cedula;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 201)
    @Column(name = "Paciente")
	private String paciente;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
	private String sexo;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FechaNace")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaNace;
	@Column(name = "edad")
	private Integer edad;

	public VwCitas() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
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

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNace() {
		return fechaNace;
	}

	public void setFechaNace(Date fechaNace) {
		this.fechaNace = fechaNace;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
}
