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
import modelo.TblTipoDiagnostico;
import modelo.TblTipoDiagnostico_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblDiagnotiscos;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblTipoDiagnosticoFacade extends AbstractFacade<TblTipoDiagnostico> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblTipoDiagnosticoFacade() {
		super(TblTipoDiagnostico.class);
	}

	public boolean isTblDiagnotiscosCollectionEmpty(TblTipoDiagnostico entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblTipoDiagnostico> tblTipoDiagnostico = cq.from(TblTipoDiagnostico.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblTipoDiagnostico, entity), cb.isNotEmpty(tblTipoDiagnostico.get(TblTipoDiagnostico_.tblDiagnotiscosCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblDiagnotiscos> findTblDiagnotiscosCollection(TblTipoDiagnostico entity) {
		TblTipoDiagnostico mergedEntity = this.getMergedEntity(entity);
		Collection<TblDiagnotiscos> tblDiagnotiscosCollection = mergedEntity.getTblDiagnotiscosCollection();
		tblDiagnotiscosCollection.size();
		return tblDiagnotiscosCollection;
	}
	
}
