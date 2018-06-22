package com.laozhao.EncrypAES;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/*
 *  �Զ���key
 */
public class EncrypAESWithSelfKey {
  public static void main(String[] args) {
    getKey();
    getKeyByPass();
  }
  /**
  * ���������Կ
  */
  public static void getKey() {
    try {
      KeyGenerator kg = KeyGenerator.getInstance("AES");
      kg.init(128);
      //Ҫ���ɶ���λ��ֻ��Ҫ�޸����Ｔ��128, 192��256
      SecretKey sk = kg.generateKey();
      byte[] b = sk.getEncoded();
      String s = byteToHexString(b);
      System.out.println(s);
      System.out.println("ʮ��������Կ����Ϊ"+s.length()+" ����Ϊ:"+s);
      System.out.println("��������Կ�ĳ���Ϊ"+s.length()*4+" ����Ϊ:"+s);
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      System.out.println("û�д��㷨��");
    }
  }
  /**
  * ʹ��ָ�����ַ���������Կ
  */
  public static void getKeyByPass() {
    //������Կ
    String password="testkey";
    try {
      KeyGenerator kg = KeyGenerator.getInstance("AES");
      // kg.init(128);//Ҫ���ɶ���λ��ֻ��Ҫ�޸����Ｔ��128, 192��256
      //SecureRandom�����ɰ�ȫ��������У�password.getBytes()�����ӣ�ֻҪ������ͬ�����о�һ�����������ɵ���Կ��һ����
      kg.init(128, new SecureRandom(password.getBytes()));
      SecretKey sk = kg.generateKey();
      byte[] b = sk.getEncoded();
      String s = byteToHexString(b);
      System.out.println(s);
      System.out.println("ʮ��������Կ����Ϊ"+s.length());
      System.out.println("��������Կ�ĳ���Ϊ"+s.length()*4);
    }
    catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      System.out.println("û�д��㷨��");
    }
  }
  /**
  * byte����ת��Ϊ16�����ַ���
  * @param bytes
  * @return
  */
  public static String byteToHexString(byte[] bytes) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < bytes.length; i++) {
      String strHex=Integer.toHexString(bytes[i]);
      if(strHex.length() > 3) {
        sb.append(strHex.substring(6));
      } else {
        if(strHex.length() < 2) {
          sb.append("0" + strHex);
        } else {
          sb.append(strHex);
        }
      }
    }
    return sb.toString();
  }
}
