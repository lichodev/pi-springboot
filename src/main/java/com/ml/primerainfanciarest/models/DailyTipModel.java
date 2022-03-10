package com.ml.primerainfanciarest.models;

import com.ml.primerainfanciarest.entities.Tip;
import com.ml.primerainfanciarest.entities.DailyTip;

import java.time.LocalDate;

public class DailyTipModel {
    private LocalDate date;
    private int tipId;
    private Tip tipByTipId;

    public DailyTipModel(DailyTip dailyTip) {
        this.date = dailyTip.getDate();
        this.tipId = dailyTip.getTipId();
        this.tipByTipId = dailyTip.getTipByTipId();
    }

    public DailyTipModel(LocalDate date, int tipId, Tip tipByTipId) {
        this.date = date;
        this.tipId = tipId;
        this.tipByTipId = tipByTipId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTipId() {
        return tipId;
    }

    public void setTipId(int tipId) {
        this.tipId = tipId;
    }
}
