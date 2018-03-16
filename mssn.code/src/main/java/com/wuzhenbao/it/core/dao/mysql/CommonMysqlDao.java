package com.wuzhenbao.it.core.dao.mysql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.wuzhenbao.it.core.vo.PropertyVO;

public interface CommonMysqlDao {
	PropertyVO getPropertyVOById(Integer id) throws SQLException;
	List<PropertyVO> selectList()throws SQLException ;
	String getPropertyDisplayNameByValeu(PropertyVO p) throws SQLException;
	Map<String,String> getProMapByPath(PropertyVO p) throws SQLException;
	List<PropertyVO> getPropertyList (PropertyVO p) throws SQLException;
}
