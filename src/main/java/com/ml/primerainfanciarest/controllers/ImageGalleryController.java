package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import com.ml.primerainfanciarest.services.ImageGalleryService;
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
 * Controlador accesible con el path "/gallery"
 * <p>Controla todas las peticiones que respectan a la tabla 'image_gallery'</p>
 * @author sole
 * @version 1.0
 */
@RestController
@RequestMapping("/gallery")
@CrossOrigin
public class ImageGalleryController {

    @Autowired
    @Qualifier("ImageGalleryService")
    private ImageGalleryService service;

    /**
     * Obtiene el listado de todas las imágenes de la gallería
     * <p>Se accede con el método GET</p>
     * @return listado de imágenes de la galería
     * @see FileHelper
     */
    @GetMapping
    public List<ImageGalleryModel> get() {
        List<ImageGalleryModel> images = this.service.get();
        for (ImageGalleryModel i:
             images) {
            i.setImage(FileHelper.decompressBytes(i.getImage()));
        }
        return images;
    }

    /**
     * Obtiene la imagen correspondiente al id recibido
     * <p>Se accede mediante el método GET y al path original se
     * le agrega el id de la imagen solicitada</p>
     * @param id de la imagen que se quiere obtener
     * @return Imagen solicitada
     */
    @GetMapping(value = "/{id}")
    public ImageGalleryModel getById(@PathVariable ("id") int id) {
        return this.service.getById(id);
    }

    /**
     * Guarda una imagen
     * <p>Se accede con el método POST
     * Solo es accesible para el usuario logueado</p>
     * @param description de la imagen. Pie de foto
     * @param image la imagen en sí
     * @return boolean que indica si esta se pudo guardar
     * @see FileHelper
     */
    @PostMapping()
    public boolean post(@RequestParam("description") String description, @RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) return false;
        byte[] byteImage = FileHelper.saveFile(image, "images/gallery");
        if (byteImage != null) {
            ImageGallery img = new ImageGallery(FileHelper.compressBytes(byteImage), description);
            return this.service.post(img);
        }
        return false;
    }


}
