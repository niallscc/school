/**
 * helper for the form creator 
 * @author niallschavez
 */
package cs460.webdnd.client.elements.formCreator;

import com.smartgwt.client.widgets.grid.ListGridRecord;

public class ElementRecord<T> extends ListGridRecord {

	public ElementRecord() {
	}

	public ElementRecord(String elementName, T type) {
		setPartName(elementName);
		typeName(type);

	}

	public void setPartName(String partName) {
		setAttribute("elementName", partName);
	}

	public void typeName(T formType) {
		setAttribute("type", formType);
	}

}