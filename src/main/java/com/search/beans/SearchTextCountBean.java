package com.search.beans;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.search.util.SearchTextCountBeanSerializer;

@JsonSerialize(using = SearchTextCountBeanSerializer.class)
public class SearchTextCountBean extends SearchTextCount{

	public SearchTextCountBean() {
		super();
	}

	public SearchTextCountBean(String text, int count) {
		super(text, count);
	}
}