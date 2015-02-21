package cs460.webdnd.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.UserData;

/**
 * For working with data.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public interface DataServiceAsync {
	/**
	 * Save as getFile but instead of byte[] you get String.
	 * 
	 * Retrieves a file from server. For the current project and current user
	 * only.
	 * 
	 * @param fileName
	 *            The name of the file you want.
	 * @param text
	 *            Text representation of that file.
	 */
	public void getText(String fileName, AsyncCallback<Response<String>> text);

	/**
	 * Send a text file (usually HTML) to the server. For the current user and
	 * project only.
	 * 
	 * @param html
	 *            File you want to be saved.
	 * @param pageName
	 *            Name of that said file.
	 * @param confirmation
	 *            True if the file was uploaded.
	 */
	public void sendHtml(String html, String pageName, AsyncCallback<Response<Boolean>> confirmation);

	/**
	 * Get file Information. For the current user and project only.
	 * 
	 * @return List of Information Objects about each file.
	 */
	public void getFiles(AsyncCallback<Response<LinkedList<FileInformation>>> callback);

	/**
	 * Returns basic user information. For the current user and project only..
	 * 
	 * @param userInformation
	 *            User Information.
	 */
	public void getUserData(AsyncCallback<Response<UserData>> userInformation);

	/**
	 * Deletes a file from Server. For the current user and project only.
	 * 
	 * @param file
	 *            File to delete.
	 * @param confirmation
	 *            True if deleted.
	 */
	public void deleteFile(String file, AsyncCallback<Response<Boolean>> confirmation);

	/**
	 * This is for files uploaded using HttpSerlvet. This should return ALL_OK
	 * if all files were uploaded.
	 * 
	 * @param confirmation
	 */
	public void getFileServletResponse(AsyncCallback<Response<Boolean>> confirmation);
	
	
}