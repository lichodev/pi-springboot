package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.DailyTipConverter;
import com.ml.primerainfanciarest.entities.DailyTip;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.repositories.DailyTipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
            tipId = this.getRandomTipId(tipQuantity);
            this.post(new DailyTip(today, tipId));
        } else
            tipId = dailyTip.getTipId();

        return this.tipService.getById(tipId);
    }

    private int getRandomTipId(int tipQuatity) {
        int tipId = 0;
        while (this.isSavedTip(tipId)) {
            tipId = (int) (Math.random()*tipQuatity+1);
        }   //I'll get tip-id when it isn't saved it daily-tip table
        return tipId;
    }

    public boolean isSavedTip(int tipId) {
        if (tipId == 0) return true;
        DailyTip dailyTip = this.repository.getByTipId(tipId);
        return dailyTip != null;
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
