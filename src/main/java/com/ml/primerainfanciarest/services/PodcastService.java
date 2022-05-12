package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.PodcastConverter;
import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.entities.Reminder;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.PodcastModel;
import com.ml.primerainfanciarest.models.ReminderModel;
import com.ml.primerainfanciarest.repositories.PodcastRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con los podcasts
 * @author sole
 * @version 1.0
 */
@Service("PodcastService")
public class PodcastService {

    @Autowired
    @Qualifier("PodcastRepository")
    private PodcastRepository repository;

    @Autowired
    @Qualifier("PodcastConverter")
    private PodcastConverter converter;

    /**
     * Obtiene el listado completo de podcasts
     * @return lista de podcasts
     */
    public List<PodcastModel> get() {
        List<PodcastModel> podcasts = new ArrayList<>();
        for (Podcast p : this.repository.findAll()) {
            p.setImage(FileHelper.decompressBytes(p.getImage()));
            p.setAudio(FileHelper.decompressBytes(p.getAudio()));
            podcasts.add(this.converter.convert(p));
        }
        return podcasts;
    }

    /**
     * Guarda el podcast recibido
     * @param podcast a guardar
     * @return boolean que indica el éxito de la transacción
     */
    public boolean post(Podcast podcast) {
        try {
            this.repository.save(podcast);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
