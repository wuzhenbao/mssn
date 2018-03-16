package com.wuzhenbao.it.core.sqoop;

import org.apache.sqoop.client.SqoopClient;
import org.apache.sqoop.model.MDriverConfig;
import org.apache.sqoop.model.MFromConfig;
import org.apache.sqoop.model.MJob;
import org.apache.sqoop.model.MLink;
import org.apache.sqoop.model.MLinkConfig;
import org.apache.sqoop.model.MStringInput;
import org.apache.sqoop.model.MSubmission;
import org.apache.sqoop.model.MToConfig;
import org.apache.sqoop.submission.counter.Counter;
import org.apache.sqoop.submission.counter.CounterGroup;
import org.apache.sqoop.submission.counter.Counters;
import org.apache.sqoop.validation.Status;

import com.mysql.jdbc.Driver;

public class OracleToHDFS {
	public static void main(String[] args) {
		new OracleToHDFS().sqoopTransfer();
	}
	 public void sqoopTransfer() {
	        //初始化
	        String url = "http://storm.master:12000/sqoop/";
	        SqoopClient client = new SqoopClient(url);
	        //创建一个源链接 JDBC
	        long fromConnectorId = 4; 
	        MLink fromLink = client.createLink(fromConnectorId);
	        fromLink.setName("JDBC connector");
	        fromLink.setCreationUser("hadoop");
	        MLinkConfig fromLinkConfig = fromLink.getConnectorLinkConfig();
	        MStringInput msi = fromLinkConfig.getStringInput("linkConfig.connectionString");
	        msi.setValue("jdbc:oracle:thin:@192.168.72.153:1521:orcl");
	        //.setValue();
	        fromLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue("oracle.jdbc.driver.OracleDriver");
	        fromLinkConfig.getStringInput("linkConfig.username").setValue("wuzhenbao");
	        fromLinkConfig.getStringInput("linkConfig.password").setValue("wzb_1983");
	        Status fromStatus = client.saveLink(fromLink);
	        Driver d;
	        if(fromStatus.canProceed()) {
	         System.out.println("创建JDBC Link成功，ID为: " + fromLink.getPersistenceId());
	        } else {
	         System.out.println("创建JDBC Link失败");
	        }
	        //创建一个目的地链接HDFS
	        long toConnectorId = 3;
	        MLink toLink = client.createLink(toConnectorId);
	        toLink.setName("HDFS connector");
	        toLink.setCreationUser("hadoop");
	        MLinkConfig toLinkConfig = toLink.getConnectorLinkConfig();
	        toLinkConfig.getStringInput("linkConfig.uri").setValue("hdfs://storm.master:8020/");
	        Status toStatus = client.saveLink(toLink);
	        if(toStatus.canProceed()) {
	         System.out.println("创建HDFS Link成功，ID为: " + toLink.getPersistenceId());
	        } else {
	         System.out.println("创建HDFS Link失败");
	        }

	        //创建一个任务
	        long fromLinkId = fromLink.getPersistenceId();
	        long toLinkId = toLink.getPersistenceId();
	        MJob job = client.createJob(fromLinkId, toLinkId);
	        job.setName("Oracle to HDFS job");
	        job.setCreationUser("hadoop");
	        //设置源链接任务配置信息
	        MFromConfig fromJobConfig = job.getFromJobConfig();
	        fromJobConfig.getStringInput("fromJobConfig.schemaName").setValue("WUZHENBAO");
	        fromJobConfig.getStringInput("fromJobConfig.tableName").setValue("T_SYS_CATALOG");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CATALOG_ID");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CATALOT_NAME_CN");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CATALOG_NAME_EN");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CATALOG_URL");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CATALOT_TYPE");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("PARENT_ID");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("ISDELETED");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CREATEBY");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("CREATEDATE");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("LASTUPDATEBY");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("LASTUPDATEDATE");
	        fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue("DESCRIPTION");
	        MToConfig toJobConfig = job.getToJobConfig();
	        toJobConfig.getStringInput("toJobConfig.outputDirectory").setValue("/sqoop/fromoracle/T_SYS_CATALOG");
	        MDriverConfig driverConfig = job.getDriverConfig();
	        driverConfig.getIntegerInput("throttlingConfig.numExtractors").setValue(3);
	        Status status = client.saveJob(job);
	        if(status.canProceed()) {
	         System.out.println("JOB创建成功，ID为: "+ job.getPersistenceId());
	        } else {
	         System.out.println("JOB创建失败。");
	        }

	        //启动任务
	        long jobId = job.getPersistenceId();
	        MSubmission submission = client.startJob(jobId);
	        System.out.println("JOB提交状态为 : " + submission.getStatus());
	        while(submission.getStatus().isRunning() && submission.getProgress() != -1) {
	          System.out.println("进度 : " + String.format("%.2f %%", submission.getProgress() * 100));
	          //三秒报告一次进度
	          try {
	            Thread.sleep(3000);
	          } catch (InterruptedException e) {
	            e.printStackTrace();
	          }
	        }
	        System.out.println("JOB执行结束... ...");
	        System.out.println("Hadoop任务ID为 :" + submission.getExternalJobId());
	        Counters counters = submission.getCounters();
	        if(counters != null) {
	          System.out.println("计数器:");
	          for(CounterGroup group : counters) {
	            System.out.print("\t");
	            System.out.println(group.getName());
	            for(Counter counter : group) {
	              System.out.print("\t\t");
	              System.out.print(counter.getName());
	              System.out.print(": ");
	              System.out.println(counter.getValue());
	            }
	          }
	        }
	        if(submission.getError() != null) {
	          System.out.println("JOB执行异常，异常信息为 : " +submission.getError());
	        }
	        System.out.println("MySQL通过sqoop传输数据到HDFS统计执行完毕");
	    }
}
