package com.wuzhenbao.it.core.hive.handler;


import java.util.ArrayList;
import java.util.List;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.hive.HiveUtil;
import com.wuzhenbao.it.core.vo.hadoop.HiveDataVO;
import com.wuzhenbao.it.core.vo.hadoop.HiveInfoVO;

public class HiveHandlerImpl implements IHiveHandler {
	public List<HiveInfoVO> getAllTablesName() throws MssnApplicationException{
		String tables = HiveUtil.showTables();
		List<HiveInfoVO> infs = new ArrayList<HiveInfoVO>();
		if(null != tables) {
			String[] tablesNames = tables.split(",");
			for(String name : tablesNames) {
				HiveInfoVO infvo = new HiveInfoVO();
				infvo.setText(name);
				infvo.setId(name);
				infvo.setState(false);
				infs.add(infvo);
			}
		}
		return infs;
	}

	public List<HiveDataVO> queryHiveData(String tableName) throws MssnApplicationException {
		return HiveUtil.selectData(tableName);
	}

	public String createExternalTable(String fileLocation) throws MssnApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public String describeTables(String tableName) throws MssnApplicationException {
		return HiveUtil.describeTables(tableName);
	}
	
}
