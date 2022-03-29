package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.models.TipModel;
import org.springframework.stereotype.Component;

@Component("TipConverter")
public class TipConverter {

    /**
     * Crea un TipModel a partir del tip recibido
     * @param tip
     * @return
     */
    public TipModel convert(Tip tip) {
        return new TipModel(tip);
    }
}
