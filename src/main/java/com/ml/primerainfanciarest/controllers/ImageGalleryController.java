package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import com.ml.primerainfanciarest.services.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gallery")
public class ImageGalleryController {

    @Autowired
    @Qualifier("ImageGalleryService")
    private ImageGalleryService service;

    @GetMapping
    public List<ImageGalleryModel> get() {
        return this.service.get();
    }

    @PostMapping
    public boolean post(@RequestBody @Validated ImageGallery image) {
        //TODO: verificar login
        return this.service.post(image);
    }
}
