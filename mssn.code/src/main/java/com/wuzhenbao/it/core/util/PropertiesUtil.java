package com.wuzhenbao.it.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * 配置文件工具类，指定读取config/mssn.app.properties
 * @author lenovo
 *
 */
public class PropertiesUtil {
	static Logger logger = Logger.getLogger(PropertiesUtil.class);
    private static Properties props = null;    
      
    static {    
        readProperties("mssn.app.properties");    
    }    
    private static void readProperties(String fileName) { 
        try {    
            props = new Properties();    
            InputStream fis =PropertiesUtil.class.getClassLoader().getResourceAsStream("config/mssn.app.properties");    
            props.load(fis);    
            logger.info("加载配置文件完成==========="+fileName);
        } catch (Exception e) {    
            e.printStackTrace();    
        }    
    }    
    /**  
     * 获取某个属性  
     */    
    public static String getProperty(String key){    
    	if(props == null) {
    		readProperties("mssn.app.properties");
    	}
        return props.getProperty(key);    
    }    
    /**  
     * 获取所有属性，返回一个map,不常用  
     * 可以试试props.putAll(t)  
     */    
    @SuppressWarnings("rawtypes")
	public static Map<String,String> getAllProperty(){    
        Map<String,String> map=new HashMap<String,String>();    
        Enumeration enu = props.propertyNames();    
        while (enu.hasMoreElements()) {    
            String key = (String) enu.nextElement();    
            String value = props.getProperty(key);    
            map.put(key, value);    
        }    
        return map;    
    }    
    /**  
     * 在控制台上打印出所有属性，调试时用。  
     */    
    public static void printProperties(){    
        props.list(System.out);    
    }    
    /**  
     * 写入properties信息  
     */    
    public static void writeProperties(String key, String value,String uri) {    
        try {    
        OutputStream fos = new FileOutputStream(new File(uri));    
            props.setProperty(key, value);    
            // 将此 Properties 表中的属性列表（键和元素对）写入输出流    
            props.store(fos, "『comments』Update key：" + key);    
        } catch (Exception e) {    
        e.printStackTrace();  
        }    
    }       
}
