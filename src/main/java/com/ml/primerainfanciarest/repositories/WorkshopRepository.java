package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla workshop
 * @author sole
 * @version 1.0
 */
@Repository("WorkshopRepository")
public interface WorkshopRepository extends JpaRepository<Workshop, Serializable> {
    public abstract List<Workshop> findAll();
}
