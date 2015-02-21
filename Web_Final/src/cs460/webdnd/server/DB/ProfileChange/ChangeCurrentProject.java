package cs460.webdnd.server.DB.ProfileChange;


import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;


/**
 * Changes the current project selected.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class ChangeCurrentProject implements ProfileChange {
	private final String projectName;

	/**
	 * 
	 * @param projectName project to change to
	 */
	public ChangeCurrentProject(String projectName)
	{
		this.projectName = projectName;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) {
		return userData.setCurrentProject(projectName);
	}
}