package com.khaledsaikat.urlnote.repository;

import org.springframework.data.repository.CrudRepository;

import com.khaledsaikat.urlnote.model.NoteLog;
import com.khaledsaikat.urlnote.model.Url;

public interface NoteLogRepository extends CrudRepository<NoteLog, Long> {
	public Iterable<NoteLog> findByUrl(Url url);
}
