package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareInsertClienteServlet
 */
@WebServlet("/PrepareInsertClienteServlet")
public class PrepareInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			// metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("insert_cliente_attr", new Cliente());
			// questo mi serve per la select di registi in pagina
			request.setAttribute("clienti_list_attribute", MyServiceFactory.getClienteServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione: errore nel caricamento della lista dei clienti");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/cliente/insert.jsp").forward(request, response);
	}

}
