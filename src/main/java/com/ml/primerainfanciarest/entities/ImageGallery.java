package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class ImageGallery {
    private int id;
    @Column(name = "image", length = 1000)
    private byte[] image;
    private String description;

    public ImageGallery() {
    }

    public ImageGallery(byte[] image, String description) {
        this.image = image;
        this.description = description;
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
    @Column(name = "image", length = 1000)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ImageGallery that = (ImageGallery) o;
        return id == that.id && Objects.equals(image, that.image) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, image, description);
    }
}
