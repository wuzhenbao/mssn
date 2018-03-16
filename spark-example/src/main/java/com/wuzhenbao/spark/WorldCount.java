package com.wuzhenbao.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

public class WorldCount {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local").setAppName("wc");
		JavaSparkContext sc = new JavaSparkContext(conf);
		JavaRDD<String> rdd1 = sc.textFile("access_log.txt");
		
		rdd1.foreach(s ->{
			System.out.println(s);
		});
		System.out.println(rdd1.count());
		sc.close();
	}
}
