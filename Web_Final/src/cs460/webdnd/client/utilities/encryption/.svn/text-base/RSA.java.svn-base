package cs460.webdnd.client.utilities.encryption;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * RSA cryptographic system. Was created to make secure network traffic, but it
 * doesn't seem to have ever been actually needed. I adapted this code from many
 * sources I found online. All of them had security holes or flat-out didn't
 * work for some inputs (especially large inputs). This module should work for
 * all input sizes and should be fairly resistant to any polynomial-time
 * algorithm.
 * 
 * @author Ian Mallett
 */
public final class RSA {
	private final static transient BigInteger one = new BigInteger("1");
	private final static transient SecureRandom random = new SecureRandom();

	private final transient BigInteger key_private;
	private final transient BigInteger key_public;
	private final transient BigInteger modulus;

	private final int max_msg_len;

	/**
	 * Constructor a a RSA encryptor/disencryptor. Security continent on Java's
	 * "BigInteger BigInteger.probablePrime(...)".
	 * 
	 * @param bits
	 *            the number of bits to encrypt with. 4096 should be basically
	 *            uncrackable even if each of the subatomic particles in the
	 *            universe could check a single solution every planck time from
	 *            the beginning of the universe to its end.
	 */
	public RSA(int bits) {
		final BigInteger p = BigInteger.probablePrime(bits / 2, random);
		final BigInteger q = BigInteger.probablePrime(bits / 2, random);
		final BigInteger phi = (p.subtract(one)).multiply(q.subtract(one));

		modulus = p.multiply(q);
		key_public = new BigInteger("65537");
		key_private = key_public.modInverse(phi);

		max_msg_len = bits / 8 / 3;
	}

	private final BigInteger encrypt(final BigInteger data) {
		return data.modPow(key_public, modulus);
	}

	private final BigInteger decrypt(final BigInteger data) {
		return data.modPow(key_private, modulus);
	}

	/**
	 * Encrypt a string of data.
	 * 
	 * @param data
	 *            the data
	 * @return the encrypted string
	 */
	public final String encrypt(final String data) {
		final StringBuilder result = new StringBuilder();
		final byte[] bytes = data.getBytes();
		int i = 0;
		while (i < bytes.length) {
			final StringBuilder block = new StringBuilder();
			for (int j = 0; j < max_msg_len && i < bytes.length; ++j, ++i) {
				block.append(String.format("%03d", bytes[i]));
			}
			final String block2 = block.toString();

			final String block3 = bigintToStr(encrypt(new BigInteger(block2)));
			// System.out.println("Block out: \""+block3+"\"");

			result.append(block3);
			result.append("-");

		}
		result.deleteCharAt(result.length() - 1);
		return result.toString();
	}

	/**
	 * Decrypt a string of data
	 * 
	 * @param data
	 *            the data
	 * @return the decrypted string
	 */
	public final String decrypt(final String data) {
		final StringBuilder result = new StringBuilder();
		final String[] data2 = data.split("-");
		for (String block : data2) {
			String temp = decrypt(strToBigint(block)).toString();
			while (temp.length() % 3 != 0)
				temp = "0" + temp;
			// System.out.println("Block in: \""+temp+"\"");
			final byte[] bytes = temp.getBytes();
			for (int i = 0; i < bytes.length; i += 3) {
				result.append((char) (100 * (bytes[i] - '0') + 10
						* (bytes[i + 1] - '0') + (bytes[i + 2] - '0')));
			}
		}
		return result.toString();
	}

	private final static String bigintToStr(final BigInteger bi) {
		final StringBuilder result = new StringBuilder();
		byte[] block3 = bi.toByteArray();
		if (block3.length % 2 != 0) {
			final byte[] block4 = new byte[block3.length + 1];
			for (int i = 0; i < block3.length; ++i)
				block4[i + 1] = block3[i];
			block3 = block4;
		}
		// System.out.print("OUTPUT: ");
		for (int i = 0; i < block3.length; i += 2) {
			final byte b1 = (byte) (block3[i] - (byte) ('0'));
			final byte b2 = (byte) (block3[i + 1] - (byte) ('0'));
			final int i1 = (b1 << 8) & 0xFF00;
			final int i2 = b2 & 0x00FF;
			final int i3 = i1 | i2;
			final char c = (char) (i3);
			// System.out.println(String.format("Output Data: %02x %02x -> %04x (%c), %x %x %x",b1,b2,(int)c,c,
			// i1,i2,i3 ));
			// System.out.print(String.format("%08x(%02x%02x) ",(int)c,b1,b2));
			result.append(c);
		}
		// System.out.println(Arrays.toString(block3));
		// System.out.println(bi);

		return result.toString();
	}

	private final static BigInteger strToBigint(final String str) {
		final byte[] bytes = new byte[str.length() * 2];
		int i = 0;
		// System.out.print("INPUT:  ");
		for (final char c : str.toCharArray()) {
			final byte b1 = (byte) ((c >> 8) & 0xFF);
			final byte b2 = (byte) (c & 0xFF);
			// System.out.println(String.format("Input Data: %04x (%c) -> %02x %02x",(int)c,c,b1,b2));
			// System.out.print(String.format("%08x(%02x%02x) ",(int)c,b1,b2));
			bytes[i++] = (byte) (b1 + (byte) ('0'));
			bytes[i++] = (byte) (b2 + (byte) ('0'));
		}
		// System.out.println(Arrays.toString(bytes));
		// System.out.println(new BigInteger(bytes));
		return new BigInteger(bytes);
	}
}