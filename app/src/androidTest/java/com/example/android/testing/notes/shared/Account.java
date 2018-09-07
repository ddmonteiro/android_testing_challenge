package com.example.android.testing.notes.shared;

public class Account {

    private String mUsername;
    private String mPassword;

    public Account(String username, String password) {
        mUsername = username;
        mPassword = password;
    }

    public static Account build() {
        return new Account("tests@mail.com", "password");
    }

}
