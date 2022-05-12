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

/**
 * Servicio encargado de la lógica relacionada con los tips
 * @author sole
 * @version 1.0
 */
@Service("TipService")
public class TipService {

    @Autowired
    @Qualifier("TipRepository")
    private TipRepository repository;

    @Autowired
    @Qualifier("TipConverter")
    private TipConverter converter;

    /**
     * Guarda en la BDD el tip recibido
     * @param tip a guardar
     * @return boolean indicador del éxito en la operación
     */
    public boolean post(Tip tip) {
        try {
            repository.save(tip);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene el listado de todos los tips
     * @return Lista completa de tips
     */
    public List<TipModel> getAll() {
        List<Tip> tips = repository.findAll();
        ArrayList<TipModel> tipsModels = new ArrayList<>();
        for (Tip tip: tips) {
            tip.setImage(FileHelper.decompressBytes(tip.getImage()));
            tipsModels.add(converter.convert(tip));
        }
        return tipsModels;
    }

    /**
     * Obtiene el tip cuyo id coincide con el recibido por parámetro
     * @param id identificador único del id
     * @return Tip cuyo id es el indicado
     */
    public TipModel getById(int id) {
        TipModel tip = converter.convert(repository.findById(id));
        tip.setImage(FileHelper.decompressBytes(tip.getImage()));
        return tip;
    }

    /**
     * Suma un like o un dislike al tip cuyo id coincide con el recibido
     * @param id representa al tip que se desa editar
     * @param value puede ser un valor positivo que indica que se debe sumar
     *              un 'like' o un valor negativo, correspondiente a un 'dislike'
     * @return boolean indicador del éxito en la operación
     */
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

    /**
     * Obtiene la cantidad de tips que hay guardados actualmente en la BDD
     * @return int cantidad
     * @see DailyTipService
     */
    public int getQuantity() {
        return this.repository.countAll();
    }

    /**
     * Obtiene el listado de los ids de todos los tips que no están presentes en
     * la tabla de tips diarios
     * @return Lista de Integer que representan los tips
     * @see DailyTipService
     */
    public List<Integer> getNotSelected() {
        return this.repository.findAllNotSelected();
    }
}
