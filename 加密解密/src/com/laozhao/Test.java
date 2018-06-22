package com.laozhao;

import com.laozhao.canTranslate.AESUtil;
import com.laozhao.canTranslate.BASE64Util;
import com.laozhao.canTranslate.DESUtil;
import com.laozhao.canTranslate.XORUtil;
import com.laozhao.notTranslate.MD5Util;
import com.laozhao.notTranslate.SHA1Util;

public class Test {
    public static void main(String[] args) {
        int xorNum = 12345;
        String res = "I am the text to be encoded and decoded.";
        String key = "������Կkey";
        System.out.println("-------------------------BASE64--------------------------");
        String base64_encodedStr = BASE64Util.getInstance().encode(res);
        System.out.println("���ܣ�" + base64_encodedStr);
        System.out.println("���ܣ�" + BASE64Util.getInstance().decode(base64_encodedStr));
        System.out.println("-------------------------MD5--------------------------");
        String md5_encodedStr = MD5Util.getInstance().encode(res);
        System.out.println("��������ܣ�" + md5_encodedStr);
        System.out.println("��������ܣ�" + MD5Util.getInstance().encode(md5_encodedStr, key));
        System.out.println("-------------------------SHA1--------------------------");
        String sha1_encodedStr = SHA1Util.getInstance().encode(res);
        System.out.println("��������ܣ�" + sha1_encodedStr);
        System.out.println("��������ܣ�" + SHA1Util.getInstance().encode(sha1_encodedStr, key));
        System.out.println("-------------------------AES--------------------------");
        String aes_encodedStr = AESUtil.getInstance().encode(res, key);
        System.out.println("���ܣ�" + aes_encodedStr);
        System.out.println("���ܣ�" + AESUtil.getInstance().decode(aes_encodedStr, key));
        System.out.println("-------------------------DES--------------------------");
        String des_encodedStr = DESUtil.getInstance().encode(res, key);
        System.out.println("���ܣ�" + des_encodedStr);
        System.out.println("���ܣ�" + DESUtil.getInstance().decode(des_encodedStr, key));
        System.out.println("-------------------------XOR--------------------------");
        String xor_encodedStr = XORUtil.getInstance().encode(res, key);
        System.out.println("�ı����ܣ�" + xor_encodedStr);
        System.out.println("�ı����ܣ�" + XORUtil.getInstance().decode(xor_encodedStr, key));
        int xor_encodedNum = XORUtil.getInstance().code(xorNum, key);
        System.out.println("���ּ��ܣ�" + xor_encodedNum);
        System.out.println("���ֽ��ܣ�" + XORUtil.getInstance().code(xor_encodedNum, key));
    }
}
