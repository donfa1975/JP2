/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TblCitas;
import modelo.TblCitas_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblAcciones;
import modelo.TblPacientes;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblCitasFacade extends AbstractFacade<TblCitas> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblCitasFacade() {
		super(TblCitas.class);
	}

	public boolean isIdAccionEmpty(TblCitas entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblCitas> tblCitas = cq.from(TblCitas.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblCitas, entity), cb.isNotNull(tblCitas.get(TblCitas_.idAccion)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblAcciones findIdAccion(TblCitas entity) {
		return this.getMergedEntity(entity).getIdAccion();
	}

	public boolean isIdPacienteEmpty(TblCitas entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblCitas> tblCitas = cq.from(TblCitas.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblCitas, entity), cb.isNotNull(tblCitas.get(TblCitas_.idPaciente)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblPacientes findIdPaciente(TblCitas entity) {
		return this.getMergedEntity(entity).getIdPaciente();
	}
	
}
