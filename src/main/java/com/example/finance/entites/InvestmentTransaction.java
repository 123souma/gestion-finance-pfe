package com.example.finance.entites;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class InvestmentTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private String type;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "investment_id")
    private Investment investment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    public InvestmentTransaction(Long id, BigDecimal amount, String type, Date date, Investment investment) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.investment = investment;
    }

    public InvestmentTransaction() {
    }
// Getters and setters
}
