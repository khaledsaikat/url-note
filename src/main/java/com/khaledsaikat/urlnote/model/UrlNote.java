package com.khaledsaikat.urlnote.model;

public class UrlNote {

	private String url;

	private String title;

	private String status;

	private String note;

	public UrlNote() {

	}

	public UrlNote(String url) {
		this.setUrl(url);
	}

	public String getUrl() {
		return url;
	}

	/**
	 * Strip query string form a url
	 * 
	 * @param String url
	 * @return String
	 */
	private String cleanupUrl(String url) {
		return url.split("\\?")[0];
	}

	public void setUrl(String url) {
		this.url = this.cleanupUrl(url);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
}