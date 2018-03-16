package com.wuzhenbao.it.core.common;

import java.util.HashMap;
import java.util.Map;

public class Constants {
	// session username
	public final static String USERNAME_KEY = "username";
	
	public final static String DATE_FORMAT_YYYYDDMMHHMMSS="yyyy-MM-dd HH:mm:ss";
	public final static String DATE_FORMAT_YYYYDDMM="yyyy-MM-dd";

	// struts mapping
	public final static String SUCCESS_KEY = "success";
	public final static String FAILURE_KEY = "failure";
	public final static String LOGOUT_KEY = "logout";

	public final static String INDEX_KEY = "index";
	public final static String LIST_KEY = "list";
	public final static String ADD_KEY = "add";
	public final static String EDIT_KEY = "edit";
	
	// pager default parameters
	public final static int pageSize = 25;
	public final static int pageNo =1;
	
	// pager session
	public final static String PAGER_ADDRESS = "pager_address";
	public final static String PAGER_SCHEDULE = "pager_schedule";
	public final static String PAGER_WORKLOG = "pager_worklog";
	public final static String PAGER_SMS = "pager_sms";
	public final static String PAGER_NOTICE = "pager_notice";
	public final static String PAGER_MEETING = "pager_meeting";

	//
	public final static String IS_DELETE_N = "1";
	public final static String IS_DELETE_Y = "0";

	public final static String ELEMENT_TYPE_SELECT="select";
	public final static String ELEMENT_TYPE_RADIO="radio";
	public final static String ELEMENT_TYPE_CHECKBOX="checkbox";
	
	public final static String PROPERTY_PATH_GENDAR="root.SYSTEM.gender";
	public final static String PROPERTY_PATH_PAGE_NO = "root.SYSTEM.pageNo";
	/** ajax提交的标志 */
	public static final String HEADER = "X-Requested-With";
	
	/** 用户登录失效的标示  */
	public static final String AJAX_ERROR_LOGIN_USER_INVAILD="-1";
	/**   订单统计中的区域信息             */
	public static Map<Integer,String> areaMap = new HashMap<Integer,String>();
	/**   订单统计中的客户信息             */
	public static Map<Integer,String> customerMap = new HashMap<Integer,String>();
	/**   订单统计中的产品信息             */
	public static Map<Integer,String> productMap = new HashMap<Integer,String>();
	static{
		areaMap.put(0, "HK-hongkong");
		areaMap.put(1, "GD-guangdong");
		areaMap.put(2, "HN-hunan");
		areaMap.put(3, "HB-hubei");
		areaMap.put(4, "BJ-beijing");
		areaMap.put(5, "SH-shanghai");
	}
	static{
		customerMap.put(0, "zhangsan");
		customerMap.put(1, "lisi");
		customerMap.put(2, "wangwu");
		customerMap.put(3, "wuzhenbao");
	}
	static{
		productMap.put(0, "phone");
		productMap.put(1, "tv");
		productMap.put(2, "pc");
		productMap.put(3, "pad");
	}
}
