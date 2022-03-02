package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import com.ml.primerainfanciarest.services.ImageGalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
            i.setImage(decompressBytes(i.getImage()));
        }
        return images;
    }

    @PostMapping(value = "{description}")
    public boolean post(@PathVariable ("description") String description, @RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) return false;
        else {
            Path directory = Paths.get("src//main//resources//static/images/gallery");
            String absolutePath = directory.toFile().getAbsolutePath();
            try {
                byte[] byteImage = image.getBytes();
                Path completePath = Paths.get(absolutePath + "//" + image.getOriginalFilename());
                Files.write(completePath, byteImage);
                ImageGallery img = new ImageGallery(compressBytes(byteImage), description);
                return this.service.post(img);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
