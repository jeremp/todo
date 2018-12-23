package com.jeremp.todo.web;

import com.jeremp.todo.domain.ToDo;
import com.jeremp.todo.repository.ToDoRepository;
import com.jeremp.todo.web.body.TodoCreate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/todo")
public class TodoController {

  private ToDoRepository toDoRepository;

  @Autowired
  public TodoController(ToDoRepository toDoRepository) {
    this.toDoRepository = toDoRepository;
  }

  @GetMapping({"", "/"})
  public ResponseEntity<List<ToDo>> getAll(){
    Iterable<ToDo> all = this.toDoRepository.findAll();
    List<ToDo> result = new ArrayList<>();
    all.forEach(t -> {result.add(t);});
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping({"", "/"})
  public ResponseEntity<ToDo> create(
      @Valid @RequestBody TodoCreate pojo
  ){
    ToDo toDo = new ToDo();
    toDo.setTitle(pojo.getTitle());
    ToDo saved = this.toDoRepository.save(toDo);
    return new ResponseEntity<>(saved, HttpStatus.CREATED);
  }

}
