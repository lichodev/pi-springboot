package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Tip;

public class TipModel {

    private int id;
    private String title;
    private String text;
    private byte[] image;
    private int likes;
    private int dislikes;

    public TipModel(Tip tip) {
        this.id = tip.getId();
        this.title = tip.getTitle();
        this.text = tip.getText();
        this.image = tip.getImage();
        this.likes = tip.getLikes();
        this.dislikes = tip.getDislikes();
    }

    public TipModel(String title, String text, byte[] image, int likes, int dislikes) {
        this.title = title;
        this.text = text;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
