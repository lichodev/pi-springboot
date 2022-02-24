package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.models.WorkshopModel;
import org.springframework.stereotype.Component;

@Component("WorkshopConverter")
public class WorkshopConverter {
    public WorkshopModel convert(Workshop workshop) {
        return new WorkshopModel(workshop);
    }
}
