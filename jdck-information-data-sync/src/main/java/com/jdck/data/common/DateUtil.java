package com.jdck.data.common;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 */
public class DateUtil {

	/**
	 * 年份格式(yyyy)
	 */
	public final static String YEAR_PATTERN = "yyyy";

	/**
	 * 时间格式(yyyy-MM-dd)
	 */
	public final static String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * 时间格式(yyyy/MM/dd)
	 */
	public final static String DATE_PATTERN_SLASH = "yyyy/MM/dd";

	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 时间格式(yyyy年M月dd日 ah:mm:ss) 代码生成器使用
	 */
	public final static String DATE_TIME_CHN_PATTERN = "yyyy年M月dd日 ah:mm:ss";

	/**
	 * Date转为时间格式(yyyy-MM-dd)的字符串
	 *
	 * @param date
	 * @return
	 */
	public static String format(Date date) {
		return format(date, DATE_PATTERN);
	}

	/**
	 * Date根据格式字符串转为字符串
	 *
	 * @param date
	 * @param pattern
	 * @return 时间字符串
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}

	/**
	 * 当前日期加天数
	 *
	 * @param startDate
	 * @param days
	 * @return
	 */
	public static Date addDays(Date startDate, int days) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(startDate);
		cl.add(Calendar.DATE, days);
		return cl.getTime();
	}

	/**
	 * 当前时间加分钟
	 *
	 * @param startDate
	 * @param days
	 * @return
	 */
	public static Date addMinutes(Date startDate, int days) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(startDate);
		cl.add(Calendar.DATE, days);
		return cl.getTime();
	}

	/**
	 * 当前当前系统年份
	 *
	 * @return
	 */
	public static String getCurrentYear() {
		SimpleDateFormat sdf = new SimpleDateFormat(YEAR_PATTERN);
		Date date = new Date();
		return sdf.format(date);
	}

	/**
	 * 时间格式(yyyy/MM/dd)
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getdate(String date) throws ParseException {
		if (date == null || "".equals(date))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN_SLASH);
		return sdf.parse(date);
	}

	/**
	 * 时间格式(yyyy-MM-dd HH:mm:ss)
	 *
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date getDate(String date) throws ParseException {
		if (date == null || "".equals(date))
			return null;

		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
		return sdf.parse(date);
	}

	public static Date getDate(String date, int type) throws ParseException {
		if (date == null || "".equals(date))
			return null;
		StringBuffer stringBuffer = new StringBuffer(date);
		date = stringBuffer.insert(4, '-').insert(7, '-').insert(10, ' ').insert(13, ":").insert(16, ":").toString();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
		return sdf.parse(date);
	}

	/**
	 * 将时间戳转成date
	 *
	 * @param date
	 * @return
	 */
	public static Date getDate(Long date) {
		if (date == null) {
			return null;
		}
		Date d = new Date(date);
		return d;
	}

	/**
	 * 获取当前时间
	 *
	 * @return
	 */
	public static Date getCurrentDate() {

		Date d = new Date();

		return d;
	}
 
	public static void main(String args[]) throws ParseException {
		// double d = -9.033123456;
		double d = 5.10529173902359E9;
 
		System.out.println( DateUtil.getCurrentDate()); 
		if ( DateUtil.getDate("2020-08-07 12:30:02").compareTo(DateUtil.getCurrentDate()) > 0) {
			 
			System.out.println("ok"); 
		}

 
	}

	public static BigDecimal formatNum(String StrBd) {

		BigDecimal bd = new BigDecimal(StrBd);
		bd = bd.setScale(4, BigDecimal.ROUND_HALF_UP);

		return bd;
	}

	public static String formatDouble(double value) {
		String retValue = null;
		NumberFormat format = NumberFormat.getInstance();
		format.setMinimumFractionDigits(0);
		format.setMaximumFractionDigits(4);
		format.setGroupingUsed(false);
		retValue = format.format(value);
		retValue = retValue.replaceAll(",", "");
		return retValue;
	}

	/**
	 * 获取传入时间的零点
	 * @return
	 */
	public static Date getCurentStartDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date zero = calendar.getTime();
		return zero;
	}

	/**
	 * 获取传入时间的23:59:59
	 * @return
	 */
	public static Date getCurentEndDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		Date zero = calendar.getTime();
		return zero;
	}

}
