package it.prova.pizzastore.web.servlet.pizza;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Pizza;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareInsertPizzaServlet
 */
@WebServlet("/PrepareInsertPizzaServlet")
public class PrepareInsertPizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//metto un bean 'vuoto' in request perché per la pagina risulta necessario
			request.setAttribute("insert_pizza_attr", new Pizza());
			// questo mi serve per la select di registi in pagina
			request.setAttribute("pizze_list_attribute",
					MyServiceFactory.getPizzaServiceInstance().listAll());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/pizza/insert.jsp").forward(request, response);
	}



}
