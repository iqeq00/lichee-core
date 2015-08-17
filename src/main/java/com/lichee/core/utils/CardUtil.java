package com.lichee.core.utils;

import java.util.Date;
import java.util.regex.Pattern;

import com.lichee.core.security.sha.Digests;

public class CardUtil {

    public static String getBirth(String cardId) throws RuntimeException {
	Pattern pattern = Pattern.compile("([0-9]{17}([0-9]|X))|([0-9]{15})");
	boolean b = pattern.matcher(cardId).matches();
	if (!b) {
	    throw new RuntimeException("请输入正确的身份证号");
	}
	String year = null;
	String month = null;
	String day = null;
	if (cardId.length() == 15) {
	    year = "19" + cardId.substring(6, 8);
	    month = cardId.substring(8, 10);
	    day = cardId.substring(10, 12);
	} else if (cardId.length() == 18) {
	    year = cardId.substring(6, 10);
	    month = cardId.substring(10, 12);
	    day = cardId.substring(12, 14);
	} else {
	    throw new RuntimeException("请输入正确的身份证号");
	}
	return year + "-" + month + "-" + day;
    }

    public static Date getBirthDate(String cardId) {

	return DateUtil.parse(getBirth(cardId),DateUtil.YYYYMMDD);
    }

    public static String getBirthPwd(String userName, String cardId) {

	String birth = getBirth(cardId);
	return Digests.shaHex(birth.replace("-", ""), userName);
    }

}
