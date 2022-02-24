package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.RequestConverter;
import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import com.ml.primerainfanciarest.repositories.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RequestService")
public class RequestService {
    @Autowired
    @Qualifier("RequestRepository")
    private RequestRepository repository;

    @Autowired
    @Qualifier("RequestConverter")
    private RequestConverter converter;

    public List<RequestModel> get() {
        List<RequestModel> requests = new ArrayList<>();
        for (Request r: this.repository.findAll()) {
            requests.add(this.converter.convert(r));
        }
        return requests;
    }

    public boolean post(Request request) {
        try {
            this.repository.save(request);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean checkRelpied(int id) {
        Request request = this.repository.getById(id);
        if (request != null) {
            request.setReplied(true);
            try {
                this.repository.save(request);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
