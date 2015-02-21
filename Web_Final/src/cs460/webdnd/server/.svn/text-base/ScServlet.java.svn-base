package cs460.webdnd.server;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Blob;

import cs460.webdnd.server.DB.MemCache;
import cs460.webdnd.server.helpers.Misc;

/**
 * For some reason added icons (mostly disable things) were getting deleted
 * thus when trying to upload to GAE these icons were not appearing.
 * 
 * This is one of the solutions that I have seen.
 *  
 * It also is a great solution to limit the amount of static files on the Server.
 * All files can be zipped up and be one static file. 
 * This reduces the amount of static files need to one and also reduced the space needed for static files immensely.
 * Lastly, this removes logs which document access to static files.
 * 
 */
public class ScServlet extends HttpServlet {


	@Override 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) {
		
		/* Get Request URI - get rid of "/web_final"*/
		String requestedURI = req.getRequestURI().replaceFirst("/web_final", "");
//		Misc.warning(requestedURI);
		
		/* Remove first character "/" presumably */
		requestedURI = requestedURI.substring(1);
		
		/* See if file is in Memory Cache */
		Blob b = MemCache.getFile( requestedURI );
		
		/* The files DNE in Memory Cache,
		 * try getting it from zip and saving to Memory Cache
		 *  */
		if (b == null )
		{
			
			try 
			{
				ZipInputStream in = new ZipInputStream(new FileInputStream("sc.zip"));
				
				ZipEntry entry;
				
				/* Search through all compressed files */
				while ((entry = in.getNextEntry()) != null) 
				{
					
					/* File Found */
					if ( requestedURI.equalsIgnoreCase((entry.getName())) ) 
						b = write(resp, in, requestedURI);
					
				}
				
				in.close();
			}
			/* Error - Return */
			catch (Exception e)
			{
				Misc.error("ScServlet { " + e.toString() + " }");
				return;
			}
			
			/* File DNE - Return */
			if ( b == null)
			{
				Misc.warning("File DNE: " + requestedURI);
				return;
			}
		}
		
		
		
		/* Get Bytes */
		byte[] bytes = b.getBytes();
		
        BufferedOutputStream bos = null;
        
        try 
        {
        	/* Set Content Type */
    		resp.setContentType   ( ScServlet.getContentType(requestedURI)         );
    		/* Set Header       */
    		resp.setHeader        ( "Content-Disposition", getHeader(requestedURI) );
        	/* Set File Length  */
        	resp .setContentLength( bytes.length                                   );
        	
        	/* Return File */
        	bos = new BufferedOutputStream( resp.getOutputStream() );
        	bos .write(bytes, 0, bytes.length);
        } 
        catch (Exception e) 
        {	
        	Misc.error("ScServlet { Failed to Return File from Memcache }");
        }
        
        finally 
        {
        	if (bos != null) 
        	{
        		try                   { bos.close(); } 
        		catch (IOException e) {		   	   	 }
        	}
        }
        
	}
	
	/**
	 * Return a file from Zip and Save That File to Memcache.
	 * 
	 * @param resp
	 * @param in Zip
	 * @return File, if any
	 * @throws IOException
	 */
	private static final Blob write (HttpServletResponse resp, ZipInputStream in, String fileName) 
			throws IOException
	{
		/* GET FILE AND SAVE TO MEMCACHE  */
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		byte[] buf = new byte[1024];
		
		int len;
		
		/* Write it */
		while ((len = in.read(buf)) > 0)	{	out.write(buf, 0, len);	}
		
		/* Close */
//		in.close();
		out.close();
		
		Blob file = new Blob(out.toByteArray());
		MemCache.addFile(fileName, file);
		
		return file;
	}
	
	private static final long serialVersionUID = -8027726343975162341L;
	
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
	
	private final static String getContentType (String fileName)
	{
		/* Get File Extension */
		String fileExt = parseString(fileName);
		
		if ( Misc.isImage(fileName) )
		{
			return "image/".concat(fileExt.toLowerCase());
		}
		else if ( fileExt.equalsIgnoreCase( "xml" ) )	
		{	
			return "text/xml";
		}
		else if ( fileExt.equalsIgnoreCase( "css" ) )
		{
			return "text/css";
		}
		else if ( fileExt.equalsIgnoreCase( "xsl" ) )
		{
			return "text/xsl";
		}
		else if ( fileExt.equalsIgnoreCase( "html") )
		{	
			return "text/html";	
		}
		else if ( fileExt.equalsIgnoreCase( "js" ) )
		{
			return "text/javascript";
		}
		return "text/plain";		
	}
	
	private static final String getHeader (String string)
	{
		String[] split = string.split("\\/");
		return "inline; filename=\"" + split[split.length-1] + "\"";
	}

}