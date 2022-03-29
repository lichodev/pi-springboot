package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.models.ExperienceModel;
import org.springframework.stereotype.Component;

@Component("ExperienceConverter")
public class ExperienceConverter {

    /**
     * Crea un ExperienceModel a partir de la Experiencia recibida por parámetro
     * @param experience
     * @return
     */
    public ExperienceModel convert(Experience experience) {
        return new ExperienceModel(experience);
    }
}
