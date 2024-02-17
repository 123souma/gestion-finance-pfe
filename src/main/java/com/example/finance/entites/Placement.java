package com.example.finance.entites;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; // Type de placement (action, obligation, fonds commun de placement, etc.)
    private String nom; // Nom du placement
    private BigDecimal montantInvesti; // Montant investi dans le placement
    private Date dateAcquisition; // Date d'acquisition du placement
    private BigDecimal tauxRendementAttendu; // Taux de rendement attendu
    private BigDecimal rendement; // Rendement du placement
    private BigDecimal plusValue; // Plus-value du placement
    private BigDecimal minusValue;


    public Long getId() {
        return id;
    }

    public Placement(Long id, String type, String nom, BigDecimal montantInvesti, Date dateAcquisition, BigDecimal tauxRendementAttendu, BigDecimal rendement, BigDecimal plusValue, BigDecimal minusValue) {
        this.id = id;
        this.type = type;
        this.nom = nom;
        this.montantInvesti = montantInvesti;
        this.dateAcquisition = dateAcquisition;
        this.tauxRendementAttendu = tauxRendementAttendu;
        this.rendement = rendement;
        this.plusValue = plusValue;
        this.minusValue = minusValue;
    }

    public BigDecimal getRendement() {
        return rendement;
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

    public BigDecimal getMinusValue() {
        return minusValue;
    }

    public void setMinusValue(BigDecimal minusValue) {
        this.minusValue = minusValue;
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public BigDecimal getMontantInvesti() {
        return montantInvesti;
    }

    public void setMontantInvesti(BigDecimal montantInvesti) {
        this.montantInvesti = montantInvesti;
    }

    public Date getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(Date dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public BigDecimal getTauxRendementAttendu() {
        return tauxRendementAttendu;
    }

    public void setTauxRendementAttendu(BigDecimal tauxRendementAttendu) {
        this.tauxRendementAttendu = tauxRendementAttendu;
    }
    // Getters et setters (à ajouter)

    // Constructeurs (à ajouter)

    public Placement() {
    }

    @Override
    public String toString() {
        return "Placement{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", nom='" + nom + '\'' +
                ", montantInvesti=" + montantInvesti +
                ", dateAcquisition=" + dateAcquisition +
                ", tauxRendementAttendu=" + tauxRendementAttendu +
                ", rendement=" + rendement +
                ", plusValue=" + plusValue +
                ", minusValue=" + minusValue +
                '}';
    }

    // Autres méthodes (le cas échéant)
}
