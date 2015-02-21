package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;

/**
 * Creates a new project.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class NewProject implements ProfileChange {
	@Override
	public Response<Boolean> makeChange(FullUserData userData) 
	{
		return userData.newProject();
	}
}