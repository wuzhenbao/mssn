package com.wuzhenbao.it.core.zookeeper.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;

@Path("/zkService")
public interface IZKService {
	/**
	 * 查询zk目录树
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/getZKTree")
	public String getZKTree() throws MssnApplicationException;
	
	/**
	 * 根据节点路径，显示节点的内容
	 * @param nodePath
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/showZKData/{nodePath}")
	public String showZKData(@PathParam("nodePath")String nodePath) throws MssnApplicationException;
	
	/**
	 * 删除zk节点
	 * @param nodePath
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/deleteZKNode/{nodePath}")
	public String deleteZKNode(@PathParam("nodePath")String nodePath) throws MssnApplicationException;
	
	/**
	 * 创建ZK节点
	 * @param zkNodeVO
	 * @return
	 * @throws MssnApplicationException
	 */
	@POST
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/createZKNode")
	public String createZKNode(@QueryParam("path")String path,@QueryParam("desc")String desc) throws MssnApplicationException;
}
