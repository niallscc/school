package cs460.webdnd.client.mvc.controller;

/**
 * The IUndoableCommand is meant to do everything that the ICommand does, with
 * the expection of being able to be undone. For this reason, this
 * IUndoableCommand requires an 'Undo' method. Please ensure that the undo is
 * the inverse of the run method. Try to make sure that all of the modifications
 * based on the command are done with relative changes from the base case,
 * rather than the absolute change where applicable.
 * 
 * @author Theodore Schnepper
 */
public interface IUndoableCommand extends ICommand {
	/***
	 * All commands must be able to be 'Undone' potentially.
	 * 
	 * This will be rather interesting to implement.
	 */
	void undo();

	/***
	 * finish is optional but is included to be helpful for those commands that
	 * allow for the destruction/restoration of elements, while still keeping
	 * their properties.
	 */
	void finish();
}