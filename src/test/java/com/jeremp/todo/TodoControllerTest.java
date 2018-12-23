package com.jeremp.todo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremp.todo.web.body.TodoCreate;
import org.junit.Test;
import org.springframework.http.MediaType;


public class TodoControllerTest extends TodoApplicationTests {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Test
	public void listTodosTest() throws Exception {
		this.mockMvc.perform(get("/todo")).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void createTest() throws Exception {
		TodoCreate create = new TodoCreate();
		create.setTitle("myTitle");
		String requestBody = MAPPER.writeValueAsString(create);

		this.mockMvc.perform(post("/todo").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
				.andExpect(status().isCreated());
	}

	@Test
	public void createIncompleteTest() throws Exception {
		TodoCreate create = new TodoCreate();
		create.setTitle(null);
		String requestBody = MAPPER.writeValueAsString(create);

		this.mockMvc.perform(post("/todo").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
				.andExpect(status().isBadRequest());
	}

}
