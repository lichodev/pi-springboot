package com.ml.primerainfanciarest.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'workshop'
 * <p>Posee un id, un título y un video</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Workshop {
    private int id;
    private String title;
    private byte[] video;

    public Workshop() {
    }

    public Workshop(String title, byte[] video) {
        this.title = title;
        this.video = video;
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
    @Column(name = "video")
    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Workshop workshop = (Workshop) o;
        return id == workshop.id && Objects.equals(title, workshop.title) && Objects.equals(video, workshop.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, video);
    }
}
