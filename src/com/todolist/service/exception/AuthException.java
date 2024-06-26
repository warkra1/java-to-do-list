package com.todolist.service.exception;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class AuthException extends Exception {
    public AuthException(String message) {
        super(message);
    }

    @Contract(" -> new")
    public static @NotNull AuthException userNotFound() {
        return new AuthException("User not found!");
    }

    @Contract(" -> new")
    public static @NotNull AuthException invalidPassword() {
        return new AuthException("Invalid Password!");
    }

    @Contract(" -> new")
    public static @NotNull AuthException userAlreadyExists() {
        return new AuthException("User already exists!");
    }
}
