package com.todolist.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ToDoList {
    @JsonProperty("items")
    private List<ToDoItem> items;

    public ToDoList(List<ToDoItem> items) {
        this.items = items;
    }

    public ToDoList() {}

    public List<ToDoItem> getItems() {
        return items;
    }

    public void setItems(List<ToDoItem> items) {
        this.items = items;
    }
}
