package com.todolist.infrastructure;

import com.todolist.model.User;
import org.jetbrains.annotations.NotNull;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {
    public static String hashPassword(@NotNull String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(digest);
        } catch (NoSuchAlgorithmException e) {
            return password;
        }
    }

    public static boolean checkPassword(@NotNull User user, String password) {
        return user.getPassword().equals(hashPassword(password));
    }
}
