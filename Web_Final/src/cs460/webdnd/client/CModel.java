package cs460.webdnd.client;

import java.util.ArrayList;

import cs460.webdnd.client.elements.WebPage;
import cs460.webdnd.client.mvc.model.AModel;
import cs460.webdnd.client.mvc.model.IState;

/**
 * This is a 'Concrete' Model, and is the base Model for the project.
 * 
 * @author Theodore Schnepper
 */
public class CModel extends AModel {
	private CState state;

	public CModel() {
		this.state = new CState(this);
		state.setPages(new ArrayList<WebPage>());
	}

	@Override
	public IState getState() {
		return state;
	}
}