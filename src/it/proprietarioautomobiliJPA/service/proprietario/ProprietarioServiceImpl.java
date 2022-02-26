package it.proprietarioautomobiliJPA.service.proprietario;

import java.util.List;

import javax.persistence.EntityManager;

import it.proprietarioautomobiliJPA.dao.EntityManagerUtil;
import it.proprietarioautomobiliJPA.dao.proprietario.ProprietarioDAO;
import it.proprietarioautomobiliJPA.model.Proprietario;

public class ProprietarioServiceImpl implements ProprietarioService {

	private ProprietarioDAO proprietarioDAO;

	public void setProprietarioDAO(ProprietarioDAO proprietarioDAO) {
		this.proprietarioDAO = proprietarioDAO;
	}

	@Override
	public List<Proprietario> listAllProprietari() throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDAO.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietario(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDAO.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public Proprietario caricaSingoloProprietarioConAutomobili(Long id) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDAO.getEagerAutomobili(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Proprietario proprietarioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.update(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void inserisciNuovo(Proprietario proprietarioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.insert(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public void rimuovi(Proprietario proprietarioInstance) throws Exception {
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// questo è come il MyConnection.getConnection()
			entityManager.getTransaction().begin();

			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			proprietarioDAO.delete(proprietarioInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}

	}

	@Override
	public int cercaTuttiIProprietariConAutomobiliImmatricolateAPartireDa(int annoConfronto) throws Exception{
		// questo è come una connection
		EntityManager entityManager = EntityManagerUtil.getEntityManager();

		try {
			// uso l'injection per il dao
			proprietarioDAO.setEntityManager(entityManager);

			// eseguo quello che realmente devo fare
			return proprietarioDAO.countAllWithAutomobileImmatricolataAPartireDa(annoConfronto);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			EntityManagerUtil.closeEntityManager(entityManager);
		}
	}

}
