package cs460.webdnd.server.helpers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.ProjectData;
import cs460.webdnd.shared.FileInformation.FileType;


/**
 * Compression and Uncompression Functionality.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class Zipper {
	
	/**
	 * Returns a compressed file from an uncompressed input.
	 * @param input byte[] representation of a file
	 * @return compressed version of input, null if error
	 */
	public static final byte[] getZip (byte[] input)
	{
		try
		{
			Deflater compressor = new Deflater();
			compressor.setLevel( Deflater.BEST_COMPRESSION );
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream( input.length );
			
			compressor.setInput(input);
			compressor.finish();
			
			// Compress the data
			byte[] buf = new byte[1024];

			while (!compressor.finished())
				bos.write(buf, 0, compressor.deflate(buf) );	
			
			compressor.finish();
			bos.close();
			
			byte[] b = bos.toByteArray(); 
			return b;
		}
		catch (Exception t)
		{
			Misc.warning( "Zipper.getZip {"+t+"}");
			return input;
		}
	}
	
	/**
	 * Returns uncompressed version of compressed input.
	 * @param zippedBytes Compressed Input
	 * @return Uncompressed Output.
	 */
	public static final byte[] getFile (byte[] zippedBytes)
	{
		try 
		{
			Inflater decompressor = new Inflater();
			decompressor.setInput(zippedBytes);
	
			ByteArrayOutputStream bos = new ByteArrayOutputStream(zippedBytes.length);
	
			byte[] buf = new byte[1024];
		

			while (true) 
			{
				int count = decompressor.inflate(buf);
				
				if (count > 0) { bos.write(buf, 0, count); } 
				else { break; } 
			 }
			
			decompressor.end();
		    bos.close();
		    
		    return bos.toByteArray();
		} 
		catch (Exception e) 
		{
			 Misc.warning("Zipper.getFile {" + e + "}" );
			 return zippedBytes;
		}
	}
	
	/**
	 * Returns a Zipped Folder of all the entities in the current Project.
	 * @param user User Name.
	 * @param ServerURL Server URL.
	 * @return Zipped Folder. Null if Something went wrong.
	 */
	public static byte[] returnZippedFolder (FullUserData user, String serverURL)
	{
		try 
		{
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ZipOutputStream zos = new ZipOutputStream(baos);
		        
			ProjectData cp = user.getCurrentProject();
			
			for (String fi : cp.getFiles())
			{
				FileType fileType = cp.getFileType(fi);
				
				/* .bin are internal files */
				if (fileType == FileType.BIN)
					continue;
				
				String dir = cp.getDirectory(fi);
				zos.putNextEntry( new ZipEntry( dir.concat(fi) ) );		/* dir.concat(fi) = "directory/filename" */ 
		        byte[] bytes = cp.getFile(fi).getBytes();
		        
		        byte[] uncompressed = getFile(bytes);					/* Uncompress File                       */
		        
		        /* .html are internal files */
		        if (fileType == FileType.HTML)
		        	uncompressed = Zipper.parseHtml( new String (uncompressed), serverURL ).getBytes(); /* Parse HTML */
		        
				zos.write(uncompressed);								/* Write file named fi to zip in dir     */
				zos.closeEntry();
			}
			
			/* Add Static Files, TODO Make Better */
			CssHelper(zos, serverURL, "BackgroundStyles.css");
			CssHelper(zos, serverURL, "BorderColor.css"     );
			CssHelper(zos, serverURL, "BorderRadius.css"    );
			CssHelper(zos, serverURL, "BorderStyle.css"     );
			CssHelper(zos, serverURL, "BorderWidth.css"     );
			CssHelper(zos, serverURL, "MenuStyles.css"      );
			
			zos.close();
			baos.close();
			
			/* Finished .ZIP Folder as Bytes */
			byte[] finished = baos.toByteArray();
			
	        zos.flush();
	        baos.flush();
	        
	        zos.close();
	        baos.close();
	        
	        return finished;
		}
		catch (Exception e)
		{
			Misc.error("Zipper.returnZippedFolder {" + e.toString() + "}");
		}
		
		return null;
	}
	
	/**
	 * Adds a CSS file from CSS dir.
	 * @param zos
	 * @param ServerURL
	 * @param name Name of the CSS file.
	 */
	private static void CssHelper(ZipOutputStream zos, String ServerURL, String name)
	{
		try 
		{
			URL url = new URL(ServerURL.concat("/CSS/").concat(name));
			InputStream input = url.openStream();
			byte[] byteArray = IOUtils.toByteArray(input);
					
			zos.putNextEntry(new ZipEntry("CSS/".concat(name) ));
			zos.write(byteArray);
			zos.closeEntry();
		}
		catch (IOException e)
		{
			Misc.error("Zipper.CssHelper {" + e.toString() + "}");
		}
	}
	
	private static String parseHtml(String html, String serverURL)
	{	
		/* Background Image Fix */
		html = html.replaceAll("BACKGROUND-IMAGE:url\\(".concat(serverURL).concat("/web_final/FileServlet\\?file="), "BACKGROUND-IMAGE:url(img/");
		
		/* HTML Location Fix    */
		html = html.replaceAll(serverURL.concat("/web_final/FileServlet\\?file="), "");
		html = html.replaceAll("/web_final/FileServlet\\?file=", "");
		
		/* CSS Location Fix     */
		html = html.replaceAll("/CSS/", "CSS/");
		/* MISC */
		html = html.replaceAll(serverURL.concat("/"), "");
		
		html = html.replaceAll(serverURL, "");
		
		
		/* TODO: Check if Works */
//		html = html.replaceAll(serverURL.concat("/CSS/"), "styles/");
//		
//		html = html.replaceAll(serverURL.concat("//"), "");
		
//		"BACKGROUND-IMAGE:url(http://dndwebsitecreator.appspot.com/web_final/FileServlet?file="
		return html;
	}
	
	@Deprecated
	private Zipper () {}
}