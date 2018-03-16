package com.wuzhenbao.it.core.sqoop;

import java.util.ArrayList;
import java.util.List;

import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;

public class HdfsTOMysql {
	public static void main(String[] args){
		SqoopClient client = MySqoopClient.getClient("http://storm.master:12000/sqoop/");
		MyLinkTool mlt = new MyLinkTool();
		String driver = "com.mysql.jdbc.Driver";
		String userName = "wuzhenbao";
		String password = "wzb_1983";
		String cnneName = "mysqlLink";
		String creatUser = "wuzhenbao";
		String url = "jdbc:mysql://storm.master:3306/wuzhenbao";
		MLink dbLink = mlt.buildDBLink(client, url, driver, userName, password, cnneName, creatUser);
		System.out.println("dbLink==========="+dbLink);
		String hdfsUrl = "hdfs://storm.master:8020/";
		String hdfsCnneName = "hdfsLink";
		
		MLink hdfsLink = mlt.buildHDFSLik(client, hdfsUrl, hdfsCnneName, creatUser);
		System.out.println("hdfsLink==========="+hdfsLink);
		String jobName = "hdfsTOMysql_02";
		String inputPath = "/sqoop/fromlocal";
		SqoopTableDefine table = new SqoopTableDefine();
		String schemaName = "wuzhenbao";
		table.setSchemaName(schemaName);
		String tableName = "T_SYS_USER";
		table.setTableName(tableName);
		List<String> partitionColumn = new ArrayList<String>();
		//partitionColumn.add("user_id");
		partitionColumn.add("user_name_cn");
		partitionColumn.add("user_name_en");
		partitionColumn.add("user_email");
		partitionColumn.add("user_phone");
		partitionColumn.add("user_password");
		partitionColumn.add("user_address");
		partitionColumn.add("user_sex");
		partitionColumn.add("atta_id");
		partitionColumn.add("isdeleted");
		partitionColumn.add("createby");
		partitionColumn.add("createdate");
		partitionColumn.add("lastupdateby");
		partitionColumn.add("lastupdatedate");
		partitionColumn.add("description");
		table.setPartitionColumn(partitionColumn);
		MJob job = mlt.createHDFSToDBJob(client, hdfsLink, dbLink, jobName, creatUser, table, inputPath);
		System.out.println("job==========="+job);
		mlt.startJob(client, job);
	}
}
