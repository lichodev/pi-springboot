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

/**
 * Servicio encargado de la lógica relacionada con los reminders
 * @author sole
 * @version 1.0
 */
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
     * <p>Los recordatorios se devuelven ordenados según la
     * columna 'since' en orden ascendente</p>
     * @return Lista ordenada de reminders
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
     * Obtiene el recordatorio asociado al id recibido
     * @param id único de reminder que se busca
     * @return Reminder solicitado
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
     * @param reminder a guardar
     * @return boolean que indica el éxito de la operación
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
     * @param id representa al tip que se desa editar
     * @param value puede ser un valor positivo que indica que se debe sumar
     *               un 'like' o un valor negativo, correspondiente a un 'dislike'
     * @return boolean indicador del éxito en la operación
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
