package com.ml.primerainfanciarest.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'question'
 * Posee un id, datos del usuario que realizó la pregunta
 * (nombre, apellido, e-mail y teléfono),
 * el texto que representa la pregunta en sí,
 * y un booleano que indica si ya fue respondida
 */
@Entity
public class Question {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String text;
    private boolean replied;

    public Question() {
    }

    public Question(String name, String lastname, String email, String phone, String text, boolean replied) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.text = text;
        this.replied = replied;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "replied")
    public boolean getReplied() {
        return replied;
    }

    public void setReplied(boolean replied) {
        this.replied = replied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id && replied == question.replied && Objects.equals(name, question.name) && Objects.equals(lastname, question.lastname) && Objects.equals(email, question.email) && Objects.equals(phone, question.phone) && Objects.equals(text, question.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, email, phone, text, replied);
    }
}
