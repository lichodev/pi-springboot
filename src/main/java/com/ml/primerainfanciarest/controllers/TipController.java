package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.entities.Workshop;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controlador accesible con el path "/tips"
 * Se encarga de todas las peticiones a la tabla 'tip'
 */
@RestController
@RequestMapping("/tips")
@CrossOrigin
public class TipController {

    @Autowired
    @Qualifier("TipService")
    private TipService service;

    /**
     * Obtiene el listado de todos los tips
     * Se accede mediante el método GET
     * @return
     */
    @GetMapping
    public List<TipModel> get() {
        return service.getAll();
    }

    /**
     * Obtiene el tip asociado al id recibido
     * Se accede mediante el método GET y al path inicial se le agrega el id solicitado
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public TipModel getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    /**
     * Recibe los datos de un nuevo tip, lo crea y lo guarda
     * Se accede mediante el método POST
     * y solo es accesible para el usuario logueado
     * @param title
     * @param text
     * @param image
     * @return
     */
    @PostMapping()
    public boolean post(@RequestParam("title") String title, @RequestParam("text") String text, @RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) return false;
        byte[] byteImage = FileHelper.saveFile(image, "images/tips");
        if (byteImage != null) {
            Tip tip = new Tip(title, text, FileHelper.compressBytes(byteImage), 0, 0);
            return this.service.post(tip);
        }
        return false;
    }

    /**
     * Suma likes o dislikes al tip coincidente con el id recibido
     * Se accede mediante el método PUT
     * @param id
     * @param value
     * @return
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }

}
