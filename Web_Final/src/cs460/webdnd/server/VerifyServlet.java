package cs460.webdnd.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.LoginManager;

/**
 * Used to verify user accounts.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class VerifyServlet  extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		parse(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		parse(req, resp);
	}

	protected final static void parse(HttpServletRequest req, HttpServletResponse resp)
	{
		/* Verification Parameter */
		String verPar = req.getParameter("ver");

		if (verPar != null)
		{
			try 
			{
				/* Try to decode verification parameter */
				String decodedPar = URLDecoder.decode(verPar,"UTF-8");
				/* Try to validate that account */
				LoginManager.validateAccount(decodedPar);

				/* Log that user in */
				// CookieManager.newSession(req, resp, email);
				// TODO - Refactor code to get e-mail
			} 
			catch (UnsupportedEncodingException e) 
			{
				Misc.warning("VerifyServlet.parse { " + e.toString() + " }");
			}
		}

		/* Get URL of Main Page */
		String scheme      = req.getScheme();
		String serverName  = req.getServerName();
		int serverPort     = req.getServerPort();
		String contextPath = req.getContextPath();

		String url = scheme.concat("://").concat(serverName).concat(":") + serverPort + contextPath;;
		String baseUrl = url.concat("/Web_Final.html");

		/* Return to main page */
		resp.setStatus(301);
		resp.setHeader( "Location", baseUrl );
		resp.setHeader( "Connection", "close" );
	}

	private static final long serialVersionUID = -5903908567924407991L;
}