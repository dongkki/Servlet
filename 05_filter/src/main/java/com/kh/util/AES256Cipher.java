package com.kh.util;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class AES256Cipher {

	private final static String secretKey = "test112345bbbbbaaaaa123456789066"; // 32bit
	private  static String IV = secretKey.substring(0, 16); // 16bit

	// ��ȣȭ
	public static String AES_Encode(String str)
			throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();

		SecretKey secureKey = new SecretKeySpec(keyData, "AES");

		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes()));

		byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
		String enStr = new String(Base64.encodeBase64(encrypted));

		return enStr;
	}

	// ��ȣȭ
	public static String AES_Decode(String str)
			throws java.io.UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] keyData = secretKey.getBytes();
		SecretKey secureKey = new SecretKeySpec(keyData, "AES");
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV.getBytes("UTF-8")));

		byte[] byteStr = Base64.decodeBase64(str.getBytes());

		return new String(c.doFinal(byteStr), "UTF-8");
	}

	
	// �Ϲ� ��ȣȭ ����Ҷ� ����Ѵ�. 
	public static void main(String[] args) {
		try {
			String id = "�ȳ��ϼ���? ���� ȫ�浿�Դϴ�.";
			String encodeId = AES256Cipher.AES_Encode(id);
			String decodeId = AES256Cipher.AES_Decode(encodeId);
			System.out.println(id);
			System.out.println(encodeId);
			System.out.println(decodeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}