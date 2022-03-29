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

/**
 * Controlador accesible mediante el path "/reminder"
 * Se encarga de resolver todas las solicitudes relacionadas con la tabla 'reminder'
 */
@RestController
@RequestMapping("/reminder")
@CrossOrigin
public class ReminderController {
    @Autowired
    @Qualifier("ReminderService")
    private ReminderService service;

    /**
     * Obtiene el listado de recordatorios
     * Se accede mediante el método GET
     * @return
     */
    @GetMapping
    public List<ReminderModel> get() {
        return this.service.get();
    }

    /**
     * Obtiene el recordatorio asociado al id recibido
     * Se accede mediante el método GET y al path inicial se
     * le agrega el id del recordatorio buscado
     * @param id
     * @return
     */
    @GetMapping(value = "{id}")
    public ReminderModel getById(@PathVariable ("id") int id) {
        return this.service.getById(id);
    }

    /**
     * Rercibe los datos de un recordatorio, lo crea y lo envía al servicio
     * correspondiente para que se guarde en la BDD
     * El usuario logueado puede acceder mediante el método POST
     * @param since
     * @param until
     * @param image
     * @return
     */
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

    /**
     * Suma likes o dislikes al recordatorio coincidente con el id recibido
     * Se accede mediante el método PUT
     * @param id
     * @param value
     * @return
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
