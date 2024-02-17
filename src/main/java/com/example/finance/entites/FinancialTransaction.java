package com.example.finance.entites;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class FinancialTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal amount;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FinancialTransaction() {
    }

    @Override
    public String toString() {
        return "FinancialTransaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                ", user=" + user +
                '}';
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public FinancialTransaction(Long id, String type, BigDecimal amount, Date date, User user) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
// Getters and setters
}
