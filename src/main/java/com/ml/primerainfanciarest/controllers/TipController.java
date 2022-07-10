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
 * <p>Se encarga de todas las peticiones a la tabla 'tip'</p>
 * @author Sole
 * @version 1.0
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
     * <p>Se accede mediante el método GET</p>
     * @return Lista de tips
     */
    @GetMapping
    public List<TipModel> get() {
        return service.getAll();
    }

    /**
     * Obtiene el tip asociado al id recibido
     * <p>Se accede mediante el método GET y al path inicial se le agrega el id solicitado</p>
     * @param id representa el identificador único del tip al que se quiere acceder
     * @return Tip con toda la información pertinente
     */
    @GetMapping(value = "/{id}")
    public TipModel getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    /**
     * Crea y guarda un nuevo tip a partir de los datos recibidos
     * <p>Se accede mediante el método POST
     * y solo es accesible para el usuario logueado</p>
     * @param title String
     * @param text String
     * @param image MultipartFile
     * @return boolean que indica si este pudo ser guardado
     */
    @PostMapping()
    public boolean post(@RequestParam("title") String title, @RequestParam("text") String text, @RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) return false;
        byte[] byteImage = FileHelper.getBytesFile(image);
        if (byteImage != null) {
            Tip tip = new Tip(title, text, FileHelper.compressBytes(byteImage), 0, 0);
            return this.service.post(tip);
        }
        return false;
    }

    /**
     * Suma likes o dislikes al tip coincidente con el id recibido
     * <p>Se accede mediante el método PUT</p>
     * @param id representa el id único del tip al que se desea evaluar
     * @param value puede ser un valor positivo que indica que se debe sumar
     *              un 'like' o un valor negativo, correspondiente a un 'dislike'
     * @return boolean que indica si se guardaron los cambios
     * @see TipService
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }

}
