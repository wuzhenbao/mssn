package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.hdfs.HdfsUtil;


public class HdfsHandler implements IHdfsHandler{
	
	private static final Log log = LogFactory.getLog(HdfsHandler.class);
	
	/**
	 * 数据去重
	 * @param inputPath 需要计算的源文件位置
	 * @param outputPath 计算结果输出的目录
	 * @return
	 * @throws MssnApplicationException
	 */
	@SuppressWarnings("deprecation")
	public boolean dataDistinct(String inputPath,String outputPath)
			throws MssnApplicationException {
		boolean flag = false;
		try {
			Configuration conf = HdfsUtil.getConf();

			Job job = new Job(conf);
			// 自动根据当前时间生成job的名称，格式:Job_线程名称_时间数
			String jobName = "Job_" + Thread.currentThread().getName() + "_" + new Date().getTime();

			job.setJobName(jobName);

			// job.setJarByClass(Dedup.class);

			FileInputFormat.addInputPath(job, new Path(HdfsUtil.formatHdfsPath(inputPath)));

			FileOutputFormat.setOutputPath(job, new Path(HdfsUtil.formatHdfsPath(outputPath)));
			job.setMapperClass(Dedup.Map.class);

			job.setReducerClass(Dedup.Reduce.class);

			job.setOutputKeyClass(Text.class);

			job.setOutputValueClass(Text.class);

			job.waitForCompletion(true);
			flag = true;
		} catch (IllegalStateException e) {
			throw new MssnApplicationException(e);
		} catch (IllegalArgumentException e) {
			throw new MssnApplicationException(e);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (IOException e) {
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			throw new MssnApplicationException(e);
		}catch (Exception e) {
			throw new MssnApplicationException(e);
		}
		return flag;
	}
	public boolean sort(String inputPath,String outputPath)throws MssnApplicationException {
		boolean flag = false;
		try {
			Configuration conf = HdfsUtil.getConf();

			Job job = new Job(conf);
			// 自动根据当前时间生成job的名称，格式:Job_线程名称_时间数
			String jobName = "Job_" + Thread.currentThread().getName() + "_" + new Date().getTime();

			job.setJobName(jobName);

			// job.setJarByClass(Dedup.class);

			FileInputFormat.addInputPath(job, new Path(HdfsUtil.formatHdfsPath(inputPath)));

			FileOutputFormat.setOutputPath(job, new Path(HdfsUtil.formatHdfsPath(outputPath)));

			job.setMapperClass(IntegerSort.Map.class);

			job.setReducerClass(IntegerSort.Reduce.class);

			job.setOutputKeyClass(IntWritable.class);

			job.setOutputValueClass(IntWritable.class);

			job.waitForCompletion(true);
			flag = true;
		} catch (IllegalStateException e) {
			throw new MssnApplicationException(e);
		} catch (IllegalArgumentException e) {
			throw new MssnApplicationException(e);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (IOException e) {
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			throw new MssnApplicationException(e);
		}catch (Exception e) {
			throw new MssnApplicationException(e);
		}
		return flag;
	}
	public boolean avg(String inputPath, String outputPath) throws MssnApplicationException {
		boolean flag = false;
		try {
			Configuration conf = HdfsUtil.getConf();

			Job job = new Job(conf);
			// 自动根据当前时间生成job的名称，格式:Job_线程名称_时间数
			String jobName = "Job_" + Thread.currentThread().getName() + "_" + new Date().getTime();

			job.setJobName(jobName);

			// job.setJarByClass(Dedup.class);

			FileInputFormat.addInputPath(job, new Path(HdfsUtil.formatHdfsPath(inputPath)));

			FileOutputFormat.setOutputPath(job, new Path(HdfsUtil.formatHdfsPath(outputPath)));

			job.setMapperClass(Avg.Map.class);

			job.setReducerClass(Avg.Reduce.class);

			job.setOutputKeyClass(Text.class);

			job.setOutputValueClass(IntWritable.class);

			job.waitForCompletion(true);
			flag = true;
		} catch (IllegalStateException e) {
			throw new MssnApplicationException(e);
		} catch (IllegalArgumentException e) {
			throw new MssnApplicationException(e);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (IOException e) {
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			throw new MssnApplicationException(e);
		}catch (Exception e) {
			throw new MssnApplicationException(e);
		}
		return flag;
	}
	public boolean dataJoin(String inputPath, String outputPath) throws MssnApplicationException {
		boolean flag = false;
		try {
			Configuration conf = HdfsUtil.getConf();

			Job job = new Job(conf);
			// 自动根据当前时间生成job的名称，格式:Job_线程名称_时间数
			String jobName = "Job_" + Thread.currentThread().getName() + "_" + new Date().getTime();

			job.setJobName(jobName);

			// job.setJarByClass(Dedup.class);

			FileInputFormat.addInputPath(job, new Path(HdfsUtil.formatHdfsPath(inputPath)));

			FileOutputFormat.setOutputPath(job, new Path(HdfsUtil.formatHdfsPath(outputPath)));

			job.setMapperClass(DataJoin.Map.class);

			job.setReducerClass(DataJoin.Reduce.class);

			job.setOutputKeyClass(Text.class);

			job.setOutputValueClass(Text.class);

			job.waitForCompletion(true);
			flag = true;
		} catch (IllegalStateException e) {
			throw new MssnApplicationException(e);
		} catch (IllegalArgumentException e) {
			throw new MssnApplicationException(e);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (IOException e) {
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			throw new MssnApplicationException(e);
		}catch (Exception e) {
			throw new MssnApplicationException(e);
		}
		return flag;
	}
	public boolean dataJoinMult(String inputPath, String outputPath) throws MssnApplicationException {
		boolean flag = false;
		try {
			Configuration conf = HdfsUtil.getConf();

			Job job = new Job(conf);
			// 自动根据当前时间生成job的名称，格式:Job_线程名称_时间数
			String jobName = "Job_" + Thread.currentThread().getName() + "_" + new Date().getTime();

			job.setJobName(jobName);

			// job.setJarByClass(Dedup.class);

			FileInputFormat.addInputPath(job, new Path(HdfsUtil.formatHdfsPath(inputPath)));

			FileOutputFormat.setOutputPath(job, new Path(HdfsUtil.formatHdfsPath(outputPath)));

			job.setMapperClass(DataJoinMutl.Map.class);

			job.setReducerClass(DataJoinMutl.Reduce.class);

			job.setOutputKeyClass(Text.class);

			job.setOutputValueClass(Text.class);

			job.waitForCompletion(true);
			flag = true;
		} catch (IllegalStateException e) {
			throw new MssnApplicationException(e);
		} catch (IllegalArgumentException e) {
			throw new MssnApplicationException(e);
		} catch (ClassNotFoundException e) {
			throw new MssnApplicationException(e);
		} catch (IOException e) {
			throw new MssnApplicationException(e);
		} catch (InterruptedException e) {
			throw new MssnApplicationException(e);
		}catch (Exception e) {
			throw new MssnApplicationException(e);
		}
		return flag;
	}
	public boolean sortDesc(String inputPath, String outputPath) throws MssnApplicationException {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
