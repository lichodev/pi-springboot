package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ReminderConverter;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ReminderModel;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.repositories.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("ReminderService")
public class ReminderService {
    @Autowired
    @Qualifier("ReminderRepository")
    private ReminderRepository repository;

    @Autowired
    @Qualifier("ReminderConverter")
    private ReminderConverter converter;

    /**
     * Obtiene el listado de todos los recordatorios
     * y los devuelve ordenados según la columna 'since' en orden ascendente
     * @return
     */
    public List<ReminderModel> get() {
        List<ReminderModel> reminders = new ArrayList<>();
        for (Reminder r: this.repository.findAll()) {
            r.setImage(FileHelper.decompressBytes(r.getImage()));
            reminders.add(this.converter.convert(r));
        }
        Collections.sort(reminders);
        return reminders;
    }

    /**
     * Obtiene el recordatorio asociado al id recibido por parámetro
     * @param id
     * @return
     */
    public ReminderModel getById(int id) {
        try {
            Reminder reminder = this.repository.findById(id);
            reminder.setImage(FileHelper.decompressBytes(reminder.getImage()));
            return this.converter.convert(reminder);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Guarda en la BDD el recordatorio recibido
     * @param reminder
     * @return
     */
    public boolean post(Reminder reminder) {
        try {
            this.repository.save(reminder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Suma un like o un dislike al recordatorio cuyo id coincide con el recibido
     * @param id
     * @param value
     * @return
     */
    public boolean put(int id, int value) {
        Reminder reminder = this.repository.findById(id);
        if (reminder != null) {
            if (value > 0) {
                reminder.plusLike();
            } else {
                reminder.plusDislike();
            }
            try {
                this.repository.save(reminder);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
