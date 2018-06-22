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
 * 使用这个进行 加密解密的时候真的很慢
 */
public class EncrypRSA {
	
	/**
	 * 加密
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
			//Cipher负责完成加密或解密工作，基于RSA  
			///RSA/None/PKCS1Padding  这种模式jdk8是不支持的
			//RSA/ECB/NoPadding
			Cipher cipher = Cipher.getInstance("RSA");
			//根据公钥，对Cipher对象进行初始化
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			byte[] resultBytes = cipher.doFinal(srcBytes);
			return resultBytes;
		}
		return null;
	}
	
	/**
	 * 解密 
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
			//Cipher负责完成加密或解密工作，基于RSA
			Cipher cipher = Cipher.getInstance("RSA");
			//根据公钥，对Cipher对象进行初始化
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
		String msg = "郭XX-精品相声";
		//KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
		//初始化密钥对生成器，密钥大小为1024位
		String password="testkey";
		keyPairGen.initialize(1024,new SecureRandom(password.getBytes()));
		//keyPairGen.initialize(1024);
		//生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		//得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();				
		//得到公钥
		RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
		
		//用公钥加密
		byte[] srcBytes = msg.getBytes();
		byte[] resultBytes = rsa.encrypt(publicKey, srcBytes);
		
		//用私钥解密
		byte[] decBytes = rsa.decrypt(privateKey, resultBytes);
		
		System.out.println("明文是:" + msg);
		System.out.println("公钥加密后是:" + new String(resultBytes));
		System.out.println("私钥解密后是:" + new String(decBytes).trim());
		
		
		//用私钥加密  
		byte[] srcBytes1 = msg.getBytes();
		byte[] resultBytes1 = rsa.encrypt(privateKey, srcBytes1);
				
	    //用公钥解密
	    byte[] decBytes1 = rsa.decrypt(publicKey, resultBytes1);
				
		System.out.println("明文是:" + msg);
		System.out.println("私钥加密后是:" + new String(resultBytes1));
		System.out.println("公钥解密后是:" + new String(decBytes1).trim());
	}

}
