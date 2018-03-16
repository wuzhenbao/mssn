package com.wuzhenbao.it.core.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.dao.mysql.CatalogMysqlDao;
import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.service.ICatalogService;
import com.wuzhenbao.it.core.vo.CatalogVO;
import com.wuzhenbao.it.core.vo.Pager;

@Component("catalogService")
public class CatalogServiceImpl implements ICatalogService {
	private static final Log log = LogFactory.getLog(CatalogServiceImpl.class);

	@Resource(name = "catalogMysqlDao")
	private CatalogMysqlDao catalogMysqlDao;

	public Pager pageList(CatalogVO c, Pager p) throws MssnApplicationException {
		try {
			List<CatalogVO> catalogList = catalogMysqlDao.pageList(c, p);
			p.setResultList(catalogList);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
		return p;
	}

	public CatalogVO select(Integer id) throws MssnApplicationException {
		CatalogVO c = null;
		try {
			c = catalogMysqlDao.select(id);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
		return c;
	}

	public Integer insert(CatalogVO record) throws MssnApplicationException {
		Integer i = null;
		try {
			i = catalogMysqlDao.insert(record);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
		return i;
	}

	public boolean update(CatalogVO record) throws MssnApplicationException {
		boolean b = false;
		try {
			b = catalogMysqlDao.update(record);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
		return b;
	}

	public boolean delete(Integer id) throws MssnApplicationException {
		try {
			return catalogMysqlDao.delete(id);
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
	}

	public List<CatalogVO> selectList() throws MssnApplicationException {
		List<CatalogVO> catalogList = null;
		try {
			catalogList = catalogMysqlDao.selectList();
		} catch (SQLException e) {
			log.info(e);
			throw new MssnApplicationException();
		}
		return catalogList;
	}

	public List<CatalogVO> catalogTree() throws MssnApplicationException {
		List<CatalogVO> catalogList = null;
		try {
			catalogList = catalogMysqlDao.catalogTree();
		} catch (SQLException e) {
			log.info(e);
		}
		return catalogList;
	}

}
