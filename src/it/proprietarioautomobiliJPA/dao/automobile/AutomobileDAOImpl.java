package it.proprietarioautomobiliJPA.dao.automobile;

import java.util.List;

import javax.persistence.EntityManager;

import it.proprietarioautomobiliJPA.model.Automobile;

public class AutomobileDAOImpl implements AutomobileDAO {
	
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Automobile> list() throws Exception {
		return entityManager.createQuery("from Automobile", Automobile.class).getResultList();
	}

	@Override
	public Automobile get(Long id) throws Exception {
		return entityManager.find(Automobile.class, id);
	}

	@Override
	public void update(Automobile input) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.persist(automobileInstance);
	}

	@Override
	public void delete(Automobile automobileInstance) throws Exception {
		if (automobileInstance == null) {
			throw new Exception("Problema valore in input");
		}

		entityManager.remove(entityManager.merge(automobileInstance));
		
	}

	@Override
	public List<Automobile> findAllByCodFisProprietarioIniziaCon(String iniziale) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
