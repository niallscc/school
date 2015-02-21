package cs460.webdnd.client.utilities.encryption;

import org.junit.Test;

import cs460.webdnd.client.utilities.compression.Compression;

/**
 * Tests encryption
 * 
 * @author Ian Mallett
 */
public final class TestRSA {
	/** Test basic encryption and decryption */
	@Test
	public final static void encryption() {
		final RSA rsa = new RSA(1024);

		final String message = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.";
		// String message = "Hello my name is bob.  Do you like lemons?";

		final String encrypt, decrypt;
		final boolean compress = false;
		if (compress) {
			// encrypt = compression.compress(rsa.encrypt(message));
			// decrypt = rsa.decrypt(compression.decompress(encrypt));
			encrypt = rsa.encrypt(Compression.compress(message));
			decrypt = Compression.decompress(rsa.decrypt(encrypt));
		} else {
			encrypt = rsa.encrypt(message);
			decrypt = rsa.decrypt(encrypt);
		}
		System.out.println("message   = (" + message.length() + ") \""
				+ message + "\"");
		System.out.println("encrypted = (" + encrypt.length() + ") \""
				+ encrypt + "\"");
		System.out.println("decrypted = (" + decrypt.length() + ") \""
				+ decrypt + "\"");
	}
}