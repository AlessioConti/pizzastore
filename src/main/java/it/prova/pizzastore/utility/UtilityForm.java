package it.prova.pizzastore.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

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
