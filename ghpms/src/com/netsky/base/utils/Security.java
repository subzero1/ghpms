package com.netsky.base.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @description: 加密摘要
 * 
 * @class name:com.netsky.base.baseObject.Security
 * @author Chiang Sep 14, 2010
 */
public class Security {

	/**
	 * 根据提供算法返回加密摘要
	 * 
	 * @param str
	 *            需要加密的字符
	 * @param algorithm
	 *            加密方式
	 * @return 加密结果
	 * @throws NoSuchAlgorithmException
	 *             String
	 */
	public static String Encryption(String str, String algorithm) throws NoSuchAlgorithmException {
		byte[] unencodedPassword = str.getBytes();
		MessageDigest md = null;
		md = MessageDigest.getInstance(algorithm);
		md.reset();

		// call the update method one or more times
		// (useful when you don't know the size of your data, eg. stream)
		md.update(unencodedPassword);

		// now calculate the hash
		byte[] encodedPassword = md.digest();

		StringBuffer buf = new StringBuffer();

		/**
		 * b[n] & 0XFF 的作用是将byte转化为int。 因为0xff是整型， byte[] b; b[index] & 0xff
		 * 向大的数据类型靠拢，就是整型了。 java中的byte 是sign的 ,所以
		 * 将一个负byte强制转换成int,就会损坏原来的binary表示
		 */
		for (int i = 0; i < encodedPassword.length; i++) {
			if ((encodedPassword[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString(encodedPassword[i] & 0xff, 16));
		}

		return buf.toString();

	}

	public static void main(String a[]) throws NoSuchAlgorithmException {
		System.out.print(Security.Encryption("28371420", "MD5"));
	}
}
