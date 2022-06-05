package it.prova.pizzastore.web.servlet.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import it.prova.pizzastore.model.Utente;
import it.prova.pizzastore.service.MyServiceFactory;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		throw new UnsupportedOperationException("Invocation of doGet not allowed for this Servlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String loginInput = request.getParameter("inputUsername");
		String passwordInput = request.getParameter("inputPassword");

		if (StringUtils.isEmpty(loginInput) || StringUtils.isEmpty(passwordInput)) {
			request.setAttribute("errorMessage", "E' necessario riempire tutti i campi.");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		}

		String destinazione = null;

		try {
			Utente utenteInstance = MyServiceFactory.getUtenteServiceInstance().accedi(loginInput, passwordInput);
			if (utenteInstance == null) {

				request.setAttribute("errorMessage", "Utente non trovato.");
				destinazione = "login.jsp";

			} else if (utenteInstance.isAdmin()) {

				request.getSession().setAttribute("userInfo", utenteInstance);
				destinazione = "/admin/index.jsp";

			} else if (utenteInstance.isPizzaiolo()) {

				request.getSession().setAttribute("userInfo", utenteInstance);
				destinazione = "/pizzaiolo/index.jsp";

			} else if (utenteInstance.isFattorino()) {

				request.getSession().setAttribute("userInfo", utenteInstance);
				destinazione = "/fattorino/index.jsp";

			} else {

				destinazione = "login.jsp";
				request.setAttribute("errorMessage",
						"Attenzione! Il login effettuato non è inerente a nessun servizio disponibile!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			destinazione = "login.jsp";
			request.setAttribute("errorMessage",
					"Attenzione! Si è verificato un errore nel login. Si prega di ritentare.");
		}

		request.getRequestDispatcher(destinazione).forward(request, response);

	}

}
