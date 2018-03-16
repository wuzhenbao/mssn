package com.wuzhenbao.it.core.util;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wuzhenbao.it.core.common.Constants;

public class CommonUtil {
	static String profile;
	/**
	 * 根据参数的格式返回当前时间的字符串
	 * @param format
	 * @return
	 */
	public static String getNowTimeStr(String format){
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new Date());
	}
	public static String deFormatPath(String oldPath) {
		return oldPath.replaceAll("a90000009a", "/");
	}
	public static String formatStrFromDate(Date d,String formatStr){
		if(null == d)return null;
		String fs = null;
		if(StringUtils.isNotBlank(formatStr)){
			fs = formatStr;
		}else{
			fs = Constants.DATE_FORMAT_YYYYDDMM;
		}
		SimpleDateFormat sf = new SimpleDateFormat(fs);
		return sf.format(d);
	}
	public static String formatStrFromSqlDate(java.sql.Date d,String formatStr){
		if(null == d)return null;
		String fs = null;
		if(StringUtils.isNotBlank(formatStr)){
			fs = formatStr;
		}else{
			fs = Constants.DATE_FORMAT_YYYYDDMM;
		}
		SimpleDateFormat sf = new SimpleDateFormat(fs);
		return sf.format(d);
	}
	/**
	 * 将日期字符串转换成日期类
	 * @param dateStr
	 * @param formatStr
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDateFromStr(String dateStr,String formatStr) throws ParseException{
		if(StringUtils.isBlank(dateStr))return null;
		String fs = null;
		if(StringUtils.isNotBlank(formatStr)){
			fs = formatStr;
		}else{
			fs = Constants.DATE_FORMAT_YYYYDDMM;
		}
		SimpleDateFormat sf = new SimpleDateFormat(fs);
		return sf.parse(dateStr);
	}
	
	public static boolean listIsNull(List list){
		return list==null || list.isEmpty();
	}
	public static boolean listNotNull(List list){
		return list!=null && !list.isEmpty();
	}
	
	public static boolean arrIsEmpty(Object[] objs){
		return null==objs || objs.length == 0;
	}
	/**
	 * 获得当前路径
	 * 方法注释
	 * 作用说明：
	 * create by: wuzhenbao
	 * created date: 2012-5-5
	 * @param request
	 * @return
	 * @throws CommonException
	 */
	public static String getBasePath(HttpServletRequest request)throws Exception{
		String htt=request.getScheme();
		String serName=request.getServerName();
		int port=request.getServerPort();
		String path=request.getContextPath();
		String basePath=htt+"://"+serName+":"+port+path;
		return basePath;
	}
	/**
	 * 获取字符串的长度
	 * @param str
	 * @return
	 */
	public static double getStrLength(String str) {
		if (StringUtils.isBlank(str))
			return 0;
		double valueLength = 0;
		String chinese = "[\u4e00-\u9fa5]";
		// 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
		for (int i = 0; i < str.length(); i++) {
			// 获取一个字符
			String temp = str.substring(i, i + 1);
			// 判断是否为中文字符
			if (temp.matches(chinese)) {
				// 中文字符长度为1
				valueLength += 1;
			} else {
				// 其他字符长度为0.5
				valueLength += 0.5;
			}
		}
		// 进位取整
		return Math.ceil(valueLength);
	}
	/**
	 * 复制属性 方法注释 作用说明： create by: wuzhenbao created date: 2012-5-5
	 * 
	 * @param obj
	 * @param oldObj
	 * @throws CommonException
	 */
	public static void  copyProperties(Object obj, Object oldObj)throws Exception{
		try {
			PropertyUtils.copyProperties(obj, oldObj);
		} catch (IllegalAccessException e) {
			throw new Exception(e);
		} catch (InvocationTargetException e) {
			throw new Exception(e);
		} catch (NoSuchMethodException e) {
			throw new Exception(e);
		}
	}
	
	public static Object getBean(String beanName) throws Exception{
		return null;
	}
}
