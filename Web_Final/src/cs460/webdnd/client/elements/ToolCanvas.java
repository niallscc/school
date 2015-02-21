package cs460.webdnd.client.elements;

import com.smartgwt.client.widgets.Canvas;

/***
 * This class is meant to do everything that the normal Canvas can, as well as
 * being able to maintain a reference to a 'selection'. This will be useful for
 * toolbars and the like, as well as the overall workarea.
 * 
 * @author Theodore Schnepper
 * 
 */
public class ToolCanvas extends Canvas {
	private Canvas selection;

	/***
	 * Build the item as it normally does
	 */
	public ToolCanvas() {}

	/***
	 * Change the selection, the input can be null, meaning there is no
	 * selection.
	 * 
	 * @param i
	 *            The Canvas to set as the selection.
	 */
	public void setSelection(Canvas i) {
		this.selection = i;
	}

	/***
	 * Returns the current selection. The returned value can be null meaning
	 * there is no current selection.
	 * 
	 * @return the current Selection of the ToolCanvas.
	 */
	public Canvas getSelection() {
		return selection;
	}
}