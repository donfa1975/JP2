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
@Table(name = "vwPaciente")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VwPaciente.findAll", query = "SELECT v FROM VwPaciente v")
	, @NamedQuery(name = "VwPaciente.findByCedula", query = "SELECT v FROM VwPaciente v WHERE v.cedula = :cedula")
	, @NamedQuery(name = "VwPaciente.findByFechaNace", query = "SELECT v FROM VwPaciente v WHERE v.fechaNace = :fechaNace")
	, @NamedQuery(name = "VwPaciente.findByNombreRepresentante", query = "SELECT v FROM VwPaciente v WHERE v.nombreRepresentante = :nombreRepresentante")
	, @NamedQuery(name = "VwPaciente.findBySexo", query = "SELECT v FROM VwPaciente v WHERE v.sexo = :sexo")
	, @NamedQuery(name = "VwPaciente.findByEdad", query = "SELECT v FROM VwPaciente v WHERE v.edad = :edad")
	, @NamedQuery(name = "VwPaciente.findByPaciente", query = "SELECT v FROM VwPaciente v WHERE v.paciente = :paciente")
	, @NamedQuery(name = "VwPaciente.findByCodigo", query = "SELECT v FROM VwPaciente v WHERE v.codigo = :codigo")
	, @NamedQuery(name = "VwPaciente.findByDiagnostico", query = "SELECT v FROM VwPaciente v WHERE v.diagnostico = :diagnostico")
	, @NamedQuery(name = "VwPaciente.findByFecha", query = "SELECT v FROM VwPaciente v WHERE v.fecha = :fecha")
	, @NamedQuery(name = "VwPaciente.findByAccion", query = "SELECT v FROM VwPaciente v WHERE v.accion = :accion")})
public class VwPaciente implements Serializable {

	@Id
	@Basic(optional = false)
    @NotNull
    @Column(name = "idPaciente")
	private int idPaciente;

	private static final long serialVersionUID = 1L;
	@Size(max = 10)
    @Column(name = "cedula")
	private String cedula;
	@Basic(optional = false)
    @NotNull
    @Column(name = "FechaNace")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaNace;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombreRepresentante")
	private String nombreRepresentante;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "sexo")
	private String sexo;
	@Column(name = "edad")
	private Integer edad;
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 201)
    @Column(name = "Paciente")
	private String paciente;
	@Id
	@Size(max = 10)
    @Column(name = "Codigo")
	private String codigo;
	@Size(max = 400)
    @Column(name = "Diagnostico")
	private String diagnostico;
	@Column(name = "fecha")
    @Temporal(TemporalType.DATE)
	private Date fecha;
	@Size(max = 450)
    @Column(name = "Accion")
	private String accion;

	public VwPaciente() {
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getFechaNace() {
		return fechaNace;
	}

	public void setFechaNace(Date fechaNace) {
		this.fechaNace = fechaNace;
	}

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
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

	public int getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(int idPaciente) {
		this.idPaciente = idPaciente;
	}
	
}
