package com.khaledsaikat.urlnote.repository;

import org.springframework.data.repository.CrudRepository;

import com.khaledsaikat.urlnote.model.Url;

public interface UrlRepository extends CrudRepository<Url, Long> {
	public Url findByUrl(String url);
}
