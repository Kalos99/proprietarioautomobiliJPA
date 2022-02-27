package it.proprietarioautomobiliJPA.dao.proprietario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import it.proprietarioautomobiliJPA.model.Automobile;
import it.proprietarioautomobiliJPA.model.Proprietario;

public class ProprietarioDAOImpl implements ProprietarioDAO{
	
	private EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Proprietario> list() throws Exception {
		// dopo la from bisogna specificare il nome dell'oggetto (lettera maiuscola) e
		// non la tabella
		return entityManager.createQuery("from Proprietario", Proprietario.class).getResultList();
	}

	@Override
	public Proprietario get(Long id) throws Exception {
		return entityManager.find(Proprietario.class, id);
	}
	
	@Override
	public Proprietario getEagerAutomobili(Long id) throws Exception {
		//join restituirebbe solo i municipi con abitanti (LAZY)
		//join fetch come sopra ma valorizza anche la lista di abitanti (EAGER)
		//left join fetch come sopra ma cerca anche tra i municipi privi di abitanti (EAGER)
		TypedQuery<Proprietario> query = entityManager
				.createQuery("from Proprietario p left join fetch p.automobili where p.id = ?1", Proprietario.class);
		query.setParameter(1, id);
		
		//return query.getSingleResult() ha il problema che se non trova elementi lancia NoResultException
		return query.getResultStream().findFirst().orElse(null);
	}

	@Override
	public void update(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		proprietarioInstance = entityManager.merge(proprietarioInstance);
		
	}

	@Override
	public void insert(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(proprietarioInstance);
		
	}

	@Override
	public void delete(Proprietario proprietarioInstance) throws Exception {
		if (proprietarioInstance == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.remove(entityManager.merge(proprietarioInstance));
		
	}

	@Override
	public Long countAllWithAutomobileImmatricolataAPartireDa(int annoConfronto) throws Exception {
		TypedQuery<Long> query = entityManager.createQuery("select count(p.id) from Proprietario p join p.automobili a where a.annoImmatricolazione >= ?1", Long.class);
		return query.setParameter(1, annoConfronto).getSingleResult();
	}

}