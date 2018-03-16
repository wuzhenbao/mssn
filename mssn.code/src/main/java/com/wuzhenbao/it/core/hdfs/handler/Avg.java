package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;


/**
 * 求平均数
 * 对输入文件中数据进行计算平均。例如输入文件中的每行内容均为一个学生的姓名和他相应的成绩，如果有多门学科，则每门学科为一个文件。
 * 要求在输出中每行有两个间隔的数据，其中，第一个代表学生的姓名，第二个代表其平均成绩
 * @author lenovo
 *
 */
public class Avg {
	private static final Log log = LogFactory.getLog(Avg.class);
	/**
	 * 
	 * @author lenovo
	 *
	 */
	public static class Map extends Mapper<LongWritable, Text, Text, IntWritable>{
		protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
				throws IOException, InterruptedException {
			// 将输入的纯文本文件的数据转化成String
			String line = new String(value.toString().getBytes("ISO-8859-1"), "UTF-8");
			log.info("avg.line================="+line);
			// 将输入的数据首先按行进行分割
			StringTokenizer tokenizer = new StringTokenizer(line);
			//
			String name = tokenizer.nextToken();
			String score = tokenizer.nextToken();
			Text outName = new Text(name);
			// 输出姓名和数字
			context.write(outName, new IntWritable(Integer.parseInt(score)));
			/*
			 * StringTokenizer tokenizer = new StringTokenizer(line, "\n");
			 * //分别对每一行进行处理 while(tokenizer.hasMoreElements()) { //
			 * StringTokenizer tokenizerLine = new
			 * StringTokenizer(tokenizer.nextToken()); String name =
			 * tokenizerLine.nextToken(); String score =
			 * tokenizerLine.nextToken();
			 * 
			 * Text outName = new Text(name);
			 * 
			 * //输出姓名和数字 context.write(outName, new
			 * IntWritable(Integer.parseInt(score))); }
			 */
		}
	}
	
	/**
	 * 
	 * @author lenovo
	 *
	 */
	public static class Reduce extends Reducer<Text, IntWritable, Text, IntWritable>{

		protected void reduce(Text key, Iterable<IntWritable> value,
				Reducer<Text, IntWritable, Text, IntWritable>.Context context) throws IOException, InterruptedException {
			int sum = 0;
			int count = 0;
			Iterator<IntWritable> iterator = value.iterator();
			while(iterator.hasNext()) {
				sum += iterator.next().get();//计算汇总数
				count ++;//计算个数
			}
			int avg = (int)sum / count; 
			context.write(key, new IntWritable(avg));
		}
	}
}
