package cs460.webdnd.server;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cs460.webdnd.client.LoginService;
import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.LoginManager;
import cs460.webdnd.server.managers.SharedManager;
import cs460.webdnd.server.managers.UserManager;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;


/**
 * This processes logins.
 * Please refer to cs460.webdnd.client.loginServiceAsync for explanation of methods.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class LoginServiceImpl extends RemoteServiceServlet implements LoginService {
	private static final long serialVersionUID = -222535937469194228L;

	@Override
	public Response<Boolean> parseLogin(String email, String pwd) 
	{
		
		/* Bad Input */
		if (!Misc.verify(email, pwd))
			return new Response<Boolean>( Res.BAD_INPUT );
		
		/* Initialize Shared Data */
		if ( !LoginManager.verifyUser  (SharedManager.EMAIL, SharedManager.PASSW) )
		{
				String url = getUrl();
				SharedManager.init(url);
		}
		
		/* Login is incorrect */
		if (!LoginManager.verifyUser(email, pwd))
		{
			return new Response<Boolean>(Res.BAD_LOGIN_INF);
		}
		
		CookieManager.newSession(getThreadLocalRequest(), getThreadLocalResponse(), email);
		
		return new Response<Boolean>( Res.ALL_OK );
	}

	@Override
	public Response<Boolean> newUser(String email, String pwd, String fName, String lName, String phone) {
		/* Bad Input */
		if (!Misc.verify(email, pwd, fName, lName, phone))
			return new Response<Boolean>(Res.BAD_INPUT);
		
		/* Add New User */
		return LoginManager.addUser(email, pwd, fName, lName, phone, getUrl());
	}

	@Override
	public Response<Boolean> logout() 
	{
		CookieManager.logout(getThreadLocalRequest(), getThreadLocalResponse(), false);
		return new Response<Boolean>( Res.ALL_OK );
	}
	
	 @Override
	 public Response<Boolean> login()
	 {
		 String usr = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), false);
		 
		 /* No Login */
		 if (usr == null)
			 return new Response<Boolean>( Res.NO_LOGIN );

		 return new Response<Boolean>(Res.ALL_OK);
	 }

	@Override
	public Response<Boolean> deleteUserAccount() {
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No login */
		if (user == null)
			return new Response<Boolean>(Res.NO_LOGIN);
		
		CookieManager.logout(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		return LoginManager.removeUser(user);
	}

	@Override
	public Response<Boolean> changePhone(String phone) {
		
		/* Bad Input */
		if (!Misc.verify(phone))
			return new Response<Boolean>(Res.BAD_INPUT);

		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (user == null)
			return new Response<Boolean>(Res.NO_LOGIN);

		return UserManager.changePhone(user, phone);
	}

	@Override
	public Response<Boolean> changeFirstName(String fName) {
		
		/* Bad Input */
		if (!Misc.verify(fName))
			return new Response<Boolean>(Res.BAD_INPUT);

		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (user == null)
			return new Response<Boolean>(Res.NO_LOGIN);

		return UserManager.changeFirstName(user, fName);
	}

	@Override
	public Response<Boolean> changeLastName(String lName) {
		
		/* Bad Input */
		if (!Misc.verify(lName))
			return new Response<Boolean>(Res.BAD_INPUT);
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (user == null)
			return new Response<Boolean>(Res.NO_LOGIN);

		return UserManager.changeLastName(user, lName);
	}
	
	@Override
	public Response<Boolean> changePassword(String newPwd, String oldPwd) 
	{
		/* Bad Input */
		if (!Misc.verify(oldPwd, newPwd))
			return new Response<Boolean>(Res.BAD_INPUT);
		
		String usr = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (usr == null)
			return new Response<Boolean>(Res.NO_LOGIN);
		
		/* Bad old password */
		if (LoginManager.verifyUser(usr, oldPwd))
			return new Response<Boolean>(Res.BAD_LOGIN_INF);
		
		return LoginManager.changePassword(usr, newPwd);
	}
	
	/**
	 * Returns Server URL
	 * @return Server URL
	 */
	private final String getUrl()
	{
		HttpServletRequest req = this.getThreadLocalRequest();
		String scheme      = req.getScheme();
		String serverName  = req.getServerName();
		int serverPort     = req.getServerPort();
		String contextPath = req.getContextPath();
	
		return scheme.concat("://").concat(serverName).concat(":") + serverPort + contextPath;
	}

	@Override
	public Response<Boolean> recoverPassword(String fName, String lName, String phone, String email) {
		
		/* Bad Input */
		if (!Misc.verify(fName, lName, phone, email))
			return new Response<Boolean>( Res.BAD_INPUT );
		
		return LoginManager.recoverPassword(fName, lName, phone, email);
	}

	@Override
	public String getFirstName() {
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		return UserManager.getFirstName(user);
	}

	@Override
	public String getLastName() {
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		return UserManager.getLastName(user);
	}

	@Override
	public String getPhoneNumber() {
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		return UserManager.getPhoneNumber(user);
	}
}