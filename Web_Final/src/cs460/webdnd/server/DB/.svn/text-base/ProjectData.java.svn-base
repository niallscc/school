package cs460.webdnd.server.DB;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.Key;

import cs460.webdnd.shared.FileInformation;
import cs460.webdnd.shared.FileInformation.FileType;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Stores a project (that is one website).
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class ProjectData {
	
	public ProjectData()
	{
		projectName = "project"; 
	}
	
	/* 
	 * ------------------------------------------------------------
	 * Main DATA
	 * ------------------------------------------------------------
	 */ 
	 
	@Persistent
	private String projectName;
	
	@Persistent(serialized = "true", defaultFetchGroup = "true")
	private Map<String, String> fileDate = new HashMap<String, String>();
	
	@Persistent(serialized = "true", defaultFetchGroup = "true")
	private Map<String, Blob> fileData = new HashMap<String, Blob>();
	
	/* 
	 * ------------------------------------------------------------
	 * DATABASE SPECIFIC
	 * ------------------------------------------------------------
	 */
	
	@Persistent
    private FullUserData user;

	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key id;
	
	/* 
	 * ------------------------------------------------------------
	 * DIRECTORIES
	 * ------------------------------------------------------------
	 */
	
	private static final String IMG_DIR  = "img/";
	private static final String VID_DIR  = "vid/";
	private static final String TXT_DIR  = "txt/";
	private static final String HTML_DIR = "";
	private static final String BIN_DIR  = "bin/";
	private static final String CSS_DIR  = "styles/";
	
	/* 
	 * ------------------------------------------------------------
	 * PROJECT NAME
	 * ------------------------------------------------------------
	 */
	
	/**
	 * @param name Name for this project.
	 */
	public void setProjectName(String name)
	{
		this.projectName = name;
	}
	
	/**
	 * Should not be called before making persistent, otherwise null pointer exception.
	 * @return Name of this project.
	 */
	public String getProjectName()
	{
		/* To ensure that all project names are different */
		if ( projectName.equals("project") )
			projectName = "project " + id.getId();
		
		return projectName;
	}
	
	/* 
	 * ------------------------------------------------------------
	 * FILE ADD / REMOVE
	 * ------------------------------------------------------------
	 */
	
	/**
	 * Adds a new Blob Object (data file).
	 * @param name File Name
	 * @param object Blob Object
	 */
	public void addFile(String name, Blob object)
	{
		fileDate.put(name, new Date().toString());
		fileData.put(name, object);	
	}
	
	public Response<Boolean> deleteFile(String name)
	{
		Blob b = fileData.remove(name);
		
		if (b == null)
			return new Response<Boolean>(Res.NO_FILE);
		
		fileDate.remove(name);
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Returns Blob file.
	 * @param file File Name
	 * @return Blob
	 */
	public Blob getFile(String file)
	{		
		return fileData.get(file);
	}
	
	/* 
	 * ------------------------------------------------------------
	 * BASIC FILE INFORMATION
	 * ------------------------------------------------------------
	 */
	
	/**
	 * Checks whether a certain file exists.
	 * @param file File Name
	 * @return Whether that file exists.
	 */
	public boolean hasFile(String file)
	{
		return fileDate.containsKey(file);
	}
	
	/**
	 * @return Number of files in the project.
	 */
	public final int getFileCount()
	{
		return fileData.size();
	}
	
	/**
	 * Returns all the files stored.
	 * @return List of File Names
	 */
	public final Set<String> getFiles()
	{
		return fileData.keySet();
	}
	
	/* 
	 * ------------------------------------------------------------
	 * ADVANCED FILE INFORMATION
	 * ------------------------------------------------------------
	 */
	
	/**
	 * Returns File Information for a certain file.
	 * @param file File Name
	 * @return File Information Object
	 */
	public FileInformation getFileInformation(String file)
	{
		if (!hasFile(file))
			return null;
		
		String date = fileDate.get(file);
		
		FileType type = this.getFileType(file);
		
		return new FileInformation(file, date, type);
	}
	
	/**
	 * Returns information of the Objects stored for this user.
	 * @return List of FileInformation Objects.
	 */
	public LinkedList<FileInformation> getAllFileInformation()
	{
		LinkedList<FileInformation> l = new LinkedList<FileInformation>();
		
		for (String file : fileDate.keySet())
			l.add( getFileInformation(file) );
		
		return l;
	}

	/* 
	 * ------------------------------------------------------------
	 * MISC
	 * ------------------------------------------------------------
	 */
	
	/**
	 * @param fileName File Name with extension
	 * @return File Type
	 */
	public FileType getFileType(String fileName)
	{
		String dir = getDirectory(fileName);
		
		if ( dir.equals(IMG_DIR)  )
			return FileType.IMAGE;
		
		if ( dir.equals(TXT_DIR)  )
			return FileType.TEXT;
		
		if ( dir.equals(VID_DIR)  )
			return FileType.VIDEO;
		
		if ( dir.equals(HTML_DIR) )
			return FileType.HTML;
		
		if ( dir.equals(BIN_DIR)  )
			return FileType.BIN;
		
		if ( dir.equals(CSS_DIR)  )
			return FileType.CSS;
		
		return FileType.MISC;
	}

	
	
	/**
	 * Returns the directory of the a certain file.
	 * Returns null if the file does not exists.
	 * @param fileName File Name
	 * @return Directory
	 */
	public String getDirectory (String fileName) 
	{
		// File Does not Exist
		if (!fileDate.containsKey(fileName))
			return null;
		
		// FileName | Extension
		String[] splitName = fileName.split("\\.");
		
		// splitName[i] should hold the file extension
		int i = splitName.length - 1;
		
		// IMAGE
		if (	splitName[i].equalsIgnoreCase( "jpg" ) 
			 || splitName[i].equalsIgnoreCase( "jpeg")
		     || splitName[i].equalsIgnoreCase( "png" )
			 || splitName[i].equalsIgnoreCase( "gif" )
			 || splitName[i].equalsIgnoreCase( "bmp" )
			)
		{
			return IMG_DIR;
		}
		
		// VIDEO
		if (    splitName[i].equalsIgnoreCase("HDMOV")
		     || splitName[i].equalsIgnoreCase("MP4")
			)
		{
			return VID_DIR;
		}
		
		// TXT
		if (    splitName[i].equalsIgnoreCase("TXT")
		     || splitName[i].equalsIgnoreCase("XML")
		    )
		{
			return TXT_DIR;
		}
		
		if( splitName[i].equalsIgnoreCase("CSS"))
			return CSS_DIR;
		
		if( splitName[i].equalsIgnoreCase("HTML"))
			return HTML_DIR;
		
		if( splitName[i].equalsIgnoreCase("BIN"))
			return BIN_DIR;
		
		return "";
	}
	
	/*
	 * NOT USED
	 */

	@Deprecated
	public Key getId(       )              { return id;		   }
	@Deprecated
	public void setId(Key id)              { this.id = id;	   }
	@Deprecated
	public FullUserData getUser()          { return user;      }
	@Deprecated
	public void setUser(FullUserData user) { this.user = user; }
}