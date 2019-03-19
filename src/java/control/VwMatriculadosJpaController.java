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
import modelo.VwMatriculados;

/**
 *
 * @author fabricio.diaz
 */
public class VwMatriculadosJpaController implements Serializable {

	public VwMatriculadosJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VwMatriculados vwMatriculados) throws PreexistingEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			em.persist(vwMatriculados);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			if (findVwMatriculados(vwMatriculados.getIdPaciente()) != null) {
				throw new PreexistingEntityException("VwMatriculados " + vwMatriculados + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VwMatriculados vwMatriculados) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			vwMatriculados = em.merge(vwMatriculados);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = vwMatriculados.getIdPaciente();
				if (findVwMatriculados(id) == null) {
					throw new NonexistentEntityException("The vwMatriculados with id " + id + " no longer exists.");
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
			VwMatriculados vwMatriculados;
			try {
				vwMatriculados = em.getReference(VwMatriculados.class, id);
				vwMatriculados.getIdPaciente();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vwMatriculados with id " + id + " no longer exists.", enfe);
			}
			em.remove(vwMatriculados);
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

	public List<VwMatriculados> findVwMatriculadosEntities() {
		return findVwMatriculadosEntities(true, -1, -1);
	}

	public List<VwMatriculados> findVwMatriculadosEntities(int maxResults, int firstResult) {
		return findVwMatriculadosEntities(false, maxResults, firstResult);
	}

	private List<VwMatriculados> findVwMatriculadosEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VwMatriculados.class));
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

	public VwMatriculados findVwMatriculados(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VwMatriculados.class, id);
		} finally {
			em.close();
		}
	}

	public int getVwMatriculadosCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VwMatriculados> rt = cq.from(VwMatriculados.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
