package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Question;
import com.ml.primerainfanciarest.models.QuestionModel;
import com.ml.primerainfanciarest.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/questions"
 * Se encarga de resolver todas las solicitudes hacia la tabla 'question'
 */
@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {
    @Autowired
    @Qualifier("QuestionService")
    private QuestionService service;

    /**
     * obtiene el listado de preguntas
     * se accede mediante el método GET
     * solo es accesible para el usuario logueado
     * @return
     */
    @GetMapping
    public List<QuestionModel> get() {
        return this.service.get();
    }

    /**
     * recibe una pregunta y la guarda
     * se accede mediante el método POST
     * @param question
     * @return
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Question question) {
        return this.service.post(question);
    }

    /**
     * Edita una pregunta para indicar que ya fue respondida
     * Se accede mediante el método PUT
     * Solo es accesible para el usuario logueado
     * @param id
     * @return
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        return this.service.checkReplied(id);
    }
}
