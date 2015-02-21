package cs460.webdnd.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cs460.webdnd.shared.Response;

/**
 * Refer to projectServiceAsync.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
@RemoteServiceRelativePath("ProjectService")
public interface ProjectService extends RemoteService {
	public Response<String> getCurrentProjectName();

	public Response<LinkedList<String>> getProjects();

	public Response<Boolean> newProject();

	public Response<Boolean> setProjectName(String name);

	public Response<Boolean> setProject(String project);

	public Response<Boolean> deleteProject(String project);
}