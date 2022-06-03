package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

/**
 * Servlet implementation class ExecuteInsertClienteServlet
 */
@WebServlet("/ExecuteInsertClienteServlet")
public class ExecuteInsertClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");
		
		Cliente clienteInstance = UtilityForm.createClienteFromParas(nomeParam, cognomeParam, indirizzoParam);
	
		clienteInstance.setAttivo(true);
		try {
			if(!UtilityForm.validateClienteBean(clienteInstance)) {
				request.setAttribute("insert_cliente_attr", indirizzoParam);
				
				request.setAttribute("clienti_list_attribute", MyServiceFactory.getClienteServiceInstance().listAll());
				request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
				request.getRequestDispatcher("/cliente/insert.jsp").forward(request, response);
				return;
			}
			MyServiceFactory.getClienteServiceInstance().inserisci(clienteInstance);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/cliente/insert.jsp").forward(request, response);
			return;
		}
		
		response.sendRedirect("ExecuteListClientiServlet?operationResult=SUCCESS");
	}

}
