package com.search.beans;

public class Error {

	private String code;
	private String issue;
	private Integer status;
	private String suggestedAction;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getSuggestedAction() {
		return suggestedAction;
	}
	public void setSuggestedAction(String suggestedAction) {
		this.suggestedAction = suggestedAction;
	}
}