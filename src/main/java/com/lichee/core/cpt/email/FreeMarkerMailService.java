package com.lichee.core.cpt.email;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 使用freemarker文件作为模板发送邮件
 * 
 * @author lichee
 */
@Service
public class FreeMarkerMailService {

	private static Logger logger = LoggerFactory.getLogger(FreeMarkerMailService.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private FreeMarkerConfigurer fmConfigurer;
	
	@Value("#{corePropertise['email.from']}")
	private String emailFrom;
	
	/**
	 * 发送邮件
	 * @param templateName 模板文件名
	 * @param toEmailAddr  要发送到的邮箱地址
	 * @param subject	邮件主题名
	 * @param content	邮件内容
	 */
	public void sendMailSync(String templateName,String toEmailAddr, String subject, Map<String,String> content){
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			helper.setTo(toEmailAddr);
			helper.setFrom(emailFrom);
			helper.setSubject(subject);
			String text = getMailContent(content,templateName);
			helper.setText(text,true);
			helper.setSentDate(new Date());
			mailSender.send(helper.getMimeMessage());
		} catch (MessagingException e) {
			logger.error("发生异常--模版邮件发送--同步发送邮件失败");
		}
		
	}
	
	/**
	 * 异步发送邮件
	 * @param templateName 模板文件名
	 * @param toEmailAddr  要发送到的邮箱地址
	 * @param subject	邮件主题名
	 * @param content	邮件内容
	 */
	@Async
	public void sendMailASync(String templateName,String toEmailAddr, String subject, Map<String,String> content){
		
		MimeMessage msg = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, false, "utf-8");
			helper.setTo(toEmailAddr);
			helper.setFrom(emailFrom);
			helper.setSubject(subject);
			String text = getMailContent(content,templateName);
			helper.setText(text,true);
			helper.setSentDate(new Date());
			mailSender.send(helper.getMimeMessage());
		} catch (MessagingException e) {
			logger.error("发生异常--模版邮件发送--异步发送邮件失败");
		}
		
	}

	/**
	 * 根据模板获得解析后的内容
	 * @param content	邮件内容
	 * @param templateName	邮件模板名
	 * @return
	 */
	private String getMailContent(Map<String,String> content, String templateName) {
		
		String result = null;
		try {
			Template template = fmConfigurer.getConfiguration().getTemplate(templateName);
			result = FreeMarkerTemplateUtils.processTemplateIntoString(template, content);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("获得freemarker模板出错");
		} catch (TemplateException e) {
			e.printStackTrace();
			System.out.println("模板解析出错");
		}
		return result;
	}
}
