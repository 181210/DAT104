package no.hvl.dat104.controller;

import static no.hvl.dat104.controller.UrlMappings.HANDLELISTE_URL;
import static no.hvl.dat104.controller.UrlMappings.LOGIN_URL;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.dataaccess.HandlelisteEAO;
import no.hvl.dat104.model.Bruker;
import no.hvl.dat104.model.Vare;
import no.hvl.dat104.util.FlashUtil;
import no.hvl.dat104.util.ValidatorUtil;

/**
 * Created by Peder on 12.09.2017.
 */
@WebServlet
public class HandlelisteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private HandlelisteEAO handlelisteEAO;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("loggedInUser") != null) {
			String navn = request.getParameter("vare"); // bruker c:out for escape i jsp
			String slett = request.getParameter("varenavn");
			if (ValidatorUtil.isValidVare(navn)) {
				handlelisteEAO.leggTilVare(opprettVaren(navn, request));
				FlashUtil.Flash(request, "Success", "Vare lagt til");
			} else if (ValidatorUtil.isNotNull0(slett)) {
				FlashUtil.Flash(request, "Success", "Vare slettet!");
				handlelisteEAO.slettVare(slett);
			} else {
				FlashUtil.Flash(request, "Error", "Du skrev inn ugyldig");
			}
		}
		response.sendRedirect(HANDLELISTE_URL);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("loggedInUser") != null) {
			Bruker b = (Bruker) request.getSession().getAttribute("currentUser");
			List<Vare> varer = handlelisteEAO.visAlleVarerTilBruker(b.getBruker_id());
			request.setAttribute("varer", varer);
			request.getRequestDispatcher("WEB-INF/handleliste.jsp").forward(request, response);
		} else {
			FlashUtil.Flash(request, "Error", "Du må være innlogget for å gjøre det!");
			response.sendRedirect(LOGIN_URL);
		}
	}

	private Vare opprettVaren(String navn, HttpServletRequest req) {
		Vare v;
		v = new Vare();
		v.setNavn(navn);
		Bruker b = (Bruker) req.getSession().getAttribute("currentUser");
		v.setKurv(b.getKurv());
		b.getKurv().leggTilVare(v);
		return v;
	}
}
