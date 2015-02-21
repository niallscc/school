package cs460.webdnd.client.elements.userAccount;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Visibility;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;

import cs460.webdnd.client.commands.ModifyAccountCommand;
import cs460.webdnd.client.mvc.model.IModel;
import cs460.webdnd.client.utilities.Misc;

public class ModifyAccount {
	IModel model;

	public ModifyAccount(IModel model) {
		this.model=model;
		create();
	}

	public void create() {

		final Canvas modifyCanvas = new Canvas();
		modifyCanvas.setBackgroundColor("white");
		modifyCanvas.setTop(40);
		modifyCanvas.setLeft(40);

		final DynamicForm form = new DynamicForm();
		form.setGroupTitle("Modify your account");
		form.setIsGroup(true);
		form.setWidth(300);
		form.setHeight(120);
		form.setNumCols(2);
		form.setColWidths(60, "*");
		form.setPadding(5);
		form.setCanDragResize(true);
		form.setResizeFrom("R");
		
		final TextItem firstName = new TextItem();
		Misc.getCState(model).getLoginService().getFirstName(new GetFirstNameCallBack(firstName));
		firstName.setColSpan(2);
		firstName.setWidth("*");
		firstName.setTitle("First Name");
		
		final TextItem lastName = new TextItem();
		Misc.getCState(model).getLoginService().getLastName(new GetFirstNameCallBack(lastName));

		lastName.setColSpan(2);
		lastName.setWidth("*");
		lastName.setTitle("Last Name");
		
		final TextItem phoneNumber = new TextItem();
		Misc.getCState(model).getLoginService().getPhoneNumber(new GetPhoneNumberCallBack(phoneNumber));

		phoneNumber.setColSpan(2);
		phoneNumber.setWidth("*");
		phoneNumber.setTitle("Phone Number");
		
		final PasswordItem currentPassword = new PasswordItem();

		currentPassword.setColSpan(2);
		currentPassword.setWidth("*");
		currentPassword.setTitle("Current Password");
		
		final PasswordItem newPassword1 = new PasswordItem();

		newPassword1.setColSpan(2);
		newPassword1.setWidth("*");
		newPassword1.setTitle("New Password");
		
		final PasswordItem newPassword2 = new PasswordItem();

		newPassword2.setColSpan(2);
		newPassword2.setWidth("*");
		newPassword2.setTitle("New Password");
		
		/********************
		 * Submit operation *
		 ********************/
		ButtonItem submit= new ButtonItem();
		submit.setTitle("Submit");
		submit.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				model.run(new ModifyAccountCommand(Misc.getCState(model),firstName, lastName, phoneNumber,
						currentPassword, newPassword1, newPassword2));
				
			}
		});
		
		/********************
		 * Cancel operation *
		 ********************/
		ButtonItem cancel = new ButtonItem();
		cancel.setTitle("Cancel");
		cancel.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				modifyCanvas.setVisibility(Visibility.HIDDEN);
			}
		});

		form.setFields(firstName,lastName, phoneNumber, currentPassword, 
				newPassword1, newPassword2, submit, cancel);

		modifyCanvas.addChild(form);
		Misc.getCState(model).getMainCanvas().addChild(modifyCanvas);
		
	}
	
	private class GetFirstNameCallBack implements AsyncCallback<String> {
		TextItem fName;
		public GetFirstNameCallBack(TextItem fName){
			this.fName= fName;
		}
		@Override
		public void onFailure(Throwable caught) {}

		@Override
		public void onSuccess(String result) {
			fName.setValue(result);
		}
		
	}
	private class GetPhoneNumberCallBack implements AsyncCallback<String> {
		TextItem lName;
		public GetPhoneNumberCallBack(TextItem lName){
			this.lName= lName;
		}
		@Override
		public void onFailure(Throwable caught) {}

		@Override
		public void onSuccess(String result) {
			lName.setValue(result);
		}
	}
}