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
import modelo.TblCantones;
import modelo.TblCantones_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblCantonesFacade extends AbstractFacade<TblCantones> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblCantonesFacade() {
		super(TblCantones.class);
	}

	public boolean isTblPacientesCollectionEmpty(TblCantones entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblCantones> tblCantones = cq.from(TblCantones.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblCantones, entity), cb.isNotEmpty(tblCantones.get(TblCantones_.tblPacientesCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblPacientes> findTblPacientesCollection(TblCantones entity) {
		TblCantones mergedEntity = this.getMergedEntity(entity);
		Collection<TblPacientes> tblPacientesCollection = mergedEntity.getTblPacientesCollection();
		tblPacientesCollection.size();
		return tblPacientesCollection;
	}
	
}
