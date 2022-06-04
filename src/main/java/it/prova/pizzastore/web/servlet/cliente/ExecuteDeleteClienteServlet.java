package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.exception.ElementNotFoundException;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/ExecuteDeleteClienteServlet")
public class ExecuteDeleteClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idClienteParam = request.getParameter("idCliente");
		
		if (!NumberUtils.isCreatable(idClienteParam)) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		try {
			MyServiceFactory.getClienteServiceInstance().disattivaCliente(Long.parseLong(idClienteParam));
		}catch (ElementNotFoundException e) {
			request.getRequestDispatcher("ExecuteListClientiServlet?operationResult=NOT_FOUND").forward(request, response);
			return;
		} catch (Exception e) {
			// qui ci andrebbe un messaggio nei file di log costruito ad hoc se fosse attivo
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore. Si prega di riprovare");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListClientiServlet?operationResult=SUCCESS");
	}

}
