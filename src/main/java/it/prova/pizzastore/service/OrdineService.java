package it.prova.pizzastore.service;

import java.util.List;

import it.prova.pizzastore.dao.OrdineDAO;
import it.prova.pizzastore.model.Ordine;

public interface OrdineService {
	public void setOrdineDAO(OrdineDAO ordineDAO);
	
	public List<Ordine> listAll() throws Exception;
	
	public Ordine caricaSingoloElemento(Long id) throws Exception;
	
	public void aggiorna(Ordine ordineInstance) throws Exception;
	
	public void inserisci(Ordine ordineInstance) throws Exception;
	
	public void rimuovi(Long idOrdine) throws Exception;
	
	public Ordine caricaElementoEager(Long id) throws Exception;
}
