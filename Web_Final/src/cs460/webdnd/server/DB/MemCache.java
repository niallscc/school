package cs460.webdnd.server.DB;

import java.util.List;

import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.memcache.Expiration;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceException;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Manages MemCache.
 * If this errors, there is no recovery.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class MemCache
{		
	/* Mem Cache Manager */
	private static final MemcacheService MEMCACHE = MemcacheServiceFactory.getMemcacheService("DEFAULT");
	
	/**
	 * 
	 * @param file File Name and Path
	 * @param data Data
	 */
	public static final void addFile (String file, Blob data)
	{
		cachePut(file, data, false);
	}
	
	/**
	 * 
	 * @param file File to return, if any
	 * @return
	 */
	public static final Blob getFile (String file)
	{
		Object b = cacheGet(file);
		
		if (b != null && b instanceof Blob)
			return (Blob)b;
		
		return null;
	}
	
	/**
	 * Does the DB has a certain user (via email)?
	 * 
	 * @param email User
	 * @return True if user exists
	 */
	public static final boolean hasUser(String email)
	{
		if ( cacheGet(email) != null )
			return true;
		
		FullUserData fd = SaverLoader.getUserData(email, false);
		
		if (fd != null)
			MemCache.cachePut(email, fd.getPassword(), false);
		
		return fd != null;
	}
	
	/**
	 * Removes User, if user exists.
	 * @param email User to remove
	 */
	public static final void removeUser(String email)
	{
		/* Delete User -> Pass Hash    */
		cacheDelete(email);
		/* Delete File Upload Response */
		cacheDelete("#".concat(email));
		/* Delete Session              */
		cacheDelete("$".concat(email));
	}
	
	/*
	 * ============================================================================
	 * FILE UPLOAD RESPONSE
	 * ============================================================================
	 * 
	 */
	
	/**
	 * Add File Upload Response for a certain user.
	 * One response per user, it stores the latest response only.
	 * @param response Response
	 * @param email User/Email
	 */
	public static final void addFileRes(Response<Boolean> response, String email)
	{
		/* So these Strings would not override email stings */
		String email2 = "#".concat(email);
		
		if (response.getResponse() == Res.ALL_OK)
		{
			/* There is no need to store an ALL_OK response
			 * By default it is ALL_OK
			 * */
			cacheDelete(email2);
		}
		else
		{
			/* Store the error msg. */
			cachePut(email2, response, true);
		}
	}
	
	/**
	 * Response to file upload.
	 * @param email For which user?
	 * @return File Upload Response - ALL_OK if none-exist / all was OK
	 */
	@SuppressWarnings("unchecked")
	public static final Response<Boolean> getFileRes(String email)
	{
		/* We do not want a KEY */
		String email2 = "#".concat(email);
		
		Object m = cacheGet(email2);
		
		if (m == null)
			return new Response<Boolean>(Res.ALL_OK);
		
		return (Response<Boolean>) m;
	}
	
	/*
	 * ============================================================================
	 * SESSION LOGIN
	 * ============================================================================
	 */
	
	/**
	 * Put UID so we can get user from a UID Cookie.
	 * @param UID UID
	 * @param user User
	 */
	public static final void putUID(String UID, String user)
	{
		if (UID == null || user == null) Misc.error("MemCache.putUID { null }");
	
		/* UID -> User */
		cachePut(UID, user, true);
		/* User -> UID */
		cachePut( "$".concat(user), UID, true );
	}
	
	/**
	 * Get UID
	 * @param user User Name
	 * @return
	 */
	public static final String getUID(String user)
	{
		if (user == null)
			return null;
		
		return (String) cacheGet( "$".concat(user) );
	}
	
	/**
	 * Gets a user from a UID, if any
	 * 
	 * @param UID
	 * @return
	 */
	public static final String getUser(String UID)
	{
		if (UID == null)
			return null;
		
		return (String) cacheGet(UID);
	}
	
	/**
	 * Removes a UID associated to a user, if any
	 * Should only be called on logout
	 * @param UID
	 */
	public static final void removeUID(String UID)
	{
		if (UID == null)
			Misc.error("MemCache.removeUID { null } ");
		
		String user = getUser(UID);
		if (user != null)
			cacheDelete( "$".concat(user) );
		
		cacheDelete(UID);
	}
	
	/*
	 * ============================================================================
	 * MISC
	 * ============================================================================
	 */
	
	/**
	 * Does another account exist with same information except different e-mail.
	 * 
	 * @param fName First Name
	 * @param lName Last Name
	 * @param phone Phone Number
	 * @return True if there is another account with same Full Name and Phone Number.
	 */
	public static final boolean hasSimilarUser(String fName, String lName, String phone)
	{
	   String queryStr = String.format("SELECT email FROM " + FullUserData.class.getName()
	    		+ " WHERE phone == '%s' && lastName == '%s' && firstName == '%s'", phone, lName, fName);

	    
	    @SuppressWarnings("unchecked") /* This should not be recoverable */
		List<String> l3 = (List<String>) SaverLoader.query(queryStr);

	    return ! l3.isEmpty();
	}
	
	/*
	 * ============================================================================
	 * MEM CACHE
	 * ============================================================================
	 * 
	 */
	
	/**
	 * Returns object from Mem. Cache.
	 * @param id Email/User
	 * @return Obj
	 */
	synchronized private static final Object cacheGet(String id)
	{
		Object r = null;
		
		try 
		{
			r = MEMCACHE.get(id);
		}
		catch (MemcacheServiceException e) 
		{
			Misc.warning("FastLookup.cacheGet { " + e.toString() + " }");
		
		}
		return r;
	}
	
	/**
	 * Removes from mem cache.
	 * @param id Email
	 */
	synchronized private static final void cacheDelete( String id )
	{
		MEMCACHE.delete(id);
	}
	
	/**
	 * Put object in memory cache.
	 * @param id email address
	 * @param o Key
	 */
	synchronized private static final void cachePut( String id, Object o, boolean time ) 
	{
		try 
		{
			if (time)
				MEMCACHE.put(id, o, Expiration.byDeltaSeconds(DURATION));
			else
				MEMCACHE.put(id, o);
		}
		catch (MemcacheServiceException e) 
		{
			Misc.error("FastLookup.cachePut { " + e.toString() + " }");
		}
	}
	
	/* 12 HR (IN SECONDS)  */
	private static final int DURATION = 60/*sec*/ * 60/*min*/ * 12/*hr*/ * 1/*day*/;
}