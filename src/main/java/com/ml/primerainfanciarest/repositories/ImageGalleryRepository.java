package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla image_gallery
 * @author sole
 * @version 1.0
 */
@Repository("ImageGalleryRepository")
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Serializable> {

    public List<ImageGallery> findAll();
}
