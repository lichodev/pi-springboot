package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Response;

public class ResponseModel {
    private int id;
    private String text;
    private int questionId;

    public ResponseModel(Response response) {
        this.id = response.getId();
        this.text = response.getText();
        this.questionId = response.getQuestionId();
    }

    public ResponseModel(String text, int questionId) {
        this.text = text;
        this.questionId = questionId;
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

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
