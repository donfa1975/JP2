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
import modelo.TblAcciones;
import modelo.TblAcciones_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblCitas;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblAccionesFacade extends AbstractFacade<TblAcciones> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblAccionesFacade() {
		super(TblAcciones.class);
	}

	public boolean isTblCitasCollectionEmpty(TblAcciones entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblAcciones> tblAcciones = cq.from(TblAcciones.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblAcciones, entity), cb.isNotEmpty(tblAcciones.get(TblAcciones_.tblCitasCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblCitas> findTblCitasCollection(TblAcciones entity) {
		TblAcciones mergedEntity = this.getMergedEntity(entity);
		Collection<TblCitas> tblCitasCollection = mergedEntity.getTblCitasCollection();
		tblCitasCollection.size();
		return tblCitasCollection;
	}
	
}
