package cs460.webdnd.client;

import cs460.webdnd.client.commands.startup.InitializeLogInCommand;
import cs460.webdnd.client.controllers.InitializeController;
import cs460.webdnd.client.mvc.controller.IController;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 * 
 * @author niallschavez
 */
public class Web_Final implements EntryPoint {
	@Override public void onModuleLoad() {
		IController cont = new InitializeController(new CModel());
		cont.getModel().run(new InitializeLogInCommand(cont));

		// cont.getModel().run(new InitializeCommand(cont));
	}
}