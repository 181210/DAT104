package no.hvl.dat104.controller;

import static no.hvl.dat104.controller.UrlMappings.LOGIN_URL;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.hvl.dat104.util.InnloggingUtil;

/**
 * Created by Peder on 12.09.2017.
 */
@WebServlet
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (InnloggingUtil.isInnlogget(request)) {
			InnloggingUtil.loggUt(request);
		}
		response.sendRedirect(LOGIN_URL);
	}
}
