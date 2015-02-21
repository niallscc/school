package cs460.webdnd.shared;

import java.io.Serializable;

/**
 * Passes the error if any from Server to Client.
 * This may also pass a file, but this is NOT guaranteed.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class Response<T extends Serializable> implements Serializable {
	/* Response */
	private Res resp = null;
	/* File, may exist depending on Response */
	private T o = null;

	/**
	 * Specific Type of Response.
	 * @param resp
	 */
	public Response (Res resp)
	{
		this.resp = resp;
	}
	
	/**
	 * Constructor for passing an Object to client code.
	 * @param obj
	 */
	public Response (T obj)
	{
		if (obj == null) 					/* Object should NOT be null, this signifies an error */
		{
			this.resp = Res.MISC_ERROR;
		}
		else if (obj instanceof Boolean)    /* Technically one should pass a Boolean, but then do this handles it */
		{
			Boolean resp = (Boolean) obj;
			
			if (resp)
				this.resp = Res.ALL_OK;
			else
				this.resp = Res.MISC_ERROR;
		}
		else                                /* Obj is not Boolean or Null thus is Stored and status is set to ALL_OK */
		{
			this.o = obj;
			this.resp = Res.ALL_OK;
		}
	}
	
	public enum Res
	{
		/** This method requires you to be logged in, but you are not */
		NO_LOGIN {
			@Override
			public String toString(){
				return "You Are Not Currently Logged in.";
			}
		},
		
		/** Did you pass a null or empty string?                     */
		BAD_INPUT {
			@Override
			public String toString(){
				return "Was Expecting Something, But Recieved Null or Empty String.";
			}
		},
		
		/** Either password or e-mail is wrong                        */
		BAD_LOGIN_INF{
			@Override
			public String toString(){
				return "The Login and/or Password is Incorrect.";
			}
		},
		
		/** Account Already Exists with this name                     */
		ACCOUNT_EXISTS {
			@Override
			public String toString(){
				return "Account already exists.";
			}
		},
		
		/** One account per name and phone number only                */
		NO_MULT_ACC {
			@Override
			public String toString()
			{
				return "You already have an account here with a different e-mail.";
			}
		},

		/** Project you requested, doesn't exist                      */
		NO_PROJECT {
			@Override
			public String toString(){
				return "The Requested Project Does Not Exist.";
			}
		},
		
		/** Project Limit */
		CANT_MAKE_PROJECT {
			@Override
			public String toString(){
				return "Project limit reached, you cannot make more projects.";
			}
		},
		
		/** Cannot have two project with the same name */
		DUPLICATE_PROJECT_NAME {
			@Override
			public String toString(){
				return "Project names MUST be different.";
			}
		},

		/** File you requested, doesn't not exist                     */
		NO_FILE {
			@Override
			public String toString(){
				return "The File Requested Does Not Exit.";
			}
		},
				
		/** File exceeds Maximum File Size                            */
		FILE_TOO_LARGE {
			@Override
			public String toString(){
				return "The Specified File Exceeds Your File Limits.";
			}
		},
		
		/** Too many files were uploaded                              */
		TOO_MANY_FILES {
			@Override
			public String toString(){
				return "You have Exceeded the Number of Files you Can Have.";
			}
		},
		
		/** Something else happened                                   */
		MISC_ERROR {
			@Override
			public String toString(){
				return "Unknown Error Occured.";
			}
		},
		
		/** Method you called completed without error                 */
		ALL_OK {
			@Override
			public String toString(){
				return "Everything's Fine.";
			}
		},
		
		BAD_FILE_NAME 
		{
			@Override
			public String toString(){
				return "File must be an image (img.jpg, img.png, etc)";
			}			
		},
	}
	
	
	public Res getResponse()
	{
		return this.resp;
	}
	
	public T getFile()
	{
		return this.o;
	}

	@Deprecated
	public Response(){}

	private static final long serialVersionUID = 2091709902385841069L;
}