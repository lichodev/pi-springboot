package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Response;
import com.ml.primerainfanciarest.models.ResponseModel;
import org.springframework.stereotype.Component;

@Component("ResponseConverter")
public class ResponseConverter {

    /**
     * Crea un ResponseModel a partir de la respuesta recibida
     * @param response
     * @return
     */
    public ResponseModel convert(Response response) {
        return new ResponseModel(response);
    }
}
