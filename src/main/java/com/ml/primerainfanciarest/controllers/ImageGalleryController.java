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

@RestController
@RequestMapping("/gallery")
@CrossOrigin
public class ImageGalleryController {

    @Autowired
    @Qualifier("ImageGalleryService")
    private ImageGalleryService service;

    @GetMapping
    public List<ImageGalleryModel> get() {
        List<ImageGalleryModel> images = this.service.get();
        for (ImageGalleryModel i:
             images) {
            i.setImage(FileHelper.decompressBytes(i.getImage()));
        }
        return images;
    }

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
