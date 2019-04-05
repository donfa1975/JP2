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
import modelo.TblUsuarios;
import modelo.TblUsuarios_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblDiagnotiscos;
import modelo.TblMatriculas;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblUsuariosFacade extends AbstractFacade<TblUsuarios> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblUsuariosFacade() {
		super(TblUsuarios.class);
	}

	public boolean isTblDiagnotiscosCollectionEmpty(TblUsuarios entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblUsuarios> tblUsuarios = cq.from(TblUsuarios.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblUsuarios, entity), cb.isNotEmpty(tblUsuarios.get(TblUsuarios_.tblDiagnotiscosCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblDiagnotiscos> findTblDiagnotiscosCollection(TblUsuarios entity) {
		TblUsuarios mergedEntity = this.getMergedEntity(entity);
		Collection<TblDiagnotiscos> tblDiagnotiscosCollection = mergedEntity.getTblDiagnotiscosCollection();
		tblDiagnotiscosCollection.size();
		return tblDiagnotiscosCollection;
	}

	public boolean isTblMatriculasCollectionEmpty(TblUsuarios entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblUsuarios> tblUsuarios = cq.from(TblUsuarios.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblUsuarios, entity), cb.isNotEmpty(tblUsuarios.get(TblUsuarios_.tblMatriculasCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblMatriculas> findTblMatriculasCollection(TblUsuarios entity) {
		TblUsuarios mergedEntity = this.getMergedEntity(entity);
		Collection<TblMatriculas> tblMatriculasCollection = mergedEntity.getTblMatriculasCollection();
		tblMatriculasCollection.size();
		return tblMatriculasCollection;
	}
	
}
