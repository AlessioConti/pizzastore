package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareUpdateOrdineServlet")
public class PrepareUpdateOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idOrdineParam = request.getParameter("idOrdine");
		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {
			request.setAttribute("update_ordine_attr",
					MyServiceFactory.getOrdineServiceInstance().caricaSingoloElemento(Long.parseLong(idOrdineParam)));
			request.setAttribute("utenti_list_attribute", MyServiceFactory.getUtenteServiceInstance().listAll());
			request.setAttribute("pizze_list_attribute", MyServiceFactory.getPizzaServiceInstance().listAll());
			request.setAttribute("clienti_list_attribute", MyServiceFactory.getClienteServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/ordine/edit.jsp").forward(request, response);

	}

}
