package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * 多表关联
 * @author lenovo
 *
 */
public class DataJoinMutl {
	private static final Log log = LogFactory.getLog(DataJoinMutl.class);
	private static int time = 0;
	
	/**
	 * 处理Map类
	 * @author lenovo
	 *
	 */
	public static class Map extends Mapper<Object, Text,Text, Text>{
		@Override
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			//以学生  -- 教师关系为例  学生表中有教师id  以教师ID为key，输出学生和教师的信息 学生信息为S  教师信息 为T
			//学生表第一个字段以S开关  教师表以T开关
			String line = value.toString();
			String flag = HdfsTools.getStrByIndex(0, line);
			if("TID".equals(flag) || "SID".equals(flag)) {
				return;
			}
			String teachId = null;
			if("T".equals(flag)) {
				teachId = HdfsTools.getStrByIndex(1, line);
			}else {
				teachId = HdfsTools.getStrByIndex(3, line);
			}
			log.info("map========="+teachId+","+ value);
			//输出内容，
			context.write(new Text(teachId), value);
		}
	}
	
	/**
	 * 处理reduce
	 * @author lenovo
	 *
	 */
	public static class Reduce extends Reducer<Text,Text,Text, Text>{
		@Override
		protected void reduce(Text key, Iterable<Text> values, Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			// 接收相同的KEY，把学生与教师的信息关联起来
			if (0 == time) {
				// context.write(new Text("factoryname"), new
				// Text("addressname"));
				time++;
			}
			Iterator<Text> ite = values.iterator();
			String[] student = new String[30];
			String teacher = null;
			int index = 0;//循环输出，学生保存到列表，每个学生输出一次，后面跟上教师的信息
			while(ite.hasNext()) {
				
				String line = ite.next().toString();
				log.info("reduce ==============key="+key.toString()+",=========line:"+line);
				if(null == line || line.trim().equals("")) {
					continue;
				}
				//因为输出的第一列是教师的ID，所以要第二列为flag
				String flag = HdfsTools.getStrByIndex(0, line);
				if("T".equals(flag)) {
					teacher = line;
				}else {
					student[index] = line;
					index++;
				}
			}
			for(String str : student) {
				if(null != str) {
					log.info("reduce out==============key="+key+",str="+str+",teach="+teacher);
					context.write(key, new Text(str+" "+teacher));
				}
			}
		}
	}
}
