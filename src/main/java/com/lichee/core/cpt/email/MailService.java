package com.lichee.core.cpt.email;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 邮件发送
 * 
 * @author lichee
 */
@Service
public class MailService {

	private static Logger logger = LoggerFactory.getLogger(MailService.class);
	@Autowired
	private JavaMailSender mailSender;
	@Value("#{corePropertise['email.from']}")
	private String emailFrom;
	@Value("#{corePropertise['email.to']}")
	private String emailTo;
	@Value("#{corePropertise['email.toGroup']}")
	private String toGroup;

	/**
	 * 同步发送
	 */
	public void sendMailSync(String subject, String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--同步发送邮件失败");
		}
	}
	
	/**
	 * 同步群发送
	 */
	public void sendMailGroupSync(String subject, String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			Iterator<String> iterator = getEmails().iterator();
			while (iterator.hasNext()) {
				msg.setTo(iterator.next());
				mailSender.send(msg);
			}
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--同步发送群邮件失败");
		}
	}

	/**
	 * 异步发送
	 */
	@Async
	public void sendMailAsync(String subject, String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(emailTo);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--异步发送邮件失败");
		}
	}
	
	/**
	 * 异步发送
	 */
	@Async
	public void sendMailAsync(String email, String subject, String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			mailSender.send(msg);
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--异步发送邮件失败");
		}
	}

	/**
	 * 异步群发送
	 */
	@Async
	public void sendMailGroupAsync(String subject, String content) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(emailFrom);
		msg.setSubject(subject);
		msg.setText(content);
		try {
			Iterator<String> iterator = getEmails().iterator();
			while (iterator.hasNext()) {
				msg.setTo(iterator.next());
				mailSender.send(msg);
			}
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--异步发送群邮件失败");
			e.printStackTrace();
		}
	}
	
	/**
	 * 异步发送
	 */
	@Async
	public void sendMimeMailAsync(String email, String subject, String content) {

		MimeMessage mailMessage = mailSender.createMimeMessage();  
        MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);  
        try {
			messageHelper.setTo(email);  
			messageHelper.setFrom(emailFrom);  
			messageHelper.setSubject(subject);  
			messageHelper.setText(content,true);  
			mailSender.send(mailMessage);
		} catch (Exception e) {
			logger.error("发生异常--邮件发送--异步发送Mime邮件失败");
		} 
	}

	/**
	 * 组装email列表 
	 */
	private List<String> getEmails() {

		List<String> list = new ArrayList<String>();
		if (StringUtils.isNotBlank(toGroup)) {
			list = Arrays.asList(StringUtils.split(toGroup, ";"));
		}
		return list;
	}
}
