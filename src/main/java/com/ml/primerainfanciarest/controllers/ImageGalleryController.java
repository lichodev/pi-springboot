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
 * Controla todas las peticiones que respectan a la tabla 'image_gallery'
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
     * Se accede con el método GET
     * @return
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
     * Obtiene la imagen correspondiente al id recibido en el path
     * Se accede mediante el método GET y al path original se
     * le agrega el id de la imagen solicitada
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    public ImageGalleryModel getById(@PathVariable ("id") int id) {
        return this.service.getById(id);
    }

    /**
     * Recibe una imagen y la guarda
     * Se accede con el método POST
     * Solo es accesible para el usuario logueado
     * @param description
     * @param image
     * @return
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
