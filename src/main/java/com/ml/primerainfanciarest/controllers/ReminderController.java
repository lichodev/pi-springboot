package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ReminderModel;
import com.ml.primerainfanciarest.services.ReminderService;
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
@RequestMapping("/reminder")
@CrossOrigin
public class ReminderController {
    @Autowired
    @Qualifier("ReminderService")
    private ReminderService service;

    @GetMapping
    public List<ReminderModel> get() {
        return this.service.get();
    }

    @GetMapping(value = "{id}")
    public ReminderModel getById(@PathVariable ("id") int id) {
        return this.service.getById(id);
    }

    @PostMapping()
    public boolean post(@RequestParam("since") int since, @RequestParam("until") int until, @RequestParam("file") MultipartFile image) {
        if (image.isEmpty()) return false;
        byte[] byteImage = FileHelper.saveFile(image, "images/reminders");
        if (byteImage != null) {
            Reminder reminder = new Reminder(since, until, FileHelper.compressBytes(byteImage), 0, 0);
            return this.service.post(reminder);
        }
        return false;
    }

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
