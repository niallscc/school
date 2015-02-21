package cs460.webdnd.server.managers;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import cs460.webdnd.server.DB.ProfileChange.AddFile;
import cs460.webdnd.server.DB.ProfileChange.DeleteFile;
import cs460.webdnd.server.DB.ProfileChange.ProfileChange;
import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.MemCache;
import cs460.webdnd.server.DB.SaverLoader;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.server.helpers.Zipper;
import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Data management code.
 * 
 * Layer between Services & Servlets and SaverLoader & MemCache.
 */
public class DataManager {
	
	/*
	 * =============================================================================================================
	 * FILE (DATASERVLET) RELATED
	 * =============================================================================================================
	 */
	
	/**
	 * Adds a file to the current Project for a certain User.
	 * @param email Email of the user account.
	 * @param fileName File Name.
	 * @param file File itself.
	 * @return True if added.
	 */
	public static Response<Boolean> addFile(String email, String fileName, byte[] file)
	{
		AddFile change = new AddFile(fileName, file);		
		return SaverLoader.makeChanges( email, change); 
	}
	
	/**
	 * Request a file for the current project of the user.
	 * @param email Email of the user account.
	 * @param fileName File Name requested.
	 * @return File itself, if present.
	 */
	public static byte[] getFile(String email, String fileName)
	{
		byte[] compressed = SaverLoader.getFile(email, fileName).getBytes();
		return Zipper.getFile(compressed);
	}

	/**
	 * Checks whether a certain user has a certain file
	 * 
	 * @param user User Name
	 * @param file Password
	 * @return Whether a certain file exists under a certain user
	 */
	public static boolean hasFile (String email, String file)
	{
		FullUserData u = SaverLoader.getUserData(email, true);
		
		// The user does not exist
		if (u == null)
			return false;
		
		return u.getCurrentProject().hasFile(file);
	}
	
	/**
	 * Gets FileInformation List for a certain user.
	 * 
	 * @param user User Name
	 * @return <code>null</code> if there is no such user
	 */
	public static Response<LinkedList<FileInformation>> getFileInfo (String email) 
	{
		
		FullUserData u = SaverLoader.getUserData(email, true);
		
		if (u == null)
			return null;
		
		LinkedList<FileInformation> l = u.getCurrentProject().getAllFileInformation();

		return new Response<LinkedList<FileInformation>> ( l );
	}
	
	/**
	 * Deletes a file
	 * @param email User's Email
	 * @param fileName File to delte
	 * @return
	 */
	public static Response<Boolean> deleteFile(String email, String fileName)
	{
		ProfileChange change = new DeleteFile(fileName);	
		return SaverLoader.makeChanges( email, change);
	}
	
	/*
	 * =============================================================================================================
	 * FILE (FILESERVLET) RELATED
	 * =============================================================================================================
	 */
	
	/**
	 * Processes uploaded by user files.
	 * Only to be used with FileServlet.
	 * @param email Email
	 * @param req
	 * @param Res
	 */
	public static Response<Boolean> addFiles(String email, HttpServletRequest req, HttpServletResponse resp) 
	{ 		
		try 
		{
			ServletFileUpload upload = new ServletFileUpload();
		      
			resp.setContentType("text/plain");
		
			FileItemIterator iterator = upload.getItemIterator(req);
			
			while ( iterator.hasNext() ) 
			{
				FileItemStream item = iterator.next();
				
				InputStream stream = item.openStream();

				if ( item.isFormField() )
					continue;
				
				/* Remove Illegal Characters */
				String cleanName = item.getName().replaceAll("[" + Misc.ILLEGAL + "]", "");
				
				/* Not an Image */
				if (!Misc.isImage(cleanName))
					return new Response<Boolean>(Res.BAD_FILE_NAME);
								
				/* Name is OK - continue */				
				byte[] uncompressed = IOUtils.toByteArray(stream);
				
				ProfileChange change = new AddFile( cleanName, uncompressed );
				
				Response<Boolean> r = SaverLoader.makeChanges(email, change);
				
				/* If there is an error, return that error */
				if (r.getResponse() != Res.ALL_OK)
					return r;
			}
		} 
		catch (Exception ex) 
		{
			Misc.error("DataManager.addFile {" + ex.toString() + "}");
	    }
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Sends a provided file using the provided response
	 * @param res Response
	 * @param bytes File
	 */
	private static void loadU (HttpServletResponse res, byte[] bytes)
	{
        BufferedOutputStream bos = null;
        
        try 
        {
        	res .setContentLength(bytes.length);
        	bos = new BufferedOutputStream(res.getOutputStream());
        	bos .write(bytes, 0, bytes.length);
        } 
        catch (Exception e) 
        {
        	Misc.warning("DataManager.loadU { " + e.toString() + " }");
        } 
        finally 
        {
        	if (bos != null) 
        	{
        		try                   { bos.close();         } 
        		catch (IOException e) 
        		{ 
        			Misc.warning("DataManager.loadU { " + e.toString() + " }");
        		}
        	}
        }
		
	}

	/**
	 * Loads a file for Client. Does nothing if error.
	 * @param user User Name of Client
	 * @param fileName File Name
	 * @param thumbnail Do you want a thumbnail?
	 * @param res
	 */
	public static void  load (String user, String fileName, HttpServletResponse res, boolean thumbnail)
	{
		byte[] compressed = SaverLoader.getFile(user, fileName).getBytes();
		
		byte[] bytes = Zipper.getFile(compressed);
		
		if (thumbnail)
		{
			try 
			{
				bytes = cs460.webdnd.server.helpers.ImageScaler.getThumbnail(bytes);
			}
			catch (Exception e)
			{
				Misc.warning("DataManager.load {" + e.toString() + "}");
				return;
			}
		}
		
		loadU(res, bytes);
	}
	
	/**
	 * Loads the whole project.
	 * @param user User Name.
	 * @param res Response
	 * @param serverURL
	 */
	public static void loadFolder (String user, HttpServletResponse res, String serverURL)
	{
		FullUserData d = SaverLoader.getUserData(user, true);
		
		if (d == null)
			return;
		
		byte[] bytes = Zipper.returnZippedFolder(d, serverURL);
		
		loadU(res,bytes);
	}
	
	
	/**
	 * Add a response to file upload.
	 * @param response
	 * @param email
	 */
	public static void addRes(Response<Boolean> response, String email)
	{
		MemCache.addFileRes(response, email);
	}
	
	/**
	 * Get the current response to file Upload.
	 * @param email
	 * @return
	 */
	public static Response<Boolean> getRes(String email)
	{
		return MemCache.getFileRes(email);
	}
}