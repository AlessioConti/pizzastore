package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdatePizzaServlet")
public class ExecuteUpdatePizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idPizza = request.getParameter("idPizza");

		if (!NumberUtils.isCreatable(idPizza)) {
			request.setAttribute("errorMessage", "Attenzione, ID non valido/non presente!");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoParam = request.getParameter("prezzoBase");

		Pizza pizzaInstance = UtilityForm.createPizzaFromParas(descrizioneParam, ingredientiParam, prezzoParam);
		pizzaInstance.setId(Long.parseLong(idPizza));
		pizzaInstance.setAttivo(true);

		if (!UtilityForm.validatePizzaBean(pizzaInstance)) {
			request.setAttribute("update_pizza_attr", pizzaInstance);
			request.setAttribute("errorMessage", "Attenzione, errore nella creazione della pizza. Riprovare.");
			request.getRequestDispatcher("/pizza/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getPizzaServiceInstance().aggiorna(pizzaInstance);
			request.setAttribute("pizze_list_attribute", MyServiceFactory.getPizzaServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage",
					"Attenzione: errore nell'aggiornamento della pizza. Si prega di riprovare");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/pizza/list.jsp").forward(request, response);
	}

}
