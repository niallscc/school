package cs460.webdnd.client.utilities.compression;

import org.junit.Test;

/**
 * Tests for the compressor.
 * 
 * @author Ian Mallett
 */
public final class TestCompression {
	/** Tests basic compression and decompression */
	@Test
	public final static void compress() {
		final String data0 = "I am happy to join with you today in what will go down in history as the greatest demonstration for freedom in the history of our nation.";
		final String data1 = Compression.compress(data0);
		final String data2 = Compression.decompress(data1);
		System.out.println("Original:      (" + data0.length() + ") \"" + data0
				+ "\"");
		System.out.println("Compressed:    (" + data1.length() + ") \"" + data1
				+ "\"");
		System.out.println("Reconstructed: (" + data2.length() + ") \"" + data2
				+ "\"");
	}
}