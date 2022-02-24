package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Recorder;

public class RecorderModel {
    private int id;
    private int since;
    private int until;
    private String image;
    private int likes;
    private int dislikes;

    public RecorderModel(Recorder recorder) {
        this.id = recorder.getId();
        this.since = recorder.getSince();
        this.until = recorder.getUntil();
        this.image = recorder.getImage();
        this.likes = recorder.getLikes();
        this.dislikes = recorder.getDislikes();
    }

    public RecorderModel(int since, int until, String image, int likes, int dislikes) {
        this.since = since;
        this.until = until;
        this.image = image;
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int until) {
        this.until = until;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }
}
