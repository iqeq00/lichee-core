package com.lichee.core.pay.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public class AlipayService {

    public Map<String, String> getSendParam(HttpServletRequest request,
	    String bizOrderId, String subject, String totalFee, String body,
	    String showUrl) {
	Map<String, String> params = new HashMap<String, String>();
	params.put("service", "create_direct_pay_by_user");
	params.put("partner", AlipayConfig.partner);
	params.put("seller_email", AlipayConfig.seller_email);
	params.put("_input_charset", AlipayConfig.input_charset);
	params.put("payment_type", AlipayConfig.payment_type);
	params.put("notify_url", AlipayConfig.notify_url);
	params.put("return_url", AlipayConfig.return_url);
	// 系统订单信息
	params.put("out_trade_no", bizOrderId);
	params.put("subject", subject);
	params.put("total_fee", totalFee);
	params.put("body", body);
	params.put("show_url", showUrl);

	Map<String, String> sPara = AlipaySubmit.buildRequestPara(params);
	// 提交地址
	sPara.put("submit_url", AlipayConfig.ALIPAY_GATEWAY_NEW);
	return sPara;
    }

    public String sync(HttpServletRequest request) throws RuntimeException {
	Map<String, String> params = new HashMap<String, String>();
	Map requestParams = request.getParameterMap();
	for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
	    String name = (String) iter.next();
	    String[] values = (String[]) requestParams.get(name);
	    String valueStr = "";
	    for (int i = 0; i < values.length; i++) {
		valueStr = (i == values.length - 1) ? valueStr + values[i]
			: valueStr + values[i] + ",";
	    }
	    // // 乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
	    // try {
	    // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
	    // } catch (UnsupportedEncodingException e) {
	    // System.out.println(e.getMessage());
	    // }
	    params.put(name, valueStr);
	}

	// 获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
	// 商户订单号

	// 交易状态
	String trade_status = request.getParameter("trade_status");

	// 返回数据验证
	if (AlipayNotify.verify(params)) {
	    // 交易完成
	    if (trade_status.equals("TRADE_FINISHED")) {
		return "success";
	    }
	    // 支付成功
	    else if (trade_status.equals("TRADE_SUCCESS")) {
		return "success";
	    }
	} else {
	    return "fail";
	}
	return "fail";
    }

}
