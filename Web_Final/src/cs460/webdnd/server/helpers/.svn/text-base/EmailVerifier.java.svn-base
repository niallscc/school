package cs460.webdnd.server.helpers;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * For sending user account verification and password retreival email.
 * 
 * @author Alexandre Rogozine (alexandre.rogozine@live.com)
 */
public class EmailVerifier {
	
	/* email must be a registered one - GAE limitation for security / spam */
	private static final String ADMIN_EMAIL = "niallsc@gmail.com";
	/* This is OUR WebCreate Team E-mail                                   */
	private static InternetAddress FROM = null;
	
	/**
	 * Sends a verification email to verify a user account.
	 * 
	 * @param email email address of the new account
	 * @param fullName Full Name of the person
	 * @param hash Password Hash
	 * @param url URL of the server
	 */
	public static final void sendVerficationEmail(String email, String fullName, String hash, String url)
	{
        try 
        {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
           
            String msgBody = getBody(fullName, hash, url);		           	/* MSG BODY          */
            
        	if (FROM == null)												/* FROM THIS ADDRESS */
				FROM = new InternetAddress(ADMIN_EMAIL, "WebCreate Team");
        	
        	InternetAddress TO = new InternetAddress(email, fullName);		/* TO THIS ADDRESS   */
        	
            Message msg = new MimeMessage(session);
            
            msg.setFrom(FROM);
            msg.addRecipient(Message.RecipientType.TO, TO);
            msg.setSubject("Please Verify Your Account");
            msg.setText(msgBody);
            Transport.send(msg);
        } 
        catch (Exception e) 
        {
        	Misc.error("EmailVerifier.sendVerificationEmail { " + e.toString() + " }");
        }
	}
	
	/**
	 * Sends a Password Reset Email
	 * 
	 * @param email
	 * @param fullName
	 * @param passw
	 */
	public static final void sendPasswordReset(String email, String fullName, String passw)
	{
        try 
        {
            Properties props = new Properties();
            Session session = Session.getDefaultInstance(props, null);
           
            String msgBody = getPassBody(fullName, passw);			        /* MSG BODY          */
            
        	if (FROM == null)												/* FROM THIS ADDRESS */
				FROM = new InternetAddress(ADMIN_EMAIL, "WebCreate Team");
        	
        	InternetAddress TO = new InternetAddress(email, fullName);		/* TO THIS ADDRESS   */
        	
            Message msg = new MimeMessage(session);
            
            msg.setFrom(FROM);
            msg.addRecipient(Message.RecipientType.TO, TO);
            msg.setSubject("Please Verify Your Account");
            msg.setText(msgBody);
            Transport.send(msg);
        } 
        catch (Exception e) 
        {
        	Misc.warning("EmailVerifier.sendPasswordReset { " + e.toString() + " }");
        }
		
	}
	
	private static final char NEWLINE = '\n';
	
	/**
	 * Returns Email body for Verification.
	 * 
	 * @param fullName Full Name
	 * @param hash Hash Password
	 * @param url URL of Site
	 * @return Body of the email
	 */
	private static String getBody(String fullName, String hash, String url)
	{
		return "Hello " + fullName + ',' + NEWLINE +
				"To finish your registration, please follow the provided link " + NEWLINE +
				url + NEWLINE + 
				"Thank You," + NEWLINE +
				'\t' + "WebCreate Team"
				; 
	}
	
	/**
	 * Returns Email body for password.
	 * 
	 * @param fullName Full Name
	 * @param passw Password (not hash)
	 * @return Body of the email
	 */
	private static String getPassBody (String fullName, String passw)
	{
		return "Your New Password, " + NEWLINE + passw;
	}
}
