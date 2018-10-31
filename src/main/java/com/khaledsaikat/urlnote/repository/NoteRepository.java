package com.khaledsaikat.urlnote.repository;

import org.springframework.data.repository.CrudRepository;

import com.khaledsaikat.urlnote.model.Note;
import com.khaledsaikat.urlnote.model.Url;

public interface NoteRepository extends CrudRepository<Note, Long> {
	public Iterable<Note> findByUrl(Url url);
}
