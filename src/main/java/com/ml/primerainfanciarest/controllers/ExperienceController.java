package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ExperienceModel;
import com.ml.primerainfanciarest.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Controlador accesible con el path "/experiences"
 * Se encarga de todas las peticiones para la tabla 'experience'
 */
@RestController
@RequestMapping("/experiences")
@CrossOrigin
public class ExperienceController {

    @Autowired
    @Qualifier("ExperienceService")
    private ExperienceService service;

    /**
     * Obtiene el listado de experiencias visibles para el usuario sin iniciar sesión
     * Se accede mediante el método GET
     * @return
     */
    @GetMapping
    public List<ExperienceModel> getAccept() {
        return this.service.getByStatus(true);
    }

    /**
     * Obtiene el listado de experiencias visibles para el usuario logueado
     * Se accede mediante el método GET y el path "/admin"
     * Solo es accesible si existe una sesión de usuario iniciada
     * @return
     */
    @GetMapping("/admin")
    public List<ExperienceModel> getPending() {
        return this.service.getByStatus(false);
    }

    /**
     * Crea y guarda una nueva experiencia
     * Se accede con el método POST
     * @param text
     * @param image
     * @return
     */
    @PostMapping()
    public boolean post(@RequestParam("text") String text, @RequestParam(value = "file", required=false) MultipartFile image) {
        if (!image.isEmpty()) {
            byte[] byteImage = FileHelper.saveFile(image, "images/experiences");
            if (byteImage != null) {
                Experience experience = new Experience(text, FileHelper.compressBytes(byteImage), false);
                return this.service.post(experience);
            }
        }
        Experience experience = new Experience(text, null, false);
        return this.service.post(experience);
    }

    /**
     * Permite que la experiencia con el id recibido por parámetro sea visible para todos los usuarios
     * Se accede con el método PUT
     * Solo es accesible si existe una sesión de usuario iniciada
     * @param id
     * @return
     */
    @PutMapping(value = "{id}")
    public boolean accept(@PathVariable ("id") int id) {
        return this.service.accept(id);
    }

    /**
     * Elimina una experiencia guardada
     * Se accede con el método DELETE
     * Solo es accesible si existe una sesión de usuario iniciada
     * @param id
     * @return
     */
    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable ("id") int id) {
        return this.service.delete(id);
    }
}
