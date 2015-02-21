package cs460.webdnd.client.commands.startup;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.PrintCanvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.KeyUpEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyUpHandler;
import com.smartgwt.client.widgets.layout.VLayout;

import com.smartgwt.client.widgets.Canvas;
import cs460.webdnd.client.LoginService;
import cs460.webdnd.client.LoginServiceAsync;

import cs460.webdnd.client.commands.CreateFrontPage;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.client.mvc.controller.IController;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

/**
 * @author niallschavez
 * this is the commanderized login, essentially makes sure the user can login 
 * then if life is good, begins the process of starting up the application
 */
public class InitializeLogInCommand implements ICommand {
	protected final LoginServiceAsync loginService = GWT.create(LoginService.class);
	protected Label errorLabel;
	protected Button sendButton, newUser;
	protected DynamicForm dForm;
	protected HorizontalPanel inputFields;
	protected String usr, pwd;
	protected PasswordItem passField;
	protected TextItem userField;
	protected IController cont;
	protected Canvas mainCanvas = new Canvas();
	protected CreateFrontPage fp;
	protected PrintCanvas pc;

	public InitializeLogInCommand(IController cont) {
		this.cont = cont;
	}

	@Override
	public void run() {
		DOM.removeChild(RootPanel.getBodyElement(),DOM.getElementById("loading"));

		TextItem passwordT = new TextItem();
		passwordT.setName("pass");
		passwordT.setVisible(false);

		TextItem usernameT = new TextItem();
		usernameT.setName("user");
		usernameT.setVisible(false);

		Button submitButton = new Button("Submit");
		Button logoutButton = new Button("Logout");
		Button cancelButton = new Button("Cancel");
		cancelButton.addStyleName("sendButton");
		submitButton.addStyleName("sendButton");
		logoutButton.addStyleName("sendButton");

		Label login = new Label("Login:");
		login.setStyleName("loginText-style");

		logoutButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				pwd = dForm.getValueAsString("Password");
				usr = dForm.getValueAsString("Username");

				sendButton.setVisible(true);
				inputFields.setVisible(true);

				passField.clearValue();
				userField.clearValue();

				sendButton.setEnabled(true);

				loginService.logout(logoutAsync);
			}

			AsyncCallback<Response<Boolean>> logoutAsync = new AsyncCallback<Response<Boolean>>() {
				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(Response<Boolean> result) {
				}
			};
		});
		cancelButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				mainCanvas.setVisible(false);

			}

		});

		dForm = new DynamicForm();

		sendButton = new Button("Send");

		VerticalPanel buttons = new VerticalPanel();
		buttons.add(sendButton);

		buttons.add(cancelButton);
		passField = new PasswordItem();
		passField.setName("Password");
		passField.setTitle("Password");
		passField.setRequired(true);

		userField = new TextItem();
		userField.setName("Username");
		userField.setTitle("Username");
		userField.setRequired(true);

		inputFields = new HorizontalPanel();
		dForm.setFields(userField, passField);
		inputFields.add(dForm);

		errorLabel = new Label();

		mainCanvas.addChild(login);
		VLayout v = new VLayout();
		v.setPadding(10);
		v.addMember(inputFields);
		v.setMembersMargin(-20);
		v.addMember(buttons);
		v.addMember(errorLabel);
		mainCanvas.addChild(v);
		mainCanvas.setBackgroundColor("#B8B8B8");
		fp = new CreateFrontPage(mainCanvas, cont);
		pc = fp.getCanvas();
		pc.draw();

		// mainCanvas.draw();

		OldUserHandler handler = new OldUserHandler();

		sendButton.addClickHandler(handler);
		passField.addKeyUpHandler(handler);
		userField.addKeyUpHandler(handler);

		loginService.login(new SessionLogin());

	}

	// Create a handler for the sendButton and nameField
	class OldUserHandler implements ClickHandler, KeyUpHandler {

		/** Fired when user presses Enter */
		@Override
		public void onKeyUp(KeyUpEvent event) {

			if (event.getKeyName().equals("Enter")) {
				sendNameToServer();
			}

		}

		/** Fired when the user clicks on the sendButton. */
		@Override
		public void onClick(ClickEvent event) {
			sendNameToServer();
		}

		/** Send stuff to Server. */
		private void sendNameToServer() {

			errorLabel.setText("");

			if (!dForm.validate()) {
				loginService.login(new SessionLogin());
				return;
			}

			pwd = dForm.getValueAsString("Password");
			usr = dForm.getValueAsString("Username");

			sendButton.setEnabled(false);

			AsyncCallback<Response<Boolean>> asyncLogin = new AsyncLogin();

			loginService.parseLogin(usr, pwd, asyncLogin);
		}
	}

	protected class SessionLogin implements AsyncCallback<Response<Boolean>> {

		@Override
		public void onFailure(Throwable caught) {
			System.err.println("Session Login Failure... ");
		}

		@Override
		public void onSuccess(Response<Boolean> result) {

			if (result.getResponse() == Res.ALL_OK) {
				mainCanvas.destroy();
				pc.destroy();
				cont.getModel().run(new InitializeCommand(cont));
				return;
			}

		}
	}

	protected class AsyncLogin implements AsyncCallback<Response<Boolean>> {

		@Override
		public void onFailure(Throwable caught) {
			// Show the RPC error message to the user
			sendButton.setEnabled(true);
			errorLabel.setText("ERROR: Server Problem!");
		}

		@Override
		public void onSuccess(Response<Boolean> result) // false = no you are already logged in, or you're not in the database
		{
			if (result!=null && result.getResponse()==Res.ALL_OK) {
				mainCanvas.destroy();
				pc.destroy();
				cont.getModel().run(new InitializeCommand(cont));
				// DOM.removeChild(RootPanel.getBodyElement(),
				// DOM.getElementById("loading"));
			} else {
				SC.say("ERROR: Incorrect Username or Password!");
				sendButton.setEnabled(true);
				errorLabel.setText("ERROR: Incorrect Username or Password!");
			}

		}

	}

}