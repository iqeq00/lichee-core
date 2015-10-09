package com.lichee.core.cpt.sms.cm;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lichee.core.cpt.sms.RandomNum;
import com.lichee.core.cpt.sms.SmsInfo;

/**
 * @author lichee
 */
@Service
public class SmsSupport {

	private static Logger logger = LoggerFactory.getLogger(SmsSupport.class);
	
	private static String valid = "phoneValid";

	@Autowired
	private SmsService smsService;

	public boolean sendOne(HttpServletRequest request, String phone) {

		String phoneValid = Integer.toString(RandomNum.getNum());
		Map<String, String> smsmap = new HashMap<String, String>();
		smsmap.put("p1", phoneValid);
		boolean isTrue = smsService.sendParam(new SmsInfo(phone,
				"验证码为：{p1}（绝不会索取此验证码，切勿告知他人），请在页面中输入以完成验证。"), smsmap);
		if (isTrue) {
			setPhoneValid(request, phoneValid);
			return true;
		}
		logger.error("发送手机验证码的短信发送出错...");
		return false;

	}

	private void setPhoneValid(HttpServletRequest request, String phoneValid) {

		request.getSession().setAttribute(valid, phoneValid);
	}

	private String getPhoneValid(HttpServletRequest request) {

		try {
			return (String) request.getSession().getAttribute(valid);
		} catch (Exception e) {
			return null;
		}
	}
	
	public void removePhoneValid(HttpServletRequest request) {

		request.getSession().removeAttribute(valid);
	}
	
	public Boolean validate(String phoneValid, HttpServletRequest request) {

		String valid = getPhoneValid(request);
		if(valid!=null){
			if(valid.equals(phoneValid)){
				return true;
			}
		}
		return false;
	}
}