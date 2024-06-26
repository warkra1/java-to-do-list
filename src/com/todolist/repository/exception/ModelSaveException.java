package com.todolist.repository.exception;

public class ModelSaveException extends RuntimeException {
    public ModelSaveException(Throwable t) {
        super(t);
    }
}
