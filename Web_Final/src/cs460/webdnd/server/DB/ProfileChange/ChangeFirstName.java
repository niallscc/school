package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Changes the first name of a user.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangeFirstName implements ProfileChange {
	private final String firstName;
	
	/**
	 * 
	 * @param firstName New first name.
	 */
	public ChangeFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	@Override
	public Response<Boolean>  makeChange(FullUserData userData) {
		userData.setFirstName(firstName);
		return new Response<Boolean> (Res.ALL_OK);
	}
}