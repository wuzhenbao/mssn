package com.wuzhenbao.it.core.dao.mysql;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.wuzhenbao.it.core.vo.UserVO;
import com.wuzhenbao.it.core.vo.Pager;

public interface UserMysqlDao {

	void addUser(UserVO record) throws SQLException;

	UserVO findUserById(UserVO user) throws SQLException;
	
	List<UserVO> findUserByCondition(UserVO user) throws SQLException;

	void updateUser(UserVO user) throws SQLException;

	void deleteUser(UserVO user) throws SQLException;
	UserVO login(UserVO user) throws SQLException;
	List<UserVO> pageList( @Param("pm")UserVO user,@Param("p")Pager p) throws SQLException;

}
