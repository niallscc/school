package cs460.webdnd.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;

@RemoteServiceRelativePath("SharedServlet")
public interface SharedService extends RemoteService {
	Response<LinkedList<String>> getThemes();
	
	Response<LinkedList<FileInformation>> getFileInfo(String project);

	Response<String> getFile(String project, String file);
}