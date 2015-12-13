package voogasalad_GucciGames.gameData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 * A class to save/load commands with capability to encrypt commands Used AES
 * encryption & SHA-256 checksum
 * 
 * @author Mike Ma (ym67)
 *
 */
public class FileLoader {
	private static final String KEY = "VOOGASALAD_GUCCI";
	private static final int BLOCK = 32;
	private static final boolean CIPHER = false;

	private Cipher myCipher;
	private SecretKeySpec myKey;

	public FileLoader() {
		try {
			myCipher = Cipher.getInstance("AES");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			e.printStackTrace();
		}
		myKey = new SecretKeySpec(KEY.getBytes(), "AES");
	}

	@SuppressWarnings("static-access")
	public void save(File file, String textToSave) throws Exception {
		OutputStream out = new BufferedOutputStream(new FileOutputStream(file));
		byte[] hash = MessageDigest.getInstance("SHA-256").digest(textToSave.getBytes());
		byte[] text = textToSave.getBytes();
		byte[] input = new byte[hash.length + text.length];
		System.arraycopy(hash, 0, input, 0, hash.length);
		System.arraycopy(text, 0, input, hash.length, text.length);
		out.write(crypto(myCipher.ENCRYPT_MODE, input));
		out.flush();
		out.close();
	}

	@SuppressWarnings("static-access")
	public String read(File file) throws Exception {
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		byte[] input = new byte[(int) file.length()];
		in.read(input);
		in.close();
		byte[] output = crypto(myCipher.DECRYPT_MODE, input);
		String text = new String(output, BLOCK, output.length - BLOCK);
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(output, BLOCK, output.length - BLOCK);
		if (compare(md.digest(), output)) {
			return text;
		} else {
			throw new Exception("Hash failed");
		}
	}

	private boolean compare(byte[] hash, byte[] src) throws Exception {
		for (int i = 0; i < hash.length; i++) {
			if (hash[i] != src[i])
				return false;
		}
		return true;
	}

	private byte[] crypto(int cipherMode, byte[] input) throws Exception {
		myCipher.init(cipherMode, myKey);
		if (CIPHER)
			return myCipher.doFinal(input);
		else
			return input;
	}
}
