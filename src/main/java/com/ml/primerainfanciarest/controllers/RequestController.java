package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import com.ml.primerainfanciarest.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador accesible con el path "/requests"
 * Se encarga de todas las solicitudes que respectan a la tabla 'request'
 */
@RestController
@RequestMapping("/requests")
@CrossOrigin
public class RequestController {
    @Autowired
    @Qualifier("RequestService")
    private RequestService service;

    /**
     * Obtiene el listado de solicitudes
     * Se accede mediante el método GET
     * y solo es accesible para el usuario logueado
     * @return
     */
    @GetMapping
    public List<RequestModel> get() {
        return this.service.get();
    }

    /**
     * Recibe y guarda una solicitud
     * Se accede mediante el método POST
     * @param request
     * @return
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Request request) {
        return this.service.post(request);
    }

    /**
     * Marca como respondida la solicitud que coincide con el id recibido
     * Se accede mediante el método PUT y agregando al path inicial el id de la solicitud
     * que se quiere editar
     * Solo es accesible para el usuario logueado
     * @param id
     * @return
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        return this.service.checkReplied(id);
    }
}
