package cs460.webdnd.server.helpers;

import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs460.webdnd.server.DB.MemCache;

/**
 * Code related to session / cookie management.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class CookieManager {
	
	private CookieManager () {}
	
	/**
	 * Gets user from UID Cookie, if not expired.
	 * If Session also has user information, makes sure those match.
	 * Then updates Session Cookie and returns user name.
	 * 
	 * @param req
	 * @param resp
	 * @param hasSession Should the user have a session?
	 * @return User Name, null if need to log in
	 */
	public static final String getUser (HttpServletRequest req, HttpServletResponse resp, boolean hasSession)
	{
		Cookie cookie = getUserCookie(req);
		
		/* No UID Cookie */
		if (cookie == null)
			return null;
		
		/* Get user associated with that cookie */
		String UID = cookie.getValue();
		String user = MemCache.getUser(UID);

		if (user != null)
		{

			String oldUID = MemCache.getUID(user);
			
			/* Logged in elsewhere */
			if (oldUID != null && !oldUID.equals(UID))
			{
				logout(req, resp, true);
				Misc.warning("CookieManager.warning { Logged in elswhere }");
				return null;
			}
			
			Object obj = req.getSession().getAttribute("user");
			if (obj != null && obj instanceof String)
			{
				String s_user = (String) obj;
				
				/* Wrong user is associated with that cookie  */
				if (!s_user.equalsIgnoreCase(user))
				{
					logout(req, resp, true);
					Misc.warning("CookieManager.warning { Wrong Session Info }");
					return null;
				}
			}
			
			/* You should have both session cookie and UID Cookie */
			if (hasSession && (obj == null))
			{
				logout(req, resp, true);
				Misc.warning("CookieManager.warning { You should have a session cookie }");
				return null;
			}

			req.getSession().setAttribute("user", user);
			addUserCookie(user, cookie.getValue(), resp);
		}
		
		return user;
	}
	
	/**
	 * Creates a new session.
	 * 
	 * @param req
	 * @param resp
	 * @param user User Name
	 */
	public static final void newSession (HttpServletRequest req, HttpServletResponse resp, String user)
	{
		// JSESSIONID
		req.getSession().setAttribute("user", user);
		
		// UID
		UUID idOne = UUID.randomUUID();
		addUserCookie(user, idOne.toString(), resp);
	}
	
	/**
	 * Logs the user out.
	 * 
	 * @param req
	 * @param resp
	 * @param invalidate Invalidate Session ?
	 */
	public static void logout (HttpServletRequest req, HttpServletResponse resp, boolean invalidate)
	{
		
		req.getSession().removeAttribute("user");
		
		Cookie cookie = getUserCookie(req);
		
		req.getSession().removeAttribute("user");
		
		if (invalidate)
			req.getSession().invalidate();
		
		if (cookie == null)
			return;
		
		MemCache.removeUID(cookie.getValue());
	}
	
	
	/**
	 * Sifts through cookies and returns UIDCOOKIE, if one exists.
	 * 
	 * @param req
	 * @return UIDCOOKIE
	 */
	private static final Cookie getUserCookie(HttpServletRequest req)
	{
		Cookie[] cookies = req.getCookies();
		
		if (cookies != null)
		{
			for (Cookie cookie : cookies)                           /* Iterate through Cookies  */
			{
				if (cookie.getName().equals(UIDCOOKIE))             /* Find UID Cookie          */  
					return cookie;
			}
		}
		
		return null;
		
	}
	
	/**
	 * Adds a User Cookie. So user could automatically log in for the next 30 min.
	 * 
	 * @param usr User Name
	 * @param UID User ID
	 * @param resp
	 */
	private static void addUserCookie(String usr, String UID, HttpServletResponse resp)
	{
		Cookie NewUIDCookie = new Cookie(UIDCOOKIE, UID);
		NewUIDCookie.setPath("/");
		NewUIDCookie.setMaxAge(DURATION);
		
		MemCache.putUID(UID, usr);
		
		resp.addCookie(NewUIDCookie);
	}
	
	/* UIDCOOKIE */
	private static final String UIDCOOKIE = "UIDCookie";
	/* 12 HR    */
	private static final int DURATION = 1000/*ms*/ * 60/*sec*/ * 60/*min*/ * 12/*hr*/ * 1/*day*/;
}