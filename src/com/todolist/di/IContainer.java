package com.todolist.di;

import com.todolist.repository.IRepository;

public interface IContainer {
    public <T> T getService(Class<T> type);

    public <T> IRepository<T> getRepository(Class<T> type);
}
