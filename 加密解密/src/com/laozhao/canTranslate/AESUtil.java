package com.laozhao.canTranslate;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.laozhao.notTranslate.MD5Util;

public class AESUtil {
    public static final String AES = "AES";
    public static final String charset = null; // �����ʽ��Ĭ��nullΪGBK
    public static final int keysizeAES = 128;

    private static AESUtil instance;

    private AESUtil() {
    }

    // ����
    public static AESUtil getInstance() {
        if (instance == null) {
            synchronized (MD5Util.class) {
                if (instance == null) {
                    instance = new AESUtil();
                }
            }
        }
        return instance;
    }

    /**
     * ʹ�� AES ���м���
     */
    public String encode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, true);
    }

    /**
     * ʹ�� AES ���н���
     */
    public String decode(String res, String key) {
        return keyGeneratorES(res, AES, key, keysizeAES, false);
    }

    // ʹ��KeyGenerator˫����ܣ�DES/AES��ע������ת��Ϊ�ַ�����ʱ���ǽ�2����תΪ16���Ƹ�ʽ���ַ���������ֱ��ת����Ϊ�����
    private String keyGeneratorES(String res, String algorithm, String key, int keysize, boolean isEncode) {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            if (keysize == 0) {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(new SecureRandom(keyBytes));
            } else if (key == null) {
                kg.init(keysize);
            } else {
                byte[] keyBytes = charset == null ? key.getBytes() : key.getBytes(charset);
                kg.init(keysize, new SecureRandom(keyBytes));
            }
            SecretKey sk = kg.generateKey();
            SecretKeySpec sks = new SecretKeySpec(sk.getEncoded(), algorithm);
            Cipher cipher = Cipher.getInstance(algorithm);
            if (isEncode) {
                cipher.init(Cipher.ENCRYPT_MODE, sks);
                byte[] resBytes = charset == null ? res.getBytes() : res.getBytes(charset);
                return parseByte2HexStr(cipher.doFinal(resBytes));
            } else {
                cipher.init(Cipher.DECRYPT_MODE, sks);
                return new String(cipher.doFinal(parseHexStr2Byte(res)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // ��������ת����16����
    private String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    // ��16����ת��Ϊ������
    private byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
}