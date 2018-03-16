package com.wuzhenbao.it.core.service;

import java.util.List;
import java.util.Map;

import com.wuzhenbao.it.core.vo.PropertyVO;
import com.wuzhenbao.it.core.vo.Pager;

public interface IPropertyService  {
	public PropertyVO getPropertyVOById(Integer id) throws Exception;
	public List<PropertyVO> selectList()	throws Exception ;
	public String getPropertyDisplayNameByValeu(PropertyVO p) throws Exception;
	public List<PropertyVO> getProListByPath(PropertyVO p) throws Exception;
	public Map<String,String> getProMapByPath(PropertyVO p) throws Exception;
	public void saveOrUpdate(PropertyVO propertyVO) throws Exception;
	public void updateObject(PropertyVO propertyVO) throws Exception;
	public void deleteObject(PropertyVO propertyVO) throws Exception;
	public Pager list(Pager pager, PropertyVO propertyVO) throws Exception;
	public Map<String, String> getProMapByPath(String propertyPathGendar) throws Exception;
}
