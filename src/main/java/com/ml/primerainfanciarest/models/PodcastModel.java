package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Podcast;

public class PodcastModel {
    private int id;
    private String title;
    private String audio;
    private String image;

    public PodcastModel(Podcast podcast) {
        this.id = podcast.getId();
        this.title = podcast.getTitle();
        this.audio = podcast.getAudio();
        this.image = podcast.getImage();
    }

    public PodcastModel(String title, String audio, String image) {
        this.title = title;
        this.audio = audio;
        this.image = image;
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

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
