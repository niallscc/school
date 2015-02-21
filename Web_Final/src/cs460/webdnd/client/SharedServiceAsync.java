package cs460.webdnd.client;

import java.util.LinkedList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;

/**
 * Meant for querying existing shared files.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public interface SharedServiceAsync {
	/**
	 * What files exist?
	 * 
	 * @param theme Theme or something
	 * @param files Files in that Theme
	 */
	void getFileInfo(String theme, AsyncCallback<Response<LinkedList<FileInformation>>> files);
	
	/**
	 * Get a file
	 * 
	 * @param theme Project Name
	 * @param fileName Name of the file (with extension)
	 * @param file Response with a File if one exists
	 */
	void getFile(String theme, String fileName, AsyncCallback<Response<String>> file);
	
	/**
	 * Which projects / themes are there?
	 * 
	 * @param projects
	 */
	void getThemes(AsyncCallback<Response<LinkedList<String>>> themes);
}