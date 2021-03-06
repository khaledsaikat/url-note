package com.khaledsaikat.urlnote.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Note extends AuditModel {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String status;

	private String note;

	@OneToOne
	@JoinColumn(name = "url_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Url url;

	public Note() {

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

	public Url getUrl() {
		return url;
	}

	public void setUrl(Url url) {
		this.url = url;
	}

}
