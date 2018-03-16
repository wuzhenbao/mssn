package com.wuzhenbao.it.core.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.UserVO;

@Path("/mongoDBService")
public interface IMongoDBService {
	/**
	 * 测试mongoDB的链接类
	 * @throws MssnApplicationException
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/testMongoDB")
	public String testMongoDB() throws MssnApplicationException;
	
	/**
	 * 通过mongoDB的添加用户信息
	 * @throws MssnApplicationException
	 */
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/addUser")
	public String addUser(@QueryParam("userVO")UserVO insetuSER) throws MssnApplicationException ;
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/findUser")
	public String findUser()throws MssnApplicationException ;
}
