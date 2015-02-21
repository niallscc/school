package cs460.webdnd.server.DB.ProfileChange;

import com.google.appengine.api.datastore.Blob;

import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.helpers.Zipper;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Adds a file to the project.
 * 
 * File gets compressed before adding. This a requirement.
 * There is a maximum number of files and a maximum file size which are observed.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class AddFile implements ProfileChange
{
	/* Maximum File Size in bytes               */
	private static final int MAX_FILE_SIZE = 1048576;
	/* Maximum amount of files a user can store */
	private static final int MAX_FILES = 128;
	
	
	private final String fileName;
	private final Blob obj;
	
	public AddFile (String fileName, byte[] uncompressed) 
	{
		byte[] compressed = Zipper.getZip(uncompressed);
		
		this.fileName = fileName;
		this.obj = new Blob(compressed);
		
	}

	@Override
	public Response<Boolean> makeChange(FullUserData userData) {
		
		if (userData.getCurrentProject().getFileCount() > MAX_FILES)
	    	return new Response<Boolean> (Res.TOO_MANY_FILES);

		if ( obj.getBytes().length > MAX_FILE_SIZE )
			return new Response<Boolean> (Res.FILE_TOO_LARGE);
		
		userData.getCurrentProject().addFile(fileName, obj);
		return new Response<Boolean> ( Res.ALL_OK );
	}
}