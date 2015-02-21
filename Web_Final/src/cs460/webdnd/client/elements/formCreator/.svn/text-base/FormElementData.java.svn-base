/**
 * another helper for the fomr creator 
 * @author niallschavez
 */

package cs460.webdnd.client.elements.formCreator;

import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class FormElementData {

	@SuppressWarnings("rawtypes")
	private ElementRecord[] records;

	@SuppressWarnings("rawtypes")
	public ElementRecord[] getRecords() {
		if (records == null) {
			records = getNewRecords();
		}
		return records;
	}

	@SuppressWarnings("rawtypes")
	public ElementRecord[] getNewRecords() {
		return new ElementRecord[] {
				new ElementRecord<TextItem>("TextField", new TextItem()),
				new ElementRecord<PasswordItem>("PasswordField",
						new PasswordItem()),
				new ElementRecord<TextAreaItem>("TextArea", new TextAreaItem()),
				new ElementRecord<CheckboxItem>("CheckBox", new CheckboxItem()),
				new ElementRecord<RadioGroupItem>("RadioButton",
						new RadioGroupItem()),
				new ElementRecord<ButtonItem>("SubmitButton", new ButtonItem()), };
	}

	@SuppressWarnings("rawtypes")
	public <T> ElementRecord addRecord(String title, T type) {
		return new ElementRecord<T>(title, type);
	}

}