/**
 * This is the main editor page. This will be integrated into the final webpage.
 * @author niallschavez
 */
package cs460.webdnd.client.elements;

import com.google.gwt.core.client.EntryPoint;

import cs460.webdnd.client.CModel;
import cs460.webdnd.client.commands.startup.InitializeCommand;
import cs460.webdnd.client.controllers.InitializeController;
import cs460.webdnd.client.mvc.controller.IController;

public class MainEditor implements EntryPoint {
	@Override public void onModuleLoad() {
		IController cont = new InitializeController(new CModel());
		cont.getModel().run(new InitializeCommand(cont));
	}
}