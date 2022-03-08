package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.PodcastModel;
import com.ml.primerainfanciarest.services.PodcastService;
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
@RequestMapping("/podcast")
@CrossOrigin
public class PodcastController {

    @Autowired
    @Qualifier("PodcastService")
    private PodcastService service;

    @GetMapping
    public List<PodcastModel> get() {
        return this.service.get();
    }

    @PostMapping()
    public boolean post(@RequestParam("title") String title, @RequestParam("audio") MultipartFile audio, @RequestParam("file") MultipartFile image) {
        if (audio.isEmpty() || image.isEmpty()) return false;
        byte[] byteAudio = FileHelper.saveFile(audio, "audio/podcasts");
        byte[] byteImage = FileHelper.saveFile(image, "images/podcasts");
        if (byteAudio != null && byteImage != null) {
            Podcast podcast = new Podcast(title, FileHelper.compressBytes(byteAudio), FileHelper.compressBytes(byteImage));
            return this.service.post(podcast);
        }
        return false;
    }
}
