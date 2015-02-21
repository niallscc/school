package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Makes a user profile valid.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class MakeValid  implements ProfileChange
{
	@Override
	public Response<Boolean> makeChange(FullUserData userData) {
		userData.setValid();
		return new Response<Boolean>(Res.ALL_OK);
	}
}