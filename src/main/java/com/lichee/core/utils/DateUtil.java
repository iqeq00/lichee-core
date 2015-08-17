package com.lichee.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用时间工具类
 *
 * @author lpf
 */

public class DateUtil {
	/*========================通用格式========================*/

	public static final String YYYYMMDD = "yyyy-MM-dd";
	public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	public static final String YYYYMMDDHHMMSSS = "yyyy-MM-dd HH:mm:ss.S";

	/*========================中文格式========================*/

	public static final String YYYYMMDD_CN = "yyyy年MM月dd日";
	public static final String YYYYMMDDHHMMSS_CN = "yyyy年MM月dd日  HH时mm分ss秒";
	public static final String YYYYMMDDHHMMSSS_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";


	/*========================星期数组========================*/  
	public static final String[] WEEKS = {"星期日", "星期一", "星期二", "星期三",
		"星期四", "星期五", "星期六"};

	/**
	 * 格式化所传时间（传入的格式）
	 * 
	 * @param date
	 *        时间
	 *        
	 * @param pattern 
	 *        时间格式
	 */
	public static String format(Date date, String pattern) {
		String returnValue = null;
		if (date != null) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				returnValue = df.format(date);
			} catch (RuntimeException e) {
				return null;
			}
		}
		return returnValue;
	}

	/**
	 * 返回当前系统时间（格式：yyyy-MM-dd）
	 */
	public static String getNowShort() {
		return format(new Date(), YYYYMMDDHHMMSSS);
	}

	/**
	 * 返回当前系统时间（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static String getNowLong() {
		return format(new Date(), YYYYMMDDHHMMSS);
	}

	/**
	 * 返回当前系统时间（格式：yyyy-MM-dd HH:mm:ss.S）
	 */
	public static String getNowFull() {
		return format(new Date(), YYYYMMDDHHMMSS);
	}

	/**
	 * 返回当前系统时间（格式：yyyy年MM月dd日）
	 */
	public static String getNowShortCN() {
		return format(new Date(), YYYYMMDD_CN);
	}

	/**
	 * 返回当前系统时间（格式：yyyy年MM月dd日  HH时mm分ss秒）
	 */
	public static String getNowLongCN() {
		return format(new Date(), YYYYMMDDHHMMSS_CN);
	}

	/**
	 * 返回当前系统时间（格式：yyyy年MM月dd日  HH时mm分ss秒SSS毫秒）
	 */
	public static String getNowFullCN() {
		return format(new Date(), YYYYMMDDHHMMSSS_CN);
	}

	/**
	 * 时间格式化（格式：yyyy-MM-dd）
	 */
	public static String getNowShort(Date date) {
		try {
			return format(date, YYYYMMDDHHMMSSS);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 时间格式化（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static String getNowLong(Date date) {
		try {
			return format(date, YYYYMMDDHHMMSS);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 时间格式化（格式：yyyy-MM-dd HH:mm:ss.S）
	 */
	public static String getNowFull(Date date) {
		try {
			return format(date, YYYYMMDDHHMMSS);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 时间格式化（格式：yyyy年MM月dd日）
	 */
	public static String getNowShortCN(Date date) {
		try {
			return format(date, YYYYMMDD_CN);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 时间格式化（格式：yyyy年MM月dd日  HH时mm分ss秒）
	 */
	public static String getNowLongCN(Date date) {
		try {
			return format(date, YYYYMMDDHHMMSS_CN);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 时间格式化（格式：yyyy年MM月dd日  HH时mm分ss秒SSS毫秒）
	 */
	public static String getNowFullCN(Date date) {
		try {
			return format(date, YYYYMMDDHHMMSSS_CN);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 使用所传格式将字符串转换成日期类型
	 * 
	 * @param strDate
	 *        日期字符串
	 *        
	 * @param pattern
	 *        日期格式
	 *            
	 * @return Date 
	 *         日期
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy-MM-dd）
	 */
	public static Date parseShort(String strDate) {
		try {
			return parse(strDate, YYYYMMDD);
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy-MM-dd HH:mm:ss）
	 */
	public static Date parseLong(String strDate) {
		try {
			return parse(strDate, YYYYMMDDHHMMSS);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy-MM-dd HH:mm:ss.S）
	 */
	public static Date parseFull(String strDate) {
		try {
			return parse(strDate, YYYYMMDDHHMMSSS);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy年MM月dd日）
	 */
	public static Date parseShortCN(String strDate) {
		try {
			return parse(strDate, YYYYMMDD_CN);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy年MM月dd日  HH时mm分ss秒）
	 */
	public static Date parseLongCN(String strDate) {
		try {
			return parse(strDate, YYYYMMDDHHMMSS_CN);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将指定格式的字符串转化为时间类型（格式：yyyy年MM月dd日  HH时mm分ss秒SSS毫秒）
	 */
	public static Date parseFullCN(String strDate) {
		try {
			return parse(strDate, YYYYMMDDHHMMSSS_CN);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 在日期上增加年数
	 * 
	 * @param date
	 *        日期
	 *        
	 * @param n
	 *        要增加的年数
	 *        
	 * @return Date 
	 *         增加后的日期
	 */
	public static Date addYear(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.YEAR, n);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 在日期上增加月数
	 * 
	 * @param date
	 *        日期
	 *        
	 * @param n
	 *        要增加的月数
	 *        
	 * @return Date 
	 *         增加后的日期
	 */
	public static Date addMonth(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.MONTH, n);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 在日期上增加天数
	 * 
	 * @param date
	 *        日期
	 *        
	 * @param n
	 *        要增加的天数
	 *        
	 * @return Date 
	 *         增加后的日期
	 */
	public static Date addDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.DATE, n);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 在日期上增加小时数
	 * 
	 * @param date
	 *        日期
	 *        
	 * @param n
	 *        要增加的分钟数
	 *        
	 * @return Date 
	 *         增加后的日期
	 */
	public static Date addHour(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.HOUR, n);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 在日期上增加分钟数
	 * 
	 * @param date
	 *        日期
	 *        
	 * @param n
	 *        要增加的分钟数
	 *        
	 * @return Date 
	 *         增加后的日期
	 */
	public static Date addMin(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			cal.add(Calendar.MINUTE, n);
			return cal.getTime();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取系统时间的星期数
	 */
	public static String getWeek() {
		Calendar cal = Calendar.getInstance();
		return WEEKS[cal.get(Calendar.DAY_OF_WEEK)-1];
	}

	/**
	 * 根据所传时间的星期数
	 */
	public static String getWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return WEEKS[cal.get(Calendar.DAY_OF_WEEK)-1];
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取所传时间的年份
	 */
	public static int getYear(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.YEAR);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取所传时间的月份
	 */
	public static int getMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.MONTH);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取所传时间在当月的日期
	 */
	public static int getDay(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.DATE);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取所传时间在当天的小时数
	 */
	public static int getHour(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.HOUR);
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * 获取所传时间在当前小时的分钟数
	 */
	public static int getMinute(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.MINUTE);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取所传时间在当前分钟的秒数
	 */
	public static int getSecond(Date date) {
		Calendar cal = Calendar.getInstance();
		try {
			cal.setTime(date);
			return cal.get(Calendar.SECOND);
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取系统时间的年份
	 */
	public static int getYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR);
	}

	/**
	 * 获取系统间的月份
	 */
	public static int getMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH);
	}

	/**
	 * 获取系统时间在当月的日期
	 */
	public static int getDay() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DATE);
	}

	/**
	 * 获取系统时间在当天的小时数
	 */
	public static int getHour() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR);
	}

	/**
	 * 获取系统时间在当前小时的分钟数
	 */
	public static int getMinute() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MINUTE);
	}

	/**
	 * 获取系统时间在当前分钟的秒数
	 */
	public static int getSecond() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.SECOND);
	}
	
	/**
	 * 时间比较
	 * 
	 * date1等于date2则返回0
	 * date1在date2之后则返回1
	 * date1在date2之前则返回-1
	 * 如果date1或者date2有为null的情况则返回2，其他情况也返回2
	 */
	public static int CompareDate1(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return 2;
		else if (date1.getTime() > date2.getTime())
			return 1;
		else if (date1.getTime() < date2.getTime())
			return -1;
		else if (date1.getTime() == date2.getTime())
			return 0;
		return 2;
	}
	
	/**
	 * 时间比较
	 * 
	 * date1在date2之后或者等于则返回true
	 * date1在date2之前则返回false
	 * 如果date1或者date2有为null的情况则返回false，其他情况也返回false
	 */
	public static boolean CompareDate2(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		else if (date1.getTime() >= date2.getTime())
			return true;
		else if (date1.getTime() < date2.getTime())
			return false;
		return false;
	}
	
	/**
	 * 时间比较
	 * 
	 * date1在date2之后则返回true
	 * date1在date2之前或者等于则返回false
	 * 如果date1或者date2有为null的情况则返回false，其他情况也返回false
	 */
	public static boolean CompareDate3(Date date1, Date date2) {
		if (date1 == null || date2 == null)
			return false;
		else if (date1.getTime() > date2.getTime())
			return true;
		else if (date1.getTime() <= date2.getTime())
			return false;
		return false;
	}
	
	/**
	 * 获取时间差天数（相差1毫秒也算一天）
	 * 
	 * @param startTime 
	 *        起始时间
	 * 
	 * @param endTime 
	 *        截止时间
	 * 
	 * @return long
	 *         时间差
	 */
	public static long getBetweenDays(Date startTime, Date endTime){  
		try {
			Calendar cal = Calendar.getInstance();  
			cal.setTime(startTime);  
			long time1 = cal.getTimeInMillis();               
			cal.setTime(endTime);  
			long time2 = cal.getTimeInMillis();       
			long between = time2-time1;
			long remainder = between%(1000*3600*24);
			if(remainder!=0)
				between+=1000*3600*24;
			long days = between/(1000*3600*24);
			return days;
		} catch (Exception e) {
			return 0;
		}         
	}  

}
