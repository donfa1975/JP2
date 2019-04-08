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
import modelo.TblPacientes;
import modelo.TblPacientes_;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblDiagnotiscos;
import modelo.TblAreas;
import modelo.TblCantones;
import modelo.TblOrigenes;
import modelo.TblZonas;
import modelo.TblMatriculas;
import modelo.TblCitas;

/**
 *
 * @author fabricio.diaz
 */
@Stateless
public class TblPacientesFacade extends AbstractFacade<TblPacientes> {

	@PersistenceContext(unitName = "JP2PU")
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public TblPacientesFacade() {
		super(TblPacientes.class);
	}

	public boolean isTblDiagnotiscosCollectionEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotEmpty(tblPacientes.get(TblPacientes_.tblDiagnotiscosCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblDiagnotiscos> findTblDiagnotiscosCollection(TblPacientes entity) {
		TblPacientes mergedEntity = this.getMergedEntity(entity);
		Collection<TblDiagnotiscos> tblDiagnotiscosCollection = mergedEntity.getTblDiagnotiscosCollection();
		tblDiagnotiscosCollection.size();
		return tblDiagnotiscosCollection;
	}

	public boolean isIdAreaEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotNull(tblPacientes.get(TblPacientes_.idArea)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblAreas findIdArea(TblPacientes entity) {
		return this.getMergedEntity(entity).getIdArea();
	}

	public boolean isIdCantonEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotNull(tblPacientes.get(TblPacientes_.idCanton)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblCantones findIdCanton(TblPacientes entity) {
		return this.getMergedEntity(entity).getIdCanton();
	}

	public boolean isIdOrigenEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotNull(tblPacientes.get(TblPacientes_.idOrigen)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblOrigenes findIdOrigen(TblPacientes entity) {
		return this.getMergedEntity(entity).getIdOrigen();
	}

	public boolean isIdzonaEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotNull(tblPacientes.get(TblPacientes_.idzona)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public TblZonas findIdzona(TblPacientes entity) {
		return this.getMergedEntity(entity).getIdzona();
	}

	public boolean isTblMatriculasCollectionEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotEmpty(tblPacientes.get(TblPacientes_.tblMatriculasCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblMatriculas> findTblMatriculasCollection(TblPacientes entity) {
		TblPacientes mergedEntity = this.getMergedEntity(entity);
		Collection<TblMatriculas> tblMatriculasCollection = mergedEntity.getTblMatriculasCollection();
		tblMatriculasCollection.size();
		return tblMatriculasCollection;
	}

	public boolean isTblCitasCollectionEmpty(TblPacientes entity) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> cq = cb.createQuery(Long.class);
		Root<TblPacientes> tblPacientes = cq.from(TblPacientes.class);
		cq.select(cb.literal(1L)).distinct(true).where(cb.equal(tblPacientes, entity), cb.isNotEmpty(tblPacientes.get(TblPacientes_.tblCitasCollection)));
		return em.createQuery(cq).getResultList().isEmpty();
	}

	public Collection<TblCitas> findTblCitasCollection(TblPacientes entity) {
		TblPacientes mergedEntity = this.getMergedEntity(entity);
		Collection<TblCitas> tblCitasCollection = mergedEntity.getTblCitasCollection();
		tblCitasCollection.size();
		return tblCitasCollection;
	}
	
}
