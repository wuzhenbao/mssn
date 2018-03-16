package com.wuzhenbao.it.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.wuzhenbao.it.core.dao.mysql.PermissionMysqlDao;
import com.wuzhenbao.it.core.service.IPermissionService;
import com.wuzhenbao.it.core.vo.PermissionVO;

public class PermissionServiceImpl implements IPermissionService {
	
	@Resource
	private PermissionMysqlDao permissionDao;
	
	public List<PermissionVO> getPermissionTree() throws Exception {
		return permissionDao.getPermissionTree();
	}

}
