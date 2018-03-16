package com.wuzhenbao.it.core.sqoop;

import java.util.List;
/**
 * sqoop表结构定义
 * @author lenovo
 *
 */
public class SqoopTableDefine {
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * schema名称
	 */
	private String schemaName;
	/**
	 * 所有列名集合
	 */
	private List<String> partitionColumn;
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public List<String> getPartitionColumn() {
		return partitionColumn;
	}
	public void setPartitionColumn(List<String> partitionColumn) {
		this.partitionColumn = partitionColumn;
	}
	
}
