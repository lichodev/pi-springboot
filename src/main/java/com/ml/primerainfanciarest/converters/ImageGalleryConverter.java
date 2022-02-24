package com.ml.primerainfanciarest.converters;

import com.ml.primerainfanciarest.entities.ImageGallery;
import com.ml.primerainfanciarest.models.ImageGalleryModel;
import org.springframework.stereotype.Component;

@Component("ImageGalleryConverter")
public class ImageGalleryConverter {
    public ImageGalleryModel convert(ImageGallery imageGallery) {
        return new ImageGalleryModel(imageGallery);
    }
}
