package cs460.webdnd.server.DB.ProfileChange;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.shared.Response;

/**
 * Deletes a certain file from a project.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class DeleteFile implements ProfileChange
{
	private final String fileName;
	
	/**
	 * 
	 * @param fileName File to be deleted
	 */
	public DeleteFile(String fileName)
	{
		this.fileName = fileName;
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) 
	{
		return userData.getCurrentProject().deleteFile(fileName);
	}
}