package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;


/**
 * 分页通用pojo
 * @author yl
 */
@SuppressWarnings("all")
public class EasyUIDataGridResult implements Serializable {

	private long total;
	private List rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
