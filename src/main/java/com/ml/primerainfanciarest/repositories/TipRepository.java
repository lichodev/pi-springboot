package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("TipRepository")
public interface TipRepository extends JpaRepository<Tip, Serializable> {

    public abstract List<Tip> findAll();
    public abstract Tip findById(int id);

    @Query("SELECT COUNT (id) AS tips FROM Tip")
    public abstract int countAll();
}
