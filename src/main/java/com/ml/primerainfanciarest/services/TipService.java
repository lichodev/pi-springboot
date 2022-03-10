package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.TipConverter;
import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.TipModel;
import com.ml.primerainfanciarest.repositories.TipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("TipService")
public class TipService {

    @Autowired
    @Qualifier("TipRepository")
    private TipRepository repository;

    @Autowired
    @Qualifier("TipConverter")
    private TipConverter converter;

    public boolean post(Tip tip) {
        try {
            repository.save(tip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<TipModel> getAll() {
        List<Tip> tips = repository.findAll();
        ArrayList<TipModel> tipsModels = new ArrayList<>();
        for (Tip tip: tips) {
            tip.setImage(FileHelper.decompressBytes(tip.getImage()));
            tipsModels.add(converter.convert(tip));
        }
        return tipsModels;
    }

    public TipModel getById(int id) {
        TipModel tip = converter.convert(repository.findById(id));
        tip.setImage(FileHelper.decompressBytes(tip.getImage()));
        return tip;
    }

    public boolean put(int id, int value) {
        Tip tip = this.repository.findById(id);
        if (tip != null) {
            if (value > 0) {
                tip.plusLike();
            } else {
                tip.plusDislike();
            }
            try {
                this.repository.save(tip);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    public int getQuantity() {
        return this.repository.countAll();
    }
}
