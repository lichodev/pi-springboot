package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.DailyTip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla daily_tip
 * @author sole
 * @version 1.0
 */
@Repository("DailyTipRepository")
public interface DailyTipRepository extends JpaRepository<DailyTip, Serializable> {

    public abstract DailyTip getByDate(LocalDate date);
    public abstract DailyTip getByTipId(int id);

    @Query("SELECT COUNT (tipId) AS tips FROM DailyTip")
    public abstract int countAll();
    public abstract void deleteAll();
}
