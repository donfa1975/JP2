/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.NonexistentEntityException;
import control.exceptions.PreexistingEntityException;
import control.exceptions.RollbackFailureException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.UserTransaction;
import modelo.VwDiagnosticos;

/**
 *
 * @author fabricio.diaz
 */
public class VwDiagnosticosJpaController implements Serializable {

	public VwDiagnosticosJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VwDiagnosticos vwDiagnosticos) throws PreexistingEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			em.persist(vwDiagnosticos);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			if (findVwDiagnosticos(vwDiagnosticos.getIdTipoDiagnostico()) != null) {
				throw new PreexistingEntityException("VwDiagnosticos " + vwDiagnosticos + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VwDiagnosticos vwDiagnosticos) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			vwDiagnosticos = em.merge(vwDiagnosticos);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = vwDiagnosticos.getIdTipoDiagnostico();
				if (findVwDiagnosticos(id) == null) {
					throw new NonexistentEntityException("The vwDiagnosticos with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(int id) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			VwDiagnosticos vwDiagnosticos;
			try {
				vwDiagnosticos = em.getReference(VwDiagnosticos.class, id);
				vwDiagnosticos.getIdTipoDiagnostico();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vwDiagnosticos with id " + id + " no longer exists.", enfe);
			}
			em.remove(vwDiagnosticos);
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

	public List<VwDiagnosticos> findVwDiagnosticosEntities() {
		return findVwDiagnosticosEntities(true, -1, -1);
	}

	public List<VwDiagnosticos> findVwDiagnosticosEntities(int maxResults, int firstResult) {
		return findVwDiagnosticosEntities(false, maxResults, firstResult);
	}

	private List<VwDiagnosticos> findVwDiagnosticosEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VwDiagnosticos.class));
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

	public VwDiagnosticos findVwDiagnosticos(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VwDiagnosticos.class, id);
		} finally {
			em.close();
		}
	}

	public int getVwDiagnosticosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VwDiagnosticos> rt = cq.from(VwDiagnosticos.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
