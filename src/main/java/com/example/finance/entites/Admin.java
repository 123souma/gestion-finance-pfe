package com.example.finance.entites;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cin;

    private String nom;
    private String password;
    private String email;
    private String prenom;
    private String phoneNumber;
    private Date date_naiss;
    private String adresse_hause ;
    private int code_postal ;
    @Column(name = "password_token")
    private String passwordToken;
    private String photoUrl;

    public Admin(Long cin, String nom, String password, String email, String prenom, String phoneNumber, Date date_naiss, String adresse_hause, int code_postal, String passwordToken, String photoUrl) {
        this.cin = cin;
        this.nom = nom;
        this.password = password;
        this.email = email;
        this.prenom = prenom;
        this.phoneNumber = phoneNumber;
        this.date_naiss = date_naiss;
        this.adresse_hause = adresse_hause;
        this.code_postal = code_postal;
        this.passwordToken = passwordToken;
        this.photoUrl = photoUrl;
    }

    public Admin() {
    }

    @Override
    public String toString() {
        return "Admin{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", prenom='" + prenom + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", date_naiss=" + date_naiss +
                ", adresse_hause='" + adresse_hause + '\'' +
                ", code_postal=" + code_postal +
                ", passwordToken='" + passwordToken + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                '}';
    }

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    public String getAdresse_hause() {
        return adresse_hause;
    }

    public void setAdresse_hause(String adresse_hause) {
        this.adresse_hause = adresse_hause;
    }

    public int getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(int code_postal) {
        this.code_postal = code_postal;
    }

    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
