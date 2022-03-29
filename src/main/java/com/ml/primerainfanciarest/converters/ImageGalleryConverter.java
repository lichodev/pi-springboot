package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import org.springframework.stereotype.Component;

@Component("ImageGalleryConverter")
public class ImageGalleryConverter {

    /**
     * Crea un ImageGalleryModel a partir de la ImageGallery recibida
     * @param imageGallery
     * @return
     */
    public ImageGalleryModel convert(ImageGallery imageGallery) {
        return new ImageGalleryModel(imageGallery);
    }
}
