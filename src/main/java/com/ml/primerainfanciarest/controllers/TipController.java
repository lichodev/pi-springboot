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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/tips")
@CrossOrigin
public class TipController {

    @Autowired
    @Qualifier("TipService")
    private TipService service;

    @GetMapping
    public List<TipModel> get() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public TipModel getById(@PathVariable("id") int id) {
        return service.getById(id);
    }


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

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
