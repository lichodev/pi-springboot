package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.WorkshopModel;
import com.ml.primerainfanciarest.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controlador accesible con el path "/workshops"
 * <p>Se encarga de todas las peticiones asociadas a la tabla 'workshop'</p>
 * @author Sole
 * @version 1.0
 */
@RestController
@RequestMapping("/workshops")
@CrossOrigin
public class WorkshopController {
    @Autowired
    @Qualifier("WorkshopService")
    private WorkshopService service;

    /**
     * Obtiene el listado de todos los talleres
     * <p>Se accede mediante el método GET</p>
     * @return Lista de talleres
     */
    @GetMapping
    public List<WorkshopModel> get() {
        return this.service.get();
    }

    /**
     * Guarda un nuevo taller
     * <p>Se accede mediante el método POST y
     * solo es accesible por el usuario logueado</p>
     * @param title del taller que se está agregando
     * @param video correspondiente al taller
     * @return boolean que indica si se pudo guardar
     */
    @PostMapping
    public boolean post(@RequestParam("title") String title, @RequestParam("video") MultipartFile video) {
        if (video.isEmpty()) return false;
        byte[] byteVideo = FileHelper.getBytesFile(video);
        if (byteVideo != null) {
            Workshop workshop = new Workshop(title, FileHelper.compressBytes(byteVideo));
            return this.service.post(workshop);
        }
        return false;
    }
}
