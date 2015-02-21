package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;


/**
 * Changes the password.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangePassword implements ProfileChange 
{
	private final String password;
	
	/**
	 * 
	 * @param password New Password.
	 */
	public ChangePassword ( String newPassword )
	{
		this.password = newPassword;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) 
	{
		userData.setPassword(password);
		return new Response<Boolean>( Res.ALL_OK );
	}
}