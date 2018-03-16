package com.wuzhenbao.it.core.hdfs.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.hadoop.HdfsDirVO;

@Path("/hdfsService")
public interface IHdfsService {
	/**
	 * 查询菜单的树形结构 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getHdfsDir")
	public String getHdfsDir() throws MssnApplicationException;
	
	/**
	 * 在hdfs上创建新目录
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/mkDir/{newDir}")
	public String mkDir(@PathParam("newDir")String newDir) throws MssnApplicationException;
	/**
	 * 删除hdfs上的目录或文件
	 * @return
	 * @throws Exception
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/deletePath")
	public String deletePath(@QueryParam("deletePath")String deletePath) throws MssnApplicationException;
	
	/**
	 * 数据去重
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataDistinct/{inputPath}")
	public String dataDistinct(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
	/**
	 * 数据排序
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataSort/{inputPath}")
	public String dataSort(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
	/**
	 * 求平均值
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataAvg/{inputPath}")
	public String dataAvg(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
	/**
	 * 单表关联
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataJoin/{inputPath}")
	public String dataJoin(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
	/**
	 * 多表关联
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataJoinMult/{inputPath}")
	public String dataJoinMult(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
	/**
	 * 倒排索引
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Path("/dataSortDesc/{inputPath}")
	public String dataSortDesc(@PathParam("inputPath")String inputPath) throws MssnApplicationException;
	
}
