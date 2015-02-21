package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Changes the last name of a user.
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangeLastName implements ProfileChange {
	private final String lastName;
	
	/**
	 * 
	 * @param lastName New Last Name
	 */
	public ChangeLastName(String lastName)
	{
		this.lastName = lastName;
	}

	@Override
	public Response<Boolean>  makeChange(FullUserData userData) {
		userData.setLastName(lastName);
		return new Response<Boolean> (Res.ALL_OK);
	}
}