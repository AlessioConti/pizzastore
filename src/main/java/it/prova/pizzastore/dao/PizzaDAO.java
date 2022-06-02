package it.prova.pizzastore.dao;

import java.util.List;

import it.prova.pizzastore.model.Pizza;

public interface PizzaDAO extends IBaseDAO<Pizza>{
	public List<Pizza> findByExample(Pizza example) throws Exception;
}
