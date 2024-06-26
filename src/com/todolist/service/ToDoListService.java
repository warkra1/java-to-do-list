package com.todolist.service;

import com.todolist.model.ToDoItem;
import com.todolist.model.ToDoList;
import com.todolist.model.User;
import com.todolist.repository.IRepository;
import com.todolist.repository.exception.ModelNotFoundException;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ToDoListService {
    private final IRepository<ToDoList> repository;

    public ToDoListService(IRepository<ToDoList> repository) {
        this.repository = repository;
    }

    public ToDoList getList(@NotNull User user) {
        try {
            return repository.read(user.getLogin());
        } catch (ModelNotFoundException e) {
            return createList(user);
        }
    }

    public ToDoList createItem(User user, ToDoItem item, int number) {
        ToDoList list = getList(user);
        if (number > list.getItems().size()) {
            number = list.getItems().size();
        }
        list.getItems().add(number, item);
        repository.write(user.getLogin(), list);
        return list;
    }

    public ToDoList updateItem(User user, ToDoItem item, int number) {
        ToDoList list = getList(user);
        if (number < list.getItems().size()) {
            list.getItems().set(number, item);
        }
        repository.write(user.getLogin(), list);
        return list;
    }

    public ToDoList deleteItem(User user, int number) {
        ToDoList list = getList(user);
        if (number < list.getItems().size()) {
            list.getItems().remove(number);
        }
        repository.write(user.getLogin(), list);
        return list;
    }

    private @NotNull ToDoList createList(@NotNull User user) {
        ToDoList list = new ToDoList(new ArrayList<ToDoItem>());
        repository.write(user.getLogin(), list);
        return list;
    }
}
