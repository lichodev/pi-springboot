package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Experience;
import com.ml.primerainfanciarest.models.ExperienceModel;
import org.springframework.stereotype.Component;

@Component("ExperienceConverter")
public class ExperienceConverter {

    public ExperienceModel convert(Experience experience) {
        return new ExperienceModel(experience);
    }
}
