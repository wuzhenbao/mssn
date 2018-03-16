package com.wuzhenbao.it.core.hive.handler;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.hadoop.HiveDataVO;
import com.wuzhenbao.it.core.vo.hadoop.HiveInfoVO;

public interface IHiveHandler {
	/**
	 * 获取所有表名称
	 * @return
	 * @throws MssnApplicationException
	 */
	public List<HiveInfoVO> getAllTablesName() throws MssnApplicationException;
	/**
	 * 从页面传递表名，查询内容
	 * @param tableName
	 * @return
	 * @throws MssnApplicationException
	 */
	public List<HiveDataVO> queryHiveData(String tableName) throws MssnApplicationException;
	/**
	 * 根据选择的HDFS路径，创建hive外部表
	 * @param fileLocation
	 * @return
	 * @throws MssnApplicationException
	 */
	public String createExternalTable(String fileLocation) throws MssnApplicationException;
	/**
	 * 显示表详情
	 * @param tableName
	 * @return
	 * @throws MssnApplicationException
	 */
	public String describeTables(String tableName) throws MssnApplicationException;
}
