package com.ml.primerainfanciarest.controllers;

import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.services.DailyTipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Controlador accesible con el path "/daily-tip"
 * <p>Se encarga de todas las peticiones para la tabla 'daily_tip'</p>
 * @author sole
 * @version 1.0
 */
@RestController
@RequestMapping("/daily-tip")
@CrossOrigin
public class DailyTipController {

    @Autowired
    @Qualifier("DailyTipService")
    private DailyTipService service;

    /**
     * Obtiene un tip asociado a la fecha actual
     * <p>Se accede mediante método GET</p>
     * @return TipModel asociado a la fecha actual
     * @see DailyTipService
     */
    @GetMapping
    public TipModel get() {
        return this.service.getDayTip();
    }

}
