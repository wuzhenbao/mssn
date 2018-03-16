package com.wuzhenbao.it.core.hive.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
@Path("/hiveService")
public interface IHiveService {

	/**
	 * 查询所有的表名称
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getAllTablesName")
	public String getAllTablesName() throws MssnApplicationException;
	/**
	 * 查询所有的表名称
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/queryHiveData/{tableName}")
	public String queryHiveData(@PathParam("tableName")String tableName) throws MssnApplicationException;
	/**
	 * 根据选择的HDFS路径，创建hive外部表
	 * @param fileLocation
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createExternalTable/{fileLocation}")
	public String createExternalTable(@PathParam("fileLocation")String fileLocation) throws MssnApplicationException;
	/**
	 * 显示表详情
	 * @param tableName
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/describeTables/{tableName}")
	public String describeTables(@PathParam("tableName")String tableName) throws MssnApplicationException;
}
