package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Recorder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("RecorderRepository")
public interface RecorderRepository extends JpaRepository<Recorder, Serializable> {

    public abstract List<Recorder> findAll();
    public abstract Recorder findById(int id);
}
