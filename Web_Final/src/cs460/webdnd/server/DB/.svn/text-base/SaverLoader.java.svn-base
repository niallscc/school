package cs460.webdnd.server.DB;

import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Blob;

import cs460.webdnd.server.DB.ProfileChange.ProfileChange;
import cs460.webdnd.server.helpers.Misc;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Saver Loader interacts with the noSQL database that App Engine uses.
 * In other words, all saving and loading happens in this class.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class SaverLoader {
	
	private static final PersistenceManagerFactory PMF = JDOHelper.getPersistenceManagerFactory("transactions-optional");	
	
	/**
	 * Adds a Persistent Object to the database (FullUserData, FastLookup, etc.)
	 * @param f
	 */
	synchronized public static void addObject(Object f)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		
		try 
		{
			pm.makePersistent(f);
		}
		catch  (Exception ex)
		{				
			Misc.error("SaverLoader.addObject { " + ex + " }");
		}
		finally	
		{	
			pm.close();				
		}
		
	}
	
	/**
	 * Returns the FullUserData object for a specific user.
	 * 
	 * @param userName User Name
	 * @return User Data
	 */
	synchronized public static final FullUserData getUserData (String userName, boolean mustExist)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		FullUserData userData = null;

		try
		{
		    userData = pm.getObjectById(FullUserData.class, userName);
		}
		catch (Exception e)
		{	
			if (mustExist)
				Misc.error("SaverLoader.getUserData {" + e.toString() + "}");
		}
		
		return userData;
	}
	
	/**
	 * Removes user from the Persistence Manager.
	 * Does not remove user from Fast Lookup.
	 * 
	 * @param userName User Name to remove.
	 * @return False if, and only if, there is a problem
	 */
	synchronized public static final Response<Boolean> removeUser (String userName)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
		
		FullUserData userData = null;

		Transaction tx = pm.currentTransaction();
		sleepWhileActive(tx);

		
		try
		{
		    tx.begin();

		    userData = pm.getObjectById(FullUserData.class, userName);
		    
		    if (userData == null)
		    	return new Response<Boolean>(Res.BAD_LOGIN_INF);

		    pm.deletePersistent(userData);

		    tx.commit();
		}
		catch (Exception e)
		{
			Misc.error("SaverLoader.removeUser {" + e.toString() + "}");

			
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		    return new Response<Boolean>(Res.MISC_ERROR);
		}
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Returns a file from an existing project for an existing user.
	 * 
	 * @param email Email
	 * @param fileName Name of the File
	 * @return File, if any
	 */
	public static final Blob getFile (String email, String fileName) 
	{
		FullUserData o = getUserData(email, true);
		
		return o.getCurrentProject().getFile(fileName);
	}
	
	
	/**
	 * Retrieve FullUserData object by email and make changes to it.
	 * 
	 * @param email email
	 * @param change Change that will be made to it.
	 * @return
	 */
	synchronized public static final Response<Boolean> makeChanges( String email, ProfileChange change )
	{
		
		PersistenceManager pm = PMF.getPersistenceManager();
		
		FullUserData userData = null;
		
		Transaction tx = pm.currentTransaction();
		
		Response<Boolean> worked = null;
		
		boolean retried = false;
		
		do /* Execute this code at least once, at most twice */ 
		{
			try
			{
				
			    userData = pm.getObjectById(FullUserData.class, email);
			    
			    tx.begin();
			    
//			    pm.setD(true);
			    userData = pm.detachCopy(userData);
			     
			    worked = change.makeChange(userData); /* This Respose is Sent if Commit is OK */
			    
				pm.makePersistent(userData);
	
			    tx.commit();
			}
			catch (Exception e)
			{
			    if (tx.isActive())
			        tx.rollback();
		        
		        if (!retried) /* If we haven't retried, retry */
		        {
		        	/* Wait */
		        	try { Thread.sleep(250); } catch (InterruptedException e1) { ; }
		        	
		        	retried = true;
		        }
		        else /* We have retried, but still got failure */
		        {
			        Misc.error("SaverLoader.makeChanges (ID) { " + e.toString() + " }");
		        	return new Response<Boolean>(Res.MISC_ERROR);
		        }
			}
		}
		while (retried); /* If we need to retry, try again */
		
		return worked;
	}
	
	
	/**
	 * Make Changes to a FullUserData object by Query instead of by ID.
	 * 
	 * @param queryStr Requested Query.
	 * @param change Change to be made to all objects that satisfy the Query.
	 * @return Returns true if all changes were made.
	 */
	@SuppressWarnings("unchecked")
	synchronized public static Response<Boolean> makeQueryChanges(String queryStr, ProfileChange change)
	{
		List<FullUserData> results = (List<FullUserData>) query(queryStr);
	    
	    /* Parse Results of Query */
	    for (FullUserData fu : results)
	    {
	    	Response<Boolean> resp = makeChanges(fu.getEmail(), change);
	    	if (resp.getResponse() != Res.ALL_OK)
	    		return resp;
	    }
	     
		return new Response<Boolean>(Res.ALL_OK);
	}

	/**
	 * Basic Query.
	 * 
	 * @param queryStr Query String.
	 * @return Object that the Query returns, if any.
	 */
	synchronized public static Object query (String queryStr)
	{
		PersistenceManager pm = PMF.getPersistenceManager();
	    
	    Query q = null;
	    Object results = null;
	    
	    try 
	    {
		    q = pm.newQuery(queryStr);
		    results = q.execute();
	    }
	    catch (Exception e) 
	    {
	    	Misc.error("SaverLoader.query { " + e.toString() + " } ");
	    }
	    
	    if (q == null || results == null)
        	return null;
    	
    	q.closeAll();
    	
		return results;
	}
	
	/**
	 * This should enforce one transaction at a time rule for Threads
	 * 
	 * @param t Current Transaction
	 */
	synchronized private static void sleepWhileActive(Transaction t)
	{
//		try
//		{
//			while ( t.isActive() ) { Thread.sleep(50); }
//		} 
//		catch (InterruptedException e) 
//		{
//			return;
//		}
	}
	
	@Deprecated
	private SaverLoader () {};
}