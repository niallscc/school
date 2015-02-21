package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;

/**
 * Deletes a project.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class DeleteProject implements ProfileChange
{
	private final String projectName;
	
	public DeleteProject(String projectName)
	{
		this.projectName = projectName;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) {
		return userData.removeProject(projectName);
	}	
}