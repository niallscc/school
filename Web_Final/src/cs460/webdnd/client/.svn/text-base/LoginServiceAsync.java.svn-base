package cs460.webdnd.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import cs460.webdnd.shared.Response;

/**
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 * 
 *         Handles logging in, creating new user, changing password, etc.
 */
public interface LoginServiceAsync {
	/**
	 * Login with specific Password and User Name.
	 * 
	 * @param usr
	 *            User Name.
	 * @param pwd
	 *            Password.
	 * @param callback
	 *            Returns true if logged in; false if Password or UserName is
	 *            wrong.
	 */
	void parseLogin(String usr, String pwd, AsyncCallback<Response<Boolean>> callback);

	/**
	 * New user registration.
	 * 
	 * @param email
	 *            User Name (and a valid email)
	 * @param pwd
	 *            Password
	 * @param fName
	 *            First Name
	 * @param lName
	 *            Last Name
	 * @param phone
	 *            Phone Number
	 * @param callback
	 *            Returns true if new user created; false means user name is
	 *            taken.
	 */
	void newUser(String email, String pwd, String fName, String lName, String phone, AsyncCallback<Response<Boolean>> callback);

	/**
	 * Change Password.
	 * 
	 * @param newPwd
	 *            New Password.
	 * @param oldPwd
	 * 			  Old Password.
	 * @param callback
	 *            True if password changed.
	 */
	void changePassword(String newPwd, String oldPwd, AsyncCallback<Response<Boolean>> callback);

	/**
	 * Logs user out.
	 * 
	 * @param callback
	 *            True if logged out.
	 */
	void logout(AsyncCallback<Response<Boolean>> callback);

	/**
	 * Automatic Log-in.
	 * 
	 * @param callback
	 *            True if automatically logged in.
	 */
	void login(AsyncCallback<Response<Boolean>> callback);

	/**
	 * Delete user account of currently logged in user.
	 * 
	 * @param callback
	 *            True if account deleted.
	 */
	void deleteUserAccount(AsyncCallback<Response<Boolean>> callback);

	/**
	 * Change Phone
	 * 
	 * @param phone
	 * @param callback
	 */
	void changePhone(String phone, AsyncCallback<Response<Boolean>> callback);

	/**
	 * Change First Name
	 * 
	 * @param fName
	 * @param callback
	 */
	void changeFirstName(String fName, AsyncCallback<Response<Boolean>> callback);

	/**
	 * Change Last Name
	 * 
	 * @param lName
	 * @param callback
	 */
	void changeLastName(String lName, AsyncCallback<Response<Boolean>> callback);

	/**
	 * 
	 * @param fName
	 * @param lName
	 * @param phone
	 * @param email
	 * @return
	 */
	void recoverPassword(String fName, String lName, String phone, String email, AsyncCallback<Response<Boolean>> callback);
	
	void getFirstName(AsyncCallback<String> firstName);
	void getLastName(AsyncCallback<String> firstName);
	void getPhoneNumber(AsyncCallback <String> phoneNumber);
}