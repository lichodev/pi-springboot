package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Experience;

public class ExperienceModel {
    private int id;
    private String text;
    private byte[] image;
    private boolean status;

    public ExperienceModel(Experience experience) {
        this.id = experience.getId();
        this.text = experience.getText();
        this.image = experience.getImage();
        this.status = experience.getStatus();
    }

    public ExperienceModel(String text, byte[] image, boolean status) {
        this.text = text;
        this.image = image;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
