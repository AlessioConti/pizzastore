package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Ordine;

public class OrdineDAOImpl implements OrdineDAO {

	private EntityManager entityManager;
	
	@Override
	public List<Ordine> list() throws Exception {
		return entityManager.createQuery("from Ordine", Ordine.class).getResultList();
	}

	@Override
	public Optional<Ordine> findOne(Long id) throws Exception {
		Ordine result = entityManager.find(Ordine.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Ordine input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
