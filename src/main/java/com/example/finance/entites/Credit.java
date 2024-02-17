package com.example.finance.entites;



import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private int duration;
    private Date startDate;

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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Credit(Long id, String type, BigDecimal amount, BigDecimal interestRate, int duration, Date startDate, User user) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.interestRate = interestRate;
        this.duration = duration;
        this.startDate = startDate;
        this.user = user;
    }

    public Credit() {
    }

    @Override
    public String toString() {
        return "Credit{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", interestRate=" + interestRate +
                ", duration=" + duration +
                ", startDate=" + startDate +
                ", user=" + user +
                '}';
    }
// Getters and setters
}
