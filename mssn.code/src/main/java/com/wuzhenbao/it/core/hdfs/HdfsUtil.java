package com.wuzhenbao.it.core.hdfs;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.IOUtils;
import org.apache.log4j.Logger;

import com.wuzhenbao.it.core.exception.MssnApplicationException;
import com.wuzhenbao.it.core.vo.hadoop.HdfsDirVO;

public class HdfsUtil {
	static Logger log = Logger.getLogger(HdfsUtil.class);
	static Configuration conf;
	static {
		conf = new Configuration();
	}

	public static FileSystem getFS() throws Exception {
		FileSystem fs = FileSystem.get(conf);
		return fs;
	}

	public static Configuration getConf() {
		if (null == conf) {
			conf = new Configuration();
		}
		return conf;
	}
	/**
	 * 上传文件到HDFS
	 * @param file
	 * @param dest
	 * @return
	 */
	public static boolean uploadToHDFS(FileItem file, String dest) throws Exception{
		FileSystem fs = null;
		try {
			fs = HdfsUtil.getFS();
			Path path = new Path(fs.getUri()+dest+"/"+file.getName());
			boolean isexist = fs.exists(path);
			InputStream in = new BufferedInputStream(file.getInputStream());
			/**
			 * FieSystem的create方法可以为文件不存在的父目录进行创建，
			 */
			OutputStream out =null;
			if(isexist) {
				out = fs.append(path);
			}else {
				out =fs.create(path);
			}
			
			IOUtils.copyBytes(in, out, 4096, true);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取hadoop的文件目录
	 * @param hdvo
	 * @return
	 * @throws MssnApplicationException
	 */
	public static HdfsDirVO getFileByPath(HdfsDirVO hdvo)
			throws MssnApplicationException {
		FileSystem fs = null;
		List<HdfsDirVO> children = new ArrayList<HdfsDirVO>();
		try {
			//获取文件系统管理员
			fs = HdfsUtil.getFS();
			//如果是/目录，则直接查询，否则要串上父层目录
			String parentPath = hdvo.isRoot()?hdvo.getText():hdvo.getParentPath()+hdvo.getText();
			Path path = new Path(parentPath);
			FileStatus[] list = fs.listStatus(path);
			//循环遍历文件目录
			for (FileStatus f : list) {
				System.out.printf("name: %s, folder: %s, size: %d\n", f.getPath(), 
						f.isDirectory(), f.getLen());
				HdfsDirVO tempdir = new HdfsDirVO();
				tempdir.setParentPath(parentPath.equals("/")?parentPath:parentPath+"/");
				tempdir.setState(false);
				tempdir.setText(f.getPath().getName());
				tempdir.setDir(f.isDirectory());
				//如果当前文件是目录，则继续往下循环子目录
				if(f.isDirectory()) {
					HdfsUtil.getFileByPath(tempdir);
				}
				children.add(tempdir);
			}
			hdvo.setChildren(children);
		} catch (Exception e) {
			throw new MssnApplicationException(e);
		}/*finally {
			try {
				fs.close();
			}catch (IOException e) {
				throw new MssnApplicationException(e);
			}
		}*/
		return hdvo;
	}
	/**
	 * 获取目录下的子目录及文档列表
	 * @return
	 * @throws MssnApplicationException
	 */
	public static HdfsDirVO getFileByPath() throws MssnApplicationException {
		HdfsDirVO hdvo = new HdfsDirVO();
		hdvo.setText("/");
		hdvo.setDir(true);
		hdvo.setRoot(true);
		return getFileByPath(hdvo);
	}
	/**
	 * 创建新文档
	 * @param filePath
	 * @param contents
	 * @throws Exception
	 */
	public static void createFile(String filePath, String contents)
			throws Exception {
		FileSystem fs = HdfsUtil.getFS();
		Path file = new Path(filePath);
		boolean exists = fs.exists(file);
		if (exists) {
			log.info("文件系统已经存在，先执行删除");
			fs.delete(file, true);
		}

		FSDataOutputStream output = fs.create(file);
		log.info("目录名称===" + file.getName());
		output.write(Bytes.toBytes(contents));
		output.close();
		fs.close();
		log.info("新建文件/目前完成");
	}

	/**
	 * 从本地的文件系统上传文件到HDFS
	 * 
	 * @param src
	 * @param dst
	 * @throws Exception
	 */
	public static void uploadFile(String src, String dst) throws Exception {
		Configuration conf = HdfsUtil.getConf();
		FileSystem fs = HdfsUtil.getFS();
		Path srcPath = new Path(src); // ԭ·��
		Path dstPath = new Path(dst); // Ŀ��·��
		fs.copyFromLocalFile(false, srcPath, dstPath);

		System.out.println("Upload to " + conf.get("fs.default.name"));
		System.out.println("------------list files------------" + "\n");
		FileStatus[] fileStatus = fs.listStatus(dstPath);
		for (FileStatus file : fileStatus) {
			System.out.println(file.getPath());
		}
		fs.close();
	}

	/**
	 * 重命名文件
	 * 
	 * @param oldName
	 * @param newName
	 * @throws IOException
	 */
	public static void rename(String oldName, String newName)
			throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path oldPath = new Path(oldName);
		Path newPath = new Path(newName);
		boolean isok = fs.rename(oldPath, newPath);
		if (isok) {
			System.out.println("rename ok!");
		} else {
			System.out.println("rename failure");
		}
		fs.close();
	}

	/**
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void delete(String filePath) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path path = new Path(filePath);
		boolean isok = fs.deleteOnExit(path);
		if (isok) {
			System.out.println("delete ok!");
		} else {
			System.out.println("delete failure");
		}
		fs.close();
	}

	// ����Ŀ¼
	public static void mkdir(String path) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path srcPath = new Path(path);
		boolean isok = fs.mkdirs(srcPath);
		if (isok) {
			System.out.println("create dir ok!");
		} else {
			System.out.println("create dir failure");
		}
		fs.close();
	}
	/**
	 * 检查目录是否存在
	 * @param dir
	 * @return
	 * @throws Exception
	 */
	public static boolean exists(String dir)throws Exception{
		FileSystem fs = HdfsUtil.getFS();
		Path file = new Path(dir);
		return fs.exists(file);
	}
	/**
	 * 把本地路径转换成HDFS路径
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public static String formatHdfsPath(String path)throws Exception {
		return HdfsUtil.getFS().getUri()+path;
	}
	/**
	 * 读取文件
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void readFile(String filePath) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);
		Path srcPath = new Path(filePath);
		InputStream in = null;
		try {
			in = fs.open(srcPath);
			IOUtils.copyBytes(in, System.out, 4096, false); // ���Ƶ���׼�����
		} finally {
			IOUtils.closeStream(in);
		}
	}
}
