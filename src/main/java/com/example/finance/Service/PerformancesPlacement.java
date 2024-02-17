package com.example.finance.Service;

import java.math.BigDecimal;

public class PerformancesPlacement {
    private BigDecimal rendement;
    private BigDecimal plusValue;
    private BigDecimal moinsValue;

    public BigDecimal getRendement() {
        return rendement;
    }

    @Override
    public String toString() {
        return "PerformancesPlacement{" +
                "rendement=" + rendement +
                ", plusValue=" + plusValue +
                ", moinsValue=" + moinsValue +
                '}';
    }

    public PerformancesPlacement() {
    }

    public PerformancesPlacement(BigDecimal rendement, BigDecimal plusValue, BigDecimal moinsValue) {
        this.rendement = rendement;
        this.plusValue = plusValue;
        this.moinsValue = moinsValue;
    }

    public void setRendement(BigDecimal rendement) {
        this.rendement = rendement;
    }

    public BigDecimal getPlusValue() {
        return plusValue;
    }

    public void setPlusValue(BigDecimal plusValue) {
        this.plusValue = plusValue;
    }

    public BigDecimal getMoinsValue() {
        return moinsValue;
    }

    public void setMoinsValue(BigDecimal moinsValue) {
        this.moinsValue = moinsValue;
    }
}
