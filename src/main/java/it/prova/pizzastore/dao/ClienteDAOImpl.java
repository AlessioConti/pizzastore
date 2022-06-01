package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import it.prova.pizzastore.model.Cliente;

public class ClienteDAOImpl implements ClienteDAO {

	private EntityManager entityManager;
	
	@Override
	public List<Cliente> list() throws Exception {
		return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
	}

	@Override
	public Optional<Cliente> findOne(Long id) throws Exception {
		Cliente result = entityManager.find(Cliente.class, id);
		return result != null ? Optional.of(result) : Optional.empty();
	}

	@Override
	public void update(Cliente input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Cliente input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.persist(input);
	}

	@Override
	public void delete(Cliente input) throws Exception {
		if(input == null)
			throw new Exception("Problema valore in input");
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
