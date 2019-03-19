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
import modelo.VwPaciente;

/**
 *
 * @author fabricio.diaz
 */
public class VwPacienteJpaController implements Serializable {

	public VwPacienteJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VwPaciente vwPaciente) throws PreexistingEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			em.persist(vwPaciente);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			if (findVwPaciente(vwPaciente.getCodigo()) != null) {
				throw new PreexistingEntityException("VwPaciente " + vwPaciente + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VwPaciente vwPaciente) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			vwPaciente = em.merge(vwPaciente);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				String id = vwPaciente.getCodigo();
				if (findVwPaciente(id) == null) {
					throw new NonexistentEntityException("The vwPaciente with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(String id) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			VwPaciente vwPaciente;
			try {
				vwPaciente = em.getReference(VwPaciente.class, id);
				vwPaciente.getCodigo();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vwPaciente with id " + id + " no longer exists.", enfe);
			}
			em.remove(vwPaciente);
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

	public List<VwPaciente> findVwPacienteEntities() {
		return findVwPacienteEntities(true, -1, -1);
	}

	public List<VwPaciente> findVwPacienteEntities(int maxResults, int firstResult) {
		return findVwPacienteEntities(false, maxResults, firstResult);
	}

	private List<VwPaciente> findVwPacienteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VwPaciente.class));
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

	public VwPaciente findVwPaciente(String id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VwPaciente.class, id);
		} finally {
			em.close();
		}
	}

	public int getVwPacienteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VwPaciente> rt = cq.from(VwPaciente.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
