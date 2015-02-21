package cs460.webdnd.server;

import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cs460.webdnd.client.SharedService;
import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.LoginManager;
import cs460.webdnd.server.managers.SharedManager;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * For accessing data about shared files.
 * Refer to SharedServiceAsync for documentation.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class SharedServiceImpl  extends RemoteServiceServlet implements SharedService {
	
	@Override
	public Response<LinkedList<FileInformation>> getFileInfo(String project)
	{
		/* No bad input please */
		if (!Misc.verify(project))
			return new Response<LinkedList<FileInformation>> ( Res.BAD_INPUT );
		
		/* You nonetheless need to be logged in to SOME account */
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		if (user == null)
			 return new Response<LinkedList<FileInformation>>( Res.NO_LOGIN );
		
		/* Verify that Shared User Acc Exists 
		 * If it does not, create it.
		 * */
		if (!LoginManager.verifyUser(SharedManager.EMAIL, SharedManager.PASSW))
			SharedManager.init(getUrl());
		
		return SharedManager.getFileInformation(project);
	}

	@Override
	public Response<String> getFile(String project, String file) 
	{
		
		/* No bad input please */
		if ( !Misc.verify(project, file) )
			return new Response<String> ( Res.BAD_INPUT );
		
		/* You none-the-less need to be logged in to SOME account */
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		if (user == null)
			 return new Response<String>( Res.NO_LOGIN );
		
		/* Verify that Shared User Acc Exists 
		 * If it does not, create it.
		 * */
		if (!LoginManager.verifyUser(SharedManager.EMAIL, SharedManager.PASSW))
			SharedManager.init(getUrl());
		
		return SharedManager.getFile(file, project);
	}

	@Override
	public Response<LinkedList<String>> getThemes() 
	{
		
		/* You none-the-less need to be logged in to SOME account */
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		if (user == null)
			 return new Response<LinkedList<String>>( Res.NO_LOGIN );
		
		/* Verify that Shared User Acc Exists 
		 * If it does not, create it.
		 * */
		if (!LoginManager.verifyUser(SharedManager.EMAIL, SharedManager.PASSW))
			SharedManager.init(getUrl());
		
		return SharedManager.getProjects();
	}
	
	/**
	 * This returns the Server's URL.
	 * @return Server's URL
	 */
	private final String getUrl()
	{
		HttpServletRequest req = this.getThreadLocalRequest();
		String scheme      = req.getScheme();
		String serverName  = req.getServerName();
		int serverPort     = req.getServerPort();
		String contextPath = req.getContextPath();
	
		String url = scheme.concat("://").concat(serverName).concat(":") + serverPort + contextPath;;
		return url;
	}
	
	private static final long serialVersionUID = 1305902545673156624L;
}