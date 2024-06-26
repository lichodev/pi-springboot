package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.WorkshopConverter;
import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.WorkshopModel;
import com.ml.primerainfanciarest.repositories.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con los talleres
 * @author sole
 * @version 0.1
 */
@Service("WorkshopService")
public class WorkshopService {
    @Autowired
    @Qualifier("WorkshopRepository")
    private WorkshopRepository repository;

    @Autowired
    @Qualifier("WorkshopConverter")
    private WorkshopConverter converter;

    /**
     * Obtiene el listado de todos los talleres
     * @return lista completa de talleres
     */
    public List<WorkshopModel> get() {
        List<WorkshopModel> workshops = new ArrayList<>();
        for (Workshop w: this.repository.findAll()) {
            w.setVideo(FileHelper.decompressBytes(w.getVideo()));
            workshops.add(this.converter.convert(w));
        }
        return workshops;
    }

    /**
     * Guarda en la BDD el taller recibido
     * @param workshop a guardar
     * @return boolean indicador del éxito en la operación
     */
    public boolean post(Workshop workshop) {
        try {
            this.repository.save(workshop);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
