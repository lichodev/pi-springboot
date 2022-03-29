package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.models.WorkshopModel;
import org.springframework.stereotype.Component;

@Component("WorkshopConverter")
public class WorkshopConverter {

    /**
     * Crea un WorkshopModel a partir del taller recibido
     * @param workshop
     * @return
     */
    public WorkshopModel convert(Workshop workshop) {
        return new WorkshopModel(workshop);
    }
}
