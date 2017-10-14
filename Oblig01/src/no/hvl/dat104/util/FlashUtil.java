package no.hvl.dat104.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Peder on 13.09.2017.
 */
public class FlashUtil {
	// beste måten å gjøre det på!
	public static void Flash(HttpServletRequest request, String flash, String melding) {
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	// alternativt kan gjøres...
	public static void loggetInn(HttpServletRequest request, String Melding, String brukernavn) {
		String flash = "Success";
		String melding = "Velkommen: " + brukernavn;
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	public static void UgyldigBruker(HttpServletRequest request) {
		String melding = "Ugyldig Brukernavn eller Passord";
		String flash = "Error";
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	public static void loggetUt(HttpServletRequest request) {
		String melding = "Logget Deg Ut!!";
		String flash = "Success";
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	public static void registrertBruker(HttpServletRequest request) {
		String melding = "Velkommen til din handleliste";
		String flash = "Success";
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	public static void UgylidRegistertBruker(HttpServletRequest request) {
		String melding = "UGYLDIG INPUT";
		String flash = "Error";
		request.getSession().setAttribute("melding", melding);
		request.getSession().setAttribute("flash", flash);
	}

	public static void duMaaVeareLoggetInn(HttpServletRequest req) {
		String melding = "Du må være innlogget for å gjøre det!";
		String flash = "Error";
		req.getSession().setAttribute("melding", melding);
		req.getSession().setAttribute("flash", flash);
	}
}
