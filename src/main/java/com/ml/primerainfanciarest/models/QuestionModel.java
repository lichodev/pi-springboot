package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Question;

public class QuestionModel {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String text;
    private boolean replied;

    public QuestionModel(Question question) {
        this.id = question.getId();
        this.name = question.getName();
        this.lastname = question.getLastname();
        this.email = question.getEmail();
        this.phone = question.getPhone();
        this.text = question.getText();
        this.replied = question.getReplied();
    }

    public QuestionModel(String name, String lastname, String email, String phone, String text, boolean replied) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.text = text;
        this.replied = replied;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isReplied() {
        return replied;
    }

    public void setReplied(boolean replied) {
        this.replied = replied;
    }
}
