package com.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ToDoItem {
    @JsonProperty("title")
    private String title;

    public ToDoItem(String title) {
        this.title = title;
    }

    public ToDoItem() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
