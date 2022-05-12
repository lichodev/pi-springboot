package com.ml.primerainfanciarest.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Entidad que representa la tabla 'daily-tip'
 * <p>Posee una fecha y el id de un tip como FK</p>
 * @author sole
 * @version 1.0
 */
@Entity
public class DailyTip {
    private LocalDate date;
    private int tipId;
    private Tip tipByTipId;

    public DailyTip() {
    }

    public DailyTip(LocalDate date, int tipId) {
        this.date = date;
        this.tipId = tipId;
    }

    @Id
    @Column(name = "date")
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Basic
    @Column(name = "tipId" )
    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DailyTip that = (DailyTip) o;
        return tipId == that.tipId && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, tipId);
    }

    @ManyToOne
    @JoinColumn(name = "tipId", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    public Tip getTipByTipId() {
        return tipByTipId;
    }

    public void setTipByTipId(Tip tipByTipId) {
        this.tipByTipId = tipByTipId;
    }
}

