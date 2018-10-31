package com.khaledsaikat.urlnote.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khaledsaikat.urlnote.model.Note;
import com.khaledsaikat.urlnote.model.Url;
import com.khaledsaikat.urlnote.model.UrlNote;
import com.khaledsaikat.urlnote.repository.NoteRepository;
import com.khaledsaikat.urlnote.repository.UrlRepository;

@RestController
public class MainController {

	@Autowired
	UrlRepository urlRepository;

	@Autowired
	NoteRepository noteRepository;

	@GetMapping("/")
	public Iterable<Url> getUrls() {
		return urlRepository.findAll();
	}

	@GetMapping("/notes")
	public Iterable<Note> getNotesByUrl(@Valid @RequestParam("url") String urlString) {
		Url url = urlRepository.findByUrl(new UrlNote(urlString).getUrl());
		if (url == null) {
			return null;
		}

		return noteRepository.findByUrl(url);
	}

	@GetMapping("/urls/{urlId}")
	public Optional<Url> getUrlsById(@PathVariable(value = "urlId") Long urlId) {
		return urlRepository.findById(urlId);
	}

	@PostMapping("/create-url")
	public Url cretaeUrl(@Valid @RequestBody Url url) {
		return urlRepository.save(url);
	}

	@PostMapping("/add")
	public Note addUrlNote(@Valid @RequestBody UrlNote urlNote) {
		Url url = null;
		url = urlRepository.findByUrl(urlNote.getUrl());
		if (url == null) {
			url = new Url(urlNote.getUrl(), urlNote.getTitle());
		}

		Note note = new Note();
		note.setUrl(url);
		note.setStatus(urlNote.getStatus());
		note.setNote(urlNote.getNote());

		urlRepository.save(url);
		return noteRepository.save(note);
	}

}
