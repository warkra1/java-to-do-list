package com.todolist.repository;

import com.todolist.model.User;
import com.todolist.repository.exception.ModelNotFoundException;

public class UserRepository extends AbstractFileRepository<User> {
    public UserRepository(String path) {
        super(path);
    }

    @Override
    protected Class<User> getModelClass() {
        return User.class;
    }
}
