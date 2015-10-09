package com.lichee.core.utils;

import com.google.common.base.Strings;

/**
 * 字符串工具类
 * 
 * @author lichee
 */
public class StringUtils {

	public static String hiddenPhone(String phone, int digitStart, int digitEnd) {

		StringBuilder sb = new StringBuilder();
		sb.append(phone.substring(0, digitStart));
		for (int i = 0; i < digitEnd - digitStart; i++) {
			sb.append("*");
		}
		sb.append(phone.substring(digitEnd));
		return sb.toString();
	}

	public static String hiddenPhone(String phone) {

		return hiddenPhone(phone, 3, 8);
	}

	public static String hiddenEmail(String email) {

		if (!Strings.isNullOrEmpty(email)) {
			StringBuilder sb = new StringBuilder();
			String[] emailArr = email.split("@");
			sb.append(emailArr[0].substring(0, 1));
			sb.append("*****");
			sb.append(emailArr[0].substring(emailArr[0].length() - 1));
			sb.append("@");
			sb.append(emailArr[1]);
			return sb.toString();
		} else {
			return null;
		}
	}

}