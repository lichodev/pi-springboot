package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.WorkshopConverter;
import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.models.WorkshopModel;
import com.ml.primerainfanciarest.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("WorkshopService")
public class WorkshopService {
    @Autowired
    @Qualifier("WorkshopRepository")
    private WorkshopRepository repository;

    @Autowired
    @Qualifier("WorkshopConverter")
    private WorkshopConverter converter;

    public List<WorkshopModel> get() {
        List<WorkshopModel> workshops = new ArrayList<>();
        for (Workshop w: this.repository.findAll()) {
            workshops.add(this.converter.convert(w));
        }
        return workshops;
    }
}
