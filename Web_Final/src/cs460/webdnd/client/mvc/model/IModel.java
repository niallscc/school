package cs460.webdnd.client.mvc.model;

import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.view.IView;

/**
 * IModel contains the running operands for all of the 'Model' of the system.
 * The model contains references to the controllers that are linked to it, as
 * well as the views that have been registered.
 * 
 * The model will also be responsible for maintaining a 'state' for the system
 * for the views to update from.
 * 
 * @author Theodore Schnepper
 */
public interface IModel {
	/**
	 * This method adds a view to the system to draw.
	 * 
	 * @param view
	 *            the view to add for updates
	 */
	void addView(IView view);

	/**
	 * Removes the provided view from the list of views to update.
	 * 
	 * @param view
	 *            the view to remove
	 */
	void removeView(IView view);

	/**
	 * This method simply calls command.run(). Nothing else happens here.
	 * 
	 * @param command
	 *            the command to be run.
	 */
	void run(ICommand command);

	/**
	 * Upon request, the Model will return a reference to it's current State, so
	 * that it may visually updated by the Views.
	 * 
	 * @return the current State of the Model.
	 */
	IState getState();

	/**
	 * This will undo any applicable previously run commands. The model will
	 * maintain a history of 20 most recent 'undoable' commands. The commands
	 * will be undone in reverse order that they were done.
	 */
	void undo();

	/**
	 * This returns whether or not there is anything in the undo stack that can
	 * be undone.
	 * 
	 * @return true if a command is available to be undone, false otherwise
	 */
	boolean canUndo();

	/**
	 * This will redo any commands that have been recently undone. Please note,
	 * That this redo stack will have the same capacity as the undo commands,
	 * and upon receiving a new command to run that's not 'redone', will clear
	 * the redo stack.
	 */
	void redo();

	/**
	 * This returns whether or not there is anything in the redo stack that can
	 * be redone.
	 * 
	 * @return true if a command is available to be redone, false otherwise.
	 */
	boolean canRedo();

	/**
	 * This method signals the model to update the views, this should only be
	 * called by IState to signal a change in state.
	 */
	void updateViews();

	/**
	 * This method is to be used when loading/saving a project. We need to have
	 * some way to clear the command stacks so that the current state is
	 * 'finalized' before continuing. This will ensure that we don't run into
	 * errors and that our commands don't try to undo or redo in a new project.
	 */
	void clearCommandStacks();
}