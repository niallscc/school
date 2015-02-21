package cs460.webdnd.server.DB;

import java.util.LinkedList;
import java.util.List;

import javax.jdo.annotations.Element;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * Persistent Object that stores
 * 	(1) User e-mail
 * 	(2) User Password
 *  (3) User First and Last Name
 *  (4) User Phone Number
 * 	(5) Whether or not the account has been validated
 *  (6) User Projects
 * 
 * Note this class has been coded to assume access by Database class.
 * The means that passing in null for anything is not supported and will crash.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
@PersistenceCapable(identityType = IdentityType.APPLICATION, detachable = "true")
public class FullUserData {
	 
	@Element(dependent = "true") /* Project Data Should Not Exist Without FullUserData */
    @Persistent(mappedBy = "user",  defaultFetchGroup = "true")
	private List<ProjectData> projects = new LinkedList<ProjectData>();
	// TODO: What if we just have a List of <Key>? Will this speed things up?
	
	@PrimaryKey
	@Persistent /* email is commonly referred to as userName or user */
	private String email;
	
	@Persistent /* This should be a password hash */
	private String password;
	
	@Persistent /* Phone Number */
	private String phone;
	
	@Persistent /* First Name of account holder */
	private String firstName;
	
	@Persistent /* Last Name of account holder */
	private String lastName;
	
	@Persistent /* The current project that is being worked on */
	private int currentProject;
	
	@Persistent /* Has the account been validated? */
	private boolean isValid;
	
	/**
	 * 
	 * @param email Email registered with
	 * @param password Password hash
	 * @param phone Phone Number
	 * @param firstName First Name of User
	 * @param lastName Last Name of User
	 */
	public FullUserData (String email, String password, String phone, String firstName, String lastName)
	{
		this.email     = email;
		this.password  = password;
		this.phone     = phone;
		this.firstName = firstName;
		this.lastName  = lastName;
		
		this.currentProject = 0;
		projects.add(new ProjectData());
		
		isValid = false;
	}
	
	/* 
	 * ----------------------------------------------------------
	 * START BASIC USER INFORMATION
	 * ----------------------------------------------------------
	 */
	
	/**
	 * @return Has the account been validated?
	 */
	public boolean isValid()
	{
		return isValid;
	}
	
	/**
	 * Validate Account.
	 */
	public void setValid()
	{
		isValid = true;
	}
	
	/**
	 * @return Phone Number.
	 */
	public String getPhone()
	{
		return phone;
	}
	
	/**
	 * @param phone New Phone Number
	 */
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	
	/**
	 * @return User's first name
	 */
	public String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * @param firstName User's new first name
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	/**
	 * @return User's last name.
	 */
	public String getLastName()
	{
		return lastName;
	}
	
	/**
	 * @param lastName User's new last name.
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	/**
	 * @return User's password hash.
	 */
	public String getPassword()
	{
		return password;
	}
	
	/**
	 * @param password New Password hash.
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}
	
	/**
	 * @return User's email address
	 */
	public String getEmail ()
	{
		return email;
	}
	
	/**
	 * @param email User's new email address
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/* 
	 * ----------------------------------------------------------
	 * END BASIC USER INFORMATION
	 * ----------------------------------------------------------
	 */
	
	
	/* 
	 * ----------------------------------------------------------
	 * START PROJECT MANAGEMENT
	 * ----------------------------------------------------------
	 */

	/**
	 * Sets the current project's name.
	 * @param name Name for the current project.
	 * @return True if succeeded.
	 */
	public Response<Boolean> setCurrentProjectName(String name)
	{
		ProjectData pd = projects.get(currentProject);
		
		// Projects Cannot Share the Same Name
		for (ProjectData p : projects)
		{
			if (p.getProjectName().equalsIgnoreCase(name))
				return new Response<Boolean>(Res.DUPLICATE_PROJECT_NAME);
		}
		
		pd.setProjectName(name);
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * Removes a certain project.
	 * @param project Project to remove.
	 * @return True if project removed.
	 */
	public Response<Boolean> removeProject(String project)
	{
		ProjectData rem = null;
		for (ProjectData p: projects)
		{
			if (p.getProjectName().equalsIgnoreCase(project))
			{
				rem = p;
				break;
			}
		}
		
		// No Such Project Found
		if (rem == null)
			return new Response<Boolean>(Res.NO_PROJECT);
		
		projects.remove(rem);
		
		// At least one project must exist
		if (projects.isEmpty())
			projects.add(new ProjectData());
		
		// Reset to default Project
		currentProject = 0;
		
		return new Response<Boolean>(Res.ALL_OK);
	}
	
	/**
	 * @return Name given to the current project.
	 */
	public String getCurrentProjectName()
	{
		return getCurrentProject().getProjectName();
	}
	
	/**
	 * @return The current project itself.
	 */
	public ProjectData getCurrentProject()
	{
		return projects.get(currentProject);
	}

	/**
	 * Sets the current project to work on.
	 * @param project Project Name.
	 * @return If such a project exists and has been set as current.
	 */
	public Response<Boolean> setCurrentProject(String project)
	{
		for (int i = 0; i != projects.size(); i++)
		{
			if  ( projects.get(i).getProjectName().equalsIgnoreCase(project) )
			{
				currentProject = i;
				return new Response<Boolean> ( Res.ALL_OK );
			}
		}
		
		return new Response<Boolean> (Res.NO_PROJECT);
	}
	
	/**
	 * @return true if new project was created.
	 */
	public Response<Boolean> newProject()
	{
		currentProject = projects.size();
		projects.add(new ProjectData());
		
//		new Response<Boolean>(res.);
		
		// TODO: Maximum project count
		
		return new Response<Boolean>( Res.ALL_OK );
	}
	
	/**
	 * Creates a List of project names.
	 * @return List of project names.
	 */
	public LinkedList<String> getProjectNames()
	{
		LinkedList<String> list = new LinkedList<String>();
		
		for ( ProjectData pd : projects )
		{
			list.add( pd.getProjectName() );
		}
		
		return list;
	}
	
	/**
	 * Gets a specific project.
	 * @param project
	 * @return
	 */
	public ProjectData getProject (String project)
	{
		ProjectData rem = null;
		
		for (ProjectData p: projects)
		{
			if (p.getProjectName().equalsIgnoreCase(project))
			{
				rem = p;
				break;
			}
		}
		
		return rem;
	}
	
	/* 
	 * ----------------------------------------------------------
	 * END PROJECT MANAGEMENT
	 * ----------------------------------------------------------
	 */
}