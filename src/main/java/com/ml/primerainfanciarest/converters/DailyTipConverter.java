package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.DailyTip;
import com.ml.primerainfanciarest.models.DailyTipModel;
import org.springframework.stereotype.Component;

@Component("DailyTipConverter")
public class DailyTipConverter {

    public DailyTipModel convert(DailyTip dailyTip) {
        return new DailyTipModel(dailyTip);
    }
}
