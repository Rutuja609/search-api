package com.search.beans;

import java.util.List;

public class CSVDataBean {
	private List<String[]> data;

	public CSVDataBean(List<String[]> data) {
		this.data = data;
	}

	public List<String[]> getData() {
		return data;
	}
}