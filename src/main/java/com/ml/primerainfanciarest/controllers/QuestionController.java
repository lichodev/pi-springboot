package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import com.ml.primerainfanciarest.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {
    @Autowired
    @Qualifier("QuestionService")
    private QuestionService service;

    @GetMapping
    public List<QuestionModel> get() {
        return this.service.get();
    }

    @PostMapping
    public boolean post(@RequestBody @Validated Question question) {
        return this.service.post(question);
    }

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        //TODO: verificar login
        return this.service.checkRelpied(id);
    }
}
