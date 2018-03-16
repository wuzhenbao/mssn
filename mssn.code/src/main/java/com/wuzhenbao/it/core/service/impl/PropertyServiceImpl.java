package com.wuzhenbao.it.core.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.dao.mysql.CatalogMysqlDao;
import com.wuzhenbao.it.core.dao.mysql.CommonMysqlDao;
import com.wuzhenbao.it.core.service.IPropertyService;
import com.wuzhenbao.it.core.vo.Pager;
import com.wuzhenbao.it.core.vo.PropertyVO;

@Component("propertyService")
public class PropertyServiceImpl implements IPropertyService {
	@Resource
	private CommonMysqlDao commonDao;
	@Resource
	private CatalogMysqlDao catalogDao;
	
	public PropertyVO getPropertyVOById(Integer id) throws Exception {
		PropertyVO p = null;
		try{
			p= commonDao.getPropertyVOById(id);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p;
	}

	public List<PropertyVO> selectList() throws Exception {
		List<PropertyVO> pList = null;
		try{
			pList= commonDao.selectList();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pList;
	}

	public String getPropertyDisplayNameByValeu(PropertyVO p)
			throws Exception {
		String name = null;
		try{
			name = commonDao.getPropertyDisplayNameByValeu(p);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return name;
	}

	public List<PropertyVO> getProListByPath(PropertyVO p)
			throws Exception {
		List<PropertyVO> ctList = null;
		try{
			ctList = commonDao.getPropertyList(p);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return ctList;
	}

	public Map<String, String> getProMapByPath(PropertyVO p)
			throws Exception {
		Map<String, String> ctList = null;
		try{
			ctList = commonDao.getProMapByPath(p);
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return ctList;
	}

	public void saveOrUpdate(PropertyVO propertyVO) throws Exception {
		
	}

	public void updateObject(PropertyVO propertyVO) throws Exception {
		
	}

	public void deleteObject(PropertyVO propertyVO) throws Exception {
		
	}

	public Pager list(Pager pager, PropertyVO propertyVO) throws Exception {
		return null;
	}

	public Map<String, String> getProMapByPath(String parentPath)
			throws Exception {
		PropertyVO p = new PropertyVO();
		p.setParentPath(parentPath);
		Map<String, String> p1 = null;
		try{
			p1 = commonDao.getProMapByPath(p);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return p1;
	}

}
