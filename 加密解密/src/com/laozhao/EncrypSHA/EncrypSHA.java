package com.laozhao.EncrypSHA;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncrypSHA {
	
	public byte[] eccrypt(String info) throws NoSuchAlgorithmException{
		MessageDigest md5 = MessageDigest.getInstance("SHA");
		byte[] srcBytes = info.getBytes();
		//ʹ��srcBytes����ժҪ
		md5.update(srcBytes);
		//��ɹ�ϣ���㣬�õ�result
		byte[] resultBytes = md5.digest();
		return resultBytes;
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException {
		String msg = "��XX-��Ʒ��������";
		EncrypSHA sha = new EncrypSHA();
		byte[] resultBytes = sha.eccrypt(msg);
		System.out.println("�����ǣ�" + msg);
		System.out.println("�����ǣ�" + new String(resultBytes));
		
	}

}

