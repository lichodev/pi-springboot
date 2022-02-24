package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.models.PodcastModel;
import org.springframework.stereotype.Component;

@Component("PodcastConverter")
public class PodcastConverter {

    public PodcastModel convert(Podcast podcast) {
        return new PodcastModel(podcast);
    }
}
