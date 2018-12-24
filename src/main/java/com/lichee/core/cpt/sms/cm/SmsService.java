package com.lichee.core.cpt.sms.cm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lichee.core.cpt.sms.SmsInfo;
import com.shcm.send.DataApi;
import com.shcm.send.OpenApi;

/**
 * 短息服务
 * 
 * @author lichee
 */
@Service
public class SmsService {

	private static Logger logger = LoggerFactory.getLogger(SmsService.class);

	@Value("#{smsPropertise['open.url']}")
	public String openUrl;
	@Value("#{smsPropertise['data.url']}")
	public String dataUrl;
	@Value("#{smsPropertise['account']}")
	public String account;
	@Value("#{smsPropertise['authkey']}")
	public String authkey;
	@Value("#{smsPropertise['cgid']}")
	public int cgid;
	@Value("#{smsPropertise['csid']}")
	public int csid;

	@PostConstruct
	public void init() {

		OpenApi.initialzeAccount(openUrl, account, authkey, cgid, csid);
		DataApi.initialzeAccount(dataUrl, account, authkey);
	}
	
	/**
	 * 查询余额
	 * 
	 * @return double 余额
	 */
	public double getBalance(){

		return OpenApi.getBalance();
	}

	/**
	 * 单人
	 * 
	 * @param smsInfo 短信对象
	 * @return boolean 发送成功与否
	 */
	public boolean sendOne(SmsInfo smsInfo) {

		int nRet = OpenApi.sendOnce(smsInfo.getPhone(), smsInfo.getContent(),
				0, 0, null);
		System.out.println("nRet="+nRet);
		if (nRet > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 多人发送
	 * 
	 * @param list　短信对象列表
	 * @return boolean 发送成功与否
	 */
	public boolean sendBatch(List<SmsInfo> list) {


		Iterator<SmsInfo> iterator = list.iterator();
		SmsInfo smsInfo = null;
		List<String> phoneList = new ArrayList<String>();
		List<String> contentList = new ArrayList<String>();
		while (iterator.hasNext()) {
			smsInfo = iterator.next();
			phoneList.add(smsInfo.getPhone());
			contentList.add(smsInfo.getContent());
		}
		int nRet = OpenApi.sendBatch(
			(String[]) phoneList.toArray(new String[0]),
			(String[]) contentList.toArray(new String[0]), 0, 0, null);
		if (nRet > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 参数发送
	 * 
	 * @param smsInfo 短信对象
	 * @param values 参数组
	 * @return boolean 发送成功与否
	 */
	public boolean sendParam(SmsInfo smsInfo, Map<String, String> values) {

		int nRet = OpenApi.sendParam(smsInfo.getPhone(), smsInfo.getContent(),
				values.values().toArray(new String[0]), 0, 0, null);
		if (nRet > 0) {
			return true;
		} else {
			return false;
		}
	}

}