package no.hvl.dat104.controller;

import static no.hvl.dat104.controller.UrlMappings.HANDLELISTE_URL;
import static no.hvl.dat104.controller.UrlMappings.REGISTER_URL;

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
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Created by Peder on 13.09.2017.
 */
@WebServlet
public class RegisterSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BrukerEAO brukerEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect(HANDLELISTE_URL);
		} else {
			String brukernavn = ValidatorUtil.escapeHtml(request.getParameter("username"));
			String passord = ValidatorUtil.escapeHtml(request.getParameter("password"));
			if (InnloggingUtil.isGyldigBrukernavn(brukernavn, passord)) {
				Boolean lagtTil = brukerEAO.leggTilBruker(brukernavn, passord);
				Bruker b = brukerEAO.finnBrukerPaaNavn(brukernavn);
				if (lagtTil) {
					FlashUtil.registrertBruker(request);
					String timeout = getServletContext().getInitParameter("timeout");
					InnloggingUtil.loggInnSom(request, b, timeout);
				} else {
					String melding = "Det eksisterer allerede en bruker med brukernavnet: " + brukernavn;
					FlashUtil.Flash(request, "Error", melding);
				}
			} else {
				FlashUtil.Flash(request, "Error", "Ugyldig input");
			}
		}
		response.sendRedirect(REGISTER_URL);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.isInnlogget(request)) {
			response.sendRedirect(HANDLELISTE_URL);
		} else {
			request.getRequestDispatcher("WEB-INF/register.jsp").forward(request, response);
		}
	}
}
