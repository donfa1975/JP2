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
@Table(name = "vwDiagnosticos")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "VwDiagnosticos.findAll", query = "SELECT v FROM VwDiagnosticos v")
	, @NamedQuery(name = "VwDiagnosticos.findByCodigo", query = "SELECT v FROM VwDiagnosticos v WHERE v.codigo = :codigo")
	, @NamedQuery(name = "VwDiagnosticos.findByDiagnostico", query = "SELECT v FROM VwDiagnosticos v WHERE v.diagnostico = :diagnostico")
	, @NamedQuery(name = "VwDiagnosticos.findByIdTipoDiagnostico", query = "SELECT v FROM VwDiagnosticos v WHERE v.idTipoDiagnostico = :idTipoDiagnostico")
	, @NamedQuery(name = "VwDiagnosticos.findByIdPaciente", query = "SELECT v FROM VwDiagnosticos v WHERE v.idPaciente = :idPaciente")
	, @NamedQuery(name = "VwDiagnosticos.findByCedula", query = "SELECT v FROM VwDiagnosticos v WHERE v.cedula = :cedula")
	, @NamedQuery(name = "VwDiagnosticos.findByApellido", query = "SELECT v FROM VwDiagnosticos v WHERE v.apellido = :apellido")
	, @NamedQuery(name = "VwDiagnosticos.findByNombre", query = "SELECT v FROM VwDiagnosticos v WHERE v.nombre = :nombre")
	, @NamedQuery(name = "VwDiagnosticos.findByFechaNace", query = "SELECT v FROM VwDiagnosticos v WHERE v.fechaNace = :fechaNace")
	, @NamedQuery(name = "VwDiagnosticos.findByNombreRepresentante", query = "SELECT v FROM VwDiagnosticos v WHERE v.nombreRepresentante = :nombreRepresentante")
	, @NamedQuery(name = "VwDiagnosticos.findByEdad", query = "SELECT v FROM VwDiagnosticos v WHERE v.edad = :edad")
	, @NamedQuery(name = "VwDiagnosticos.findByEdadDiagnostico", query = "SELECT v FROM VwDiagnosticos v WHERE v.edadDiagnostico = :edadDiagnostico")
	, @NamedQuery(name = "VwDiagnosticos.findByFechaDiagnostico", query = "SELECT v FROM VwDiagnosticos v WHERE v.fechaDiagnostico = :fechaDiagnostico")})
public class VwDiagnosticos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Size(max = 10)
    @Column(name = "Codigo")
	private String codigo;
	@Size(max = 400)
    @Column(name = "Diagnostico")
	private String diagnostico;
	@Id
	@Basic(optional = false)
    @NotNull
    @Column(name = "idTipoDiagnostico")
	private int idTipoDiagnostico;
	@Id
	@Column(name = "idPaciente")
	private Integer idPaciente;
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
	@Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NombreRepresentante")
	private String nombreRepresentante;
	@Column(name = "edad")
	private Integer edad;
	@Column(name = "edadDiagnostico")
	private Integer edadDiagnostico;
	@Column(name = "fechaDiagnostico")
    @Temporal(TemporalType.TIMESTAMP)
	private Date fechaDiagnostico;

	public VwDiagnosticos() {
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

	public int getIdTipoDiagnostico() {
		return idTipoDiagnostico;
	}

	public void setIdTipoDiagnostico(int idTipoDiagnostico) {
		this.idTipoDiagnostico = idTipoDiagnostico;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
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

	public String getNombreRepresentante() {
		return nombreRepresentante;
	}

	public void setNombreRepresentante(String nombreRepresentante) {
		this.nombreRepresentante = nombreRepresentante;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public Integer getEdadDiagnostico() {
		return edadDiagnostico;
	}

	public void setEdadDiagnostico(Integer edadDiagnostico) {
		this.edadDiagnostico = edadDiagnostico;
	}

	public Date getFechaDiagnostico() {
		return fechaDiagnostico;
	}

	public void setFechaDiagnostico(Date fechaDiagnostico) {
		this.fechaDiagnostico = fechaDiagnostico;
	}
	
}
