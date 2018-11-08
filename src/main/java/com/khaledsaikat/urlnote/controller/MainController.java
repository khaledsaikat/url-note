package com.khaledsaikat.urlnote.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.khaledsaikat.urlnote.exception.ResourceNotFoundException;
import com.khaledsaikat.urlnote.model.Note;
import com.khaledsaikat.urlnote.model.Url;
import com.khaledsaikat.urlnote.model.UrlNote;
import com.khaledsaikat.urlnote.repository.NoteRepository;
import com.khaledsaikat.urlnote.repository.UrlRepository;

@CrossOrigin
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

	/**
	 * Get notes by providing URL as query string
	 * 
	 * @param urlString
	 * @return Iterable<Note>
	 */
	@GetMapping("/get-notes")
	public Iterable<Note> getNotesByUrl(@Valid @RequestParam("url") String urlString) {
		Url url = urlRepository.findByUrl(new UrlNote(urlString).getUrl())
				.orElseThrow(() -> new ResourceNotFoundException("URL"));

		return noteRepository.findByUrl(url);
	}

	@GetMapping("/urls/{urlId}")
	public Url getUrlsById(@PathVariable(value = "urlId") Long urlId) {
		return urlRepository.findById(urlId).orElseThrow(() -> new ResourceNotFoundException("URL"));
	}

	@PostMapping("/create-url")
	public Url cretaeUrl(@Valid @RequestBody Url url) {
		return urlRepository.save(url);
	}

	@PostMapping("/add-note")
	public Note addUrlNote(@Valid @RequestBody UrlNote urlNote) {
		Url url = urlRepository.findByUrl(urlNote.getUrl()).orElse(new Url(urlNote.getUrl(), urlNote.getTitle()));
		Note note = new Note();
		note.setUrl(url);
		note.setStatus(urlNote.getStatus());
		note.setNote(urlNote.getNote());
		urlRepository.save(url);

		return noteRepository.save(note);
	}

}
