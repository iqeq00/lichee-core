package com.lichee.core.cpt.sms;

/**
 * 短信对象
 *
 * @author lichee
 */
public class SmsInfo {

	private String phone;
	private String content;
	private String cell;
	private String sendTime;

	public SmsInfo() {
	}

	public SmsInfo(String phone, String content) {
		this.phone = phone;
		this.content = content;
		this.cell = "";
		this.sendTime = "";
	}
	
	public SmsInfo(String phone, String content, String sendTime) {
		this.phone = phone;
		this.content = content;
		this.cell = "";
		this.sendTime = sendTime;
	}

	public SmsInfo(String phone, String content, String cell, String sendTime) {
		this.phone = phone;
		this.content = content;
		this.cell = cell;
		this.sendTime = sendTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

}