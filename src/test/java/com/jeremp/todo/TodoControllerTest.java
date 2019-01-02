package com.jeremp.todo;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jeremp.todo.domain.ToDo;
import com.jeremp.todo.repository.ToDoRepository;
import com.jeremp.todo.web.body.TodoCreate;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Optional;


public class TodoControllerTest extends TodoApplicationTests {

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private ToDoRepository toDoRepository;

	@Test
	public void listTodosTest() throws Exception {
		List<ToDo> toDos = retrieveAllTodos();
		Assert.assertEquals("returned todos", 3, toDos.size());
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
	public void deleteTest() throws Exception {
		int initialCount = retrieveAllTodos().size();
		long idToDelete = 10002L ;
		this.mockMvc.perform(delete("/todo/{id}", idToDelete).contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful());

		List<ToDo> toDos = retrieveAllTodos();
		Assert.assertEquals("one Todo should have disappeared after delete", initialCount-1, toDos.size());
		Optional<ToDo> deletedTodo = toDos.stream().filter(t -> t.getId() == idToDelete).findFirst();
		Assert.assertFalse("deleted Todo should not be returned anymore", deletedTodo.isPresent());

	}

	@Test
	public void createIncompleteTest() throws Exception {
		TodoCreate create = new TodoCreate();
		create.setTitle(null);
		String requestBody = MAPPER.writeValueAsString(create);

		this.mockMvc.perform(post("/todo").contentType(MediaType.APPLICATION_JSON_UTF8).content(requestBody))
				.andExpect(status().isBadRequest());
	}

	private List<ToDo> retrieveAllTodos() throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(get("/todo"))
				//.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		return MAPPER.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<List<ToDo>>() {});
	}


	private void deleteOne(long id){

	}



}
