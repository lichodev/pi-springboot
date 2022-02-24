package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.RecorderConverter;
import com.ml.primerainfanciarest.entities.Recorder;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.models.RecorderModel;
import com.ml.primerainfanciarest.repositories.RecorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("RecorderService")
public class RecorderService {
    @Autowired
    @Qualifier("RecorderRepository")
    private RecorderRepository repository;

    @Autowired
    @Qualifier("RecorderConverter")
    private RecorderConverter converter;

    public List<RecorderModel> get() {
        List<RecorderModel> recorders = new ArrayList<>();
        for (Recorder r: this.repository.findAll()) {
            recorders.add(this.converter.convert(r));
        }
        return recorders;
    }

    public boolean post(Recorder recorder) {
        try {
            this.repository.save(recorder);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean put(int id, int value) {
        Recorder recorder = this.repository.findById(id);
        if (recorder != null) {
            if (value > 0) {
                recorder.plusLike();
            } else {
                recorder.plusDislike();
            }
            try {
                this.repository.save(recorder);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }
}
