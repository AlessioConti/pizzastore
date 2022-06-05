package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertPizzaServlet")
public class ExecuteInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String descrizioneParam = request.getParameter("descrizione");
		String ingredientiParam = request.getParameter("ingredienti");
		String prezzoParam = request.getParameter("prezzoBase");

		Pizza pizzaInstance = UtilityForm.createPizzaFromParas(descrizioneParam, ingredientiParam, prezzoParam);

		pizzaInstance.setAttivo(true);
		try {
			if (!UtilityForm.validatePizzaBean(pizzaInstance)) {
				request.setAttribute("clienti_list_attribute", MyServiceFactory.getPizzaServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/pizza/insert.jsp").forward(request, response);
				return;
			}
			MyServiceFactory.getPizzaServiceInstance().inserisci(pizzaInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione: errore anomalo nell'inserimento della pizza");
			request.getRequestDispatcher("/pizza/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListPizzeServlet?operationResult=SUCCESS");
	}

}
