/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import control.exceptions.IllegalOrphanException;
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
import modelo.TblAsiste;

/**
 *
 * @author fabricio.diaz
 */
public class TblAsisteJpaController implements Serializable {

	public TblAsisteJpaController(UserTransaction utx, EntityManagerFactory emf) {
		this.utx = utx;
		this.emf = emf;
	}
	private UserTransaction utx = null;
	private EntityManagerFactory emf = null;

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void create(TblAsiste tblAsiste) throws RollbackFailureException, Exception {
		if (tblAsiste.getTblPacientesCollection() == null) {
			tblAsiste.setTblPacientesCollection(new ArrayList<TblPacientes>());
		}
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			Collection<TblPacientes> attachedTblPacientesCollection = new ArrayList<TblPacientes>();
			for (TblPacientes tblPacientesCollectionTblPacientesToAttach : tblAsiste.getTblPacientesCollection()) {
				tblPacientesCollectionTblPacientesToAttach = em.getReference(tblPacientesCollectionTblPacientesToAttach.getClass(), tblPacientesCollectionTblPacientesToAttach.getIdPaciente());
				attachedTblPacientesCollection.add(tblPacientesCollectionTblPacientesToAttach);
			}
			tblAsiste.setTblPacientesCollection(attachedTblPacientesCollection);
			em.persist(tblAsiste);
			for (TblPacientes tblPacientesCollectionTblPacientes : tblAsiste.getTblPacientesCollection()) {
				TblAsiste oldIdasisteOfTblPacientesCollectionTblPacientes = tblPacientesCollectionTblPacientes.getIdasiste();
				tblPacientesCollectionTblPacientes.setIdasiste(tblAsiste);
				tblPacientesCollectionTblPacientes = em.merge(tblPacientesCollectionTblPacientes);
				if (oldIdasisteOfTblPacientesCollectionTblPacientes != null) {
					oldIdasisteOfTblPacientesCollectionTblPacientes.getTblPacientesCollection().remove(tblPacientesCollectionTblPacientes);
					oldIdasisteOfTblPacientesCollectionTblPacientes = em.merge(oldIdasisteOfTblPacientesCollectionTblPacientes);
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

	public void edit(TblAsiste tblAsiste) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TblAsiste persistentTblAsiste = em.find(TblAsiste.class, tblAsiste.getIdasiste());
			Collection<TblPacientes> tblPacientesCollectionOld = persistentTblAsiste.getTblPacientesCollection();
			Collection<TblPacientes> tblPacientesCollectionNew = tblAsiste.getTblPacientesCollection();
			List<String> illegalOrphanMessages = null;
			for (TblPacientes tblPacientesCollectionOldTblPacientes : tblPacientesCollectionOld) {
				if (!tblPacientesCollectionNew.contains(tblPacientesCollectionOldTblPacientes)) {
					if (illegalOrphanMessages == null) {
						illegalOrphanMessages = new ArrayList<String>();
					}
					illegalOrphanMessages.add("You must retain TblPacientes " + tblPacientesCollectionOldTblPacientes + " since its idasiste field is not nullable.");
				}
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			Collection<TblPacientes> attachedTblPacientesCollectionNew = new ArrayList<TblPacientes>();
			for (TblPacientes tblPacientesCollectionNewTblPacientesToAttach : tblPacientesCollectionNew) {
				tblPacientesCollectionNewTblPacientesToAttach = em.getReference(tblPacientesCollectionNewTblPacientesToAttach.getClass(), tblPacientesCollectionNewTblPacientesToAttach.getIdPaciente());
				attachedTblPacientesCollectionNew.add(tblPacientesCollectionNewTblPacientesToAttach);
			}
			tblPacientesCollectionNew = attachedTblPacientesCollectionNew;
			tblAsiste.setTblPacientesCollection(tblPacientesCollectionNew);
			tblAsiste = em.merge(tblAsiste);
			for (TblPacientes tblPacientesCollectionNewTblPacientes : tblPacientesCollectionNew) {
				if (!tblPacientesCollectionOld.contains(tblPacientesCollectionNewTblPacientes)) {
					TblAsiste oldIdasisteOfTblPacientesCollectionNewTblPacientes = tblPacientesCollectionNewTblPacientes.getIdasiste();
					tblPacientesCollectionNewTblPacientes.setIdasiste(tblAsiste);
					tblPacientesCollectionNewTblPacientes = em.merge(tblPacientesCollectionNewTblPacientes);
					if (oldIdasisteOfTblPacientesCollectionNewTblPacientes != null && !oldIdasisteOfTblPacientesCollectionNewTblPacientes.equals(tblAsiste)) {
						oldIdasisteOfTblPacientesCollectionNewTblPacientes.getTblPacientesCollection().remove(tblPacientesCollectionNewTblPacientes);
						oldIdasisteOfTblPacientesCollectionNewTblPacientes = em.merge(oldIdasisteOfTblPacientesCollectionNewTblPacientes);
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
				Integer id = tblAsiste.getIdasiste();
				if (findTblAsiste(id) == null) {
					throw new NonexistentEntityException("The tblAsiste with id " + id + " no longer exists.");
				}
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
		EntityManager em = null;
		try {
			utx.begin();
			em = getEntityManager();
			TblAsiste tblAsiste;
			try {
				tblAsiste = em.getReference(TblAsiste.class, id);
				tblAsiste.getIdasiste();
			} catch (EntityNotFoundException enfe) {
				throw new NonexistentEntityException("The tblAsiste with id " + id + " no longer exists.", enfe);
			}
			List<String> illegalOrphanMessages = null;
			Collection<TblPacientes> tblPacientesCollectionOrphanCheck = tblAsiste.getTblPacientesCollection();
			for (TblPacientes tblPacientesCollectionOrphanCheckTblPacientes : tblPacientesCollectionOrphanCheck) {
				if (illegalOrphanMessages == null) {
					illegalOrphanMessages = new ArrayList<String>();
				}
				illegalOrphanMessages.add("This TblAsiste (" + tblAsiste + ") cannot be destroyed since the TblPacientes " + tblPacientesCollectionOrphanCheckTblPacientes + " in its tblPacientesCollection field has a non-nullable idasiste field.");
			}
			if (illegalOrphanMessages != null) {
				throw new IllegalOrphanException(illegalOrphanMessages);
			}
			em.remove(tblAsiste);
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

	public List<TblAsiste> findTblAsisteEntities() {
		return findTblAsisteEntities(true, -1, -1);
	}

	public List<TblAsiste> findTblAsisteEntities(int maxResults, int firstResult) {
		return findTblAsisteEntities(false, maxResults, firstResult);
	}

	private List<TblAsiste> findTblAsisteEntities(boolean all, int maxResults, int firstResult) {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(TblAsiste.class));
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

	public TblAsiste findTblAsiste(Integer id) {
		EntityManager em = getEntityManager();
		try {
			return em.find(TblAsiste.class, id);
		} finally {
			em.close();
		}
	}

	public int getTblAsisteCount() {
		EntityManager em = getEntityManager();
		try {
			CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
			Root<TblAsiste> rt = cq.from(TblAsiste.class);
			cq.select(em.getCriteriaBuilder().count(rt));
			Query q = em.createQuery(cq);
			return ((Long) q.getSingleResult()).intValue();
		} finally {
			em.close();
		}
	}
	
}
