package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.entities.Request;
import com.ml.primerainfanciarest.models.RequestModel;
import com.ml.primerainfanciarest.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
@CrossOrigin
public class RequestController {
    @Autowired
    @Qualifier("RequestService")
    private RequestService service;

    @GetMapping
    public List<RequestModel> get() {
        return this.service.get();
    }

    @PostMapping
    public boolean post(@RequestBody @Validated Request request) {
        return this.service.post(request);
    }

    @PutMapping(value = "{id}")
    public boolean put(@PathVariable ("id") int id) {
        return this.service.checkRelpied(id);
    }
}
