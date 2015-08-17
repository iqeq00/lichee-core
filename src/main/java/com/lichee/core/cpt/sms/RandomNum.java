package com.lichee.core.cpt.sms;

/**
 * 生成6位随机码 
 * 
 * @author lichee
 */
public class RandomNum {

	public static int getNum() {
		return (int) (Math.random() * 1000000);
	}
}