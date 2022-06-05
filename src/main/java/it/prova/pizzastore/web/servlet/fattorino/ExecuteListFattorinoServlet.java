package it.prova.pizzastore.web.servlet.fattorino;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.service.MyServiceFactory;

/**
 * Servlet implementation class ExecuteListFattorinoServlet
 */
@WebServlet("/ExecuteListFattorinoServlet")
public class ExecuteListFattorinoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idUserParam = request.getParameter("idUser");
		if (!NumberUtils.isCreatable(idUserParam)) {
			request.setAttribute("errorMessage", "Attenzione, ID non valido/non presente!");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		try {

			String operationResult = request.getParameter("operationResult");
			if (StringUtils.isNotBlank(operationResult) && operationResult.equalsIgnoreCase("SUCCESS"))
				request.setAttribute("successMessage", "Operazione effettuata con successo");

			request.setAttribute("ordini_list_attribute",
					MyServiceFactory.getOrdineServiceInstance().trovaOrdiniAperti(Long.parseLong(idUserParam)));
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione: errore nel caricamento della lista.");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/fattorino/list.jsp").forward(request, response);
	}

}
