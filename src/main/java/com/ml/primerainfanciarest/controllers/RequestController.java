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
 * <p>Se encarga de todas las solicitudes que respectan a la tabla 'request'</p>
 * @author sole
 * @version 1.0
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
     * <p>Se accede mediante el método GET
     * y solo es accesible para el usuario logueado</p>
     * @return listado de requests
     */
    @GetMapping
    public List<RequestModel> get() {
        return this.service.get();
    }

    /**
     * Guarda una solicitud
     * <p>Se accede mediante el método POST</p>
     * @param request a guardar
     * @return boolea que indica si se pudo guardar
     */
    @PostMapping
    public boolean post(@RequestBody @Validated Request request) {
        return this.service.post(request);
    }

    /**
     * Marca como 'respondida' la solicitud que coincide con el id recibido
     * <p>Se accede mediante el método PUT y agregando al path inicial el id de la solicitud
     * que se quiere editar</p>
     * <p>Solo es accesible para el usuario logueado</p>
     * @param id único del request que se desea editar
     * @return boolean que indica si se realizó la edición correctamente
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        return this.service.checkReplied(id);
    }
}
