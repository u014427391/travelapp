package com.tsw.utils;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
public class TextUtility
{
	public static boolean isNull(String str)
	{
		if (null==str||str.length()==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static String subString(String str)
	{
		if (isNull(str))
		{
			return "";
		}
		else
		{
			str = str.substring(0, 50);
			str = str.replaceAll("\r\n", "，");
			str = str.replace("<p>", "");
			str = str.replace("</p>", "");
			str = str.replace("　　", "");
			str +="...";
			return str;
		}
	}
	
	/**
	 * 从字符串转换成整形
	 * @param str 待转换字符串 
	 * @return
	 */
	public static int String2Int(String str)
	{
		try
		{
			int value = Integer.valueOf(str);
			return value;
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 进行编码
	 * @param str
	 * @return
	 */
	public static String toUTF8(String str)
	{
		if (!isNull(str))
		{
			try
			{
				str = new String(str.getBytes("ISO8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
		}
		return str;
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date)
	{
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(date);
	}
}
