package com.todolist.di;

import com.todolist.CommandProcessor;
import com.todolist.model.ToDoList;
import com.todolist.model.User;
import com.todolist.repository.IRepository;
import com.todolist.repository.ToDoListRepository;
import com.todolist.repository.UserRepository;
import com.todolist.service.AuthService;
import com.todolist.service.ToDoListService;

import java.util.HashMap;
import java.util.Map;

public class Container implements IContainer {
    private static Container instance;
    private final Map<Class<?>, Object> services = new HashMap<>();
    private final Map<Class<?>, Object> repositories = new HashMap<>();

    private Container() {
        repositories.put(User.class, new UserRepository("data/user"));
        repositories.put(ToDoList.class, new ToDoListRepository("data/list"));
        services.put(AuthService.class, new AuthService(getRepository(User.class)));
        services.put(ToDoListService.class, new ToDoListService(getRepository(ToDoList.class)));
        services.put(CommandProcessor.class, new CommandProcessor(getService(AuthService.class), getService(ToDoListService.class)));
    }

    @Override
    public <T> T getService(Class<T> type) {
        T service = (T) services.get(type);
        if (service == null) {
            throw new IllegalArgumentException("No registered service");
        }

        return service;
    }

    @Override
    public <T> IRepository<T> getRepository(Class<T> type) {
        IRepository<T> repository = (IRepository<T>) repositories.get(type);

        if (repository == null) {
            throw new IllegalArgumentException("No registered repository");
        }

        return repository;
    }

    public static Container getInstance() {
        if (instance == null) {
            instance = new Container();
        }
        return instance;
    }
}
