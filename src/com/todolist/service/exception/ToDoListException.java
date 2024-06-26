package com.todolist.service.exception;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public class ToDoListException extends Exception {
    public ToDoListException(String message) {
        super(message);
    }

    @Contract("_ -> new")
    public static @NotNull ToDoListException invalidNumber(String message) {
        return new ToDoListException(message);
    }
}
