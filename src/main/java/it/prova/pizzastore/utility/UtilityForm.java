package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.model.Pizza;

public class UtilityForm {

	public static Pizza createPizzaFromParas(String descrizioneParam, String ingredientiParam,
			Integer prezzoBaseParam) {
		Pizza result = new Pizza(descrizioneParam, ingredientiParam, prezzoBaseParam);
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
	
	public static Ordine createOrdineFromParas(String codiceParas, Date dataParas) {
		Ordine result = new Ordine(codiceParas);
		result.setData(parseDateArrivoFromString(codiceParas));
		return result;
	}
	
	public static boolean validateOrdineBean(Ordine ordineToBeValidated) {
		if(StringUtils.isBlank(ordineToBeValidated.getCodice()) || ordineToBeValidated.getData() == null)
			return false;
		return false;
	}

	public static Date parseDateArrivoFromString(String dataStringParam) {
		if (StringUtils.isBlank(dataStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataStringParam);
		} catch (ParseException e) {
			return null;
		}
	}
}
