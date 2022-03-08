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
import java.util.List;

@Service("ExperienceService")
public class ExperienceService {

    @Autowired
    @Qualifier("ExperienceRepository")
    private ExperienceRepository repository;

    @Autowired
    @Qualifier("ExperienceConverter")
    private ExperienceConverter converter;

    public List<ExperienceModel> getByStatus(boolean status) {
        List<ExperienceModel> experiences = new ArrayList<>();
        for (Experience e: this.repository.findAllByStatus(status)) {
            if (e.getImage() != null)
                e.setImage(FileHelper.decompressBytes(e.getImage()));
            experiences.add(this.converter.convert(e));
        }
        return experiences;
    }

    public boolean post(Experience experience) {
        try {
            repository.save(experience);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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

    //TODO: mejorar (retorna true, pero no elimina)
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
