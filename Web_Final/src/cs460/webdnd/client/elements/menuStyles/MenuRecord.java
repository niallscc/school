/**
 * @author niallschavez
 * the getters and setters for the different menu options 
 */
package cs460.webdnd.client.elements.menuStyles;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class MenuRecord extends ListGridRecord {
	private String link;

	public MenuRecord(String name, String colorScheme, String creator,
			String createdDate, String styleSheet) {
		setName(name);
		setColorScheme(colorScheme);
		setCreatedDate(createdDate);
		setCreator(creator);
		setStyleSheet(styleSheet);
	}

	public void SetLink(String link) {
		this.link = link;
	}

	private void setStyleSheet(String styleSheet) {
		setAttribute("styleSheet", styleSheet);

	}

	private void setCreator(String creator) {
		setAttribute("creator", creator);

	}

	private void setCreatedDate(String createdDate) {
		setAttribute("date", createdDate);
	}

	private void setColorScheme(String colorScheme) {
		setAttribute("scheme", colorScheme);
	}

	private void setName(String name) {
		setAttribute("name", name);
	}

	public String getLink() {
		return link;
	}

	public String getName() {
		return getAttributeAsString("name");
	}

	public String getColorScheme() {
		return getAttributeAsString("scheme");
	}

	public String getCreator() {
		return getAttributeAsString("creator");
	}

	public String getDate() {
		return getAttributeAsString("date");
	}
}
