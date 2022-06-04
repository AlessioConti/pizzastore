package it.prova.pizzastore.service;

import java.util.List;

import javax.persistence.EntityManager;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.web.listener.LocalEntityManagerFactoryListener;

public class ClienteServiceImpl implements ClienteService {
	private ClienteDAO clienteDAO;

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}

	public List<Cliente> listAll() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);

			return clienteDAO.list();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public List<Cliente> cercaClientiAttivi() throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);

			return clienteDAO.findAttivi();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public Cliente caricaSingoloElemento(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);
			return clienteDAO.findOne(id).orElse(null);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public void aggiorna(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			clienteDAO.update(clienteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public void inserisci(Cliente clienteInstance) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			clienteDAO.insert(clienteInstance);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public void rimuovi(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			Cliente clienteDelete = clienteDAO.findOne(id).orElse(null);
			if (clienteDelete == null)
				throw new ElementNotFoundException("Cliente con id " + id + " non trovato");

			clienteDAO.delete(clienteDelete);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public void disattivaCliente(Long id) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			entityManager.getTransaction().begin();

			clienteDAO.setEntityManager(entityManager);

			Cliente clienteDaDisattivare = clienteDAO.findOne(id).orElse(null);
			if (clienteDaDisattivare == null)
				throw new ElementNotFoundException("Cliente con id " + id + " non trovato");
			clienteDAO.disattiva(clienteDaDisattivare);

			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

	public List<Cliente> findByExample(Cliente input) throws Exception {
		EntityManager entityManager = LocalEntityManagerFactoryListener.getEntityManager();

		try {
			clienteDAO.setEntityManager(entityManager);

			return clienteDAO.findByExample(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			LocalEntityManagerFactoryListener.closeEntityManager(entityManager);
		}
	}

}
