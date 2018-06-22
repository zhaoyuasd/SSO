package com.laozhao.canTranslate;

import java.util.HashMap;
import java.util.Map;

/**
 * Base64�ӽ����㷨
 * </p>
 * Base64�����㷨��<br/>
 * 1����ȡ�ַ�����ÿ���ַ���ASCII�룻<br/>
 * 2������ÿ3��8bit���ַ�Ϊһ�������飬��ÿ��24bit��<br/>
 * 3������24bit���ֳ�4��6bit��4����λ��ÿ����λǰ�����2��0���õ�4��8bit�ĵ�λ��<br/>
 * 4����ÿ��8bit�ĵ�λת����ʮ�������֣�����Base64������ҵ���Ӧ���ַ�����ƴ�ӣ��õ����յļ��ܺ���ַ�����<br/>
 * </p>
 * Base64�����㷨��<br/>
 * 1������4���ַ�������Base64������ҵ��ַ���Ӧ������������4��6Ϊ��ֵ��<br/>
 * 2������4��6Ϊ��ֵƴ���������γ�һ��24Ϊ��ֵ��<br/>
 * 3�������24λ��ֵ����8λһ��ضϳ�3��8λ��ֵ��<br/>
 * 4������ASCII���ҵ�������8λ��ֵ��Ӧ���ַ������������ַ���<br/>
 * </p>
 * ע�����<br/>
 * 1����������ַ�������8bit�ģ���������ASCII�뷶Χ�ڣ����Ĳ��У�<br/>
 * 2�������������ַ����Ȳ���3�ı���������������1��2��0����Ӧ������ַ�Ϊ��=����
 * 3������һ���ַ�������Base64����������м��ܺ���ܣ��õ��Ľ���Ͳ��ǿ�ʼʱ����ַ����ˡ�<br/>
 */
public class BASE64Util {
    private static final Map<Integer, Character> base64CharMap = new HashMap<>();
    private static final String base64CharString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    private static BASE64Util instance;

    private BASE64Util() {
        for (int i = 0; i < base64CharString.length(); i++) {
            char c = base64CharString.charAt(i);
            base64CharMap.put(new Integer(i), new Character(c));
        }
    }

    public static BASE64Util getInstance() {
        if (instance == null) {
            synchronized (BASE64Util.class) {
                if (instance == null) {
                    instance = new BASE64Util();
                }
            }
        }
        return instance;
    }

    /**
     * This method is used to encode a normal string to base64 string @param
     * origin The String to be encoded @return The String after encoded.
     */
    public String encode(String origin) {
        if (origin == null) {
            return null;
        }
        if (origin.length() == 0) {
            return "";
        }
        int length = origin.length();
        String binaryString = "";
        // to binary String
        for (int i = 0; i < length; i++) {
            int ascii = origin.charAt(i);
            String binaryCharString = Integer.toBinaryString(ascii);
            while (binaryCharString.length() < 8) {
                binaryCharString = "0" + binaryCharString;
            }
            binaryString += binaryCharString;
        }

        // to base64 index
        int beginIndex = 0;
        int endIndex = beginIndex + 6;
        String base64BinaryString = "";
        String charString = "";
        while ((base64BinaryString = binaryString.substring(beginIndex, endIndex)).length() > 0) {
            // if length is less than 6, add "0".
            while (base64BinaryString.length() < 6) {
                base64BinaryString += "0";
            }
            int index = Integer.parseInt(base64BinaryString, 2);
            char base64Char = base64CharMap.get(index);
            charString = charString + base64Char;
            beginIndex += 6;
            endIndex += 6;
            if (endIndex >= binaryString.length()) {
                endIndex = binaryString.length();
            }
            if (endIndex < beginIndex) {
                break;
            }
        }
        if (length % 3 == 2) {
            charString += "=";
        }
        if (length % 3 == 1) {
            charString += "==";
        }
        return charString;
    }

    public String decode(String encodedString) {
        if (encodedString == null) {
            return null;
        }
        if (encodedString.length() == 0) {
            return "";
        }
        // get origin base64 String
        String origin = encodedString.substring(0, encodedString.indexOf("="));
        String equals = encodedString.substring(encodedString.indexOf("="));

        String binaryString = "";
        // convert base64 string to binary string
        for (int i = 0; i < origin.length(); i++) {
            char c = origin.charAt(i);
            int ascii = base64CharString.indexOf(c);
            String binaryCharString = Integer.toBinaryString(ascii);
            while (binaryCharString.length() < 6) {
                binaryCharString = "0" + binaryCharString;
            }
            binaryString += binaryCharString;
        }
        // the encoded string has 1 "=", means that the binary string has append
        // 2 "0"
        if (equals.length() == 1) {
            binaryString = binaryString.substring(0, binaryString.length() - 2);
        }
        // the encoded string has 2 "=", means that the binary string has append
        // 4 "0"
        if (equals.length() == 2) {
            binaryString = binaryString.substring(0, binaryString.length() - 4);
        }

        // convert to String
        String charString = "";
        String resultString = "";
        int beginIndex = 0;
        int endIndex = beginIndex + 8;
        while ((charString = binaryString.substring(beginIndex, endIndex)).length() == 8) {
            int ascii = Integer.parseInt(charString, 2);
            resultString += (char) ascii;
            beginIndex += 8;
            endIndex += 8;
            if (endIndex > binaryString.length()) {
                break;
            }
        }
        return resultString;
    }
}