package com.wuzhenbao.it.core.vo;


import java.io.Serializable;
import java.util.List;


@SuppressWarnings("serial")
public class Pager implements Serializable {

	public Pager(){}
	// 默认每页显示15条记录
	protected Integer pageSize = 15;

	// 默认当前从第一页开始
	protected Integer pageNo = 1;

	// 默认所有行数据 为0
	protected Integer rowCount = 0;

	// 默认页数为0
	protected Integer pageCount = 0;

	// 起始行数
	protected Integer startIndex = 0;

	// 结束行数
	protected Integer endIndex = 0;
	//第一页的面码
	protected Integer firstPageNo = 1;
	//前一页
	protected Integer prePageNo = 1;
	//下一页
	protected Integer nextPageNo = 1;
	//第后一页
	protected Integer lastPageNo = 1;

	//返回第一页的数据List
	protected List<?> resultList;
	
	protected String actionPath;
	
	public String getActionPath() {
		return actionPath;
	}

	public void setActionPath(String actionPath) {
		this.actionPath = actionPath;
	}
	 public void setTotalRecord(int totalRecord) {  
	       this.rowCount = totalRecord;  
	       //在设置总页数的时候计算出对应的总页数，在下面的三目运算中加法拥有更高的优先级，所以最后可以不加括号。  
	       int totalPage = totalRecord%pageSize==0 ? totalRecord/pageSize : totalRecord/pageSize + 1;  
	       this.setPageCount(totalPage); 
	    }  
	public Pager(Integer pageSize, Integer pageNo, Integer rowCount, List<?> resultList) throws Exception {
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.rowCount = rowCount;
		this.resultList = resultList;
		if (rowCount % pageSize == 0) {
			this.pageCount = rowCount / pageSize;
		} else {
			this.pageCount = rowCount / pageSize + 1;
		}
		this.startIndex = pageSize * (pageNo - 1);
		this.endIndex = this.startIndex + resultList.size();
		this.lastPageNo = this.pageCount;
		if (this.pageNo > 1) this.prePageNo = this.pageNo -1;
		if (this.pageNo == this.lastPageNo){
			this.nextPageNo = this.lastPageNo;
		} else {
			this.nextPageNo = this.pageNo + 1;
		}
	}
	

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public Integer getEndNo(){
		return (this.getStartIndex() + this.getPageSize());
	}
	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public List<?> getResultList() {
		return resultList;
	}

	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	public Integer getRowCount() {
		return rowCount;
	}

	public void setRowCount(Integer rowCount) {
		this.rowCount = rowCount;
	}

	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getFirstPageNo() {
		return firstPageNo;
	}

	public void setFirstPageNo(Integer firstPageNo) {
		this.firstPageNo = firstPageNo;
	}

	public Integer getLastPageNo() {
		return lastPageNo;
	}

	public void setLastPageNo(Integer lastPageNo) {
		this.lastPageNo = lastPageNo;
	}

	public Integer getNextPageNo() {
		return nextPageNo;
	}

	public void setNextPageNo(Integer nextPageNo) {
		this.nextPageNo = nextPageNo;
	}

	public Integer getPrePageNo() {
		return prePageNo;
	}

	public void setPrePageNo(Integer prePageNo) {
		this.prePageNo = prePageNo;
	}

	
}
