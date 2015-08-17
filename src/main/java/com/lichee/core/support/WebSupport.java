package com.lichee.core.support;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.lichee.core.utils.EncodeUtils;

/**
 * web support
 * 
 * @author lichee
 */
public class WebSupport {
	
	private static Map<String, Object> getParameterStringWith(
			HttpServletRequest request) {

		return getParameterStringWith(request, null);
	}

	private static Map<String, Object> getParameterStringWith(
			HttpServletRequest request, String prefix) {

		Enumeration<String> paramNames = request.getParameterNames();
		Map<String, Object> params = new TreeMap<String, Object>();
		if (prefix == null) {
			prefix = "";
		}
		while ((paramNames != null) && paramNames.hasMoreElements()) {
			String paramName = (String) paramNames.nextElement();
			if ("".equals(prefix) || paramName.startsWith(prefix)) {
				String unprefixed = paramName.substring(prefix.length());
				String[] values = request.getParameterValues(paramName);
				if ((values == null) || (values.length == 0)) {
				} else if (values.length > 1) {
					params.put(unprefixed, values);
				} else {
					params.put(unprefixed, EncodeUtils.urlEncode(values[0]));
				}
			}
		}
		return params;
	}
	
	public static String setParameterStringWith(Map<String, Object> params) {

		return setParameterStringWith(params,null);
	}

	private static String setParameterStringWith(Map<String, Object> params,
			String prefix) {

		if (isEmpty(params)) {
			return "";
		}

		if (prefix == null) {
			prefix = "";
		}

		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append(prefix).append(entry.getKey())
					.append('=').append(entry.getValue());
			if (it.hasNext()) {
				queryStringBuilder.append('&');
			}
		}
		return queryStringBuilder.toString();
	}

	private static boolean isEmpty(Map map) {
		return (map == null) || map.isEmpty();
	}

	public static void putParams(HttpServletRequest request, Model model,
			String className) {

		Map<String, Object> searchParams = getParameterStringWith(request);
		request.getSession().setAttribute(className, setParameterStringWith(searchParams));
		searchParams.remove("page");
		model.addAttribute("searchParams", setParameterStringWith(searchParams));
	}

	public static String returnUrl(HttpServletRequest request,
			String baseReturn, String name) {

		String url = baseReturn + "?"
				+ (String) request.getSession().getAttribute(name);
		return url;
	}

}
