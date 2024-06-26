package com.todolist.repository.exception;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException() {
        super("Model not found!");
    }
}
