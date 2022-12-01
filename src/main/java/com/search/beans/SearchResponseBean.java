package com.search.beans;

import java.util.List;

public class SearchResponseBean {
	private List<SearchTextCountBean> counts;

	public SearchResponseBean() {

	}

	public SearchResponseBean(List<SearchTextCountBean> counts) {
		setCounts(counts);
	}

	public List<SearchTextCountBean> getCounts() {
		return counts;
	}

	public void setCounts(List<SearchTextCountBean> counts) {
		this.counts = counts;
	}

}
