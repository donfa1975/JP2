/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.RollbackFailureException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.TblPacientes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import modelo.TblAreas;

/**
 *
 * @author fabricio.diaz
 */
public class TblAreasJpaController implements Serializable {

	public TblAreasJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(TblAreas tblAreas) throws RollbackFailureException, Exception {
		if (tblAreas.getTblPacientesCollection() == null) {
			tblAreas.setTblPacientesCollection(new ArrayList<TblPacientes>());
		}
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			Collection<TblPacientes> attachedTblPacientesCollection = new ArrayList<TblPacientes>();
			for (TblPacientes tblPacientesCollectionTblPacientesToAttach : tblAreas.getTblPacientesCollection()) {
				tblPacientesCollectionTblPacientesToAttach = em.getReference(tblPacientesCollectionTblPacientesToAttach.getClass(), tblPacientesCollectionTblPacientesToAttach.getIdPaciente());
				attachedTblPacientesCollection.add(tblPacientesCollectionTblPacientesToAttach);
			}
			tblAreas.setTblPacientesCollection(attachedTblPacientesCollection);
			em.persist(tblAreas);
			for (TblPacientes tblPacientesCollectionTblPacientes : tblAreas.getTblPacientesCollection()) {
				TblAreas oldIdAreaOfTblPacientesCollectionTblPacientes = tblPacientesCollectionTblPacientes.getIdArea();
				tblPacientesCollectionTblPacientes.setIdArea(tblAreas);
				tblPacientesCollectionTblPacientes = em.merge(tblPacientesCollectionTblPacientes);
				if (oldIdAreaOfTblPacientesCollectionTblPacientes != null) {
					oldIdAreaOfTblPacientesCollectionTblPacientes.getTblPacientesCollection().remove(tblPacientesCollectionTblPacientes);
					oldIdAreaOfTblPacientesCollectionTblPacientes = em.merge(oldIdAreaOfTblPacientesCollectionTblPacientes);
				}
			}
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(TblAreas tblAreas) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TblAreas persistentTblAreas = em.find(TblAreas.class, tblAreas.getIdArea());
			Collection<TblPacientes> tblPacientesCollectionOld = persistentTblAreas.getTblPacientesCollection();
			Collection<TblPacientes> tblPacientesCollectionNew = tblAreas.getTblPacientesCollection();
			Collection<TblPacientes> attachedTblPacientesCollectionNew = new ArrayList<TblPacientes>();
			for (TblPacientes tblPacientesCollectionNewTblPacientesToAttach : tblPacientesCollectionNew) {
				tblPacientesCollectionNewTblPacientesToAttach = em.getReference(tblPacientesCollectionNewTblPacientesToAttach.getClass(), tblPacientesCollectionNewTblPacientesToAttach.getIdPaciente());
				attachedTblPacientesCollectionNew.add(tblPacientesCollectionNewTblPacientesToAttach);
			}
			tblPacientesCollectionNew = attachedTblPacientesCollectionNew;
			tblAreas.setTblPacientesCollection(tblPacientesCollectionNew);
			tblAreas = em.merge(tblAreas);
			for (TblPacientes tblPacientesCollectionOldTblPacientes : tblPacientesCollectionOld) {
				if (!tblPacientesCollectionNew.contains(tblPacientesCollectionOldTblPacientes)) {
					tblPacientesCollectionOldTblPacientes.setIdArea(null);
					tblPacientesCollectionOldTblPacientes = em.merge(tblPacientesCollectionOldTblPacientes);
				}
			}
			for (TblPacientes tblPacientesCollectionNewTblPacientes : tblPacientesCollectionNew) {
				if (!tblPacientesCollectionOld.contains(tblPacientesCollectionNewTblPacientes)) {
					TblAreas oldIdAreaOfTblPacientesCollectionNewTblPacientes = tblPacientesCollectionNewTblPacientes.getIdArea();
					tblPacientesCollectionNewTblPacientes.setIdArea(tblAreas);
					tblPacientesCollectionNewTblPacientes = em.merge(tblPacientesCollectionNewTblPacientes);
					if (oldIdAreaOfTblPacientesCollectionNewTblPacientes != null && !oldIdAreaOfTblPacientesCollectionNewTblPacientes.equals(tblAreas)) {
						oldIdAreaOfTblPacientesCollectionNewTblPacientes.getTblPacientesCollection().remove(tblPacientesCollectionNewTblPacientes);
						oldIdAreaOfTblPacientesCollectionNewTblPacientes = em.merge(oldIdAreaOfTblPacientesCollectionNewTblPacientes);
					}
				}
			}
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				Integer id = tblAreas.getIdArea();
				if (findTblAreas(id) == null) {
					throw new NonexistentEntityException("The tblAreas with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TblAreas tblAreas;
			try {
				tblAreas = em.getReference(TblAreas.class, id);
				tblAreas.getIdArea();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tblAreas with id " + id + " no longer exists.", enfe);
			}
			Collection<TblPacientes> tblPacientesCollection = tblAreas.getTblPacientesCollection();
			for (TblPacientes tblPacientesCollectionTblPacientes : tblPacientesCollection) {
				tblPacientesCollectionTblPacientes.setIdArea(null);
				tblPacientesCollectionTblPacientes = em.merge(tblPacientesCollectionTblPacientes);
			}
			em.remove(tblAreas);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public List<TblAreas> findTblAreasEntities() {
		return findTblAreasEntities(true, -1, -1);
	}

	public List<TblAreas> findTblAreasEntities(int maxResults, int firstResult) {
		return findTblAreasEntities(false, maxResults, firstResult);
	}

	private List<TblAreas> findTblAreasEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(TblAreas.class));
			Query q = em.createQuery(cq);
			if (!all) {
				q.setMaxResults(maxResults);
				q.setFirstResult(firstResult);
			}
			return q.getResultList();
		} finally {
			em.close();
		}
	}

	public TblAreas findTblAreas(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(TblAreas.class, id);
		} finally {
			em.close();
		}
	}

	public int getTblAreasCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<TblAreas> rt = cq.from(TblAreas.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
