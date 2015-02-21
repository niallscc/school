package cs460.webdnd.server.managers;

import java.util.Set;

import cs460.webdnd.server.DB.ProfileChange.ChangeFirstName;
import cs460.webdnd.server.DB.ProfileChange.ChangeLastName;
import cs460.webdnd.server.DB.ProfileChange.ChangePhoneNumber;
import cs460.webdnd.server.DB.ProfileChange.ProfileChange;
import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.SaverLoader;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.UserData;

/**
 * Handles the code related to User Management.
 * 
 * Layer between Services & Servlets and SaverLoader & MemCache.
 */
public class UserManager {
	/*
	 * =============================================================================================================
	 * USER INFORMATION MANAGEMENT
	 * =============================================================================================================
	 */
	
	public static Response<Boolean> changePhone (String email, String phone)
	{
		ProfileChange change = new ChangePhoneNumber(phone);
		return SaverLoader.makeChanges( email, change);
	}
	
	public static Response<Boolean> changeLastName (String email, String lName)
	{
		ProfileChange change = new ChangeLastName(lName);
		return SaverLoader.makeChanges( email, change);
	}
	
	public static Response<Boolean> changeFirstName (String email, String fName)
	{
		ProfileChange change = new ChangeFirstName(fName);
		return SaverLoader.makeChanges( email, change);
	}
	
	
	/**
	 * Generates a UserData object which has basic user data.
	 * @param user Email
	 * @return UserData if present, null otherwise.
	 */
	public static UserData generateUserData(String user)
	{
		FullUserData fud = SaverLoader.getUserData(user, true);
		
		String email = fud.getEmail();
		String fName = fud.getFirstName();
		String lName = fud.getLastName();
		String phone = fud.getPhone();
		Set<String> files = fud.getCurrentProject().getFiles();
		
		UserData data = new UserData(email, fName, lName, phone, files);
		
		return data;
	}
	

	/**
	 * Gets First Name of User
	 * 
	 * @param user User's Email
	 * @return
	 */
	public static String getFirstName(String user) {
		return SaverLoader.getUserData(user, true).getFirstName();
	}

	/**
	 * Gets User's Phone Number
	 * 
	 * @param user User's Email
	 * @return
	 */
	public static String getLastName(String user) {
		return SaverLoader.getUserData(user, true).getLastName();
	}

	/**
	 * Gets User's Phone Numbe
	 * @param user User's Email
	 * @return
	 */
	public static String getPhoneNumber(String user) {
		return SaverLoader.getUserData(user, true).getPhone();
	}
}