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
 * <p>Se encarga de resolver todas las solicitudes relacionadas
 * con la tabla 'reminder'</p>
 * @author sole
 * @version 1.0
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
     * <p>Se accede mediante el método GET</p>
     * @return Lista de reminders
     */
    @GetMapping
    public List<ReminderModel> get() {
        return this.service.get();
    }

    /**
     * Obtiene el recordatorio asociado al id recibido
     * <p>Se accede mediante el método GET y al path inicial se
     * le agrega el id del recordatorio buscado</p>
     * @param id representa el identificador único del reminder al que se quiere acceder
     * @return Reminder con toda la información pertinente
     */
    @GetMapping(value = "{id}")
    public ReminderModel getById(@PathVariable ("id") int id) {
        return this.service.getById(id);
    }

    /**
     * Crea y guarda un recordatorio con los datos recibidos
     * <p>El usuario logueado puede acceder mediante el método POST</p>
     * @param since edad a partir de la cual se aplica
     * @param until edad máxima a la cual se aplica
     * @param image representativa
     * @return boolean que indica si se pudo guardar
     * @see Reminder
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
     * <p>Se accede mediante el método PUT</p>
     * @param id del reminder al que se quiere modificar
     * @param value puede ser un valor positivo que indica que se debe sumar
     *              un 'like' o un valor negativo, correspondiente a un 'dislike'
     * @return boolean que indica si se guardaron los cambios
     * @see ReminderService
     */
    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
