package com.example.finance.entites;




import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String name;
    private BigDecimal investedAmount;
    private Date acquisitionDate;
    private BigDecimal expectedReturnRate;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getInvestedAmount() {
        return investedAmount;
    }

    public void setInvestedAmount(BigDecimal investedAmount) {
        this.investedAmount = investedAmount;
    }

    public Date getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(Date acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public BigDecimal getExpectedReturnRate() {
        return expectedReturnRate;
    }

    public void setExpectedReturnRate(BigDecimal expectedReturnRate) {
        this.expectedReturnRate = expectedReturnRate;
    }

    @Override
    public String toString() {
        return "Investment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", investedAmount=" + investedAmount +
                ", acquisitionDate=" + acquisitionDate +
                ", expectedReturnRate=" + expectedReturnRate +
                '}';
    }

    public Investment(Long id, String type, String name, BigDecimal investedAmount, Date acquisitionDate, BigDecimal expectedReturnRate) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.investedAmount = investedAmount;
        this.acquisitionDate = acquisitionDate;
        this.expectedReturnRate = expectedReturnRate;
    }

    public Investment() {
    }
// Getters and setters
}
