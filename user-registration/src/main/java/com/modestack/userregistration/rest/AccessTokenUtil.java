package com.modestack.userregistration.rest;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AccessTokenUtil {

	public String getAccessToken(String password, String email) {
		StringBuilder sb = new StringBuilder();
		sb.append(email);
		sb.append("@");
		sb.append(password);
		sb.append("@");
		sb.append(System.currentTimeMillis());
		String token = null;
		try {

			SecretKey secKey = getSecretEncryptionKey();
			byte[] cipherText = encryptText(sb.toString(), secKey);

			token = new String(DatatypeConverter.printHexBinary(cipherText));
		} catch (Exception e) {
		}
		return token;
	}

	public static SecretKey getSecretEncryptionKey() throws Exception {
		KeyGenerator generator = KeyGenerator.getInstance("AES");
		generator.init(128);
		SecretKey secKey = generator.generateKey();
		return secKey;
	}

	public static byte[] encryptText(String plainText, SecretKey secKey) throws Exception {
		Cipher aesCipher = Cipher.getInstance("AES");
		aesCipher.init(Cipher.ENCRYPT_MODE, secKey);
		byte[] byteCipherText = aesCipher.doFinal(plainText.getBytes());
		return byteCipherText;
	}

}
