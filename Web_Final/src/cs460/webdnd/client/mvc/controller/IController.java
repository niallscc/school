package cs460.webdnd.client.mvc.controller;

import cs460.webdnd.client.mvc.model.IModel;

/**
 * A controller is maintained as a medium between the Model and the Commands.
 * The entire purpose of the controller is to pass commands to the Model, by
 * invoking them to be run on the model.
 * 
 * @author Theodore Schnepper
 */
public interface IController {
	/**
	 * Will register a Model to the controller to maintain the reference. It can
	 * only keep one Model per controller.
	 * 
	 * @param model
	 *            The Model to register to the controller
	 */
	void registerModel(IModel model);

	/**
	 * This is included at the moment just in case it is needed... It is
	 * probably a bad idea overall, but ultimately it depends on our needs.
	 * 
	 * @return The Model this controller is referencing
	 */
	IModel getModel();
}