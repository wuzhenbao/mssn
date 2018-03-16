package com.wuzhenbao.it.core.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.UserVO;
import com.wuzhenbao.it.core.vo.Pager;

@Path("/userService")  
public interface IUserService {

	/**
	 * 检查用户名是否存在
	 **/ 
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/isExist")   
	public boolean isExist(@QueryParam("user")UserVO user) throws MssnApplicationException;

	/**
	 * 注册用户
	 * 
	 * **/ 
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/register")
	public Integer register(@QueryParam("user")UserVO user) throws MssnApplicationException;
	
	/**
	 * 查询单个用户的信息
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/getUser")
	public UserVO getUser(@QueryParam("user")UserVO user) throws MssnApplicationException;
	/**
	 * 更新用户信息
	 * @param user
	 * @throws Exception
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/updateUser")
	public void updateUser(@QueryParam("user")UserVO user) throws MssnApplicationException;
	/**
	 * 新增用户信息
	 * @param user
	 * @throws Exception
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/addUser")
	public void addUser(@QueryParam("user")UserVO user) throws MssnApplicationException;
	/**
	 * 删除用户信息
	 * @param user
	 * @throws Exception
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/deleteUser")
	public void deleteUser(@QueryParam("user")UserVO user) throws MssnApplicationException;
	/**
	 * 分布查询系统中的用户信息
	 * @param pager
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/pageList")
	public Pager pageList(@QueryParam("pager")Pager pager, @QueryParam("user")UserVO user) throws MssnApplicationException;
}
