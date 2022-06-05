package it.prova.pizzastore.dao;

import java.util.List;
import java.util.Optional;

import it.prova.pizzastore.model.Ordine;

public interface OrdineDAO extends IBaseDAO<Ordine> {
	
	public List<Ordine> findByExample(Ordine example) throws Exception;
	
	public Optional<Ordine> findOneEager(Long id) throws Exception;
	
	public List<Ordine> listOrdiniAperti(Long id) throws Exception;
	
	public Ordine chiudi(Ordine input) throws Exception;
}
