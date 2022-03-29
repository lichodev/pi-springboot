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
     * @return
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
     * Guarda en la BDD el podcast recibido
     * @param podcast
     * @return
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
