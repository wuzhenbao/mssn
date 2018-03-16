package com.wuzhenbao.it.core.dao.mysql;

import java.util.List;

import com.wuzhenbao.it.core.vo.PermissionVO;

public interface PermissionMysqlDao {
	public List<PermissionVO> getPermissionTree() throws Exception;
}
