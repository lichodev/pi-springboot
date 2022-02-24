package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.PodcastConverter;
import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.models.PodcastModel;
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

    public List<PodcastModel> get() {
        ArrayList<PodcastModel> podcasts = new ArrayList<>();
        for (Podcast p : this.repository.findAll()) {
            podcasts.add(this.converter.convert(p));
        }
        return podcasts;
    }

    public boolean save(Podcast podcast) {
        try {
            this.repository.save(podcast);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
