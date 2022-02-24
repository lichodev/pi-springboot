package com.ml.primerainfanciarest.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Workshop {
    private int id;
    private String title;
    private String video;

    public Workshop() {
    }

    public Workshop(int id, String title, String video) {
        this.id = id;
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
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
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
