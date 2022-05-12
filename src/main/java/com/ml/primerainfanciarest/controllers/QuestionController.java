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
 * <p>Se encarga de resolver todas las solicitudes hacia la tabla 'question'</p>
 * @author sole
 * @version 1.0
 */
@RestController
@RequestMapping("/questions")
@CrossOrigin
public class QuestionController {
    @Autowired
    @Qualifier("QuestionService")
    private QuestionService service;

    /**
     * Obtiene el listado de preguntas
     * <p>se accede mediante el método GET
     * solo es accesible para el usuario logueado</p>
     * @return listado completo de las preguntas registradas
     */
    @GetMapping
    public List<QuestionModel> get() {
        return this.service.get();
    }

    /**
     * Guarda una pregunta
     * <p>se accede mediante el método POST</p>
     * <p>Al guardarse una pregunta por primera vez, siempre se encuentra como 'no respondida'</p>
     * @param question a guardar
     * @return boolean que indica si se pudo guardar
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Question question) {
        return this.service.post(question);
    }

    /**
     * Edita una pregunta para indicar que ya fue respondida
     * <p>Se accede mediante el método PUT
     * Solo es accesible para el usuario logueado</p>
     * @param id único para la pregunta que se desea editar
     * @return boolean indicando si se pudo editar
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        return this.service.checkReplied(id);
    }
}
