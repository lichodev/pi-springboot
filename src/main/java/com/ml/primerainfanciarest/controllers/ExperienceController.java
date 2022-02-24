package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.models.ExperienceModel;
import com.ml.primerainfanciarest.services.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experiences")
public class ExperienceController {

    @Autowired
    @Qualifier("ExperienceService")
    private ExperienceService service;

    @GetMapping
    public List<ExperienceModel> getByStatus() {
        //TODO: verificar login. Si está logueado, status=false, si no, status=true
        boolean status = true;
        return this.service.getByStatus(status);
    }

    @PostMapping
    public boolean add(@RequestBody @Validated Experience experience) {
        return service.save(experience);
    }

    @PutMapping(value = "{id}")
    public boolean accept(@PathVariable ("id") int id) {
        //TODO: verificar login
        return this.service.accept(id);
    }

    @DeleteMapping(value = "{id}")
    public boolean delete(@PathVariable ("id") int id) {
        //TODO: verificar login
        return this.service.delete(id);
    }
}
