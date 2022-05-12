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
 * <p>Se encarga de todas las peticiones para la tabla 'experience'</p>
 * @author sole
 * @version 1.0
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
     * <p>Se accede mediante el método GET</p>
     * @return listado de experiencias visibles para el usuario sin iniciar sesión
     */
    @GetMapping
    public List<ExperienceModel> getAccept() {
        return this.service.getByStatus(true);
    }

    /**
     * Obtiene el listado de experiencias visibles para el usuario logueado
     * <p>Se accede mediante el método GET y el path "/admin"
     * Solo es accesible si existe una sesión de usuario iniciada</p>
     * @return listado de experiencias visibles para el usuario logueado
     */
    @GetMapping("/admin")
    public List<ExperienceModel> getPending() {
        return this.service.getByStatus(false);
    }

    /**
     * Crea y guarda una nueva experiencia
     * <p>Se accede con el método POST</p>
     * <p>En un primer momento, la experiencia sólo será visible por los administradores
     * hasta que uno de estos decida hacerla pública</p>
     * @param text de la experiencia a guardar
     * @param image opcional que acompaña el texto
     * @return boolean que indica si se pudo guardar
     * @see FileHelper
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
     * Hace visible una experiencia
     * <p>Permite que la experiencia con el id
     * recibido por parámetro sea visible para todos los usuarios</p>
     * <p>Se accede con el método PUT</p>
     * <p>Solo es accesible si existe una sesión de usuario iniciada</p>
     * @param id de la experiencia que quiere hacerse visible
     * @return boolean que indica si se completó correctamente la solicitud
     */
    @PutMapping(value = "{id}")
    public boolean accept(@PathVariable ("id") int id) {
        return this.service.accept(id);
    }

    /**
     * Elimina una experiencia guardada
     * <p>Se accede con el método DELETE
     * Solo es accesible si existe una sesión de usuario iniciada</p>
     * @param id de la experiencia que se desea eliminar
     * @return bollean que indica si se pudo eliminar
     */
    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable ("id") int id) {
        return this.service.delete(id);
    }
}
