package com.ml.primerainfanciarest.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'podcast'
 * <p>Posee un id, un título, un audio y una imagen para mostrar</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Podcast {
    private int id;
    private String title;
    private byte[] audio;
    private byte[] image;

    public Podcast() {
    }

    public Podcast(String title, byte[] audio, byte[] image) {
        this.title = title;
        this.audio = audio;
        this.image = image;
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "audio")
    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    @Basic
    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Podcast podcast = (Podcast) o;
        return id == podcast.id && Objects.equals(title, podcast.title) && Objects.equals(audio, podcast.audio) && Objects.equals(image, podcast.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, audio, image);
    }
}
