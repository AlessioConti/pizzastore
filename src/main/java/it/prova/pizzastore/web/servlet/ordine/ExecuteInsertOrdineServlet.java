package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteInsertOrdineServlet")
public class ExecuteInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String codiceParam = request.getParameter("codice");
		String dataParam = request.getParameter("data");

		String utenteIdParam = request.getParameter("utente.id");
		String clienteIdParam = request.getParameter("cliente.id");
		String[] pizzeIdParam = request.getParameterValues("pizza.id");

		Ordine ordineInstance = new Ordine();

		try {
			ordineInstance = UtilityForm.createOrdineFromParams(codiceParam, dataParam, clienteIdParam, pizzeIdParam,
					utenteIdParam);
			if (!UtilityForm.validateOrdineBean(ordineInstance)) {

				request.setAttribute("insert_ordine_attr", ordineInstance);
				request.setAttribute("lista_fattorini", MyServiceFactory.getUtenteServiceInstance().listAll());

				request.setAttribute("lista_pizze", MyServiceFactory.getPizzaServiceInstance().listAll());
				request.setAttribute("lista_clienti", MyServiceFactory.getClienteServiceInstance().listAll());

				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
				return;
			}

			ordineInstance.sommaPrezziPizza();
			MyServiceFactory.getOrdineServiceInstance().inserisci(ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione: errore nell'inserimento dell'ordine");
			request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
			return;
		}

		response.sendRedirect("ExecuteListOrdiniServlet?operationResult=SUCCESS");
	}

}