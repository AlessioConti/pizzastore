package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class PizzaServiceImpl implements PizzaService {

	private PizzaDAO pizzaDAO;
	
	@Override
	public void setPizzaDAO(PizzaDAO pizzaDAO) {
		this.pizzaDAO = pizzaDAO;
	}

	@Override
	public List<Pizza> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			pizzaDAO.setEntityManager(entityManager);
			
			return pizzaDAO.list();
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public Pizza caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			pizzaDAO.setEntityManager(entityManager);
			
			return pizzaDAO.findOne(id).orElse(null);
		}catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void aggiorna(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			pizzaDAO.setEntityManager(entityManager);
			
			pizzaDAO.update(pizzaInstance);
			
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void inserisci(Pizza pizzaInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			pizzaDAO.setEntityManager(entityManager);
			
			pizzaDAO.insert(pizzaInstance);
			
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	@Override
	public void rimuovi(Long idPizza) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();
		
		try {
			entityManager.getTransaction().begin();
			
			pizzaDAO.setEntityManager(entityManager);
			
			Pizza pizzaDelete = pizzaDAO.findOne(idPizza).orElse(null);
			if(pizzaDelete == null)
				throw new ElementNotFoundException("Pizza con Id "+idPizza+" non trovata");
			
			pizzaDAO.delete(pizzaDelete);
			
			entityManager.getTransaction().commit();
		}catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
