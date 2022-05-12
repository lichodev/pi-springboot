package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ResponseConverter;
import com.ml.primerainfanciarest.entities.Response;
import com.ml.primerainfanciarest.models.ResponseJoinModel;
import com.ml.primerainfanciarest.repositories.ResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con las responses
 * @author sole
 * @version 1.0
 */
@Service("ResponseService")
public class ResponseService {

    @Autowired
    @Qualifier("ResponseConverter")
    private ResponseConverter converter;

    @Autowired
    @Qualifier("ResponseRepository")
    private ResponseRepository repository;

    /**
     * Obtiene todas las responses guardadas y las questions a las que hacen referencia
     * <p>Estas son exhibidas en la sección de 'preguntas frecuentes'</p>
     * @return listado de preguntas con sus respectivas respuestas
     */
    public List<ResponseJoinModel> get() {
        return this.repository.get();
    }

    /**
     * Guarda la response recibida
     * @param response a guardar
     * @return boolean indicador del éxito en la transacción
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
