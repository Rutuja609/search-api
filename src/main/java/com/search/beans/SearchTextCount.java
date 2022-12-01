package com.search.beans;

public class SearchTextCount {		
	private String text;
	private int count;

	public SearchTextCount() {

	}

	public SearchTextCount(String text, int count) {
		setText(text);
		setCount(count);
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}