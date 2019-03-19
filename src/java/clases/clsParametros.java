/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.*;

/**
 *
 * @author fabricio.diaz
 */
public class clsParametros {
	
	private List<TblAreas> _areas;
	private List<TblCantones> _cantones;
	private List<TblOrigenes> _origenes;
	private List<TblZonas> _zonas;
	private List<TblTipoDiagnostico> _tipoDiag;

	public clsParametros() {
		
	}
	
	public void loadData()
	{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JP2PU");
        EntityManager em = emf.createEntityManager();
		
		Query q1=em.createQuery("select c from TblAreas c order by c.area");
		_areas=q1.getResultList();
		
		Query q2=em.createQuery("select c from TblCantones c order by c.canton");
		_cantones=q2.getResultList();
		
		Query q3=em.createQuery("select c from TblOrigenes c order by c.origen");
		_origenes=q3.getResultList();
		
		Query q4=em.createQuery("select c from TblZonas c order by c.zona");
		_zonas=q4.getResultList();
		
		Query q5=em.createQuery("select c from TblTipoDiagnostico c order by c.codigo");
		_tipoDiag=q5.getResultList();
		
		
	}
	
//<editor-fold desc="propiedades">
	public List<TblAreas> getAreas() {
		return _areas;
	}

	public void setAreas(List<TblAreas> _areas) {
		this._areas = _areas;
	}

	public List<TblCantones> getCantones() {
		return _cantones;
	}

	public void setCantones(List<TblCantones> _cantones) {
		this._cantones = _cantones;
	}

	public List<TblOrigenes> getOrigenes() {
		return _origenes;
	}

	public void setOrigenes(List<TblOrigenes> _origenes) {
		this._origenes = _origenes;
	}

	public List<TblZonas> getZonas() {
		return _zonas;
	}

	public void setZonas(List<TblZonas> _zonas) {
		this._zonas = _zonas;
	}

	public List<TblTipoDiagnostico> getTipoDiag() {
		return _tipoDiag;
	}

	public void setTipoDiag(List<TblTipoDiagnostico> _tipoDiag) {
		this._tipoDiag = _tipoDiag;
	}
//</editor-fold>
	
	
	
}
