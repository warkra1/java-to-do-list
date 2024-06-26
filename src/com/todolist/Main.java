package com.todolist;

import com.todolist.di.Container;

public class Main {
    public static void main(String[] args) {
        CommandProcessor commandProcessor = Container.getInstance().getService(CommandProcessor.class);

        commandProcessor.run();
    }
}