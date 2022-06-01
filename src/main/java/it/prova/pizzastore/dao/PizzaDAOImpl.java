package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Pizza;

public class PizzaDAOImpl implements PizzaDAO {


	private EntityManager entityManager;
	
	public List<Pizza> list() throws Exception {
		return entityManager.createQuery("from Pizza", Pizza.class).getResultList();
	}

	@Override
	public Optional<Pizza> findOne(Long id) throws Exception {
		Pizza result = entityManager.find(Pizza.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Pizza input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Pizza input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Pizza input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
