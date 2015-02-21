package cs460.webdnd.client.utilities.compression;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import cs460.webdnd.client.utilities.Misc;

/**
 * A compression stream. Was created to make the rendered webpages take up less
 * space on the server, but it doesn't seem to have ever been actually needed.
 * 
 * @author Ian Mallett
 */
public final class Compression {
	private final static byte[] buf = new byte[1024];

	/**
	 * Compresses the given input. If there are errors, they may fail silently.
	 * 
	 * @param data
	 *            the input
	 * @return the compressed data stream.
	 */
	public final static String compress(final String data) {
		final Deflater compresser = new Deflater();
		try {
			compresser.setInput(data.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			Misc.warn("Cannot encode data into UTF-8!",
					Misc.MODULE.MODULE_COMPRESS);
		}
		compresser.finish();

		final StringBuilder builder = new StringBuilder();

		final ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
		while (!compresser.finished()) {
			final int numof_compressed_bytes = compresser.deflate(buf);
			ba_os.write(buf, 0, numof_compressed_bytes);
			for (int i = 0; i < numof_compressed_bytes; ++i) {
				builder.append((char) (buf[i]));
			}
		}

		try {
			ba_os.close();
		} catch (IOException ioe) {
		}

		return builder.toString();
	}

	/**
	 * Decompresses a compressed string. The data should have already been
	 * encoded with the "String .compress(...)" method. In the event of an
	 * invalid input, a warning will be created, and subsequent problems may be
	 * silently ignored.
	 * 
	 * @param data
	 *            the compressed data
	 * @return the uncompressed data
	 */
	public final static String decompress(final String data) {
		final int compressed_length = data.length();

		final Inflater decompresser = new Inflater();

		final byte[] data2 = new byte[compressed_length];
		for (int i = 0; i < compressed_length; ++i) {
			data2[i] = (byte) (data.charAt(i));
		}

		decompresser.setInput(data2, 0, compressed_length);

		final ByteArrayOutputStream ba_os = new ByteArrayOutputStream();
		while (!decompresser.finished()) {
			try {
				final int numof_decompressed_bytes = decompresser.inflate(buf);
				ba_os.write(buf, 0, numof_decompressed_bytes);
			} catch (DataFormatException e) {
				Misc.warn("Data is malformed and cannot be decompressed!",
						Misc.MODULE.MODULE_COMPRESS);
				break;
			}
		}
		decompresser.end();

		try {
			ba_os.close();
		} catch (IOException ioe) {
		}

		return ba_os.toString();
	}
}