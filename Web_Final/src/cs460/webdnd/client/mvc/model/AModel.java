package cs460.webdnd.client.mvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IUndoableCommand;
import cs460.webdnd.client.mvc.view.IView;

/***
 * Just an abstract class implementing the underlying methods that are
 * neccessary to the Model. This way we don't have to care about the record
 * keeping of views and the running of commands when we extend the AModel.
 * 
 * @author Theodore Schnepper
 */
public abstract class AModel implements IModel {
	private ArrayList<IView> views = new ArrayList<IView>();
	private LinkedList<IUndoableCommand> commands = new LinkedList<IUndoableCommand>();
	private LinkedList<IUndoableCommand> commandsredo = new LinkedList<IUndoableCommand>();
	private final int undoLength = 20;

	@Override
	public void addView(IView view) {
		views.add(view);
	}

	@Override
	public void removeView(IView view) {
		views.remove(view);
	}

	@Override
	public void run(ICommand command) {
		if (command instanceof IUndoableCommand){
			commands.add((IUndoableCommand) command);
			commandsredo.clear();
		}
		//else
		//	commands.clear();

		if (commands.size() > undoLength)
			commands.remove();

		for (IUndoableCommand c : commandsredo)
			c.finish();

		command.run();
		updateViews();
	}

	@Override
	public void undo() {
		if (commands.size() == 0)
			return;
		IUndoableCommand c = commands.removeLast();
		if (c != null) {
			c.undo();

			if (commandsredo.size() > undoLength)
				commandsredo.remove().finish();

			commandsredo.add(c);
			updateViews();
		}
	}

	@Override
	public boolean canUndo() {
		return commands.size() > 0;
	}

	@Override
	public void redo() {
		if (commandsredo.size() == 0)
			return;
		IUndoableCommand c = commandsredo.removeLast();
		if (c != null) {
			c.run();
			commands.add(c);
			updateViews();
		}
	}

	@Override
	public boolean canRedo() {
		return commandsredo.size() > 0;
	}

	@Override
	public void updateViews() {
		Iterator<IView> i = views.iterator();
		while (i.hasNext()) {
			IView v = i.next();
			v.Update(this.getState());
		}
	}

	@Override
	public void clearCommandStacks() {
		for (IUndoableCommand c : commandsredo) {
			c.finish();
		}
		commandsredo.clear();

		for (IUndoableCommand c : commands) {
			c.finish();
		}
		commands.clear();
	}
}