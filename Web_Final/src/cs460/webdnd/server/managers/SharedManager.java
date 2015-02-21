package cs460.webdnd.server.managers;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.LinkedList;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import com.google.appengine.api.datastore.Blob;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.ProjectData;
import cs460.webdnd.server.DB.SaverLoader;
import cs460.webdnd.server.helpers.BCrypt;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.helpers.Zipper;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Shared Manager handles code relating to the shared user.
 * NOTE: Shared Manager does not check whether shared account exists!
 * 
 * Layer between Services & Servlets and SaverLoader & MemCache.
 */
public class SharedManager {

	public static final String EMAIL = "shared@dndwebsitecreator.com";
	public static final String PASSW = "purefree";

	@Deprecated
	private SharedManager(){};


	private static final String[][] THEME_FILES = 
		{
		{ /*Name of Project, File in Project, File in Project, File in Project, etc..*/ },
		{ /*Name of Project, File in Project, File in Project, File in Project, etc..*/ },
		{ /*Name of Project, File in Project, File in Project, File in Project, etc..*/ },
		};


	/**
	 * Creates a Shared User Account.
	 * 
	 * @param url Server URL
	 */
	public synchronized static final void init(String url)
	{

		String hashp = BCrypt.hashpw(PASSW, BCrypt.gensalt());
		String phone = "0-000-000-0000";	
		String fName = "nil";
		String lName = "nil";

		FullUserData shared = new FullUserData( EMAIL, hashp, phone, fName, lName );

		boolean firstProject = true;

		/* LOAD STATIC THEMES */
		for (String[] theme : THEME_FILES)
		{
			boolean name = true;

			/* LOAD STATIC FILES IN A THEME */
			for (String file : theme)
			{

				/* First String is Theme Name */
				if ( name )
				{
					/* Very First Project Is Auto Created */
					if (!firstProject)
					{
						shared.newProject();
						firstProject = false;
					}

					shared.getCurrentProject().setProjectName(file);
					name = false;

				} 
				/* Add File */
				else
				{
					try 
					{
						/* (Site Address) + (File Location/Name.Extension)*/
						String cUrl = url.concat(file);
						Blob b = new Blob ( loadStatic(cUrl) );
						shared.getCurrentProject().addFile(file, b);
					}
					catch (Exception e)
					{
						Misc.error("SharedManager.loadStatic {" + e.toString() + "}");
					}
				}
			}			
		}

		shared.setValid();
		SaverLoader.addObject(shared);	
	}

	/**
	 * Adds a CSS file from CSS dir.
	 * 
	 * @param zos
	 * @param ServerURL
	 * @param name Name of the CSS file.
	 */
	private static byte[] loadStatic(String stringUrl) throws Exception
	{
		/* Create URL to File  */
		URL url = new URL(stringUrl);
		/* Input Stream To URL */
		InputStream input = url.openStream();
		/* Get byte Array      */
		byte[] byteArray = IOUtils.toByteArray(input);
		/* Zip That            */
		return Zipper.getZip( byteArray );
	}

	/**
	 * Returns a file as String, if file exists
	 * 
	 * @param file File Name
	 * @param project Project Name
	 * @return Appropriate Response
	 */
	public static final Response<String> getFile(String file, String project)
	{
		FullUserData shared = SaverLoader.getUserData(EMAIL, true);

		ProjectData d = shared.getProject(project);
		/* If Project DNE */
		if (d == null)
			return new Response<String> ( Res.NO_PROJECT );

		Blob blob = d.getFile(file);
		/* If File DNE    */
		if (blob == null )
			return new Response<String> ( Res.NO_FILE );

		/* Uncompress and make into a String */
		String fileString = new String( Zipper.getFile(blob.getBytes()) );

		return new Response<String>(fileString);
	}

	/**
	 * Get Information about all Files in a project
	 * 
	 * @param project Project / Theme
	 * @return Appropriate Response
	 */
	public static final Response<LinkedList<FileInformation>> getFileInformation(String project)
	{
		FullUserData shared = SaverLoader.getUserData(EMAIL, true);
		ProjectData d = shared.getProject(project);

		/* If Project DNE */
		if (d == null)
			return new Response<LinkedList<FileInformation>> ( Res.NO_PROJECT );

		return new Response<LinkedList<FileInformation>> ( d.getAllFileInformation() );
	}

	/**
	 * List of all Themes / Projects in Shared Manager
	 * @return Response
	 */
	public static final Response<LinkedList<String>> getProjects()
	{
		FullUserData shared = SaverLoader.getUserData(EMAIL, true);
		return new Response<LinkedList<String>> ( shared.getProjectNames() );
	}

	/**
	 * Loads a file for Client. Does nothing if error.
	 * 
	 * @param user User Name of Client
	 * @param fileName File Name
	 * @param thumbnail Do you want a thumbnail?
	 * @param res
	 */
	public static void load(String file, String project, HttpServletResponse res)
	{
		FullUserData shared = SaverLoader.getUserData(EMAIL, true);
		ProjectData d = shared.getProject(project);

		/* No Such Project */
		if (d == null)
			return;

		Blob blob = d.getFile(file);

		/* No Such File              */
		if (blob == null )
			return;

		/* Decompress before Sending */
		byte[] compressed = blob.getBytes();
		byte[] bytes = Zipper.getFile(compressed);

		BufferedOutputStream bos = null;

		try 
		{
			res .setContentLength(bytes.length);
			bos = new BufferedOutputStream(res.getOutputStream());
			bos .write(bytes, 0, bytes.length);
		}

		/* Error Handling */

		catch (Exception e) 
		{
			Misc.error("SharedManager.load { " + e.toString() + " }");
		}
		finally 
		{
			if (bos != null) 
			{
				try                   { bos.close();         } 
				catch (IOException e) 
				{ 
					Misc.error("SharedManager.load { " + e.toString() + " }");
				}
			}
		}
	}
}