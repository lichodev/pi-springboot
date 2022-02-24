package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.Recorder;
import com.ml.primerainfanciarest.models.RecorderModel;
import org.springframework.stereotype.Component;

@Component("RecorderConverter")
public class RecorderConverter {
    public RecorderModel convert(Recorder recorder) {
        return new RecorderModel(recorder);
    }
}
