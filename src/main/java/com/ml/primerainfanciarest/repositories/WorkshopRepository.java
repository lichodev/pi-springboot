package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("WorkshopRepository")
public interface WorkshopRepository extends JpaRepository<Workshop, Serializable> {
    public abstract List<Workshop> findAll();
}
