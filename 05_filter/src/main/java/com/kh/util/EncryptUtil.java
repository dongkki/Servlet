package com.kh.util;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptUtil {
	/*
	 * ��ȣȭ�� ���⼺ 1) �ܹ��� : ��ȣȭ�� ���� �ٽ� ��ȣȭ �� �� ���� ��ȣȭ ����̴�. (SHA2(Secure Hash
	 * Algorithm 2), MD5(Message-Digest Algorithm 5)) 2) ����� : ��ȣȭ�� ���� �ٽ� ��ȣȭ �� ��
	 * �ִ� ��ȣȭ ����̴�. 2-1) ��ĪŰ(�� ����Ű) ��� : ��/��ȣȭ�� ����ϴ� Ű�� �����ϴ�. (AES(), DES()) - ���� :
	 * �ӵ��� ������. - ���� : Ű�� �����ϴ� �������� ����� ���ɼ��� ����.(Ű�� ����Ǹ� ��...) 2-2) �� ��ĪŰ(����Ű) ��� :
	 * ��/��ȣȭ�� ����ϴ� Ű�� ���� �ٸ���. (RSA()) - ���� : ��/��ȣȭ Ű�� �ٸ��� ������ ���� �������� ����Ǿ ��ȣȭ �� ��
	 * ����. - ���� : �ӵ��� ������.
	 * 
	 * SHA2(Secure Hash Algorithm 2) 1) SHA-256 - SHA-256�� ������ ���� �޽����� 256��Ʈ(32����Ʈ)��
	 * ���� �޽����� ������ �ؽ� �˰����̴�. - ���� �о߿��� ä���Ͽ� ����ϰ� �ִ� ��ȣȭ ���. ��� �ӵ��� �����ٴ� ������
	 * �����ִ�. 2) SHA-512 - SHA-512�� ������ ���� �޽����� 512��Ʈ(64����Ʈ)�� ���� �޽����� ������ �ؽ�
	 * �˰����̴�. 3) ��ȣȭ �ؽ� �Լ��� �������� ��ȭ�ϱ� ���� �߰��� ��Ʈ��Ī, ��Ʈ 2���� ����� ����Ѵ�.
	 * 
	 */

	// �ܹ��� ��ȣȭ(MD2, MD5, SHA, SHA-1, SHA-256, SHA-512�� �����Ѵ�.)
	public static String oneWayEnc(String message) {
		String encMessage = "";
		String algorithm = "SHA-256";

		try {
			// �޽��� ��������Ʈ��, �Է� ���� ���̿� ������� �������� ������ �׻� ������ ������ �ؽ� ���� ������.
			MessageDigest md = MessageDigest.getInstance(algorithm);

			// ��(Message)�� byte[] ��ȯ�Ͽ� �޽��� ��������Ʈ�� �����Ѵ�.
			md.update(message.getBytes(Charset.forName("UTF-8")));

			// ��ȣȭ ���Ŀ� �°� �����Ͽ� byte[]�� ��ȯ�ϴ� �޼ҵ�
			byte[] digest = md.digest();

			// Base64�� ����Ͽ� byte ������ �����͸� ���ڿ��� Encoding �Ѵ�.
			// - Base64�� Binary Data�� Text�� �ٲٴ� Encoding(binary-to-text encoding schemes)��
			// �ϳ���
			// Binary Data�� Character Set�� ������ ���� �ʴ� ASCII ������ ���ڷθ� �̷���� ���ڿ��� �ٲٴ�
			// Encoding�̴�.
			// - Binary Data�� 6bit �� �ڸ� �� 6bit�� �ش��ϴ� ���ڸ� Base64 ����ǥ���� ã�Ƽ� ġȯ�Ѵ�.
			encMessage = Base64.getEncoder().encodeToString(digest);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return encMessage;
	}

	// ��й�ȣ�� DB ������ �� �����.
	public static void main(String[] args) {
		String hashcde = EncryptUtil.oneWayEnc("test1235");
		System.out.println(hashcde);
	}

}