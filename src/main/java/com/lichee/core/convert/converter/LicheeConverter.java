package com.lichee.core.convert.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * CustomerConverter
 * 
 * @author lichee
 */
public class LicheeConverter implements Converter<String, Date> {

    private static Logger logger = LoggerFactory
	    .getLogger(LicheeConverter.class);
    public static final DateFormat DF_LONG = new SimpleDateFormat(
	    "yyyy-MM-dd HH:mm:ss");
    public static final DateFormat DF_SHORT = new SimpleDateFormat("yyyy-MM-dd");
    public static final int SHORT_DATE = 10;

    @Override
    public Date convert(String source) {

	if (!StringUtils.hasLength(source)) {
	    return null;
	}
	try {
	    if (source.length() <= SHORT_DATE) {
		DF_SHORT.setLenient(false);
		return DF_SHORT.parse(source);
	    } else {
		DF_LONG.setLenient(false);
		return DF_LONG.parse(source);
	    }
	} catch (ParseException e) {
	    logger.error(String.format("类型转换失败，需要格式%s，但格式是[%s]", source));
	    return null;
	}
    }
}