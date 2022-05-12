package com.ml.primerainfanciarest.models;

/**
 * Clase que representa un resumen de una 'question' con la 'response' asociada
 * <p>Posee el texto de la pregunta y el texto de la respuesta, omitiendo información
 * del usuario que realizó la pregunta.</p>
 * @author sole
 * @version 1.0
 */
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
