package com.lichee.core.utils;

/**
 * 字符串工具类
 * 
 * @author lichee
 */
public class ChineseUtils {

	/**
	 * 计算字符长度，中文自动转换成2个长度
	 */
	public static int chineseLength(String value) {

		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		if (value == null || "".equals(value)) {
			return 0;
		}
		for (int i = 0; i < value.length(); i++) {
			String temp = value.substring(i, i + 1);
			if (temp.matches(chinese)) {
				valueLength += 2;
			} else {
				valueLength += 1;
			}
		}
		return valueLength;
	}

}