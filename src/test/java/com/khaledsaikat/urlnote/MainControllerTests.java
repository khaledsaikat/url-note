package com.khaledsaikat.urlnote;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainControllerTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void indexPageReturnsJson() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
	}

	@Test
	public void addNoteWithAllRequiredFields() throws Exception {
		JSONObject urlNoteData = new JSONObject();
		urlNoteData.put("url", "URL");
		urlNoteData.put("title", "TITLE");
		urlNoteData.put("status", "STATUS");
		urlNoteData.put("note", "NOTE");
		MockHttpServletRequestBuilder requestBuilder = post("/add-note")
				.contentType(MediaType.APPLICATION_JSON)
				.content(urlNoteData.toString());

		this.mockMvc.perform(requestBuilder)
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.createdAt", isA(String.class)))
				.andExpect(jsonPath("$.status", is(urlNoteData.get("status"))))
				.andExpect(jsonPath("$.note", is(urlNoteData.get("note"))))
				.andExpect(jsonPath("$.url.createdAt", isA(String.class)))
				.andExpect(jsonPath("$.url.url", is(urlNoteData.get("url"))))
				.andExpect(jsonPath("$.url.title", is(urlNoteData.get("title"))));
	}

}
