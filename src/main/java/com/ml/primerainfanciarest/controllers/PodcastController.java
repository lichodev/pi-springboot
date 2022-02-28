package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Podcast;
import com.ml.primerainfanciarest.models.PodcastModel;
import com.ml.primerainfanciarest.services.PodcastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/podcast")
@CrossOrigin
public class PodcastController {

    @Autowired
    @Qualifier("PodcastService")
    private PodcastService service;

    @GetMapping
    public List<PodcastModel> get() {
        return this.service.get();
    }

    @PostMapping
    public boolean post(@RequestBody @Validated Podcast podcast) {
        //TODO: verificar login
        return this.service.save(podcast);
    }
}
