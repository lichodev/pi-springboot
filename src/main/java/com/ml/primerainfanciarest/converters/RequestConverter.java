package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import org.springframework.stereotype.Component;

@Component("RequestConverter")
public class RequestConverter {

    /**
     * Crea un RequestModel a partir de la solicitud recibida
     * @param request
     * @return
     */
    public RequestModel convert(Request request) {
        return new RequestModel(request);
    }
}
