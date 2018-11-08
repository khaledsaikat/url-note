package com.khaledsaikat.urlnote;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import com.khaledsaikat.urlnote.model.UrlNote;

public class UrlNoteTest {

	@Test
	public void removeQueryStringFromUrl() {
		UrlNote note = new UrlNote("http://example.com/var?foo=bar");
		assertThat(note.getUrl()).isEqualTo("http://example.com/var");
	}
}
