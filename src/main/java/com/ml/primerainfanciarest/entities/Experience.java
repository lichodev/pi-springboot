package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'experience'
 * <p>Posee un id, un texto, una imagen (NO obligatoria)
 * y un estado que indica si puede ser expuesta para todos los usuarios</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Experience {
    private int id;
    private String text;
    private byte[] image;
    private boolean status;

    public Experience() {
    }

    public Experience(String text, byte[] image, boolean status) {
        this.text = text;
        this.image = image;
        this.status = status;
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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "status")
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return id == that.id && status == that.status && Objects.equals(text, that.text) && Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, image, status);
    }
}
