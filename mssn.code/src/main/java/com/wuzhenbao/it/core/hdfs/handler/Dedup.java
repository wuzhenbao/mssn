package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 数据去重类
 * @author lenovo
 *
 */
public class Dedup {
	//Map
	public static class Map extends Mapper<Object,Text,Text,Text>{
		//每行数据
		private static Text line = new Text();
		
		//实现map函数
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			line = value;
			context.write(line, new Text(""));
		}
	}
	
	//Reduce  
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		//实现reduce函数  将输入中的key复制到输出数据的key上，并直接输出
		@Override
		protected void reduce(Text key, Iterable<Text> values, 
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			context.write(key, new Text(""));
		}
	}
}
