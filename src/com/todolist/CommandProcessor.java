package com.todolist;

import com.todolist.model.ToDoItem;
import com.todolist.model.ToDoList;
import com.todolist.service.AuthService;
import com.todolist.service.ToDoListService;
import com.todolist.service.exception.AuthException;

import java.util.Scanner;

public class CommandProcessor {
    private final AuthService authService;
    private final ToDoListService toDoListService;
    private Scanner scanner;

    public CommandProcessor(AuthService authService, ToDoListService toDoListService) {
        this.authService = authService;
        this.toDoListService = toDoListService;
        scanner = new Scanner(System.in);
    }

    public void run() {
        System.out.println("Hello, this is a to do list CLI App");

        while (true) {
            String commandInput = askString("Please, Enter a command (or type \"help\" to see all commands): ");
            Commands command;
            try {
                command = Commands.valueOf(commandInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid command!");
                continue;
            }
            switch (command) {
                case login:
                    login();
                    break;
                case register:
                    register();
                    break;
                case get_list:
                    getList();
                    break;
                case create_item:
                    createItem();
                    break;
                case update_item:
                    updateItem();
                    break;
                case delete_item:
                    deleteItem();
                    break;
                case help:
                    help();
                    break;
                case exit:
                    exit();
                    break;
            }
        }
    }

    private void login() {
        String login = askString("Enter login: ");
        String password = askString("Enter password: ");
        try {
            authService.login(login, password);
            System.out.println("Successfully logged in!");
        } catch (AuthException e) {
            System.out.println(e.getMessage());
        }
    }

    private void register() {
        String login = askString("Enter login: ");
        String password = askString("Enter password: ");
        try {
            authService.register(login, password);
            System.out.println("Successfully registered!");
        } catch (AuthException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getList() {
        if (!checkAuth()) {
            return;
        }

        ToDoList list = toDoListService.getList(authService.getCurrentUser());
        System.out.println("To Do List: ");
        if (list.getItems().isEmpty()) {
            System.out.println("\tYour list is empty");
        } else {
            for (int i = 0; i < list.getItems().size(); i++) {
                int number = i + 1;
                System.out.println("\t[" + number + "] " + list.getItems().get(i).getTitle());
            }
        }
    }

    private void createItem() {
        if (!checkAuth()) {
            return;
        }

        String title = askString("Enter title: ");
        int number = askNumber("Enter number: ");

        toDoListService.createItem(authService.getCurrentUser(), new ToDoItem(title), number - 1);
        System.out.println("Item successfully created!");
    }

    private void updateItem() {
        if (!checkAuth()) {
            return;
        }

        int number = askNumber("Enter number: ");
        String title = askString("Enter title: ");

        toDoListService.updateItem(authService.getCurrentUser(), new ToDoItem(title), number - 1);
        System.out.println("Item successfully updated!");
    }

    private void deleteItem() {
        if (!checkAuth()) {
            return;
        }

        int number = askNumber("Enter number: ");

        toDoListService.deleteItem(authService.getCurrentUser(), number - 1);
        System.out.println("Item successfully deleted!");
    }

    private void help() {
        System.out.println("List of commands");
        System.out.println("\t" + Commands.login + " - authorize to application");
        System.out.println("\t" + Commands.register + " - create new user");
        System.out.println("\t" + Commands.get_list + " - get current user's todo list");
        System.out.println("\t" + Commands.create_item + " - create a new item in todo list");
        System.out.println("\t" + Commands.update_item + " - updates a specific item in list");
        System.out.println("\t" + Commands.delete_item + " - deletes a specific item in list");
        System.out.println("\t" + Commands.help + " - show commands");
        System.out.println("\t" + Commands.exit + " - exit from application");
    }

    private void exit() {
        System.out.println("Bye!");
        System.exit(0);
    }

    private boolean checkAuth() {
        if (authService.getCurrentUser() == null) {
            System.out.println("You should login first");
            return false;
        }
        return true;
    }

    private String askString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    private int askNumber(String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number!");
            }
        }
    }
}
