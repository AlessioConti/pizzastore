package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

public class UtilityForm {

	public static Pizza createPizzaFromParas(String descrizioneParam, String ingredientiParam, String prezzoBaseParam) {
		Pizza result = new Pizza(descrizioneParam, ingredientiParam);
		if (NumberUtils.isCreatable(prezzoBaseParam))
			result.setPrezzoBase(Integer.parseInt(prezzoBaseParam));

		return result;
	}

	public static boolean validatePizzaBean(Pizza pizzaToBeValidated) {
		if (StringUtils.isBlank(pizzaToBeValidated.getDescrizione())
				|| StringUtils.isBlank(pizzaToBeValidated.getIngredienti()) || pizzaToBeValidated.getPrezzoBase() == 0)
			return false;
		return true;
	}

	public static Cliente createClienteFromParas(String nomeParas, String cognomeParas, String indirizzoParas) {
		Cliente result = new Cliente(nomeParas, cognomeParas, indirizzoParas);
		return result;
	}

	public static boolean validateClienteBean(Cliente clienteToBeValidated) {
		if (StringUtils.isBlank(clienteToBeValidated.getNome())
				|| StringUtils.isBlank(clienteToBeValidated.getCognome())
				|| StringUtils.isAllBlank(clienteToBeValidated.getIndirizzo()))
			return false;
		return true;
	}

	public static Ordine createOrdineFromParams(String codiceParam, String dataParam, String clienteIdParam,
			String[] pizzeIdParam, String utenteIdParam) throws NumberFormatException, Exception {
		Ordine result = new Ordine(codiceParam);
		result.setData(parseDateFromString(dataParam));

		if (NumberUtils.isCreatable(clienteIdParam)) {
			Cliente cliente = new Cliente();
			cliente.setId(Long.parseLong(clienteIdParam));
			result.setCliente(cliente);
		}
		if (NumberUtils.isCreatable(utenteIdParam)) {
			Utente utente = new Utente();
			utente.setId(Long.parseLong(utenteIdParam));
			result.setUtente(utente);
		}

		Set<Pizza> listaPizze = new HashSet<Pizza>();

		if (pizzeIdParam == null || pizzeIdParam.length == 0) {
			result.setPizze(null);

		} else {
			for (String idPizza : pizzeIdParam) {
				if (NumberUtils.isCreatable(idPizza)) {
					listaPizze.add(
							MyServiceFactory.getPizzaServiceInstance().caricaSingoloElemento(Long.parseLong(idPizza)));

				}

			}
			result.setPizze(listaPizze);
		}

		return result;
	}

	public static boolean validateOrdineBean(Ordine ordineInstance) {
		if (ordineInstance == null || StringUtils.isBlank(ordineInstance.getCodice())
				|| ordineInstance.getData() == null || ordineInstance.getCliente() == null
				|| ordineInstance.getCliente().getId() == null || ordineInstance.getCliente().getId() < 1
				|| ordineInstance.getUtente() == null || ordineInstance.getUtente().getId() < 1
				|| controllaPizze(ordineInstance.getPizze())) {

			return false;
		}
		return true;
	}

	private static boolean controllaPizze(Set<Pizza> listaPizze) {
		if (listaPizze == null || listaPizze.isEmpty()) {
			return true;
		}

		for (Pizza pizza : listaPizze) {
			if (pizza.getId() < 1) {
				return true;
			}
		}
		return false;
	}

	public static Date parseDateFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
