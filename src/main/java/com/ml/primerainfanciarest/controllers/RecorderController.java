package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Recorder;
import com.ml.primerainfanciarest.models.RecorderModel;
import com.ml.primerainfanciarest.services.RecorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recorders")
public class RecorderController {
    @Autowired
    @Qualifier("RecorderService")
    private RecorderService service;

    @GetMapping
    public List<RecorderModel> get() {
        return this.service.get();
    }

    @PostMapping
    public boolean post(@RequestBody @Validated Recorder recorder) {
        //TODO: verificar login
        return this.service.post(recorder);
    }

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
