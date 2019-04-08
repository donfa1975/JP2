/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TblMatriculas;
import modelo.TblMatriculas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;
import modelo.TblUsuarios;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblMatriculasFacade extends AbstractFacade<TblMatriculas> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblMatriculasFacade() {
		super(TblMatriculas.class);
	}

	public boolean isIdPacienteEmpty(TblMatriculas entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblMatriculas> tblMatriculas = cq.from(TblMatriculas.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblMatriculas, entity), cb.isNotNull(tblMatriculas.get(TblMatriculas_.idPaciente)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblPacientes findIdPaciente(TblMatriculas entity) {
		return this.getMergedEntity(entity).getIdPaciente();
	}

	public boolean isIdusuarioEmpty(TblMatriculas entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblMatriculas> tblMatriculas = cq.from(TblMatriculas.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblMatriculas, entity), cb.isNotNull(tblMatriculas.get(TblMatriculas_.idusuario)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblUsuarios findIdusuario(TblMatriculas entity) {
		return this.getMergedEntity(entity).getIdusuario();
	}
	
}
