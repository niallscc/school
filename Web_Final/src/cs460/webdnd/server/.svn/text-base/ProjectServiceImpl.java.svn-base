package cs460.webdnd.server;

import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cs460.webdnd.client.ProjectService;
import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.ProjectManager;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Refer to projectServiceAsync.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */

public class ProjectServiceImpl  extends RemoteServiceServlet implements ProjectService {
	
	@Override
	public Response<String> getCurrentProjectName() {
		String usr = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad Login  */
		if (usr == null)
			 return new Response<String>( Res.NO_LOGIN) ;
		
		return ProjectManager.getCurrentProjectName(usr);
	}

	@Override
	public Response<LinkedList<String>> getProjects() {
		String usr = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad Login */
		if (usr == null)
			 return new Response<LinkedList<String>>( Res.NO_LOGIN );
		
		return ProjectManager.getProjectList(usr);
	}

	@Override
	public Response<Boolean> setProjectName(String name) {
		/* Bad Input */
		if (!Misc.verify(name))
			return new Response<Boolean> ( Res.BAD_INPUT );
		
		String usr = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad Login */
		if (usr == null)
			 return new Response<Boolean> ( Res.NO_LOGIN );
		
		return ProjectManager.changeProjectName(usr, name);
	}	
	
	@Override
	public Response<Boolean> newProject() {
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad login */
		if (user == null)
			 return new Response<Boolean> (Res.NO_LOGIN);
		
		return ProjectManager.newProject(user);
	}
	
	@Override
	public Response<Boolean> setProject(String project) {
		/* Bad Input */
		if (!Misc.verify(project))
			return new Response<Boolean> ( Res.BAD_INPUT );
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad Login */
		if (user == null)
			 return new Response<Boolean> ( Res.NO_LOGIN );
		
		return ProjectManager.changeCurrentProject(user, project);
	}

	@Override
	public Response<Boolean> deleteProject(String project) {
		/* Bad Input */
		if (!Misc.verify(project))
			return new Response<Boolean> ( Res.BAD_INPUT );
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* Bad Login */
		if (user == null)
			 return new Response<Boolean> ( Res.NO_LOGIN );
		
		return ProjectManager.deleteProject(user, project) ;
	}	
	
	private static final long serialVersionUID = 190649226617497268L;
}