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

    public TipModel getDayTip() {
        LocalDate today = LocalDate.now(); //get a daily-tip with current date
        DailyTip dailyTip = this.repository.getByDate(today);
        int tipId = 0;
        if (dailyTip == null) {
            int tipQuantity = this.tipService.getQuantity();
            if (this.repository.countAll() == tipQuantity) {
                this.repository.emptyTable();
            }
            tipId = this.getRandomTipId();
            this.post(new DailyTip(today, tipId));
        } else
            tipId = dailyTip.getTipId();

        return this.tipService.getById(tipId);
    }

    private int getRandomTipId() {
        List<Integer> tips= this.tipService.getNotSelected();
        return (int) (Math.random()*tips.size()+1);
    }

    public boolean post(DailyTip dailyTip) {
        try {
            this.repository.save(dailyTip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
