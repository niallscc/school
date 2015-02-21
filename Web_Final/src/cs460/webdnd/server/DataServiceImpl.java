package cs460.webdnd.server;

import java.util.LinkedList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import cs460.webdnd.client.DataService;
import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.DataManager;
import cs460.webdnd.server.managers.UserManager;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;
import cs460.webdnd.shared.UserData;

/**
 * Please refer to cs460.webdnd.client.DataServiceAsync for explanation of methods.
 * 
 * @author Alexandre Rogozine(alexandre.rogozine@live.com)
 */
public class DataServiceImpl extends RemoteServiceServlet implements DataService {
	
	@Override
	public Response<String> getText(String fileName) {
		
		/* Bad Input */
		if ( !Misc.verify(fileName) )
			return new Response<String>( Res.BAD_INPUT );
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No User Login */
		if (user == null)
			 return new Response<String>( Res.NO_LOGIN );
		
		byte[] bytes = DataManager.getFile(user, fileName);
		
		/* No File */
		if ( bytes == null )
			return new Response<String> ( Res.NO_FILE );
		
		/* All OK */
		return new Response<String> ( new String(bytes) );
	}

	@Override
	public synchronized Response<Boolean> sendHtml(String html, String pageName) {
		
		/* Bad Input */
		if (!Misc.verify(pageName) || html == null || html.isEmpty() )
			return new Response<Boolean>(Res.BAD_INPUT);
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No User */
		if (user == null)
			 return new Response<Boolean>( Res.NO_LOGIN );

		return DataManager.addFile(user, pageName, html.getBytes() ) ;
	}
	
	@Override
	public Response<LinkedList<FileInformation>> getFiles() 
	{
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No User */
		if (user == null)
			 return new Response<LinkedList<FileInformation>> ( Res.NO_LOGIN );
		
		return DataManager.getFileInfo(user);
	}

	@Override
	public Response<UserData> getUserData() {
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No User */
		if (user == null)
			 return new Response<UserData> ( Res.NO_LOGIN );

		return new Response<UserData> ( UserManager.generateUserData(user) );
	}


	@Override
	public Response<Boolean> deleteFile(String fileName) {
	
		/* Bad input */
		if (!Misc.verify(fileName))
			return new Response<Boolean> ( Res.BAD_INPUT );
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (user == null)
			 return new Response<Boolean> ( Res.NO_LOGIN );
		
		return DataManager.deleteFile(user, fileName);
	}

	@Override
	public Response<Boolean> getFileServletResponse() {
		
		String user = CookieManager.getUser(getThreadLocalRequest(), getThreadLocalResponse(), true);
		
		/* No Login */
		if (user == null)
			 return new Response<Boolean> ( Res.NO_LOGIN );
		
		return DataManager.getRes(user);
	}

	private static final long serialVersionUID = -410951359088792221L;
}