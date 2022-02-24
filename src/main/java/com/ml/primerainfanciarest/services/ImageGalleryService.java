package com.ml.primerainfanciarest.services;

import com.ml.primerainfanciarest.converters.ImageGalleryConverter;
import com.ml.primerainfanciarest.entities.ImageGallery;
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
    ImageGalleryRepository repository;

    @Autowired
    @Qualifier("ImageGalleryConverter")
    ImageGalleryConverter converter;

    public List<ImageGalleryModel> get() {
        List<ImageGalleryModel> images = new ArrayList<>();
        for (ImageGallery i : this.repository.findAll()) {
            images.add(this.converter.convert(i));
        }
        return images;
    }

    public boolean post(ImageGallery imageGallery) {
        try {
            this.repository.save(imageGallery);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
