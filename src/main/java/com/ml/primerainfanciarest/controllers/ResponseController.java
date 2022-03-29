package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Response;
import com.ml.primerainfanciarest.models.ResponseJoinModel;
import com.ml.primerainfanciarest.models.ResponseModel;
import com.ml.primerainfanciarest.services.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/response"
 * Se encarga de todas las peticiones asociadas con la tabla 'response'
 */
@RestController
@RequestMapping("/response")
@CrossOrigin
public class ResponseController {

    @Autowired
    @Qualifier("ResponseService")
    private ResponseService service;

    /**
     * Obtiene el listado de preguntas que tienen una respuesta asociada y la
     * respectiva respuesta
     * Se accede mediante el método GET
     * @return
     */
    @GetMapping
    public List<ResponseJoinModel> get() {
        return this.service.get();
    }

    /**
     * Guarda una respuesta asociada a una pregunta
     * Se accede mediante el método POST
     * y solo es accesible para el usuario logueado
     * @param response
     * @return
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Response response) {
        return this.service.post(response);
    }
}
