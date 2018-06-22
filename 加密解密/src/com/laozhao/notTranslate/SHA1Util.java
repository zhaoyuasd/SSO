package com.laozhao.notTranslate;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.laozhao.canTranslate.BASE64Util;

/**
 * ʹ�� SHA1 �������м���<br/>
 * SHA1���������ǲ�����ģ����ܽ��ܣ�Ҫ����ܾͱ���ʹ�ñ�������<br/>
 * <p/>
 * �����е� res ������ԭʼ������<br/>
 * �����е� key ��������Կ���������д<br/>
 */
public class SHA1Util {
    public static final String SHA1 = "SHA1";
    public static final String HmacSHA1 = "HmacSHA1";
    public static final String charset = null; // �����ʽ��Ĭ��nullΪGBK

    private static SHA1Util instance;

    private SHA1Util() {
    }

    // ����
    public static SHA1Util getInstance() {
        if (instance == null) {
            synchronized (SHA1Util.class) {
                if (instance == null) {
                    instance = new SHA1Util();
                }
            }
        }
        return instance;
    }

    /**
     * ʹ�� SHA1 �������ܣ������룩
     */
    public String encode(String res) {
        try {
            MessageDigest md = MessageDigest.getInstance(SHA1);
            byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
            return BASE64Util.getInstance().encode(md.digest(resBytes).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * ʹ�� SHA1 �������ܣ����������룩
     */
    public String encode(String res, String key) {
        try {
            SecretKey sk = null;
            if (key == null) {
                KeyGenerator kg = KeyGenerator.getInstance(HmacSHA1);
                sk = kg.generateKey();
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                sk = new SecretKeySpec(keyBytes, HmacSHA1);
            }
            Mac mac = Mac.getInstance(HmacSHA1);
            mac.init(sk);
            byte[] result = mac.doFinal(res.getBytes());
            return BASE64Util.getInstance().encode(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}