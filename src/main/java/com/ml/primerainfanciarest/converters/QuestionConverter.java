package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import org.springframework.stereotype.Component;

@Component("QuestionConverter")
public class QuestionConverter {

    /**
     * Crea un Question Model a partir de la pregunta recibida por parámetro
     * @param question
     * @return
     */
    public QuestionModel convert(Question question) {
        return new QuestionModel(question);
    }
}
