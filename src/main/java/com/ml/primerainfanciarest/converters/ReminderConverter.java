package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.models.ReminderModel;
import org.springframework.stereotype.Component;

@Component("ReminderConverter")
public class ReminderConverter {

    /**
     * Crea un ReminderModel a partir del recordatorio recibido
     * @param reminder
     * @return
     */
    public ReminderModel convert(Reminder reminder) {
        return new ReminderModel(reminder);
    }
}
