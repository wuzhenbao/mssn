package com.wuzhenbao.it.core.hbase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.log4j.Logger;

public class HBaseTools {
	static Logger log = Logger.getLogger(HBaseTools.class);
	private static Configuration conf = null;
	 
    static {
        conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.quorum", "192.168.137.2");
        conf.set("hbase.zookeeper.property.clientPort", "2181");
    }
    
    public static Configuration getConf(){
    	if(null == conf){
    		 conf = HBaseConfiguration.create();
    	     conf.set("hbase.zookeeper.quorum", "192.168.72.146");
    	     conf.set("hbase.zookeeper.property.clientPort", "2181");
    	}
    	return conf;
    }
    
    public static boolean isExists(String tabName) throws Exception{
    	HBaseAdmin admin = new HBaseAdmin(conf);
    	boolean exists = admin.tableExists(Bytes.toBytes(tabName));
    	admin.close();
    	return exists;
    }
    
    public static void deleteTable(String tableName) throws Exception{
    	HBaseAdmin admin = new HBaseAdmin(conf);
    	boolean exists = isExists(tableName);
    	log.info("==============���Ƿ���ڣ�"+exists);
    	if(exists){
    		admin.disableTable(tableName);
        	admin.deleteTable(tableName);
        	log.info("===============ɾ���"+tableName+"�ɹ�");
    	}
    	admin.close();
    }
    
    public static void createTable(String tableName,String[] columnFamilys,boolean override)throws Exception{
    	
    	HBaseAdmin admin =  new HBaseAdmin(conf);
    	boolean exists = isExists(tableName);
    	if(exists){
    		log.info("��"+tableName+"�Ѿ�����");
    		if(override){
    			deleteTable(tableName);
    			log.info("��"+tableName+"�Ѿ�ɾ��");
    		}else{
    			log.info("���Ѵ��ڣ������´���");
    			admin.close();
    			return;
    		}
    	}
    	HTableDescriptor table = new HTableDescriptor(TableName.valueOf(tableName));
    	for(String family : columnFamilys){
    		table.addFamily(new HColumnDescriptor(Bytes.toBytes(family)));
    	}
    	admin.createTable(table);
    	log.info("��"+tableName+"�Ѿ������ɹ�");
    	admin.close();
    }
    
    // ���һ�����
    public static void addRow(String tableName, String row,
            String columnFamily, String column, String value) throws Exception {
        HTable table = new HTable(conf, tableName);
        Put put = new Put(Bytes.toBytes(row));// ָ����
        // ����ֱ�:���塢�С�ֵ
        put.add(Bytes.toBytes(columnFamily), Bytes.toBytes(column),
                Bytes.toBytes(value));
        table.put(put);
        table.close();
    }
 // ɾ��һ��(��)���
    public static void delRow(String tableName, String row) throws Exception {
        HTable table = new HTable(conf, tableName);
        Delete del = new Delete(Bytes.toBytes(row));
        table.delete(del);
        table.close();
    }
    public static void delMultiRows(String tableName, String[] rows)
            throws Exception {
        HTable table = new HTable(conf, tableName);
        List<Delete> delList = new ArrayList<Delete>();
        for (String row : rows) {
            Delete del = new Delete(Bytes.toBytes(row));
            delList.add(del);
        }
        table.delete(delList);
        table.close();
    }
    public static void getRow(String tableName, String row) throws Exception {
        HTable table = new HTable(conf, tableName);
        Get get = new Get(Bytes.toBytes(row));
        Result result = table.get(get);
        for(Cell cell : result.rawCells()){
        	log.info("����:"+s(CellUtil.cloneRow(cell)));
        	long l =cell.getTimestamp();
        	Date d =new Date();
        	d.setTime(l);
        	log.info("ʱ���:"+new SimpleDateFormat("YYYY-MM-DD").format(d));
        	log.info("������:"+s(CellUtil.cloneFamily(cell)));
        	log.info("����:"+s(CellUtil.cloneQualifier(cell)));
        	log.info("ֵ:"+s(CellUtil.cloneValue(cell)));
        }
        table.close();
    }
    public static void getAllRows(String tableName) throws Exception {
        HTable table = new HTable(conf, tableName);
        Scan scan = new Scan();
        ResultScanner results = table.getScanner(scan);
        for (Result result : results) {
        	 for(Cell cell : result.rawCells()){
        		 log.info("����:"+s(CellUtil.cloneRow(cell)));
             	long l =cell.getTimestamp();
             	Date d =new Date();
             	d.setTime(l);
             	log.info("ʱ���:"+new SimpleDateFormat("yyyy-mm-dd").format(d));
             	log.info("������:"+s(CellUtil.cloneFamily(cell)));
             	log.info("����:"+s(CellUtil.cloneQualifier(cell)));
             	log.info("ֵ:"+s(CellUtil.cloneValue(cell)));
             }
        }
        table.close();
    }
    
	public static byte[] b(String s){
		if(null == s){
			return null;
		}
		return Bytes.toBytes(s);
	}
	public static String s(byte[] b){
		if(null == b){
			return null;
		}
		return new String(b);
	}
}
