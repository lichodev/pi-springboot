package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.ImageGallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("ImageGalleryRepository")
public interface ImageGalleryRepository extends JpaRepository<ImageGallery, Serializable> {

    public List<ImageGallery> findAll();
}
