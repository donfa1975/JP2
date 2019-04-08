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
import modelo.TblOrigenes;
import modelo.TblOrigenes_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblOrigenesFacade extends AbstractFacade<TblOrigenes> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblOrigenesFacade() {
		super(TblOrigenes.class);
	}

	public boolean isTblPacientesCollectionEmpty(TblOrigenes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblOrigenes> tblOrigenes = cq.from(TblOrigenes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblOrigenes, entity), cb.isNotEmpty(tblOrigenes.get(TblOrigenes_.tblPacientesCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblPacientes> findTblPacientesCollection(TblOrigenes entity) {
		TblOrigenes mergedEntity = this.getMergedEntity(entity);
		Collection<TblPacientes> tblPacientesCollection = mergedEntity.getTblPacientesCollection();
		tblPacientesCollection.size();
		return tblPacientesCollection;
	}
	
}
