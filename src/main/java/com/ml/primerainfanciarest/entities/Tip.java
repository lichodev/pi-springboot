package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'tip'
 * <p>Posee un id, un título, un texto, una imagen y la cantidad
 * de veces que los usuarios indicaron si les fue útil o no la información</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Tip {
    private int id;
    private String title;
    private String text;
    private byte[] image;
    private int likes;
    private int dislikes;

    public Tip() {
    }

    public Tip(String title, String text, byte[] image, int likes, int dislikes) {
        this.title = title;
        this.text = text;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    @Column(name = "image" )
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
        Tip tip = (Tip) o;
        return id == tip.id && likes == tip.likes && dislikes == tip.dislikes && Objects.equals(title, tip.title) && Objects.equals(text, tip.text) && Objects.equals(image, tip.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, image, likes, dislikes);
    }
}
