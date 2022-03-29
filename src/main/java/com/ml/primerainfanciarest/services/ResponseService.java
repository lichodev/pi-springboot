package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ResponseConverter;
import com.ml.primerainfanciarest.entities.Response;
import com.ml.primerainfanciarest.models.ResponseJoinModel;
import com.ml.primerainfanciarest.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ResponseService")
public class ResponseService {

    @Autowired
    @Qualifier("ResponseConverter")
    private ResponseConverter converter;

    @Autowired
    @Qualifier("ResponseRepository")
    private ResponseRepository repository;

    /**
     * Obtiene todas las respuestas guardadas y las preguntas a las que hacen referencia
     * @return
     */
    public List<ResponseJoinModel> get() {
        return this.repository.get();
    }

    /**
     * Guarda en la BDD la respuesta recibida
     * @param response
     * @return
     */
    public boolean post(Response response) {
        try {
            this.repository.save(response);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
