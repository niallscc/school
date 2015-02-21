package cs460.webdnd.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.UserData;

/**
 * Please refer to dataServiceAsync.java
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
@RemoteServiceRelativePath("DataService")
public interface DataService extends RemoteService {
	public Response<String> getText(String fileName);

	public Response<Boolean> sendHtml(String html, String pageName);

	public Response<LinkedList<FileInformation>> getFiles();

	public Response<UserData> getUserData();

	public Response<Boolean> deleteFile(String fileName);

	public Response<Boolean> getFileServletResponse();
}