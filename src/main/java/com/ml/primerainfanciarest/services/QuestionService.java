package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.QuestionConverter;
import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import com.ml.primerainfanciarest.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service("QuestionService")
public class QuestionService {
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository repository;

    @Autowired
    @Qualifier("QuestionConverter")
    private QuestionConverter converter;

    public List<QuestionModel> get() {
        List<QuestionModel> questions = new ArrayList<>();
        for (Question q : this.repository.findAll()) {
            questions.add(this.converter.convert(q));
        }
        Collections.sort(questions);
        return questions;
    }


    public boolean post(Question question) {
        try {
            this.repository.save(question);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkRelpied(int id) {
        Question q = this.repository.getById(id);
        if (q != null) {
            q.setReplied(true);
            try {
                this.repository.save(q);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
