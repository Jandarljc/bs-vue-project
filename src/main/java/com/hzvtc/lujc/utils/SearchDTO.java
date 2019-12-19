package com.hzvtc.lujc.utils;

import java.io.Serializable;

public class SearchDTO<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private T entity;
	
	private Integer page;
	
	private Integer rows;
	
	public SearchDTO(){}
	
	public SearchDTO(T entity, Integer page, Integer rows) {
		super();
		this.entity = entity;
		this.page = page;
		this.rows = rows;
	}



	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	@Override
	public String toString() {
		return "SearchDTO [entity=" + entity + ", page=" + page + ", rows=" + rows + "]";
	}
	
}
