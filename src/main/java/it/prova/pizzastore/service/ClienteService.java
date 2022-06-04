package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.ClienteDAO;
import it.prova.pizzastore.model.Cliente;

public interface ClienteService {
	public void setClienteDAO(ClienteDAO clienteDAO);
	
	public List<Cliente> listAll() throws Exception;
	
	public Cliente caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Cliente clienteInstance) throws Exception;
	
	public void inserisci(Cliente clienteInstance) throws Exception;
	
	public void rimuovi(Long idCliente) throws Exception;
	
	public List<Cliente> cercaClientiAttivi() throws Exception;
	
	public void disattivaCliente(Long id) throws Exception;
	
	public List<Cliente> findByExample(Cliente input) throws Exception;
}
