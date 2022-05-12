package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ImageGalleryConverter;
import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.helpers.FileHelper;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import com.ml.primerainfanciarest.repositories.ImageGalleryRepository;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio encargado de la lógica relacionada con las imageGallery
 * @author sole
 * @version 1.0
 */
@Service("ImageGalleryService")
public class ImageGalleryService {
    @Autowired
    @Qualifier("ImageGalleryRepository")
    private ImageGalleryRepository repository;

    @Autowired
    @Qualifier("ImageGalleryConverter")
    private ImageGalleryConverter converter;

    /**
     * Obtiene el listado de imageGallery
     * @return galería de imágenes completa en forma de lista
     */
    public List<ImageGalleryModel> get() {
        List<ImageGalleryModel> images = new ArrayList<>();
        for (ImageGallery i : this.repository.findAll()) {
            images.add(this.converter.convert(i));
        }
        return images;
    }

    /**
     * Obtiene una imageGallery coincidente con el id recibido
     * @param id de la imageGallery que se desea recuperar
     * @return imageGallery solicitada
     */
    public ImageGalleryModel getById(int id) {
        ImageGallery ig = this.repository.getById(id);
        ig.setImage(FileHelper.decompressBytes(ig.getImage()));
        return this.converter.convert(ig);
    }

    /**
     * Guarda la imagen
     * @param imageGallery a guardar
     * @return boolean indicador del éxito en la transacción
     */
    public boolean post(ImageGallery imageGallery) {
        try {
            this.repository.save(imageGallery);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
