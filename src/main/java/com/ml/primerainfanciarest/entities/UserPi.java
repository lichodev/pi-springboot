package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'user-pi'
 * <p>posee un id, un nombre de usuario y una contraseña</p>
 * @author sole
 * @version 1.0
 */
@Table(name = "userPi")
@Entity
public class UserPi {
    private int id;
    private String username;
    private String password;

    public UserPi() {
    }

    public UserPi(String username, String password) {
        this.username = username;
        this.password = password;
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
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPi userPi = (UserPi) o;
        return id == userPi.id && Objects.equals(username, userPi.username) && Objects.equals(password, userPi.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password);
    }
}
