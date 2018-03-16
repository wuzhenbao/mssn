package com.wuzhenbao.it.core.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.vo.RoleVO;

public interface RoleMysqlDao {

	List<RoleVO> getRoleList(RoleVO role) throws SQLException;

	RoleVO getRole(RoleVO queryVO) throws SQLException;

	RoleVO getRoleVOById(Integer id) throws SQLException;

	void addRole(RoleVO role) throws SQLException;

	List<RoleVO> pageList(@Param("p")Pager pager,@Param("r") RoleVO role) throws SQLException;

	void updateRole(RoleVO role) throws SQLException;

	void deleteRole(RoleVO role) throws SQLException;

}
