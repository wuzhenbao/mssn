package com.wuzhenbao.it.core.hdfs.handler;

import com.wuzhenbao.it.core.exception.MssnApplicationException;

public interface IHdfsHandler {
	public static final String outputPath = "/mapreduce/output/";
	/**
	 * 数据去重
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean dataDistinct(String inputPath,String outputPath)
			throws MssnApplicationException;
	/**
	 * 整数排序
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean sort(String inputPath,String outputPath)throws MssnApplicationException;
	/**
	 * 求平均值
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean avg(String inputPath,String outputPath)throws MssnApplicationException;
	/**
	 * 单表关联
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean dataJoin(String inputPath,String outputPath)throws MssnApplicationException;
	/**
	 * 多表关联
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean dataJoinMult(String inputPath,String outputPath)throws MssnApplicationException;
	/**
	 * 整数倒排
	 * @param inputPath
	 * @param outputPath
	 * @return
	 * @throws MssnApplicationException
	 */
	public boolean sortDesc(String inputPath,String outputPath)throws MssnApplicationException;
}
