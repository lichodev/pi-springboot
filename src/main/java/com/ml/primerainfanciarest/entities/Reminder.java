package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'reminder'
 * <p>Es un recordatorio de consejos útiles para familias con niños
 * en un rango de edad determinado</p>
 * <p>Se guarda un id, el rango de edad para el que está indicado (desde - hasta)
 * una imagen, y la cantidad de veces que los usuarios indicaron si les fue útil o no</p>
 * la información.
 * @author sole
 * @version 1.0
 */
@Entity
public class Reminder {
    private int id;
    private int since;
    private int until;
    private byte[] image;
    private int likes;
    private int dislikes;

    public Reminder() {
    }

    public Reminder(int since, int until, byte[] image, int likes, int dislikes) {
        this.since = since;
        this.until = until;
        this.image = image;
        this.likes = likes;
        this.dislikes = dislikes;
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
    @Column(name = "since")
    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    @Basic
    @Column(name = "until")
    public int getUntil() {
        return until;
    }

    public void setUntil(int until) {
        this.until = until;
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
    @Column(name = "likes")
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    @Basic
    @Column(name = "dislikes")
    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public void plusLike() {
        this.likes++;
    }

    public void plusDislike() {
        this.dislikes++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reminder recorder = (Reminder) o;
        return id == recorder.id && since == recorder.since && until == recorder.until && likes == recorder.likes && dislikes == recorder.dislikes && Objects.equals(image, recorder.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, since, until, image, likes, dislikes);
    }
}
