package cs460.webdnd.client.commands;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;

import cs460.webdnd.client.CState;
import cs460.webdnd.client.mvc.controller.ICommand;
import cs460.webdnd.shared.Response;
import cs460.webdnd.shared.Response.Res;

public class ModifyAccountCommand implements ICommand{
	private CState state;
	private TextItem firstName, lastName, phoneNumber;
	private PasswordItem current, new1, new2;
	
	public ModifyAccountCommand(CState state, TextItem firstName, TextItem lastName, TextItem phoneNumber,
			PasswordItem current, PasswordItem new1, PasswordItem new2){
		this.state = state;
		this.firstName= firstName;
		this.lastName= lastName;
		this.phoneNumber= phoneNumber;
		this.current= current;
		this.new1= new1;
		this.new2=new2;
		
	}

	@Override
	public void run() {
		
		if( firstName.getValueAsString()!=null)
			state.getLoginService().changeFirstName(firstName.getValueAsString(), new ModifyFirstName(firstName.getValueAsString()));
			//new ModifyFirstName(firstName.getValueAsString());
		
		if( lastName.getValueAsString()!=null)
			//new ModifyLastName(lastName.getValueAsString());
			state.getLoginService().changeLastName(lastName.getValueAsString(), new ModifyLastName(lastName.getValueAsString()));
		
		if( phoneNumber.getValueAsString()!=null)
			state.getLoginService().changePhone(phoneNumber.getValueAsString(), new ModifyPhoneNumber(phoneNumber.getValueAsString()));
			//new ModifyPhoneNumber(phoneNumber.getValueAsString());
		
		if( new1.getValueAsString() != null && new1.getValueAsString().equals(new2.getValueAsString()))
			state.getLoginService().changePassword(current.getValueAsString(), new1.getValueAsString(), new ModifyPassword(current.getValueAsString(), new1.getValueAsString()));
			//new ModifyPassword( current.getValueAsString(), new1.getValueAsString());
				
	}

	protected class ModifyFirstName implements AsyncCallback<Response<Boolean>> {
		private String fName;
		public ModifyFirstName(String fName){
			this.fName = fName;
		}
	
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to change first name, server error");
		}
	
		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result.getResponse() != Res.ALL_OK)
				SC.say("Unable to Change the First Name: " + fName);
		}
	}
	
	protected class ModifyLastName implements AsyncCallback<Response<Boolean>> {
		private String lName;
		public ModifyLastName(String lName){
			//new ChangeFirstName(lName);
			this.lName = lName;
		}
	
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to change last name, server error");
		}
	
		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result.getResponse() != Res.ALL_OK)
				SC.say("Unable to Change Last Name: " + lName);
		}
	}
	
	protected class ModifyPhoneNumber implements AsyncCallback<Response<Boolean>> {
		private String phoneNumber;
		public ModifyPhoneNumber(String phoneNumber){
			//new ChangeFirstName(phoneNumber);
			this.phoneNumber = phoneNumber;
		}
	
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to change phone number, server error");
		}
	
		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result.getResponse() != Res.ALL_OK)
				SC.say("Unable to Change Phone Number: " + phoneNumber);
		}
	}
	
	protected class ModifyPassword implements AsyncCallback<Response<Boolean>> {
		//private String oldPassword, newPassword;
		public ModifyPassword(String oldPassword, String newPassword){
			//new ChangePassword(oldPassword, newPassword);
			//this.oldPassword = oldPassword;
			//this.newPassword = newPassword;
		}
	
		@Override
		public void onFailure(Throwable caught) {
			SC.say("Unable to change password, server error");
		}
	
		@Override
		public void onSuccess(Response<Boolean> result) {
			if(result.getResponse() != Res.ALL_OK)
				SC.say("Unable to Change Password, Please Ensure your Current Password is Correct.");
		}
	}
}