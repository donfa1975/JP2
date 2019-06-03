/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fabricio.diaz
 */
@Entity
@Table(name = "tblAsiste")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "TblAsiste.findAll", query = "SELECT t FROM TblAsiste t")
	, @NamedQuery(name = "TblAsiste.findByIdasiste", query = "SELECT t FROM TblAsiste t WHERE t.idasiste = :idasiste")
	, @NamedQuery(name = "TblAsiste.findByAsiste", query = "SELECT t FROM TblAsiste t WHERE t.asiste = :asiste")})
public class TblAsiste implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idasiste")
	private Integer idasiste;
	@Size(max = 500)
    @Column(name = "asiste")
	private String asiste;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idasiste")
	private Collection<TblPacientes> tblPacientesCollection;

	public TblAsiste() {
	}

	public TblAsiste(Integer idasiste) {
		this.idasiste = idasiste;
	}

	public Integer getIdasiste() {
		return idasiste;
	}

	public void setIdasiste(Integer idasiste) {
		this.idasiste = idasiste;
	}

	public String getAsiste() {
		return asiste;
	}

	public void setAsiste(String asiste) {
		this.asiste = asiste;
	}

	@XmlTransient
	public Collection<TblPacientes> getTblPacientesCollection() {
		return tblPacientesCollection;
	}

	public void setTblPacientesCollection(Collection<TblPacientes> tblPacientesCollection) {
		this.tblPacientesCollection = tblPacientesCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idasiste != null ? idasiste.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof TblAsiste)) {
			return false;
		}
		TblAsiste other = (TblAsiste) object;
		if ((this.idasiste == null && other.idasiste != null) || (this.idasiste != null && !this.idasiste.equals(other.idasiste))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "modelo.TblAsiste[ idasiste=" + idasiste + " ]";
	}
	
}
