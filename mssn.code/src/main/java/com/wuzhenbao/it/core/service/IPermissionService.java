package com.wuzhenbao.it.core.service;

import java.util.List;

import com.wuzhenbao.it.core.vo.PermissionVO;

public interface IPermissionService {
	public List<PermissionVO> getPermissionTree() throws Exception;
}
