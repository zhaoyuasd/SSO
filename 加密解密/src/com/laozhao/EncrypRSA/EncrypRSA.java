package com.laozhao.EncrypRSA;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/*
 * ʹ��������� ���ܽ��ܵ�ʱ����ĺ���
 */
public class EncrypRSA {
	
	/**
	 * ����
	 * @param publicKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	protected byte[] encrypt(Key publicKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		if(publicKey!=null){
			//Cipher������ɼ��ܻ���ܹ���������RSA  
			///RSA/None/PKCS1Padding  ����ģʽjdk8�ǲ�֧�ֵ�
			//RSA/ECB/NoPadding
			Cipher cipher = Cipher.getInstance("RSA");
			//���ݹ�Կ����Cipher������г�ʼ��
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}
	
	/**
	 * ���� 
	 * @param privateKey
	 * @param srcBytes
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	protected byte[] decrypt(Key privateKey,byte[] srcBytes) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
		if(privateKey!=null){
			//Cipher������ɼ��ܻ���ܹ���������RSA
			Cipher cipher = Cipher.getInstance("RSA");
			//���ݹ�Կ����Cipher������г�ʼ��
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}

	/**
	 * @param args
	 * @throws NoSuchAlgorithmException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws InvalidKeyException 
	 */
	public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
		EncrypRSA rsa = new EncrypRSA();
		String msg = "��XX-��Ʒ����";
		//KeyPairGenerator���������ɹ�Կ��˽Կ�ԣ�����RSA�㷨���ɶ���
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		//��ʼ����Կ������������Կ��СΪ1024λ
		String password="testkey";
		keyPairGen.initialize(1024,new SecureRandom(password.getBytes()));
		//keyPairGen.initialize(1024);
		//����һ����Կ�ԣ�������keyPair��
		KeyPair keyPair = keyPairGen.generateKeyPair();
		//�õ�˽Կ
		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();				
		//�õ���Կ
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		
		//�ù�Կ����
		byte[] srcBytes = msg.getBytes();
		byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);
		
		//��˽Կ����
		byte[] decBytes = rsa.decrypt(privateKey, resultBytes);
		
		System.out.println("������:" + msg);
		System.out.println("��Կ���ܺ���:" + new String(resultBytes));
		System.out.println("˽Կ���ܺ���:" + new String(decBytes).trim());
		
		
		//��˽Կ����  
		byte[] srcBytes1 = msg.getBytes();
		byte[] resultBytes1 = rsa.encrypt(privateKey, srcBytes1);
				
	    //�ù�Կ����
	    byte[] decBytes1 = rsa.decrypt(publicKey, resultBytes1);
				
		System.out.println("������:" + msg);
		System.out.println("˽Կ���ܺ���:" + new String(resultBytes1));
		System.out.println("��Կ���ܺ���:" + new String(decBytes1).trim());
	}

}
