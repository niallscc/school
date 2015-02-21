package cs460.webdnd.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import cs460.webdnd.shared.Response;

/**
 * See loginServiceAsync for help.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
@RemoteServiceRelativePath("LoginService")
public interface LoginService extends RemoteService {

	Response<Boolean> login();

	Response<Boolean> logout();

	Response<Boolean> parseLogin(String usr, String pwd);

	Response<Boolean> newUser(String email, String pwd, String fName,
			String lName, String phone);

	Response<Boolean> deleteUserAccount();

	Response<Boolean> changePassword(String mewPwd, String oldPwd);

	Response<Boolean> changePhone(String phone);

	Response<Boolean> changeFirstName(String fName);

	Response<Boolean> changeLastName(String lName);

	Response<Boolean> recoverPassword(String fName, String lName, String phone,
			String email);

	String getFirstName();

	String getLastName();
	
	String getPhoneNumber();
}