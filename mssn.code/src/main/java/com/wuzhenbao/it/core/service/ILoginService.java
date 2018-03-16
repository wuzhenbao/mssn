package com.wuzhenbao.it.core.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnLoginException;
import com.wuzhenbao.it.core.vo.UserVO;

@Path("/login")   
public interface ILoginService {
	
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/dologin")   
	public String login(@QueryParam("loginUser")UserVO user) throws MssnLoginException;
	
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/userlogin/{userId}")   
	public String userLogin(@PathParam("userId")String userId) throws MssnLoginException;
}
