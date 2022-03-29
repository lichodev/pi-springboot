package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ExperienceConverter;
import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ExperienceModel;
import com.ml.primerainfanciarest.models.ReminderModel;
import com.ml.primerainfanciarest.repositories.ExperienceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("ExperienceService")
public class ExperienceService {

    @Autowired
    @Qualifier("ExperienceRepository")
    private ExperienceRepository repository;

    @Autowired
    @Qualifier("ExperienceConverter")
    private ExperienceConverter converter;

    /**
     * Obtiene el listado de todas las experiencias, cuyo estado sea igual al recibido por parámetro
     * @param status
     * @return
     */
    public List<ExperienceModel> getByStatus(boolean status) {
        List<ExperienceModel> experiences = new ArrayList<>();
        for (Experience e: this.repository.findAllByStatus(status)) {
            if (e.getImage() != null)
                e.setImage(FileHelper.decompressBytes(e.getImage()));
            experiences.add(this.converter.convert(e));
        }
        Collections.sort(experiences);
        return experiences;
    }

    /**
     * Recibe una experiencia y la guarda en la base de datos
     * @param experience
     * @return
     */
    public boolean post(Experience experience) {
        try {
            repository.save(experience);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene la experiencia coincidente con el id recibido por parámetro
     * Cambia el estado a verdadero haciendo que sea visible para los usuarios no logueados
     * Guarda los cambios en la BDD
     * @param id
     * @return
     */
    public boolean accept(int id) {
        Experience e = this.repository.findById(id);
        e.setStatus(true);
        try {
            this.repository.save(e);
            return true;
        } catch (Exception er) {
            return false;
        }
    }

    /**
     * Si existe una experiencia con el id recibido por parámetro la elimina de la base de datos
     * @param id
     * @return
     */
    public boolean delete(int id) {
        try {
            if (this.repository.existsById(id)) {
                this.repository.deleteById((Serializable) id);
                return true;
            } else return false;
        } catch (Exception e) {
            return false;
        }
    }
}
