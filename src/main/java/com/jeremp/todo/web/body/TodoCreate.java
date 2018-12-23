package com.jeremp.todo.web.body;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class TodoCreate implements Serializable {
  @NotNull(message = "title cannot be null")
  private String title ;

  public TodoCreate() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }
}
