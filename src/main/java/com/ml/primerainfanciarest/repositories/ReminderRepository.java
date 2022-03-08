package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("ReminderRepository")
public interface ReminderRepository extends JpaRepository<Reminder, Serializable> {

    public abstract List<Reminder> findAll();
    public abstract Reminder findById(int id);
}
