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
import modelo.VwListaPacientes;

/**
 *
 * @author fabricio.diaz
 */
public class VwListaPacientesJpaController implements Serializable {

	public VwListaPacientesJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(VwListaPacientes vwListaPacientes) throws PreexistingEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			em.persist(vwListaPacientes);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			if (findVwListaPacientes(vwListaPacientes.getIdPaciente()) != null) {
				throw new PreexistingEntityException("VwListaPacientes " + vwListaPacientes + " already exists.", ex);
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void edit(VwListaPacientes vwListaPacientes) throws NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			vwListaPacientes = em.merge(vwListaPacientes);
			utx.commit();
		} catch (Exception ex) {
			try {
				utx.rollback();
			} catch (Exception re) {
				throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
			}
			String msg = ex.getLocalizedMessage();
			if (msg == null || msg.length() == 0) {
				int id = vwListaPacientes.getIdPaciente();
				if (findVwListaPacientes(id) == null) {
					throw new NonexistentEntityException("The vwListaPacientes with id " + id + " no longer exists.");
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
			VwListaPacientes vwListaPacientes;
			try {
				vwListaPacientes = em.getReference(VwListaPacientes.class, id);
				vwListaPacientes.getIdPaciente();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The vwListaPacientes with id " + id + " no longer exists.", enfe);
			}
			em.remove(vwListaPacientes);
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

	public List<VwListaPacientes> findVwListaPacientesEntities() {
		return findVwListaPacientesEntities(true, -1, -1);
	}

	public List<VwListaPacientes> findVwListaPacientesEntities(int maxResults, int firstResult) {
		return findVwListaPacientesEntities(false, maxResults, firstResult);
	}

	private List<VwListaPacientes> findVwListaPacientesEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(VwListaPacientes.class));
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

	public VwListaPacientes findVwListaPacientes(int id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(VwListaPacientes.class, id);
		} finally {
			em.close();
		}
	}

	public int getVwListaPacientesCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<VwListaPacientes> rt = cq.from(VwListaPacientes.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
