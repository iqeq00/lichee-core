package com.lichee.core.security.sha;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

import com.lichee.core.utils.EncodeUtils;

/**
 * @author lichee
 */
public class Digests {
	
	private static final String SHA = "SHA-512";

	private static byte[] sha(byte[] input, String salt, int iterations) {
		return digest(input, SHA, salt, iterations);
	}
	
	private static byte[] digest(byte[] input, String algorithm, String salt, int iterations) {
		try {
			MessageDigest digest = MessageDigest.getInstance(algorithm);
			if (salt != null) {
				digest.update(salt.getBytes("utf-8"));
			}
			byte[] result = digest.digest(input);
			iterations = iterations + 323; 
			for (int i = 1; i < iterations; i++) {
				digest.reset();
				result = digest.digest(result);
			}
			return result;
		} catch (GeneralSecurityException e) {
			throw Exceptions.unchecked(e);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String shaHex(String input, String salt) {
		return EncodeUtils.hexEncode(sha(input.getBytes(), salt, (input+salt).length()));
	}
	
	public static String shaHex(String input, String salt, int iterations) {
		return EncodeUtils.hexEncode(sha(input.getBytes(), salt, iterations));
	}
	
	public static String shaBase64(String input, String salt) {
		return EncodeUtils.base64Encode(sha(input.getBytes(), salt, (input+salt).length()));
	}
	
	public static String shaBase64(String input, String salt, int iterations) {
		return EncodeUtils.base64Encode(sha(input.getBytes(), salt, iterations));
	}
	
	public static void main(String[] args) {
		String anc = "姚金池";	
		String pwd = "123456";	
		System.out.println(shaHex(pwd, anc));
	}
	
}