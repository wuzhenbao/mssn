package com.wuzhenbao.it.core.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.vo.RoleVO;
@Path("/roleService")  
public interface IRoleService {
	/**
	 * 按分布查询角色列表
	 * @param role
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/getRoleList")  
	public List<RoleVO> getRoleList(@QueryParam("role")RoleVO role) throws MssnApplicationException;
	/**
	 * 按ID查询角色
	 * @param id
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/getRoleVOById/{roleId}")  
	public RoleVO getRoleVOById(@PathParam("roleId")Integer roleId) throws MssnApplicationException;
	/**
	 * u判断是否已经存在角色名称
	 * @param roleName
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/isExist/{roleName}")  
	public boolean isExist(@PathParam("roleName")String roleName) throws MssnApplicationException;
	/**
	 * 查询单个角色的信息
	 * @param role
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/getRole")  
	public RoleVO getRole(@QueryParam("role")RoleVO role) throws MssnApplicationException;
	/**
	 * 更新单个角色的信息
	 * @param role
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/updateRole")  
	public void updateRole(@QueryParam("role")RoleVO role) throws MssnApplicationException;
	/**
	 * 分布查询角色的列表
	 * @param pager
	 * @param role
	 * @return
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/pageList")  
	public Pager pageList(@QueryParam("pager")Pager pager, @QueryParam("role")RoleVO role)throws MssnApplicationException;
	/**
	 * 新增一个角色信息
	 * @param role
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/addRole")  
	public void addRole(@QueryParam("role")RoleVO role) throws MssnApplicationException;
	/**
	 * 删除一个角色信息
	 * @param role
	 * @throws MssnApplicationException
	 */
	@GET     
    @Produces ({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})  
    @Path("/deleteRole/{roleId}")  
	public void deleteRole(@PathParam("roleId")int roleId) throws MssnApplicationException;
}
