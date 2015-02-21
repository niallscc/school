package cs460.webdnd.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs460.webdnd.server.helpers.CookieManager;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.managers.DataManager;
import cs460.webdnd.server.managers.LoginManager;
import cs460.webdnd.server.managers.SharedManager;

/**
 * This is for uploaded and downloading files once logged in.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class FileServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		parse(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		parse(req, resp);
	}

	/**
	 * Parses the request.
	 * 
	 * @param req
	 * @param resp
	 */
	protected void parse(HttpServletRequest req, HttpServletResponse resp)
	{
		/* Must be logged in */
		String user = CookieManager.getUser(req, resp, true);

		if (user == null)
		{
			Misc.warning("FileServlet.parse { no login }" );
			return;
		}

		resp.setStatus(HttpServletResponse.SC_OK);

		/* File Download Request */
		String filePar = req.getParameter("file");
		if (filePar != null)
		{
			load(user, filePar, resp );		  		  
			return;
		}

		/* Thumbnail Download Request */
		String thumbPar = req.getParameter("thumb");
		if (thumbPar != null)
		{		  
			loadThumb(user, thumbPar, resp);
			return;
		}

		/* Shared File */
		String sharedFile = req.getParameter("sFile");
		String sharedTheme = req.getParameter("sTheme");
		if (sharedFile != null && sharedTheme != null)
		{
			String url = getUrl(req);
			loadStatic (sharedFile, sharedTheme, url, resp);
			return;
		}

		/* Project Download Request */
		String zipPar = req.getParameter("folder.zip");
		if (zipPar != null)
		{
			resp.setHeader("Content-Disposition", "attachment; filename=\"" + "folder.zip" + "\"");
			resp.setContentType( "application/download" );

			String url = getUrl(req);
			DataManager.loadFolder(user, resp, url);

			return;
		}

		/* Tried to Upload Files 
		 * Saves Response
		 * */
		DataManager.addRes ( DataManager.addFiles(user, req, resp), user );

		return;
	}

	/**
	 * Sends a file.
	 * 
	 * @param user User Name
	 * @param fileName File Name
	 * @param res Response
	 */
	private final static void load(String user, String fileName, HttpServletResponse res)
	{
		/* File Does Not Exist In Userbase */
		if ( !DataManager.hasFile(user, fileName) )
			return;

		String fileExt = parseString(fileName);

		if (fileExt == null)
			return;
		
		res.setHeader("Content-Disposition", "inline; filename=\"" + fileName + "\"");
		
		if ( Misc.isImage(fileName) )
		{
			res.setContentType( "image/".concat(fileExt.toLowerCase()));
		}
		else if ( fileExt.equalsIgnoreCase( "xml" ) )	
		{	
			res.setContentType( "text/plain" );
		}
		else if ( fileExt.equalsIgnoreCase( "html") )
		{	
			res.setContentType( "text/html" );	
		}
		else res.setContentType( "application/download" );

		DataManager.load(user, fileName, res, false);
	}

	/**
	 * Sends an image thumbnail
	 * 
	 * @param user User Name
	 * @param fileName File Name
	 * @param res Response
	 */
	private final static void loadThumb(String user, String fileName, HttpServletResponse res)
	{
		/* Return if not an image */
		if ( !Misc.isImage(fileName) )
			return;
		
		/* File Does Not Exist In Userbase */
		if (!DataManager.hasFile(user, fileName))
			return;

		DataManager.load(user, fileName, res, true);
	}

	/**
	 * Loads a shared resourse.
	 * 
	 * @param fileName File Name
	 * @param url URL, in case SharedData is not created.
	 * @param res Response
	 */
	private final static void loadStatic (String fileName, String project, String url, HttpServletResponse res)
	{

		/* If sharedData does not exist, create it */
		if (!LoginManager.verifyUser(SharedManager.EMAIL, SharedManager.PASSW))
			SharedManager.init(url);

		String fileExt = parseString(fileName);

		/* Files Must Have an Extension */
		if (fileExt == null)
			return;

		SharedManager.load(fileName, project, res);
	}

	/**
	 * Gets the extension from the string.
	 * 
	 * @param fileName File Name (presumably w. extension)
	 * @return Null if no extension, otherwise file extension
	 */
	private final static String parseString (String fileName)
	{
		/* FileName | Extension */
		String[] splitName = fileName.split("\\.");

		/* No extension! */
		if (splitName.length < 2)
			return null;

		/* fileExt should hold the file extension */
		int i = splitName.length - 1;

		return splitName[i];
	}

	/**
	 * Gets the URL
	 * 
	 * @param req
	 * @return
	 */
	private static final String getUrl(HttpServletRequest req)
	{
		String scheme      = req.getScheme();
		String serverName  = req.getServerName();
		// TODO Do we need the server port?
		int serverPort     = req.getServerPort();
		String contextPath = req.getContextPath();

		return scheme.concat("://").concat(serverName).concat(":") + serverPort + contextPath;
	}

	private static final long serialVersionUID = -5594308973498051611L;
}