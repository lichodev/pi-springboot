package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.models.TipModel;
import org.springframework.stereotype.Component;

@Component("TipConverter")
public class TipConverter {

    public TipModel convert(Tip tip) {
        return new TipModel(tip);
    }
}
