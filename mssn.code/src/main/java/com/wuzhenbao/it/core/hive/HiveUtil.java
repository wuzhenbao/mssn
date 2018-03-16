package com.wuzhenbao.it.core.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.hadoop.HiveDataVO;

public class HiveUtil {
	private static String driverName = "org.apache.hive.jdbc.HiveDriver";
	private static String url = "jdbc:hive2://docker01:10000/hivedata";
	private static String user = "wuzhenbao";
	private static String password = "wzb-1983!@#456";
	private static ResultSet res;
	private static final Logger log = Logger.getLogger(HiveUtil.class);

	public static void countData(Statement stmt, String tableName) throws SQLException {
		String sql = "select count(1) from " + tableName;
		log.info("Running:" + sql);
		res = stmt.executeQuery(sql);
		log.info("ִ开始统计:");
		while (res.next()) {
			log.info("count  统计数量 ------>" + res.getString(1));
		}
	}

	public static void closeConnection(Connection conn, Statement stmt) {
		short s = 1;
		s = s++;
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			log.info("Close Connection Exception :" + e);
		}
		try {
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
		} catch (SQLException e) {
			log.info("Close Connection Exception :" + e);
		}
	}
	public static List<HiveDataVO> selectData(String tableName) throws MssnApplicationException{
		try{
			Connection conn = HiveUtil.getConn();
			Statement stmt = conn.createStatement();
			return selectData(stmt,tableName);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (SQLException e) {
			throw new MssnApplicationException(e);
		}
	}
	/**
	 * 根据表名称查询所有的数据
	 * @param stmt
	 * @param tableName
	 * @throws SQLException
	 */
	private static List<HiveDataVO> selectData(Statement stmt, String tableName) throws SQLException {
		String sql = "select * from " + tableName;
		log.info("Running:" + sql);
		res = stmt.executeQuery(sql);
		log.info("开始输出查询结果:");
		List<HiveDataVO> datas = new ArrayList<HiveDataVO>();
		ResultSetMetaData md = null;
		while (res.next()) {
			HiveDataVO data = new HiveDataVO();
			md = res.getMetaData();
			int columns = md.getColumnCount();
			for(int i = 1;i <= columns;i++) {
				Object obj = res.getObject(i);
				if(null != obj) {
					data.setColumnByIndex(i, obj.toString());
					log.info(obj.toString());
				}
			}
			
			datas.add(data);
		}
		res.close();
		return datas;
	}

	public static void loadData(Statement stmt, String tableName) throws SQLException {
		String filepath = "/home/hadoop01/data";
		String sql = "load data local inpath '" + filepath + "' into table " + tableName;
		log.info("Running:" + sql);
		res = stmt.executeQuery(sql);
	}
	/**
	 * 显示表详情
	 * @param tableName
	 * @return
	 * @throws MssnApplicationException
	 */
	public static String describeTables(String tableName) throws MssnApplicationException{
		try {
			Connection conn = HiveUtil.getConn();
			Statement stmt = conn.createStatement();
			return describeTables(stmt,tableName);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (SQLException e) {
			throw new MssnApplicationException(e);
		}
	}
	private static String describeTables(Statement stmt, String tableName) throws SQLException {
		String sql = "describe " + tableName;
		log.info("Running:" + sql);
		res = stmt.executeQuery(sql);
		log.info("ִ显示表的描述信息:");
		StringBuffer sb = new StringBuffer();
		while (res.next()) {
			
		}
		return sb.toString();
	}
	/**
	 * 
	 * @return
	 * @throws MssnApplicationException
	 */
	public static String showTables() throws MssnApplicationException {
		try {
			Connection conn = HiveUtil.getConn();
			Statement stmt = conn.createStatement();
			return showTables(stmt);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (SQLException e) {
			throw new MssnApplicationException(e);
		}
	}
	/**
	 * 显示所有表的名称
	 * @param stmt
	 * @return
	 * @throws SQLException
	 */
	private static String showTables(Statement stmt) throws SQLException {

		ResultSet rs = null;
		String sql = "show tables ";
		log.info("Running:" + sql);
		rs = stmt.executeQuery(sql);
		log.info("ִ显示表的相关信息:");
		String tableNames = null;
		while (rs.next()) {
			if(tableNames == null) {
				tableNames = rs.getString(1);
			}else {
				tableNames += ","+rs.getString(1);
			}
		}
		rs.close();
		log.info("tableNames==="+tableNames);
		return tableNames;
	}

	public static String createExternalTable(String fileLocation,String tableName)throws MssnApplicationException{
		return "success";
	}
	public static void createTable(Statement stmt, String sql) throws SQLException {
		stmt.executeQuery(sql);
	}

	public static String dropTable(Statement stmt, String tableName) throws SQLException {
		String sql = "drop table " + tableName;
		stmt.executeQuery(sql);
		return tableName;
	}

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		Class.forName(driverName);
		Connection conn = DriverManager.getConnection(url, user, password);
		return conn;
	}
}
