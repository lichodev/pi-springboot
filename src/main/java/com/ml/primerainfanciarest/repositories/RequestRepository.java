package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla request
 * @author sole
 * @version 1.0
 */
@Repository("RequestRepository")
public interface RequestRepository extends JpaRepository<Request, Serializable> {
    public abstract List<Request> findAll();
    public abstract Request getById(int id);
}
