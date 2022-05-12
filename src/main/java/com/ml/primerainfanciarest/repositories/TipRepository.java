package com.ml.primerainfanciarest.repositories;

import com.ml.primerainfanciarest.entities.DailyTip;
import com.ml.primerainfanciarest.entities.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * repositorio encargado de las transacciones con la BBDD correspondientes a la tabla tip
 * @author sole
 * @version 1.0
 */
@Repository("TipRepository")
public interface TipRepository extends JpaRepository<Tip, Serializable> {

    public abstract List<Tip> findAll();
    public abstract Tip findById(int id);

    /**
     * Cuenta la cantidad de tuplas en la tabla
     * @return int cantidad
     */
    @Query("SELECT COUNT (id) AS tips FROM Tip")
    public abstract int countAll();

    /**
     * Obtiene los tips que no se han registrado como tip diario
     * @return Lista de tips
     */
    @Query("SELECT tip.id  FROM Tip AS tip WHERE tip.id NOT IN (SELECT dailyTip.tipId FROM DailyTip AS dailyTip)")
    public abstract List<Integer> findAllNotSelected();
}
