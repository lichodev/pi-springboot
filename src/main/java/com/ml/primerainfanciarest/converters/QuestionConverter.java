package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import org.springframework.stereotype.Component;

@Component("QuestionConverter")
public class QuestionConverter {
    public QuestionModel convert(Question question) {
        return new QuestionModel(question);
    }
}
