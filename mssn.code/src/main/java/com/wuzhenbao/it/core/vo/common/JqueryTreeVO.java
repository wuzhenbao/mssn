package com.wuzhenbao.it.core.vo.common;

import java.util.List;

public class JqueryTreeVO extends BaseVO {

	   /**
	 * 
	 */
	private static final long serialVersionUID = -3215794363149035348L;
		//目录或文件ID，使用全路径标识
		private String id;
		//目录或文件名称
		private String text;
		//目录状态，true为展开，false为收缩
		private boolean state;
		//目录或文件属性
		private List<String> attributes;
		public String getId() {
			return this.id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getText() {
			return text;
		}
		public void setText(String text) {
			this.text = text;
		}
		public boolean isState() {
			return state;
		}
		public void setState(boolean state) {
			this.state = state;
		}
		public List<String> getAttributes() {
			return attributes;
		}
		public void setAttributes(List<String> attributes) {
			this.attributes = attributes;
		}
}
