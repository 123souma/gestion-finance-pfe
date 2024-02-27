package com.example.finance.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class CreditPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private Date date;
    private String status;

    @ManyToOne
    @JoinColumn(name = "credit_id")
    private Credit credit;

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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Credit getCredit() {
        return credit;
    }

    public void setCredit(Credit credit) {
        this.credit = credit;
    }

    public CreditPayment() {
    }

    @Override
    public String toString() {
        return "CreditPayment{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", credit=" + credit +
                '}';
    }

    public CreditPayment(Long id, BigDecimal amount, Date date, String status, Credit credit) {
        this.id = id;
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.credit = credit;
    }
// Getters and setters
}
