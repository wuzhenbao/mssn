package com.wuzhenbao.it.core.common;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.wuzhenbao.it.core.vo.StormOrderCountVO;
import com.wuzhenbao.it.core.vo.StormOrderVO;


public class MySQLConnection {
	private static final Log LOG = LogFactory.getLog(MySQLConnection.class);

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver"); // 加载mysq驱动
			conn = DriverManager.getConnection(
					"jdbc:mysql://docker01:3306/STORM", "root",
					"123456");
		} catch (ClassNotFoundException e) {
			LOG.info("驱动加载错误" + e);
		} catch (SQLException e) {
			LOG.info("连接数据库错误" + e);
		}
		return conn;
	}


	public static void insertOrder(StormOrderVO order) throws Exception {
		Connection conn = getConn();
		PreparedStatement ps = getInsertOrderSQL(order, conn);
		try {
			int flag = ps.executeUpdate();
			LOG.info("保存结果"+flag);
		}catch (Exception e) {
			LOG.info("插入出错"+e);
		}finally{
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	/**
	 * 拼接插入order对象的sql
	 * @param order
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement getInsertOrderSQL(StormOrderVO order,
			Connection conn) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO STORM.ORDER_T(product_name,"
						+ "ip,area, order_type, address,price,qty,order_date,customer,batch_id)"
						+ "VALUES (?,?,?,?,?,?,?,?,?,?)");
		
		ps.setString(1, order.getProductName());
		ps.setString(2, order.getIp());
		ps.setString(3, order.getArea());
		ps.setString(4, order.getOrderType());
		ps.setString(5, order.getAddress());
		ps.setDouble(6, order.getPrice());
		ps.setInt(7, order.getQty());
		ps.setDate(8, new Date(order.getOrderDate().getTime()));
		ps.setString(9, order.getCustomer());
		ps.setString(10, order.getBatchId());
		return ps;
	}
	/**
	 * 插入order对象
	 * @param StormOrderCountVO
	 * @throws Exception
	 */
	public static void insertOrder(StormOrderCountVO StormOrderCountVO) throws Exception {
		Connection conn = getConn();
		PreparedStatement ps = getInsertStormOrderCountVOSQL(StormOrderCountVO, conn);
		try {
			int flag = ps.executeUpdate();
			LOG.info("保存结果"+flag);
		}catch (Exception e) {
			LOG.info("插入出错"+e);
		}finally{
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	/**
	 * 获取插入StormOrderCountVO对象的sql
	 * @param StormOrderCountVO
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement getInsertStormOrderCountVOSQL(StormOrderCountVO StormOrderCountVO,
			Connection conn) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO STORM.ORDER_COUNT_T(PRODUCT_NAME,CUSTOMER,"
                                +"ORDER_TYPE,ORDER_COUNT,ORDER_QTY_SUM,ORDER_AMOUNT_TOTAL,BATCH_ID)"
                                +"VALUES (?,?,?,?,?,?,?)");
		
		ps.setString(1, StormOrderCountVO.getProductName());
		ps.setString(2, StormOrderCountVO.getCustomer());
		ps.setString(3, StormOrderCountVO.getOrderType());
		ps.setLong(4, StormOrderCountVO.getOrderCount());
		ps.setDouble(5, StormOrderCountVO.getOrderQtySum());
		ps.setDouble(6, StormOrderCountVO.getOrderAmountTotal());
		ps.setString(7, StormOrderCountVO.getBatchId());
		return ps;
	}
	/**
	 * 获取更新StormOrderCountVO的sql
	 * @param StormOrderCountVO
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement getUpdateStormOrderCountVOSQL(StormOrderCountVO StormOrderCountVO,
			Connection conn) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("UPDATE ORDER_COUNT_T OC SET OC.ORDER_COUNT = ?,"+
								  " OC.ORDER_QTY_SUM = ?, OC.ORDER_AMOUNT_TOTAL = ?"+
								  "WHERE OC.ORDER_COUNT_ID = ?");
		
		ps.setLong(1, StormOrderCountVO.getOrderCount());
		ps.setDouble(2, StormOrderCountVO.getOrderQtySum());
		ps.setDouble(3, StormOrderCountVO.getOrderAmountTotal());
		ps.setLong(4, StormOrderCountVO.getOrderCountId());
		return ps;
	}
	/**
	 * 新增或插入StormOrderCountVO
	 * @param StormOrderCountVO
	 * @throws Exception
	 */
	public static void addOrUpdate(StormOrderCountVO StormOrderCountVO) throws Exception {
		Connection conn = getConn();
		PreparedStatement ps = getQueryStormOrderCountVOSQL(StormOrderCountVO, conn);
		try {
			ResultSet rs = ps.executeQuery();
			LOG.info("查询结果"+rs);
			Long StormOrderCountVOId ;
			StormOrderCountVO oc = StormOrderCountVO;
			if(rs.next()){
				StormOrderCountVOId = rs.getLong(1);
				oc.setOrderCountId(StormOrderCountVOId);
				ps = getUpdateStormOrderCountVOSQL(oc, conn);
				ps.executeUpdate();
			}else{
				ps = getInsertStormOrderCountVOSQL(oc, conn);
				ps.executeUpdate();
			}
		}catch (Exception e) {
			LOG.info("插入出错"+e);
			e.printStackTrace();
		}finally{
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}

	/**
	 * 获取查询StormOrderCountVO对象的sql
	 * @param StormOrderCountVO
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	private static PreparedStatement getQueryStormOrderCountVOSQL(
			StormOrderCountVO StormOrderCountVO, Connection conn) throws SQLException {
		PreparedStatement ps = conn
				.prepareStatement("SELECT OC.ORDER_COUNT_ID FROM ORDER_COUNT_T OC "+
								  "WHERE OC.PRODUCT_NAME = ?"+
								  "AND OC.CUSTOMER = ?"+
								  "AND OC.ORDER_TYPE = ?"+
								  "AND OC.BATCH_ID = ?");
		
		ps.setString(1, StormOrderCountVO.getProductName());
		ps.setString(2, StormOrderCountVO.getCustomer());
		ps.setString(3, StormOrderCountVO.getOrderType());
		ps.setString(4, StormOrderCountVO.getBatchId());
		return ps;
	}
	/**
	 * 批量插入StormOrderCountVO对象
	 * @param StormOrderCountVOList
	 * @throws Exception
	 */
	public static void insertOrder(List<StormOrderCountVO> StormOrderCountVOList)
			throws Exception {
		Connection conn = getConn();
		PreparedStatement ps = null;
		int flag = 0;
		if (StormOrderCountVOList != null && StormOrderCountVOList.size() > 0) {
			for (StormOrderCountVO StormOrderCountVO : StormOrderCountVOList) {
				ps = conn
						.prepareStatement("INSERT INTO STORM.ORDER_COUNT_T(IP,PRODUCT_NAME,CUSTOMER,"
								+ "ORDER_TYPE,ORDER_COUNT,ORDER_QTY_SUM,ORDER_AMOUNT_TOTAL,BATCH_ID)"
								+ "VALUES (?,?,?,?,?,?,?,?)");

				ps.setString(1, StormOrderCountVO.getIp());
				ps.setString(2, StormOrderCountVO.getProductName());
				ps.setString(3, StormOrderCountVO.getCustomer());
				ps.setString(4, StormOrderCountVO.getOrderType());
				ps.setLong(5, StormOrderCountVO.getOrderCount());
				ps.setDouble(6, StormOrderCountVO.getOrderQtySum());
				ps.setDouble(7, StormOrderCountVO.getOrderAmountTotal());
				ps.setString(8, StormOrderCountVO.getBatchId());
				flag = ps.executeUpdate();
			}
			LOG.info("保存结果" + flag);
			if (ps != null) {
				ps.close();
				ps = null;
			}
			if (conn != null) {
				conn.close();
				conn = null;
			}
		}
	}
}
