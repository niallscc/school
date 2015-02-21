/**
 *  Checks to see what form element is which, its a helper
 *  @author niallschavez
 */

package cs460.webdnd.client.elements.formCreator;

import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.RadioGroupItem;
import com.smartgwt.client.widgets.form.fields.SliderItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

public class FormElementFactory {

	private FormItem i;

	public FormElementFactory(String title) {
		System.out.println("type is:" + title);
		if (title.equalsIgnoreCase("textField"))
			i = new TextItem();
		else if (title.equalsIgnoreCase("TextArea"))
			i = new TextAreaItem();
		else if (title.equalsIgnoreCase("radiobutton"))
			i = new RadioGroupItem();
		else if (title.equalsIgnoreCase("passwordfield"))
			i = new PasswordItem();
		else if (title.equalsIgnoreCase("checkbox"))
			i = new CheckboxItem();
		else if (title.equalsIgnoreCase("submitButton"))
			i = new ButtonItem();
		else
			i = new SliderItem();
	}

	public FormItem getFormElement(String value) {
		i.setTitle(value);
		return i;
	}

}
