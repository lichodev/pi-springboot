package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Request;

public class RequestModel implements Comparable{
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String why;
    private String how;
    private boolean replied;

    public RequestModel(Request request) {
        this.id = request.getId();
        this.name = request.getName();
        this.lastname = request.getLastname();
        this.email = request.getEmail();
        this.phone = request.getPhone();
        this.why = request.getWhy();
        this.how = request.getHow();
        this.replied = request.getReplied();
    }

    public RequestModel(String name, String lastname, String email, String phone, String why, String how, boolean replied) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.why = why;
        this.how = how;
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

    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    public boolean isReplied() {
        return replied;
    }

    public void setReplied(boolean replied) {
        this.replied = replied;
    }

    @Override
    public int compareTo(Object o) {
        RequestModel r = (RequestModel) o;
        if (this.replied && !r.replied) return 1;
        if (!this.replied && r.replied) return -1;
        return 0;
    }
}
