package cs460.webdnd.client;

import java.util.LinkedList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cs460.webdnd.shared.Response;

/**
 * For working with different projects.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public interface ProjectServiceAsync {
	/**
	 * Returns the name of the current project.
	 * 
	 * @param confirmation
	 *            Name of current project.
	 */
	public void getCurrentProjectName(
			AsyncCallback<Response<String>> confirmation);

	/**
	 * Returns a list of Strings of all the project names. This should always be
	 * at least one in length.
	 * 
	 * @param confirmation
	 *            Names of current projects
	 */
	void getProjects(AsyncCallback<Response<LinkedList<String>>> confirmation);

	/**
	 * Sets the name of the current project.
	 * 
	 * @param name
	 *            Name of the current project.
	 * @param confirmation
	 *            Confirmation.
	 */
	public void setProjectName(String name,
			AsyncCallback<Response<Boolean>> confirmation);

	/**
	 * Sets the current project.
	 * 
	 * @param project
	 *            Project you want to edit.
	 * @param confirmation
	 *            Confirmation.
	 */
	public void setProject(String project,
			AsyncCallback<Response<Boolean>> confirmation);

	/**
	 * Creates a new project.
	 * 
	 * @param confirmation
	 *            Confirmation.
	 */
	public void newProject(AsyncCallback<Response<Boolean>> confirmation);

	/**
	 * Deletes an existing project.
	 * 
	 * @param project
	 *            Project to delete.
	 * @param confirmation
	 *            Confirmation.
	 */
	public void deleteProject(String project,
			AsyncCallback<Response<Boolean>> confirmation);

}