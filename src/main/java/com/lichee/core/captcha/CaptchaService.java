package com.lichee.core.captcha;
import java.awt.image.BufferedImage;

import javax.servlet.http.HttpServletRequest;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;

/**
 * @author lichee 
 */
public class CaptchaService {

	private static ImageCaptchaService instance = new DefaultManageableImageCaptchaService(
			new FastHashMapCaptchaStore(), new LicheeEngine(), 180, 100000,
			75000);

	public static Boolean validate(String captcha, HttpServletRequest request) {

		Boolean isResponseCorrect = Boolean.FALSE;
		String captchaId = request.getSession().getId();
		try {
			isResponseCorrect = instance.validateResponseForID(captchaId, captcha);
		} catch (CaptchaServiceException e) {
			return isResponseCorrect;
		}
		return isResponseCorrect;
	}

	public static BufferedImage challenge(HttpServletRequest request) {

		return (BufferedImage) instance.getChallengeForID(
				request.getSession(true).getId(), request.getLocale());
	}

}