package com.wuzhenbao.it.core.dao.mysql;


import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuzhenbao.it.core.vo.CatalogVO;
import com.wuzhenbao.it.core.vo.Pager;


public interface CatalogMysqlDao {
		/**
		 * 查询菜单的树开结构		 
		 * lenovo
		 * 2015年4月12日
		 * @return
		 * @throws Exception
		 */
		List<CatalogVO> catalogTree() throws SQLException;
		
		// 取得一个记录
		public CatalogVO select(Integer id) throws SQLException;
		
		// 插入
		public Integer insert(CatalogVO record) throws SQLException;

		// 更新
		public boolean update(CatalogVO record) throws SQLException;

		// 删除
		public boolean delete(Integer id) throws SQLException;
		//查询列表
		public List<CatalogVO> selectList() throws SQLException;
		
		List<CatalogVO> pageList(@Param("pm")CatalogVO c, @Param("p")Pager p) throws SQLException;
}
