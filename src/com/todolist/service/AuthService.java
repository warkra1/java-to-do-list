package com.todolist.service;

import com.todolist.infrastructure.PasswordHasher;
import com.todolist.model.User;
import com.todolist.repository.IRepository;
import com.todolist.repository.exception.ModelNotFoundException;
import com.todolist.service.exception.AuthException;

public class AuthService {
    private User user = null;
    private final IRepository<User> repository;

    public AuthService(IRepository<User> repository) {
        this.repository = repository;
    }

    public User getCurrentUser() {
        return user;
    }

    public void login(String login, String password) throws AuthException {
        User user;
        try {
            user = repository.read(login);
        } catch (ModelNotFoundException e) {
            throw AuthException.userNotFound();
        }

        if (!PasswordHasher.checkPassword(user, password)) {
            throw AuthException.invalidPassword();
        }

        this.user = user;
    }

    public void register(String login, String password) throws AuthException {
        if (exists(login)) {
            throw AuthException.userAlreadyExists();
        }

        User user = new User(login, PasswordHasher.hashPassword(password));
        repository.write(login, user);
        this.user = user;
    }

    private boolean exists(String login) {
        try {
            repository.read(login);
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }
}
