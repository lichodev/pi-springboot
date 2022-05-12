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
 * <p>Se encarga de todas las peticiones asociadas con la tabla 'response'</p>
 * @author sole
 * @version 1.0
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
     * <p>Se accede mediante el método GET</p>
     * <p>El objetivo es que sean utilizadas en la sección de 'preguntas frecuentes'</p>
     * @return listado de preguntas y respuestas
     */
    @GetMapping
    public List<ResponseJoinModel> get() {
        return this.service.get();
    }

    /**
     * Guarda una respuesta asociada a una pregunta
     * <p>Se accede mediante el método POST
     * y solo es accesible para el usuario logueado</p>
     * @param response asociada a una question
     * @return boolean que indica si la respuesta se guardó correctamente
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Response response) {
        return this.service.post(response);
    }
}
