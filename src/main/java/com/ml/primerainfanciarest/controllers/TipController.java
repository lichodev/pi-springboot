package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.services.TipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tips")
public class TipController {

    @Autowired
    @Qualifier("TipService")
    private TipService service;

    @GetMapping
    public List<TipModel> get() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}")
    public TipModel getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PostMapping
    public boolean add(@RequestBody @Validated Tip tip) {
        //TODO: verificar login
        return service.save(tip);
    }

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable("id") int id, @RequestBody int value) {
        return this.service.put(id, value);
    }
}
