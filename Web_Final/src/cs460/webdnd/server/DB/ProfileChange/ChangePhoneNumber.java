package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;


/**
 * Changes the profile Phone Number.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangePhoneNumber implements ProfileChange {
	private final String phoneNumber;
	
	/**
	 * 
	 * @param phoneNumber New Phone Number
	 */
	public ChangePhoneNumber (String phoneNumber) 
	{
		this.phoneNumber = phoneNumber;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) 
	{
		userData.setPhone(phoneNumber);
		return new Response<Boolean> (Res.ALL_OK);
	}
}