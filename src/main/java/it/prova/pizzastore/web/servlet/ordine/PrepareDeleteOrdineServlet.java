package it.prova.pizzastore.web.servlet.ordine;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Ordine;
import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class PrepareDeleteOrdineServlet
 */
@WebServlet("/PrepareDeleteOrdineServlet")
public class PrepareDeleteOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idOrdineParam = request.getParameter("idOrdine");

		if (!NumberUtils.isCreatable(idOrdineParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		try {
			Ordine ordineInstance = MyServiceFactory.getOrdineServiceInstance()
					.caricaElementoEager(Long.parseLong(idOrdineParam));

			if (ordineInstance == null) {
				request.setAttribute("errorMessage", "Elemento non trovato.");
				request.getRequestDispatcher("ExecuteListOrdineServlet?operationResult=NOT_FOUND").forward(request,
						response);
				return;
			}

			request.setAttribute("delete_ordine_attr", ordineInstance);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/ordine/delete.jsp").forward(request, response);
	}

}
