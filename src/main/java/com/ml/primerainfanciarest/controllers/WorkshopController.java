package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.WorkshopModel;
import com.ml.primerainfanciarest.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controlador accesible con el path "/workshops"
 * Se encarga de todas las peticiones asociadas a la tabla 'workshop'
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
     * Se accede mediante el método GET
     * @return
     */
    @GetMapping
    public List<WorkshopModel> get() {
        return this.service.get();
    }

    /**
     * Recibe y guarda un nuevo taller
     * Se accede mediante el método POST y
     * solo es accesible por el usuario logueado
     * @param title
     * @param video
     * @return
     */
    @PostMapping
    public boolean post(@RequestParam("title") String title, @RequestParam("video") MultipartFile video) {
        if (video.isEmpty()) return false;
        byte[] byteVideo = FileHelper.saveFile(video, "videos/workshops");
        if (byteVideo != null) {
            Workshop workshop = new Workshop(title, FileHelper.compressBytes(byteVideo));
            return this.service.post(workshop);
        }
        return false;
    }
}
