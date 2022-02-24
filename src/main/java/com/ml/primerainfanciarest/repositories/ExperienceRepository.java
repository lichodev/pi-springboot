package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("ExperienceRepository")
public interface ExperienceRepository extends JpaRepository<Experience, Serializable> {

    public abstract List<Experience> findAllByStatus(boolean status);
    public abstract Experience findById(int id);
    public abstract void deleteById(int id);
    public abstract void deleteExperiencesById(int id);
}
