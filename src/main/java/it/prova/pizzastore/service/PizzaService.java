package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.PizzaDAO;
import it.prova.pizzastore.model.Pizza;

public interface PizzaService {

	public void setPizzaDAO(PizzaDAO pizzaDAO);

	public List<Pizza> listAll() throws Exception;

	public Pizza caricaSingoloElemento(Long id) throws Exception;

	public void aggiorna(Pizza pizzaInstance) throws Exception;

	public void inserisci(Pizza pizzaInstance) throws Exception;

	public void rimuovi(Long idPizza) throws Exception;

	public List<Pizza> findByExample(Pizza input) throws Exception;
}
