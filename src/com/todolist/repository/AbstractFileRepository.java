package com.todolist.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todolist.repository.exception.ModelNotFoundException;
import com.todolist.repository.exception.ModelSaveException;

import java.io.File;
import java.io.IOException;

public abstract class AbstractFileRepository<T> implements IRepository<T> {
    private final String path;

    public AbstractFileRepository(String path) {
        this.path = path;
    }

    @Override
    public T read(String id) throws ModelNotFoundException {
        ObjectMapper objectMapper = new ObjectMapper();
        String filename = path + "/" + id + ".json";
        try {
            return objectMapper.readValue(new File(filename), getModelClass());
        } catch (IOException e) {
            throw new ModelNotFoundException();
        }
    }

    @Override
    public void write(String id, T obj) throws ModelSaveException {
        ObjectMapper objectMapper = new ObjectMapper();
        String filename = path + "/" + id + ".json";
        try {
            objectMapper.writeValue(new File(filename), obj);
        } catch (IOException e) {
            throw new ModelSaveException(e);
        }
    }

    protected abstract Class<T> getModelClass();
}
