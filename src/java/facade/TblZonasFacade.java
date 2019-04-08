/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.Collection;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TblZonas;
import modelo.TblZonas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblZonasFacade extends AbstractFacade<TblZonas> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblZonasFacade() {
		super(TblZonas.class);
	}

	public boolean isTblPacientesCollectionEmpty(TblZonas entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblZonas> tblZonas = cq.from(TblZonas.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblZonas, entity), cb.isNotEmpty(tblZonas.get(TblZonas_.tblPacientesCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblPacientes> findTblPacientesCollection(TblZonas entity) {
		TblZonas mergedEntity = this.getMergedEntity(entity);
		Collection<TblPacientes> tblPacientesCollection = mergedEntity.getTblPacientesCollection();
		tblPacientesCollection.size();
		return tblPacientesCollection;
	}
	
}
