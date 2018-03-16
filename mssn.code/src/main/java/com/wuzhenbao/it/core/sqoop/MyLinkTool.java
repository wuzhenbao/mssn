package com.wuzhenbao.it.core.sqoop;

import java.util.List;

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

public class MyLinkTool {
	/**
	 * 创建一个数据库的链接
	 * @param client
	 * @param url
	 * @param driver
	 * @param userName
	 * @param password
	 * @param cnneName
	 * @param creatUser
	 * @return
	 */
	public MLink buildDBLink(SqoopClient client,String url,String driver,
			String userName,String password,String cnneName,String creatUser) {
		//如果已经存在链接，则使用原来的链接
		List<MLink> mlist= client.getLinks();
		for(MLink link  : mlist){
			String name = link.getName();
			if(name.equals(cnneName)){
				System.out.println("已经存在link============="+cnneName);
				return link;
			}
		}
		
		//默认db的链接类型都是4
		long fromConnectorId = 4;
		MLink dbLink = client.createLink(fromConnectorId);
		dbLink.setName(cnneName);
		dbLink.setCreationUser(creatUser);
		MLinkConfig fromLinkConfig = dbLink.getConnectorLinkConfig();
		MStringInput msi = fromLinkConfig
				.getStringInput("linkConfig.connectionString");
		msi.setValue(url);
		fromLinkConfig.getStringInput("linkConfig.jdbcDriver").setValue(driver);
		fromLinkConfig.getStringInput("linkConfig.username").setValue(userName);
		fromLinkConfig.getStringInput("linkConfig.password").setValue(password);
		Status fromStatus = client.saveLink(dbLink);
		if (fromStatus.canProceed()) {
			System.out.println("创建JDBC Link成功，ID为: "
					+ dbLink.getPersistenceId());
		} else {
			System.out.println("创建JDBC Link失败");
		}
		return dbLink;
	}
	/**
	 * 创建一个HDFS的链接
	 * @param client
	 * @param url
	 * @param driver
	 * @param cnneName
	 * @param creatUser
	 * @return
	 */
	public MLink buildHDFSLik(SqoopClient client,String url,
			String cnneName,String creatUser){
		//如果已经存在链接，则使用原来的链接
		List<MLink> mlist= client.getLinks();
		for(MLink link  : mlist){
			String name = link.getName();
			if(name.equals(cnneName)){
				System.out.println("已经存在link============="+cnneName);
				return link;
			}
		}
		//创建一个目的地链接HDFS
        long toConnectorId = 3;
        MLink hdfsLink = client.createLink(toConnectorId);
        hdfsLink.setName(cnneName);
        hdfsLink.setCreationUser(creatUser);
        MLinkConfig toLinkConfig = hdfsLink.getConnectorLinkConfig();
        toLinkConfig.getStringInput("linkConfig.uri").setValue(url);
        Status toStatus = client.saveLink(hdfsLink);
        if(toStatus.canProceed()) {
         System.out.println("创建HDFS Link成功，ID为: " + hdfsLink.getPersistenceId());
        } else {
         System.out.println("创建HDFS Link失败");
        }
        return hdfsLink;
	}
	
	public MJob createDBToHDFSJob(SqoopClient client,MLink fromLink,MLink toLink,String jobName
			,String creatUser,SqoopTableDefine table,String outputPath){
		//创建一个任务
        long fromLinkId = fromLink.getPersistenceId();
        long toLinkId = toLink.getPersistenceId();
        MJob job = client.createJob(fromLinkId, toLinkId);
        job.setName(jobName);
        job.setCreationUser(creatUser);
        //设置源链接任务配置信息
        MFromConfig fromJobConfig = job.getFromJobConfig();
        fromJobConfig.getStringInput("fromJobConfig.schemaName").setValue(table.getSchemaName());
        fromJobConfig.getStringInput("fromJobConfig.tableName").setValue(table.getTableName());
        List<String> columns = table.getPartitionColumn();
        for(String column : columns){
        	fromJobConfig.getStringInput("fromJobConfig.partitionColumn").setValue(column);
        }
        MToConfig toJobConfig = job.getToJobConfig();
        toJobConfig.getStringInput("toJobConfig.outputDirectory").setValue(outputPath);
        MDriverConfig driverConfig = job.getDriverConfig();
        driverConfig.getIntegerInput("throttlingConfig.numExtractors").setValue(3);
        Status status = client.saveJob(job);
        if(status.canProceed()) {
         System.out.println("JOB创建成功，ID为: "+ job.getPersistenceId());
        } else {
         System.out.println("JOB创建失败。");
        }
        
        return job;
	}
	public MJob createHDFSToDBJob(SqoopClient client,MLink fromLink,MLink toLink,String jobName
			,String creatUser,SqoopTableDefine table,String inputPath){
		//创建一个任务
        long fromLinkId = fromLink.getPersistenceId();
        long toLinkId = toLink.getPersistenceId();
        MJob job = client.createJob(fromLinkId, toLinkId);
        job.setName(jobName);
        job.setCreationUser(creatUser);
        //设置源链接任务配置信息
        MToConfig toJobConfig = job.getToJobConfig();
        toJobConfig.getStringInput("toJobConfig.schemaName").setValue(table.getSchemaName());
        toJobConfig.getStringInput("toJobConfig.tableName").setValue(table.getTableName());
        List<String> columns = table.getPartitionColumn();
        for(String column : columns){
        	//toJobConfig.getStringInput("toJobConfig.partitionColumn").setValue(column);
        }
        MFromConfig fromJobConfig = job.getFromJobConfig();
        fromJobConfig.getStringInput("fromJobConfig.inputDirectory").setValue(inputPath);
        MDriverConfig driverConfig = job.getDriverConfig();
        driverConfig.getIntegerInput("throttlingConfig.numExtractors").setValue(3);
        Status status = client.saveJob(job);
        if(status.canProceed()) {
         System.out.println("JOB创建成功，ID为: "+ job.getPersistenceId());
        } else {
         System.out.println("JOB创建失败。");
        }
        
        return job;
	}
	public void startJob(SqoopClient client,MJob job){
		MJob currentJob = null;
		List<MJob> jlist = client.getJobs();
		boolean exists = false;
		for(MJob j : jlist){
			if(job.getName().equals(j.getName())){
				exists = true;
				currentJob = j;
				break;
			}
		}
		if(exists){
			System.out.println("Job"+job.getName()+"已经存在！");
		}else{
			currentJob = job;
			Status status = client.saveJob(currentJob);
	        if(status.canProceed()) {
	         System.out.println("JOB创建成功，ID为: "+ currentJob.getPersistenceId());
	        } else {
	         System.out.println("JOB创建失败。");
	        }
		}
        //启动任务
        long jobId = currentJob.getPersistenceId();
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
        System.out.println("通过sqoop传输数据统计执行完毕");
	}
}
