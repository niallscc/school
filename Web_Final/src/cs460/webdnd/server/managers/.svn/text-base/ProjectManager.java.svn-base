package cs460.webdnd.server.managers;
import java.util.LinkedList;

import cs460.webdnd.server.DB.ProfileChange.*;
import cs460.webdnd.server.DB.FullUserData;
import cs460.webdnd.server.DB.SaverLoader;
import cs460.webdnd.shared.Response;

/**
 * Code to manage project selection.
 * 
 * Layer between Services & Servlets and SaverLoader & MemCache.
 */
public class ProjectManager {
	private ProjectManager () {};
	
	/*
	 * =============================================================================================================
	 * PROJECT MANAGEMENT RELATED
	 * =============================================================================================================
	 */
	
	public static Response<Boolean> newProject(String email)
	{
		ProfileChange change = new NewProject();
		return SaverLoader.makeChanges( email, change);
	}
	
	public static Response<Boolean> deleteProject(String email, String project)
	{
		ProfileChange change = new DeleteProject(project);
		return SaverLoader.makeChanges( email, change);
	}
		
	public static Response<Boolean> changeCurrentProject(String email, String project)
	{
		ProfileChange change = new ChangeCurrentProject(project);
		return SaverLoader.makeChanges( email, change);
	}
	
	public static Response<Boolean> changeProjectName(String email, String name)
	{
		ProfileChange change = new ChangeProjectName(name);
		return SaverLoader.makeChanges( email, change);
	}
	
	public static Response<String> getCurrentProjectName(String email)
	{
		FullUserData fud = SaverLoader.getUserData(email, true);
		return new Response<String> ( fud.getCurrentProjectName() );
	}
	
	public static Response<LinkedList<String>> getProjectList(String email)
	{
		FullUserData fud = SaverLoader.getUserData(email, true);
		return new Response<LinkedList<String>> ( fud.getProjectNames() );
	}
}