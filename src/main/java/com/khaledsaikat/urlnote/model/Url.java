package com.khaledsaikat.urlnote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Url extends AuditModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String url;

	@Column(nullable = false)
	private String title;

	protected Url() {

	}

	public Url(String url, String title) {
		this.url = url;
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
