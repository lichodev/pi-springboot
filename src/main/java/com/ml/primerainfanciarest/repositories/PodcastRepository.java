package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla podcast
 * @author sole
 * @version 1.0
 */
@Repository("PodcastRepository")
public interface PodcastRepository extends JpaRepository<Podcast, Serializable> {
    public abstract List<Podcast> findAll();
}
