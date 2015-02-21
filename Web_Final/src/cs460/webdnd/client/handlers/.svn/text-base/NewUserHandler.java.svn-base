/**
 * @author niallschavez
 */
package cs460.webdnd.client.handlers;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;

import cs460.webdnd.client.LoginServiceAsync;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.client.LoginService;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

public class NewUserHandler implements ClickHandler {
	LoginServiceAsync loginService = GWT.create(LoginService.class);
	TextBox firstName, lastName, phoneNumber, email, captcha;
	PasswordTextBox password1, password2;
	Canvas mainCanvas;
	IController cont;
	Button submit;
	Label errorLabel = new Label("");

	public NewUserHandler(TextBox email, TextBox firstName, TextBox lastName,
			TextBox phoneNumber, PasswordTextBox password1,
			PasswordTextBox password2, /* TextBox captcha, */
			Canvas mainCanvas, Button submit, IController cont) {

		this.password1 = password1;
		this.password2 = password2;
		// this.captcha = captcha;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.mainCanvas = mainCanvas;
		this.submit = submit;
		this.cont = cont;
	}

	@Override
	public void onClick(ClickEvent event) {
		System.err.println("Click!!!");

		//submit.setEnabled(false);
		mainCanvas.blur();
		String firstName, lastName, phoneNumber, email, password1, password2;
		firstName = this.firstName.getValue();
		lastName = this.lastName.getValue();
		phoneNumber = this.phoneNumber.getValue();
		phoneNumber= phoneNumber.replaceAll("[^\\d]", "");
		System.out.println("phone number is: "+ phoneNumber);
		email = this.email.getValue();
		password1 = this.password1.getValue();
		password2 = this.password2.getValue();
		boolean error = false;

		if (password1.equals(null)) {
			SC.say("First password is empty");
			error = true;
		} else if (password2.equals(null)) {
			SC.say("Second password is empty");
			error = true;
		} else if (firstName==null ) {
			SC.say("First name cannot be null");
			error = true;
		} else if (lastName == null) {
			SC.say("name can only be alpha numeric, no spaces");
			error = true;
		} else if (phoneNumber.length()!= 7 && phoneNumber.length()!= 10 ) {
			SC.say("Phone number must be of form 5051234567 "+ phoneNumber.length());
			error = true;
			
		} else if  (!email.matches(".+@.+\\.[a-z]+")) {
			SC.say("Email is not valid please try again");
			error = true;
		}


		if (password1.length() < 7 || password2.length() < 7) {
			SC.say("Password needs to be 7 characters long");
			error = true;
		}
		if (!error) {
			if (password1.equals(password2)){
				loginService.newUser(email, password1, firstName, lastName,
						phoneNumber, /* captcha.getValue(), */newUserAsync);
				submit.setEnabled(false);
			}
			else
				SC.say("Passwords do not match");
		}
	}

	AsyncCallback<Response<Boolean>> newUserAsync = new AsyncCallback<Response<Boolean>>() {
		@Override
		public void onFailure(Throwable caught) {
		}

		@Override
		public void onSuccess(Response<Boolean> result) {
			if (result.getResponse() == Res.ALL_OK) {
				SC.say("Please check your email to verify your account.");
			} else {
				errorLabel.setText("Username already taken!");
			}
		}
	};
}