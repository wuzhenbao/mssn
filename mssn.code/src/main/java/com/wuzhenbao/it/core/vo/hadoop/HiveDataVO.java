package com.wuzhenbao.it.core.vo.hadoop;

import com.wuzhenbao.it.core.vo.common.BaseVO;

public class HiveDataVO extends BaseVO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7821258030550551658L;
	private String column1;
	private String column2;
	private String column3;
	private String column4;
	private String column5;
	private String column6;
	private String column7;
	private String column8;
	private String column9;
	private String column10;
	private String column11;
	public String getColumn1() {
		return column1;
	}
	public void setColumn1(String column1) {
		this.column1 = column1;
	}
	public String getColumn2() {
		return column2;
	}
	public void setColumn2(String column2) {
		this.column2 = column2;
	}
	public String getColumn3() {
		return column3;
	}
	public void setColumn3(String column3) {
		this.column3 = column3;
	}
	public String getColumn4() {
		return column4;
	}
	public void setColumn4(String column4) {
		this.column4 = column4;
	}
	public String getColumn5() {
		return column5;
	}
	public void setColumn5(String column5) {
		this.column5 = column5;
	}
	public String getColumn6() {
		return column6;
	}
	public void setColumn6(String column6) {
		this.column6 = column6;
	}
	public String getColumn7() {
		return column7;
	}
	public void setColumn7(String column7) {
		this.column7 = column7;
	}
	public String getColumn8() {
		return column8;
	}
	public void setColumn8(String column8) {
		this.column8 = column8;
	}
	public String getColumn9() {
		return column9;
	}
	public void setColumn9(String column9) {
		this.column9 = column9;
	}
	public String getColumn10() {
		return column10;
	}
	public void setColumn10(String column10) {
		this.column10 = column10;
	}
	public String getColumn11() {
		return column11;
	}
	public void setColumn11(String column11) {
		this.column11 = column11;
	}
	public void setColumnByIndex(int i,String value) {
		if(i == 1) {setColumn1(value);}
		if(i == 2) {setColumn2(value);}
		if(i == 3) {setColumn3(value);}
		if(i == 4) {setColumn4(value);}
		if(i == 5) {setColumn5(value);}
		if(i == 6) {setColumn6(value);}
		if(i == 7) {setColumn7(value);}
		if(i == 8) {setColumn8(value);}
		if(i == 9) {setColumn9(value);}
		if(i == 10) {setColumn10(value);}
		if(i == 11) {setColumn11(value);}
	}
}
