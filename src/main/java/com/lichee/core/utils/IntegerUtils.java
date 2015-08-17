package com.lichee.core.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * integer 工具类 
 */
public class IntegerUtils {

	public static Integer[] toIntArray(String str) {

		if(StringUtils.isNotBlank(str)){
			String strArr[] = str.split(",");
			Integer array[] = new Integer[strArr.length];
			for (int i = 0; i < strArr.length; i++) {
				array[i] = Integer.parseInt(strArr[i]);
			}
			return array;
		}else {
			return new Integer[0];
		}
		
	}
	
	public static List<Integer> toIntList(String str) {

		return Arrays.asList(toIntArray(str));
	}
	
	public static void main(String[] args) {
		
		List<Integer> list = toIntList(null);
		System.out.println(list.size());
	}
}
