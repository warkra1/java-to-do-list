package com.todolist.repository;

import com.todolist.model.ToDoList;

public class ToDoListRepository extends AbstractFileRepository<ToDoList> {
    public ToDoListRepository(String path) {
        super(path);
    }

    @Override
    protected Class<ToDoList> getModelClass() {
        return ToDoList.class;
    }
}
