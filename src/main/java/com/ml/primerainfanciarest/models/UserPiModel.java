package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.UserPi;

public class UserPiModel {
    private int id;
    private String username;
    private String password;

    public UserPiModel(UserPi user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
    }

    public UserPiModel(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
