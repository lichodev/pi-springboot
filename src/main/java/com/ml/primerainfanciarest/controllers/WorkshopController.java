package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.models.WorkshopModel;
import com.ml.primerainfanciarest.services.WorkshopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workshops")
public class WorkshopController {
    @Autowired
    @Qualifier("WorkshopService")
    private WorkshopService service;

    @GetMapping
    public List<WorkshopModel> get() {
        return this.service.get();
    }
}
