package com.wuzhenbao.it.core.hdfs;


import java.io.IOException;
import java.io.InputStream;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

public class TestHdfs {
	static Logger log = Logger.getLogger(TestHdfs.class);
			
	
	public static void createFile(String filePath,String contents) throws Exception{
		FileSystem fs = HdfsUtil.getFS();
		Path file = new Path(filePath);
		boolean exists = fs.exists(file);
		if(exists){
			log.info("文件系统已经存在，先执行删除");
			fs.delete(file,true);
		}
		
		FSDataOutputStream output = fs.create(file);
		log.info("目录名称==="+file.getName());
		output.write(Bytes.toBytes(contents));
		output.close();
		fs.close();
		log.info("新建文件/目前完成");
	}
	
    /**
     * 上传文件
     * @param src
     * @param dst
     * @throws Exception
     */
	public static void uploadFile(String src,String dst) throws Exception{
    	Configuration conf = HdfsUtil.getConf();
        FileSystem fs = HdfsUtil.getFS();
        Path srcPath = new Path(src); //ԭ·��
        Path dstPath = new Path(dst); //Ŀ��·��
        fs.copyFromLocalFile(false,srcPath, dstPath);
        
        System.out.println("Upload to "+conf.get("fs.default.name"));
        System.out.println("------------list files------------"+"\n");
        FileStatus [] fileStatus = fs.listStatus(dstPath);
        for (FileStatus file : fileStatus) 
        {
            System.out.println(file.getPath());
        }
        fs.close();
    }
    
    /**
     * 重命名文件
     * @param oldName
     * @param newName
     * @throws IOException
     */
    public static void rename(String oldName,String newName) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path oldPath = new Path(oldName);
        Path newPath = new Path(newName);
        boolean isok = fs.rename(oldPath, newPath);
        if(isok){
            System.out.println("rename ok!");
        }else{
            System.out.println("rename failure");
        }
        fs.close();
    }
   /**
    * 
    * @param filePath
    * @throws IOException
    */
    public static void delete(String filePath) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path path = new Path(filePath);
        boolean isok = fs.deleteOnExit(path);
        if(isok){
            System.out.println("delete ok!");
        }else{
            System.out.println("delete failure");
        }
        fs.close();
    }
    
    //����Ŀ¼
    public static void mkdir(String path) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(path);
        boolean isok = fs.mkdirs(srcPath);
        if(isok){
            System.out.println("create dir ok!");
        }else{
            System.out.println("create dir failure");
        }
        fs.close();
    }
    
    /**
     * 读取文件
     * @param filePath
     * @throws IOException
     */
    public static void readFile(String filePath) throws IOException{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(filePath);
        InputStream in = null;
        try {
            in = fs.open(srcPath);
            IOUtils.copyBytes(in, System.out, 4096, false); //���Ƶ���׼�����
       } finally {
           IOUtils.closeStream(in);
       }
   }
   
   
   public static void main(String[] args) throws Exception {
       //uploadFile("D:\\c.txt", "/user/hadoop/test/");
       /*byte[] contents =  "hello world �������\n".getBytes();
       createFile("/user/hadoop/test1/d.txt",contents);*/
       //rename("/user/hadoop/test/d.txt", "/user/hadoop/test/dd.txt");
       //delete("test/dd.txt"); //ʹ�����·��
       //delete("test1");    //ɾ��Ŀ¼
       //mkdir("test1");
	   //delete("/test/hdfs/test1/d.txt");
       createFile("/test/hdfs/test1/d.txt","aaaaaaa");
       //readFile("/test/hdfs/test1/d.txt");
   }
}
