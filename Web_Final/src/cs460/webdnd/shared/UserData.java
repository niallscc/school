package cs460.webdnd.shared;

import java.io.Serializable;
import java.util.Set;

/**
 * Slim user data obj. 
 * 
 * TODO: Check if useful.
 */
public class UserData implements Serializable {
	
	private Set<String> fileList;
	
	private String email;
	private String fName;
	private String lName;
	private String phone;
	
	/**
	 * 
	 * @param email Email
	 * @param fName First Name
	 * @param lName Last Name
	 * @param phone Phone
	 * @param fileList FileList // TODO
	 */
	public UserData(String email, String fName, String lName, String phone, Set<String> fileList)
	{
		this.email = email;
		this.fName = fName;
		this.lName = lName;
		this.phone = phone;
		this.fileList = fileList;
	}
	
	@Deprecated
	public UserData (){}
	
	/**
	 * 
	 * @return Email
	 */
	public final String getEmail () 
	{
		return email;
	}
	
	/**
	 * 
	 * @return First Name
	 */
	public final String getFirstName ()
	{
		return fName;
	}
	
	/**
	 * 
	 * @return Last Name 
	 */
	public final String getLastName ()
	{
		return lName;
	}
	
	/**
	 * 
	 * @return Phone
	 */
	public final String getPhone ()
	{
		return phone;
	}
	
	/**
	 * 
	 * @return File List?
	 */
	public final Set<String> getFileList()
	{
		return fileList;
	}
	
	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 8380171830447456050L;

}
