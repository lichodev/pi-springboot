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

@Service("ImageGalleryService")
public class ImageGalleryService {
    @Autowired
    @Qualifier("ImageGalleryRepository")
    private ImageGalleryRepository repository;

    @Autowired
    @Qualifier("ImageGalleryConverter")
    private ImageGalleryConverter converter;

    /**
     * Obtiene el listado de imágenes
     * @return
     */
    public List<ImageGalleryModel> get() {
        List<ImageGalleryModel> images = new ArrayList<>();
        for (ImageGallery i : this.repository.findAll()) {
            images.add(this.converter.convert(i));
        }
        return images;
    }

    /**
     * Obtiene una imagen coincidente con el id recibido por parámetro
     * @param id
     * @return
     */
    public ImageGalleryModel getById(int id) {
        ImageGallery ig = this.repository.getById(id);
        ig.setImage(FileHelper.decompressBytes(ig.getImage()));
        return this.converter.convert(ig);
    }

    /**
     * Guarda la imagen en la BDD
     * @param imageGallery
     * @return
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
