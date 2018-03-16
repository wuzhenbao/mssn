package com.wuzhenbao.it.core.hive.service.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.hive.handler.HiveHandlerImpl;
import com.wuzhenbao.it.core.hive.handler.IHiveHandler;
import com.wuzhenbao.it.core.hive.service.IHiveService;
import com.wuzhenbao.it.core.vo.hadoop.HiveDataVO;
import com.wuzhenbao.it.core.vo.hadoop.HiveInfoVO;

import net.sf.json.JSONObject;
@Component("hiveService")
public class HiveService implements IHiveService {
	private IHiveHandler handler ;
	
	public HiveService(){
		handler = new HiveHandlerImpl();
	}

	public String getAllTablesName() throws MssnApplicationException {
		List<HiveInfoVO> hiveTales  = handler.getAllTablesName();;
		
		String tableJson = null;
		for(HiveInfoVO ht : hiveTales) {
			if(null == tableJson) {
				tableJson = JSONObject.fromObject(ht).toString();
			}else {
				tableJson += ","+JSONObject.fromObject(ht).toString();
			}
		}
		return "["+tableJson+"]";
	}

	public String queryHiveData(String tableName) throws MssnApplicationException {
		List<HiveDataVO> datas = handler.queryHiveData(tableName);
		String dataJson = null;
		for(HiveDataVO data : datas) {
			if(null == dataJson) {
				dataJson = JSONObject.fromObject(data).toString();
			}else {
				dataJson += ","+JSONObject.fromObject(data).toString();
			}
		}
		return "["+dataJson+"]";
	}

	public String createExternalTable(String fileLocation) throws MssnApplicationException {
		String dataJson = handler.createExternalTable(fileLocation);
		String result = JSONObject.fromObject(dataJson).toString();
		/*for(HiveDataVO data : datas) {
			if(null == dataJson) {
				dataJson = JSONObject.fromObject(data).toString();
			}else {
				dataJson += ","+JSONObject.fromObject(data).toString();
			}
		}*/
		return "["+result+"]";
	}

	public String describeTables(String tableName) throws MssnApplicationException {
		String dataJson = handler.describeTables(tableName);
		String result = JSONObject.fromObject(dataJson).toString();
		/*for(HiveDataVO data : datas) {
			if(null == dataJson) {
				dataJson = JSONObject.fromObject(data).toString();
			}else {
				dataJson += ","+JSONObject.fromObject(data).toString();
			}
		}*/
		return "["+result+"]";
	}

}
