package cs460.webdnd.server.managers;

import java.net.URLEncoder;
import java.util.UUID;

import cs460.webdnd.server.DB.ProfileChange.ChangePassword;
import cs460.webdnd.server.DB.ProfileChange.MakeValid;
import cs460.webdnd.server.DB.ProfileChange.ProfileChange;
import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.MemCache;
import cs460.webdnd.server.DB.SaverLoader;
import cs460.webdnd.server.helpers.BCrypt;
import cs460.webdnd.server.helpers.EmailVerifier;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;
/**
 * Login Management Code.
 * Layer between Services & Servlets and SaverLoader & MemCache.
 */
public class LoginManager {
	
	/*
	 * =============================================================================================================
	 * USER PROFILE BASICS
	 * =============================================================================================================
	 */

	/**
	 * Adds a User.
	 * 
	 * @param email Email
	 * @param pass Password
	 * @param fName First Name
	 * @param lName Last Name
	 * @param phone Phone Number
	 * @param baseURL URL of Server
	 * @return
	 */
	public static Response<Boolean> addUser(String email, String pass, String fName, String lName, String phone, String baseURL) 
	{
		/* User Name Already Taken */
		if (MemCache.hasUser(email))
			return new Response<Boolean>(Res.ACCOUNT_EXISTS);
		
		/* This is Shared Account  */
		if (email.equals(SharedManager.EMAIL))
			return new Response<Boolean>(Res.MISC_ERROR);
		
		/* A similar user already exists */
		if (MemCache.hasSimilarUser(fName, lName, phone))
			return new Response<Boolean>(Res.NO_MULT_ACC);
		
		/* Create Account */
		String hash = BCrypt.hashpw(pass, BCrypt.gensalt());
		FullUserData fD = new FullUserData(email, hash, phone, fName, lName);
		SaverLoader.addObject(fD);
		
		/* Send Verification Email */
	    try
	    {	    
	    	String fullURL = baseURL.concat("/web_final/VerifyServlet?ver=").concat(URLEncoder.encode(hash, "UTF-8"));
	    	System.out.println(fullURL);
		    EmailVerifier.sendVerficationEmail(email, fName.concat(" ").concat(lName), hash, fullURL);
			Misc.warning(fullURL);
		} 
	    catch (Exception e) 
		{
	    	Misc.error("LoginManager.newUser {" + e.toString() + "}");
		}

		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Removes User.
	 * @param user User Name.
	 */
	public static Response<Boolean> removeUser(String user)
	{
		/* User DNE */
		if (!MemCache.hasUser(user))
			return new Response<Boolean>(Res.MISC_ERROR);
		
		/* You cannot delete Shared Account */
		if (user.equalsIgnoreCase(SharedManager.EMAIL))
			return new Response<Boolean>(Res.MISC_ERROR); 

		MemCache.removeUser(user);
		
		Response<Boolean> r = SaverLoader.removeUser(user);
		
		if (r.getResponse() != Res.ALL_OK)
			return r;
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Checks whether UserName and Password are correct.
	 * 
	 * Returns true if (1) user exists and (2) user password is correct.
	 * 
	 * @param email Email
	 * @param pwd User Password
	 * @return Whether or not UserName and Password are correct.
	 */
	public static boolean verifyUser(String email, String pwd)
	{
		if (!MemCache.hasUser(email))
			return false;
	
		/* Don't allow multiple logins by same user */
		String UID = MemCache.getUID(email);
		
		if (UID != null)
			MemCache.removeUID(UID);
		
		FullUserData u = SaverLoader.getUserData( email, true );
		
		if (!u.isValid())
			return false;
		
		String hash = u.getPassword();
		
		return BCrypt.checkpw(pwd, hash);
	}
	
	/**
	 * Validate an account so login is possible.
	 * 
	 * @param hash This should be a password hash for that account.
	 * @return True if validated. False if hash is wrong.
	 */
	public static Response<Boolean> validateAccount(String hash)
	{
		// Create a Query
	    String queryStr = String.format("select from " + FullUserData.class.getName() + 
	            " where password == '%s'", hash);
	    
		ProfileChange change = new MakeValid();	

		return SaverLoader.makeQueryChanges(queryStr, change);
	}
	
	/**
	 * Recover a password.
	 * 
	 * @param fName First Name
	 * @param lName Last Name
	 * @param phone Phone Number
	 * @param email Email
	 * @return 
	 */
	public static Response<Boolean> recoverPassword(String fName, String lName, String phone, String email)
	{
		/* No Such User */
		if ( !MemCache.hasUser(email) )
			return new Response<Boolean>(Res.BAD_LOGIN_INF);
		
		/* You cannot recover Shared Account */
		if ( email.equalsIgnoreCase(SharedManager.EMAIL))
			return new Response<Boolean>(Res.MISC_ERROR);
		
		/* Get User Data */
		FullUserData u = SaverLoader.getUserData(email, true);
		
		/* Check Information */
		if (!u.getFirstName().equalsIgnoreCase(fName))
			return new Response<Boolean>(Res.MISC_ERROR);
		
		if (!u.getLastName().equalsIgnoreCase(lName))
			return new Response<Boolean>(Res.MISC_ERROR);
		
		if (!u.getPhone().equals(phone))
			return null;
		
		/* Generate new password */
		UUID idOne = UUID.randomUUID();
		String newPwd = idOne.toString();
		
		/* Set new password      */
		String hash = BCrypt.hashpw(newPwd, BCrypt.gensalt());
		
		
		
		
		Misc.warning("LoginManager.recoverPassword { new Password = " + newPwd + " }"); // TODO
		
		
		ProfileChange change = new ChangePassword(hash);
		Response<Boolean> r = SaverLoader.makeChanges( email, change);
		if (r.getResponse() != Res.ALL_OK)
			return r;
		
		EmailVerifier.sendPasswordReset(email, fName.concat(" ").concat(lName), newPwd);
		
		return new Response<Boolean>(Res.ALL_OK);
	}

	/**
	 * Changes the password
	 * 
	 * @param email Email
	 * @param newPwd New Password
	 * @return
	 */
	public static Response<Boolean> changePassword(String email, String newPwd) 
	{
		String hash = BCrypt.hashpw(newPwd, BCrypt.gensalt());
		ProfileChange change = new ChangePassword( hash );
		
		return SaverLoader.makeChanges( email, change);
	}
}
