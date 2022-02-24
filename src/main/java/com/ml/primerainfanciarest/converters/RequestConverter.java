package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import org.springframework.stereotype.Component;

@Component("RequestConverter")
public class RequestConverter {
    public RequestModel convert(Request request) {
        return new RequestModel(request);
    }
}
