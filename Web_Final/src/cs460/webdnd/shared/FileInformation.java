package cs460.webdnd.shared;

import java.io.Serializable;

/**
 * File Information
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class FileInformation implements Serializable {
	/* Required */
	private static final long serialVersionUID = 6062333771630735892L;
	
	private String fileName, /* File Name as Uploaded */
				   date;     /* Date of Upload        */
	
	private FileType type;   /* File Type           */

	/**
	 * 
	 * @param fileName File Name
	 * @param date Date of Creation of File
	 * @param type Type of File
	 */
	public FileInformation(String fileName, String date, FileType type)
	{
		this.fileName = fileName;
		this.date     = date;
		this.type     = type;
	}
	
	/** @return File Name                */
	public String getFileName()	
	{	
		return fileName;	
	}

	/** @return Date of File Upload      */
	public String getDate()
	{	
		return date;	
	}
	
	/** @return FileType      */
	public FileType getType()
	{
		return type;
	}
	
	/** File Types  */
	public enum FileType 
	{
		TEXT, HTML, CSS, BIN, IMAGE, VIDEO, AUDIO, MISC
	};
	
	@Deprecated
	public FileInformation() { }
}