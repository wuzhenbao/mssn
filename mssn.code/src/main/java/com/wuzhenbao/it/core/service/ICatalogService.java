package com.wuzhenbao.it.core.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.CatalogVO;
import com.wuzhenbao.it.core.vo.Pager;

@Path("/hdfsService")
public interface ICatalogService {

	/**
	 * 查询菜单的树开结构 lenovo 2015年4月12日
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/catalogTree")
	List<CatalogVO> catalogTree() throws MssnApplicationException;

	/**
	 * 查询单个菜单信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/select/{catalogId}")
	public CatalogVO select(@PathParam("catalogId")Integer catalogId) throws MssnApplicationException;

	/**
	 * 插入菜单信息
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/insert")
	public Integer insert(@QueryParam("record")CatalogVO record) throws MssnApplicationException;

	/**
	 * 更新菜单信息
	 * 
	 * @param record
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/update")
	public boolean update(@QueryParam("record")CatalogVO record) throws MssnApplicationException;

	/**
	 * 删除菜单信息
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/delete/{catalogId}")
	public boolean delete(@PathParam("catalogId")Integer catalogId) throws MssnApplicationException;

	/**
	 * 查询所有的菜单列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/selectList")
	public List<CatalogVO> selectList() throws MssnApplicationException;

	/**
	 * 分布查询菜单信息
	 * 
	 * @param catalog
	 * @param pager
	 * @return
	 * @throws Exception
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Path("/pageList")
	Pager pageList(@QueryParam("catalog")CatalogVO catalog, @QueryParam("pager")Pager pager)
			throws MssnApplicationException;
}
