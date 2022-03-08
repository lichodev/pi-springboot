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

@RestController
@RequestMapping("/experiences")
@CrossOrigin
public class ExperienceController {

    @Autowired
    @Qualifier("ExperienceService")
    private ExperienceService service;

    @GetMapping
    public List<ExperienceModel> getAccept() {
        return this.service.getByStatus(true);
    }

    @GetMapping("/admin")
    public List<ExperienceModel> getPending() {
        return this.service.getByStatus(false);
    }

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

    @PutMapping(value = "{id}")
    public boolean accept(@PathVariable ("id") int id) {
        return this.service.accept(id);
    }

    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable ("id") int id) {
        return this.service.delete(id);
    }
}
