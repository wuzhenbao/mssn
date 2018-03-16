package com.wuzhenbao.it.core.hbase;


import java.util.Date;

import org.apache.log4j.Logger;

public class HBaseTest {
	static Logger log = Logger.getLogger(HBaseTest.class);
	public static void main(String[] args) throws Exception {
		String tableName ="table_test03";
		if(HBaseTools.isExists("table_test03")){
			log.info("HBaseTools.isExists========"+HBaseTools.isExists("table_test02"));
		}else{
			String[] columnFamilys = new String[]{"user","loginfo"};
			
			HBaseTools.createTable(tableName, columnFamilys, true);
			HBaseTools.addRow(tableName, "rk000001", "user", "userName", "wuzhenbao");
			HBaseTools.addRow(tableName, "rk000001", "user", "userAge", "33");
			HBaseTools.addRow(tableName, "rk000001", "user", "userAddress", "guang dong shen zhen");
			HBaseTools.addRow(tableName, "rk000001", "user", "userDesc", "test data");
			
			HBaseTools.addRow(tableName, "rk000001", "loginfo", "ip", "192.168.72.1");
			HBaseTools.addRow(tableName, "rk000001", "loginfo", "time", ""+new Date().getTime());
		}
		HBaseTools.getAllRows(tableName);
	}

}
