package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.DailyTipConverter;
import com.ml.primerainfanciarest.entities.DailyTip;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.repositories.DailyTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service("DailyTipService")
public class DailyTipService {

    @Autowired
    @Qualifier("DailyTipRepository")
    private DailyTipRepository repository;

    @Autowired
    @Qualifier("DailyTipConverter")
    private DailyTipConverter converter;

    @Autowired
    @Qualifier("TipService")
    private TipService tipService;

    /**
     * Si existe un tip asociado a la fecha actual, lo devuelve
     * Si no existe, toma un tip que no esté asociado a ninguna fecha y lo asocia a la fecha actual
     * Si todos los tips tienen una fecha asociada, borra todas las asociaciones y empieza de nuevo
     * @return el tip que resulta asociado con la fecha actual
     */
    public TipModel getDayTip() {
        LocalDate today = LocalDate.now(); //get a daily-tip with current date
        DailyTip dailyTip = this.repository.getByDate(today);
        int tipId = 0;
        if (dailyTip == null) {
            int tipQuantity = this.tipService.getQuantity();
            if (this.repository.countAll() == tipQuantity) {
                this.repository.deleteAll();
            }
            tipId = this.getRandomTipId();
            this.post(new DailyTip(today, tipId));
        } else
            tipId = dailyTip.getTipId();

        return this.tipService.getById(tipId);
    }

    /**
     * Obtiene el listado de ids de tips que no tienen una fecha asociada
     * y elige un id al azar
     * @return
     */
    private int getRandomTipId() {
        List<Integer> tips= this.tipService.getNotSelected();
        int i = (int) (Math.random()*tips.size()+1);
        return tips.get(i-1);
    }

    /**
     * Guarda en la BDD una asociación de un tip y la fecha actual
     * @param dailyTip
     * @return
     */
    public boolean post(DailyTip dailyTip) {
        try {
            this.repository.save(dailyTip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
