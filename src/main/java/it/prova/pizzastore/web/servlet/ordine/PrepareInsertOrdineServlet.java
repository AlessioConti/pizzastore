package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/PrepareInsertOrdineServlet")
public class PrepareInsertOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("insert_ordine_attr", new Ordine());
			// questo mi serve per la select di registi in pagina
			request.setAttribute("ordini_list_attribute", MyServiceFactory.getOrdineServiceInstance().listAll());
			request.setAttribute("lista_pizze", MyServiceFactory.getPizzaServiceInstance().listAll());
			request.setAttribute("lista_fattorini", MyServiceFactory.getUtenteServiceInstance().listAll());
			request.setAttribute("lista_clienti", MyServiceFactory.getClienteServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione: errore anomalo nel passaggio di pagine web");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/insert.jsp").forward(request, response);
	}

}
