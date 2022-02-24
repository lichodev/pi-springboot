package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.ImageGallery;

public class ImageGalleryModel {
    private int id;
    private String image;
    private String description;

    public ImageGalleryModel(ImageGallery imageGallery) {
        this.id = imageGallery.getId();
        this.image = imageGallery.getImage();
        this.description = imageGallery.getDescription();
    }

    public ImageGalleryModel(String image, String description) {
        this.image = image;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
