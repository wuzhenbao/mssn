package com.wuzhenbao.it.core.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.dao.mysql.RoleMysqlDao;
import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.service.IRoleService;
import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.vo.RoleVO;

@Component("roleService")
public class RoleServiceImpl implements IRoleService {

	private static final Log log = LogFactory.getLog(RoleServiceImpl.class);

	@Resource
	private RoleMysqlDao roleDao;

	public List<RoleVO> getRoleList(RoleVO role)
			throws MssnApplicationException {
		try {
			List<RoleVO> l = roleDao.getRoleList(role);
			return l;
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public RoleVO getRoleVOById(Integer id) throws MssnApplicationException {
		try {
			RoleVO role = roleDao.getRoleVOById(id);
			return role;
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public boolean isExist(String roleName) throws MssnApplicationException {
		try {
			RoleVO queryVO = new RoleVO();
			queryVO.setRoleName(roleName);
			RoleVO role = roleDao.getRole(queryVO);
			if (null != role) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public RoleVO getRole(RoleVO role) throws MssnApplicationException {
		try {
			RoleVO r = roleDao.getRole(role);
			return r;
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public void updateRole(RoleVO role) throws MssnApplicationException {
		try {
			roleDao.updateRole(role);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public Pager pageList(Pager pager, RoleVO role)
			throws MssnApplicationException {
		try {
			List<RoleVO> roleList = roleDao.pageList(pager, role);
			pager.setResultList(roleList);
			return pager;
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public void addRole(RoleVO role) throws MssnApplicationException {
		try {
			roleDao.addRole(role);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public void deleteRole(int roleId) throws MssnApplicationException {
		try {
			RoleVO role = new RoleVO();
			role.setRoleId(roleId);
			roleDao.deleteRole(role);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

}
