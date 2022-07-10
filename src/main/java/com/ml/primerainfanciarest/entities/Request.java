package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'request'
 * <p>Es una solicitud que puede dejar cualquier usuario para colaborar con el proyecto
 * De estas solicitudes se guarda un id,
 * los datos del usuario interesado (nombre, apellido, email y teléfono)
 * el motivo por el que quiere colaborar, cómo piensa que puede hacerlo
 * y un boolean que indica si algún administrador ya se puso en contacto con el usuario</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Request {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String phone;
    private String why;
    private String how;
    private boolean replied;

    public Request() {
    }

    public Request(String name, String lastname, String email, String phone, String why, String how, boolean replied) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.why = why;
        this.how = how;
        this.replied = replied;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "why")
    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    @Basic
    @Column(name = "how")
    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
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
        Request request = (Request) o;
        return id == request.id && replied == request.replied && Objects.equals(name, request.name) && Objects.equals(lastname, request.lastname) && Objects.equals(email, request.email) && Objects.equals(phone, request.phone) && Objects.equals(why, request.why) && Objects.equals(how, request.how);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, email, phone, why, how, replied);
    }
}
