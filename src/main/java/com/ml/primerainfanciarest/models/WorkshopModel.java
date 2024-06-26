package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Workshop;

public class WorkshopModel {
    private int id;
    private String title;
    private byte[] video;

    public WorkshopModel(Workshop workshop) {
        this.id = workshop.getId();
        this.title = workshop.getTitle();
        this.video = workshop.getVideo();
    }

    public WorkshopModel(String title, byte[] video) {
        this.title = title;
        this.video = video;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public byte[] getVideo() {
        return video;
    }

    public void setVideo(byte[] video) {
        this.video = video;
    }
}
