package com.ml.primerainfanciarest.models;

public class ResponseJoinModel {
    private String question, response;

    public ResponseJoinModel() {
    }

    public ResponseJoinModel(String question, String response) {
        this.question = question;
        this.response = response;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
