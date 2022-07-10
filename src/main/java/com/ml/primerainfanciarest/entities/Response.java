package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'response'
 * <p>Posee un id, un texto y un id que referencia la pregunta
 * a la que se está respondiendo</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class Response {
    private int id;
    private String text;
    private int questionId;
    private Question questionByQuestionId;

    public Response() {
    }

    public Response(String text, int questionId) {
        this.text = text;
        this.questionId = questionId;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    @Basic
    @Column(name = "question_id")
    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, questionId);
    }

    @Override
    public boolean equals(Object o) {
        Response r = (Response) o;
        return this.getId() == r.getId() && this.getText().equals(r.getText()) && this.getQuestionId() == r.getQuestionId();
    }

    @ManyToOne
    @JoinColumn(name = "question_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Question getQuestionByQuestionId() {
        return questionByQuestionId;
    }

    public void setQuestionByQuestionId(Question questionByQuestionId) {
        this.questionByQuestionId = questionByQuestionId;
    }
}
