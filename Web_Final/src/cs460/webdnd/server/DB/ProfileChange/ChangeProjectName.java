package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;


/**
 * Changes project name.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangeProjectName implements ProfileChange
{
	private final String projectName;
	
	/**
	 * @param projectName New Project Name.
	 */
	public ChangeProjectName(String projectName)
	{
		this.projectName = projectName;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) 
	{
		return userData.setCurrentProjectName(projectName);
	}
}