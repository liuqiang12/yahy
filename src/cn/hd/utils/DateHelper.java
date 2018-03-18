package cn.hd.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateHelper {
	static SimpleDateFormat sdfShort = new SimpleDateFormat("yyyyMMdd");
	static SimpleDateFormat sdfLong = new SimpleDateFormat("yyyy-MM-dd");
	static SimpleDateFormat sdfLongCn = new SimpleDateFormat("yyyy年MM月dd日");
	static SimpleDateFormat sdfShortU = new SimpleDateFormat("MMM dd",Locale.ENGLISH);
	static SimpleDateFormat sdfLongU = new SimpleDateFormat("MMM dd,yyyy",Locale.ENGLISH);
	static SimpleDateFormat sdfLongTime = new SimpleDateFormat("yyyyMMddHHmmss");
	static SimpleDateFormat sdfLongTimePlus = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static SimpleDateFormat sdfShortLongTimePlusCn = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	static SimpleDateFormat sdfShortLongTimePlusCn_ = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
	static SimpleDateFormat sdfLongTimePlusMill = new SimpleDateFormat("yyyyMMddHHmmssSSSS");
	static SimpleDateFormat sdfMd = new SimpleDateFormat("MM月dd日");
	private static long DAY_IN_MILLISECOND = 0x5265c00L;

	public DateHelper()
	{
	}
	/**
	 * Descrption:取得当前日期到毫秒极,格式为:yyyyMMddHHmmssSSSS
	 * @return String
	 * @throws java.lang.Exception
	 */
	public static String getNowPlusTimeMill()
	{
		String nowDate = "";
		try
		{
			java.sql.Date date = null;
			date = new java.sql.Date(new java.util.Date().getTime());
			nowDate = sdfLongTimePlusMill.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}
	public static String getFormatsdfLongTimePlus(Long time)
	{
		String nowDate = "";
		try
		{
			Date date = null;
			date = new Date(time);
			nowDate = sdfLongTimePlus.format(date);
			return nowDate;
		}
		catch (Exception e)
		{
			throw e;
		}
	}

	public static void main(String[] args)
	{
		System.out.println(getFormatsdfLongTimePlus(new Date().getTime()));
		System.out.println(DateHelper.getNowPlusTimeMill());
	}

}
