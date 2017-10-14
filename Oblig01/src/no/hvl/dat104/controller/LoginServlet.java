package no.hvl.dat104.controller;

import static no.hvl.dat104.controller.UrlMappings.HANDLELISTE_URL;
import static no.hvl.dat104.controller.UrlMappings.LOGIN_URL;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.BrukerEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.InnloggingUtil;

/**
 * Created by Peder on 12.09.2017.
 */
@WebServlet
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BrukerEAO brukerEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String brukernavn = request.getParameter("username");
		String passord = request.getParameter("password");
		if (InnloggingUtil.isGyldigBrukernavn(brukernavn, passord)) {
			Bruker b = brukerEAO.finnBrukerPaaNavn(brukernavn);
			if (b != null && passord.equals(b.getPassord())) {
				String timeout = getServletContext().getInitParameter("timeout");
				InnloggingUtil.loggInnSom(request, b, timeout);
			} else {
				FlashUtil.Flash(request, "Error", "Ugyldig input");
			}
		} else {
			FlashUtil.Flash(request, "Error", "Ugyldig brukernavn eller passord");
		}
		response.sendRedirect(LOGIN_URL);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect(HANDLELISTE_URL);
		} else {
			request.getRequestDispatcher("WEB-INF/login.jsp").forward(request, response);
		}
	}
}
