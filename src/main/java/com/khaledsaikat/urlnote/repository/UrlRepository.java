package com.khaledsaikat.urlnote.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.khaledsaikat.urlnote.model.Url;

public interface UrlRepository extends CrudRepository<Url, Long> {
	public Optional<Url> findByUrl(String url);
}
