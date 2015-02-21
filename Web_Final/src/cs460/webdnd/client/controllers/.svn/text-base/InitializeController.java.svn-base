package cs460.webdnd.client.controllers;

import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.mvc.model.IModel;

/**
 * This Controller is the initial Controller of the Project. In order to run
 * commands, and to adhere to the full nature of MVC, we needed to run them
 * command by accessing the model via a Controller. As such, This controller was
 * created to run all of the initialization commands, as other controllers are
 * overtly utilized by the user to alter properties of the project.
 * 
 * @author Theodore Schnepper
 */
public class InitializeController implements IController {
	private IModel model;

	public InitializeController(IModel model) {
		this.model = model;
	}

	@Override public void registerModel(IModel model) {
		this.model = model;
	}

	@Override public IModel getModel() {
		return model;
	}
}