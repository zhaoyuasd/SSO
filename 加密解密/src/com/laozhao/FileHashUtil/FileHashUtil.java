package com.laozhao.FileHashUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;



/*
 *"D:/hapfish/ShellFolder.java",
 *"D:/hapfish/ShellFolder - ����.java",
 *"E:/ShellFolder - ����.java",
 *"E:/ShellFolder.txt",
 *"D:/hapfish/ShellFolder.jpg",
 *��������ļ���ͬһ�ļ��������ơ�����չ���ģ��������ϣ�����һ�µġ�
 *"E:/ShellFolder�����ַ�.txt" �����˼����ַ������Ͳ�һ����
 *"D:/hapfish/birosoft.jar" ��ȫ����ص�����һ���ļ�
 */


/*
 * ��󣬰����н���������е�ռ�ռ䣬��ҪΪ��˵�������Լ��Ĳ��롣
 * һ����˵ͬһ��ϣ�㷨��ͬһ�ļ�(������չ�����޸�)�������Ľ��Ӧ����һ�µġ� 
 * ����и����룬��baidu�Ŀ⡢��Ѷ��Ⱥ�����ϴ�ʱ���Ȼ��ж��Ƿ�����ͬ�ļ���
 * ��ĳ�ֿ�������˵Ҳ�����˶��ļ��Ĺ�ϣ�㷨���Ͼ��ӱ�������һ����ϣ�㷨���õ���ֵҪ�Ȱ�����
 * �ļ�����ȥ�Ƚ�ʵ�ݵöࡣ�����ַ����ıȽ�Ҳ�Ǻܷ���ġ� 
 *����ĳһ�ֹ�ϣ�㷨������һ�ֿ��ܣ�����������ͬ���ļ�����������Ĺ�ϣֵ������һ���ġ���ȻΪ�˱��գ�
 *������������������Ĺ�ϣ�㷨��ֻ����ÿ���㷨��õĹ�ϣֵ����ͬʱ�������ж���ͬһ���ļ��� 
 *�������Ҳ���û��ϴ����ļ����й�ϣ����Ļ����Ϳ��Խ�ʡ��Դ��ͬ�����ļ�����˵���Լ����ϴ���������
 */
public class FileHashUtil {

	public static final char[] hexChar = { 
		    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	public static final String[] hashTypes = new String[] { "MD2", "MD5", "SHA1", "SHA-256", "SHA-384", "SHA-512" };
	
	public void MD5File(String fileName) throws Exception{
		//String fileName = args[0];
		System.out.println("��Ҫ��ȡhash���ļ�Ϊ����" + fileName);
		java.util.List<MessageDigest> mds = new java.util.ArrayList<MessageDigest>();
		for (String hashType : hashTypes) {
			MessageDigest md = MessageDigest.getInstance(hashType);
			mds.add(md);
		}
		InputStream fis = null;
		try {
			fis = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				for (MessageDigest md : mds) {
					md.update(buffer, 0, numRead);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		for (MessageDigest md : mds) {
			System.out.println(md.getAlgorithm() + " == " + toHexString(md.digest()));
		}
	}
	

	public static void main(String[] args) throws Exception {
		String[] fileName = new String[] {"D:/hapfish/ShellFolder.java","D:/hapfish/ShellFolder - ����.java",
				  "E:/ShellFolder - ����.java","E:/ShellFolder.txt","D:/hapfish/ShellFolder.jpg",
				  "E:/ShellFolder�����ַ�.txt","D:/hapfish/birosoft.jar"};
		FileHashUtil files  = new FileHashUtil();
		for(int i=0;i<fileName.length;i++){
			files.MD5File(fileName[i]);
		} 
		
		
	}

	public static String toHexString(byte[] b) {
		StringBuilder sb = new StringBuilder(b.length * 2);
		for (int i = 0; i < b.length; i++) {
			sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
			sb.append(hexChar[b[i] & 0x0f]);
		}
		return sb.toString();
	}

}
