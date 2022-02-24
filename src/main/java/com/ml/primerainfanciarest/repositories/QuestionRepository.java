package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("QuestionRepository")
public interface QuestionRepository extends JpaRepository<Question, Serializable> {
    public abstract List<Question> findAll();
    public abstract Question getById(int id);
}
