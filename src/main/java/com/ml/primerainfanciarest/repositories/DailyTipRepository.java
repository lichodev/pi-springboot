package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.DailyTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;

@Repository("DailyTipRepository")
public interface DailyTipRepository extends JpaRepository<DailyTip, Serializable> {

    public abstract DailyTip getByDate(LocalDate date);
    public abstract DailyTip getByTipId(int id);

    @Query("SELECT COUNT (tipId) AS tips FROM DailyTip")
    public abstract int countAll();

    @Query("DELETE FROM DailyTip")
    public abstract void emptyTable();
}
