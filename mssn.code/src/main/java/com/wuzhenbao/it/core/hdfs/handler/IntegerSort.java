package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

public class IntegerSort {
	
	//map类
	public static class Map extends Mapper<Object, Text, IntWritable, IntWritable>{
		private static IntWritable data = new IntWritable();
		//map  把输入的数据作为key输出
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, IntWritable,
				IntWritable>.Context context)
				throws IOException, InterruptedException {
			String line = value.toString();
			data.set(Integer.parseInt(line));
			context.write(data, new IntWritable(1));
		}
	}
	
	//reduce类
	public static class Reduce extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable>{
		//reduce将输入中的key复制到输出数据的key上，
	    //然后根据输入的value-list中元素的个数决定key的输出次数
	    //用全局linenum来代表key的位次
		private static IntWritable linenum = new IntWritable();
		
		//reduce
		protected void reduce(IntWritable key, Iterable<IntWritable> values,
				Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
				throws IOException, InterruptedException {
			for(IntWritable val : values) {
				context.write(linenum, key);
				linenum = new IntWritable(linenum.get() + 1);
			}
		}
	}
}
