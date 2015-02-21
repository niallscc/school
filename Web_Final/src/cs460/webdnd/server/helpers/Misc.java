package cs460.webdnd.server.helpers;

import java.util.logging.Level;
import java.util.logging.Logger;

import cs460.webdnd.server.FileServlet;

/**
 * Code that is too lonely to be by itself
 */
public class Misc {
	
	public static final String ILLEGAL = new String (new char[] 
		{
		
		/* Windows Forbids These */
		'"' ,
		'*' ,
		'|' ,
		'\'',
		
		'\\',
		'\\', /* regex fix */
		
		'/' ,
		'>' ,
		'<' ,
		'?' ,
		
		/* Additional Ones We Forbid */
		'#',
		'^',
		'&',
		'{',
		'}',
		';',
		':',
		'$'
		
		});
	
	/* To print out WARNING msgs */
	private static final Logger LOG = Logger.getLogger(FileServlet.class.getName());
	
	/**
	 * To log a warning.
	 * 
	 * @param warning Warning.
	 */
	public static void warning (String warning)
	{
		LOG.log(Level.WARNING, warning);
	}
	
	/**
	 * To log an error.
	 * Error is something not recoverable.
	 * 
	 * @param error Error.
	 */
	public static void error (String error)
	{
		LOG.log(Level.SEVERE, error);
	}
	
	/**
	 * Confirm that the Strings are valid.
	 * 
	 * @param strings Strings.
	 * @return True if all are valid (not null, etc.)
	 */
	public static boolean verify (String... strings)
	{
		for (String s : strings)
		{
			if (s == null)
			{
				warning("FieldVerifier { Null in Input }");
				return false;
			}
			
			if ( s.isEmpty() )
			{
				warning("FieldVerifier { Empty String as Input }");
				return false;
			}
	
			/* Remove Illegal Characters */
			if ( s.matches( ".*[" + ILLEGAL + "].*") )  
			{
				warning("FieldVerifier. verify { Illegal Character - [ " + s + " ] }");
				return false;
			}
		}

		return true;
	}
	
/*	public static String formatEmail ( String email )
	{
		return email.toLowerCase();
	}
	
	public static String formatPhone ( String phone )
	{
		return phone.toLowerCase();
	}*/
	
	public static final boolean isImage (String fileName)
	{
		/* FileName | Extension */
		String[] splitName = fileName.split("\\.");

		/* No extension! */
		if (splitName.length < 2)
			return false;

		/* fileExt should hold the file extension */
		int i = splitName.length - 1;
		
		String fileExt = splitName[i];

		/* images only */
		if      ( fileExt.equalsIgnoreCase( "jpg" ) )	{ return true;	}
		else if ( fileExt.equalsIgnoreCase( "jpeg") )	{ return true;	}
		else if ( fileExt.equalsIgnoreCase( "png" ) )	{ return true;	}
		else if ( fileExt.equalsIgnoreCase( "apng") )	{ return true;	}
		else if ( fileExt.equalsIgnoreCase( "gif" ) )	{ return true;	}
		else if ( fileExt.equalsIgnoreCase( "bmp" ) )	{ return true;	}
		
		return false;
	}
	
	public static final String getContentType (String fileName)
	{
		/* FileName | Extension */
		String[] splitName = fileName.split("\\.");

		/* No extension! */
		if (splitName.length < 2)
			return "";

		/* fileExt should hold the file extension */
		int i = splitName.length - 1;
		
		String fileExt = splitName[i];
		
		if ( fileExt.equalsIgnoreCase( "jpg" ) )	
		{ 
			return "image/jpg";	
		}
		
		if ( fileExt.equalsIgnoreCase( "jpeg") )	
		{ 
			return "image/jpg";
		}
		
		if ( fileExt.equalsIgnoreCase( "png" ) )	
		{ 
			return "image/png";
		}
		
		if ( fileExt.equalsIgnoreCase( "apng") )	
		{ 
			return "image/apng";
		}
		
		if ( fileExt.equalsIgnoreCase( "gif" ) )
		{ 
			return "image/gif";
		}
		
		if ( fileExt.equalsIgnoreCase( "bmp" ) )
		{ 
			return "image/bmp";	
		}

		if ( fileExt.equalsIgnoreCase( "xml" ) )	
		{	
			return "text/xml";
		}
		
		if ( fileExt.equalsIgnoreCase( "css" ) )
		{
			return "text/css";
		}
		
		if ( fileExt.equalsIgnoreCase( "xsl" ) )
		{
			return "text/xsl";
		}
		
		if ( fileExt.equalsIgnoreCase( "html") )
		{	
			return "text/html";	
		}
		
		if ( fileExt.equalsIgnoreCase( "js" ) )
		{
			return "text/javascript";
		}
		
		if ( fileExt.equalsIgnoreCase("zip") )
		{
			return "application/download";
		}
		
		return "text/plain";
	}
	
}