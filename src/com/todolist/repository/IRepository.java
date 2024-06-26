package com.todolist.repository;

import com.todolist.repository.exception.ModelNotFoundException;
import com.todolist.repository.exception.ModelSaveException;

public interface IRepository<T> {
    T read(String id) throws ModelNotFoundException;

    void write(String id, T obj) throws ModelSaveException;
}
