package it.prova.pizzastore.web.servlet.cliente;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.pizzastore.model.Cliente;
import it.prova.pizzastore.service.MyServiceFactory;
import it.prova.pizzastore.utility.UtilityForm;

@WebServlet("/ExecuteUpdateClienteServlet")
public class ExecuteUpdateClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idClienteParam = request.getParameter("idCliente");

		if (!NumberUtils.isCreatable(idClienteParam)) {
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}

		String nomeParam = request.getParameter("nome");
		String cognomeParam = request.getParameter("cognome");
		String indirizzoParam = request.getParameter("indirizzo");

		Cliente clienteAggiornato = UtilityForm.createClienteFromParas(nomeParam, cognomeParam, indirizzoParam);
		clienteAggiornato.setId(Long.parseLong(idClienteParam));
		clienteAggiornato.setAttivo(true);

		if (!UtilityForm.validateClienteBean(clienteAggiornato)) {
			request.setAttribute("update_cliente_attr", clienteAggiornato);
			request.setAttribute("errorMessage", "Attenzione, errore nella creazione del cliente. Riprovare.");
			request.getRequestDispatcher("/cliente/edit.jsp").forward(request, response);
			return;
		}

		try {
			MyServiceFactory.getClienteServiceInstance().aggiorna(clienteAggiornato);
			request.setAttribute("clienti_list_attribute",
					MyServiceFactory.getClienteServiceInstance().cercaClientiAttivi());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errorino.");
			request.getRequestDispatcher("home.jsp").forward(request, response);
			return;
		}

		request.getRequestDispatcher("/cliente/list.jsp").forward(request, response);
	}

}
