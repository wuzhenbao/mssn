package com.wuzhenbao.it.core.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.dao.mysql.UserMysqlDao;
import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.service.IUserService;
import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.vo.UserVO;

@Component("userService")
public class UserServiceImpl  implements IUserService {
	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	
	@Resource(name="userMysqlDao")
	private UserMysqlDao userMysqlDao;
	
	public Integer register(UserVO record) throws MssnApplicationException{
		Integer p = null;
		try{
				userMysqlDao.addUser(record);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
		return p;
	}

	public UserVO login(UserVO user) throws MssnApplicationException{
		UserVO user1 = null;
		try{
				 user1= userMysqlDao.login(user);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
		return user1;
	}
	
	public boolean isExist(UserVO user) throws MssnApplicationException{
		List<UserVO> existUser =null;
		try{
				existUser = userMysqlDao.findUserByCondition(user);
			if (existUser == null || existUser.isEmpty()) {
				return false;
			} else {
				return true;
			}
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public UserVO getUser(UserVO user) throws MssnApplicationException {
		UserVO u = null;
		try{
				u = userMysqlDao.findUserById(user);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
		return u;
	}

	public void updateUser(UserVO user) throws MssnApplicationException {
		try{
				userMysqlDao.updateUser(user);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public void addUser(UserVO user) throws MssnApplicationException {
		try{
				userMysqlDao.addUser(user);
		}catch(Exception e){
			log.info(e);
			throw new MssnApplicationException();
		}
		
	}

	public void deleteUser(UserVO user) throws MssnApplicationException {
		try{
				userMysqlDao.deleteUser(user);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
		
	}

	public Pager pageList(Pager pager, UserVO user) throws MssnApplicationException {
		List<UserVO> userList = null;
		try{
				userList = userMysqlDao.pageList(user,pager);
			pager.setResultList(userList);
		}catch(SQLException e){
			log.info(e);
			throw new MssnApplicationException();
		}
		return pager;
	}
}
