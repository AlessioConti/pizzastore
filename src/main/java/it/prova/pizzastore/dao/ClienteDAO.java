package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Cliente;

public interface ClienteDAO extends IBaseDAO<Cliente>{
	public List<Cliente> findByExample(Cliente example) throws Exception;
	
	public List<Cliente> findAttivi() throws Exception;
	
	public Cliente disattiva(Cliente input) throws Exception;
}
