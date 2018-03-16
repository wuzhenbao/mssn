package com.wuzhenbao.it.core.hdfs.handler;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;

import com.wuzhenbao.it.core.hdfs.service.impl.HdfsService;

/**
 * 单表关联
 * @author lenovo
 *
 */
public class DataJoin {
	private static final Log log = LogFactory.getLog(DataJoin.class);
	private static int time;
	/**
	 * map将输出分割child和parent，然后正序输出一次作为右表，
     * 反序输出一次作为左表，需要注意的是在输出的value中必须
     * 加上左右表的区别标识。
	 * @author lenovo
	 *
	 */
	public static class Map extends Mapper<Object, Text, Text, Text>{
		protected void map(Object key, Text value, Mapper<Object, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			String child = new String();//孩子
			String parent = new String();//父母
			String relationType = new String();//关系
			
			//处理每行输出
			StringTokenizer line = new StringTokenizer(value.toString());
			String[] values = new String[2];
			int i = 0;
			while(line.hasMoreTokens()) {
				values[i] = line.nextToken();
				i++;
			}
			//过滤掉第一行后，把后面的内容输出
			if(values[0].compareTo("child") != 0) {
				child = values[0];
				parent = values[1];
				relationType = "1";
				//输出左表 孩子为key
				log.info("DataJoin.map================"+values[1]+":"+ relationType+"+"+child+"+"+parent);
				context.write(new Text(values[1]), new Text(relationType+"+"+child+"+"+parent));
				relationType = "2";
				//输出右表 父亲为key
				context.write(new Text(values[0]), new Text(relationType+"+"+child+"+"+parent));
				log.info("DataJoin.map================"+values[0]+":"+ relationType+"+"+child+"+"+parent);
			}
		}
		
		
	}
	
	public static class Reduce extends Reducer<Text, Text, Text, Text>{
		@Override
		protected void reduce(Text key, Iterable<Text> values, 
				Reducer<Text, Text, Text, Text>.Context context)
				throws IOException, InterruptedException {
			 // 输出表头
            if (0 == time) {
                context.write(new Text("grandchild"), new Text("grandparent"));
                time++;
            }
            int grandchildnum = 0;
            String[] grandchild = new String[10];
            int grandparentnum = 0;
            String[] grandparent = new String[10];
            Iterator ite = values.iterator();
            while (ite.hasNext()) {
                String record = ite.next().toString();
                log.info("DataJoin.reduce  record================"+record);
                int len = record.length();
                int i = 2;
                if (0 == len) {
                    continue;
                }
 
                // 取得左右表标识
                char relationtype = record.charAt(0);
                // 定义孩子和父母变量
                String childname = new String();
                String parentname = new String();
 
                // 获取value-list中value的child
                while (record.charAt(i) != '+') {
                    childname += record.charAt(i);
                    i++;
                }
 
                i = i + 1;
 
                // 获取value-list中value的parent
                while (i < len) {
                    parentname += record.charAt(i);
                    i++;
                }
 
                // 左表，取出child放入grandchildren
                if ('1' == relationtype) {
                    grandchild[grandchildnum] = childname;
                    grandchildnum++;
                }
 
                // 右表，取出parent放入grandparent
                if ('2' == relationtype) {
                    grandparent[grandparentnum] = parentname;
                    grandparentnum++;
                }
            }
            log.info("DataJoin.reduce  record================");
            // grandchild和grandparent数组求笛卡尔儿积
            if (0 != grandchildnum && 0 != grandparentnum) {
                for (int m = 0; m < grandchildnum; m++) {
                	log.info("DataJoin.reduce  grandchild================"+grandchild[m]);
                    for (int n = 0; n < grandparentnum; n++) {
                    	log.info("DataJoin.reduce  grandparent================"+grandparent[n]);
                        // 输出结果
                        context.write(new Text(grandchild[m]), new Text(grandparent[n]));
                    }
                }
            }
		}
	}
}
