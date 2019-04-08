/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.TblDiagnotiscos;
import modelo.TblDiagnotiscos_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;
import modelo.TblTipoDiagnostico;
import modelo.TblUsuarios;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblDiagnotiscosFacade extends AbstractFacade<TblDiagnotiscos> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblDiagnotiscosFacade() {
		super(TblDiagnotiscos.class);
	}

	public boolean isIdPacienteEmpty(TblDiagnotiscos entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblDiagnotiscos> tblDiagnotiscos = cq.from(TblDiagnotiscos.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblDiagnotiscos, entity), cb.isNotNull(tblDiagnotiscos.get(TblDiagnotiscos_.idPaciente)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblPacientes findIdPaciente(TblDiagnotiscos entity) {
		return this.getMergedEntity(entity).getIdPaciente();
	}

	public boolean isIdTipoDiagnosticoEmpty(TblDiagnotiscos entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblDiagnotiscos> tblDiagnotiscos = cq.from(TblDiagnotiscos.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblDiagnotiscos, entity), cb.isNotNull(tblDiagnotiscos.get(TblDiagnotiscos_.idTipoDiagnostico)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblTipoDiagnostico findIdTipoDiagnostico(TblDiagnotiscos entity) {
		return this.getMergedEntity(entity).getIdTipoDiagnostico();
	}

	public boolean isIdusuarioEmpty(TblDiagnotiscos entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblDiagnotiscos> tblDiagnotiscos = cq.from(TblDiagnotiscos.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblDiagnotiscos, entity), cb.isNotNull(tblDiagnotiscos.get(TblDiagnotiscos_.idusuario)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblUsuarios findIdusuario(TblDiagnotiscos entity) {
		return this.getMergedEntity(entity).getIdusuario();
	}
	
}
