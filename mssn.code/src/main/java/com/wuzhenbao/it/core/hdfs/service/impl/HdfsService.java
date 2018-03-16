package com.wuzhenbao.it.core.hdfs.service.impl;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.hdfs.HdfsUtil;
import com.wuzhenbao.it.core.hdfs.handler.HdfsHandler;
import com.wuzhenbao.it.core.hdfs.handler.IHdfsHandler;
import com.wuzhenbao.it.core.hdfs.service.IHdfsService;
import com.wuzhenbao.it.core.util.CommonUtil;
import com.wuzhenbao.it.core.vo.hadoop.HdfsDirVO;

import net.sf.json.JSONObject;
@Component("hdfsService")
public class HdfsService implements IHdfsService {
	private static final Log log = LogFactory.getLog(HdfsService.class);
	private IHdfsHandler handler;
	public HdfsService() {
		System.out.println("==HdfsService");
		handler = new HdfsHandler();
	}
	public String getHdfsDir() throws MssnApplicationException {
		HdfsDirVO hfdir =  HdfsUtil.getFileByPath();
		
		String pathJson = JSONObject.fromObject(hfdir).toString();
		return "["+pathJson+"]";
	}
	
	public String mkDir(String newDir) throws MssnApplicationException {
		String createNewDir = CommonUtil.deFormatPath(newDir);
		boolean flag = true;
		String message = null;
		try{
			if(HdfsUtil.exists(createNewDir)) {
				flag = false;
				message ="{result:'fail',errormsg:'dir is exists!',code:'50010001'}";
			}else {
				 HdfsUtil.mkdir(createNewDir);
			}
		}catch(Exception e) {
			message ="{result:'fail',errormsg:'"+e+"'}";
			flag = false;
			throw new MssnApplicationException(e);
		}
		if(flag) {
			message ="{result:'success',code:'200'}";
		}
		return  JSONObject.fromObject(message).toString();
	}

	public String deletePath(String deletePath) throws MssnApplicationException {
		String message = null;
		try {
			String createDeleteDir = CommonUtil.deFormatPath(deletePath);
			HdfsUtil.delete(createDeleteDir);
			message = "{result:'success',errormsg:'',code:'200'}";
		} catch (Exception e) {
			message = "{result:'fail',errormsg:'" + e + "'}";
			throw new MssnApplicationException(e);
		}
		return JSONObject.fromObject(message).toString();
	}
	
	public String dataDistinct(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.dataDistinct(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}
	public String dataSort(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.sort(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}
	public String dataAvg(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.avg(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}
	public String dataJoin(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.dataJoin(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}
	public String dataJoinMult(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.dataJoinMult(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}
	public String dataSortDesc(String inputPath) throws MssnApplicationException {
		String message = null;
		//输出目录等于/wuzhenbao/output/0xxxx
		String outputPath = IHdfsHandler.outputPath + new Date().getTime();
		boolean flag = handler.sortDesc(CommonUtil.deFormatPath(inputPath),
				CommonUtil.deFormatPath(outputPath));
		if(flag) {
			message = "{result:'success',errormsg:'',code:'200'}";
		}else {
			message = "{result:'fail',errormsg:'error!'}";
		}
		return JSONObject.fromObject(message).toString();
	}

}
