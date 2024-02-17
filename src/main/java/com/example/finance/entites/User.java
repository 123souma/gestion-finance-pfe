package com.example.finance.entites;
import javax.persistence.*;
import java.util.List;

import java.util.Date;
@Entity
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cin;
  private String nom;
  private  String prenom;
    @Column(name = "password_token")
    private String passwordToken;
    private String email;
    private String password;
    private String ville ;
    private Long num_tel;


    private Date date_naiss;
    private  String reset_password;
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }



    public User(Long cin, String nom, String prenom, String email, String password, String ville, Long num_tel, String passwordToken, Date date_naiss, String reset_password, List<Investment> investments, List<Credit> credits) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.ville = ville;
        this.num_tel = num_tel;
        this.passwordToken = passwordToken;
        this.date_naiss = date_naiss;
        this.reset_password = reset_password;
        this.investments = investments;
        this.credits = credits;
    }


    public String getPasswordToken() {
        return passwordToken;
    }

    public void setPasswordToken(String passwordToken) {
        this.passwordToken = passwordToken;
    }

    public String getReset_password() {
        return reset_password;
    }

    public void setReset_password(String reset_password) {
        this.reset_password = reset_password;
    }



    @OneToMany(mappedBy = "user")
    private List<Investment> investments;

    @OneToMany(mappedBy = "user")
    private List<Credit> credits;

    public Long getCin() {
        return cin;
    }

    public void setCin(Long cin) {
        this.cin = cin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public Long getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(Long num_tel) {
        this.num_tel = num_tel;
    }

    public Date getDate_naiss() {
        return date_naiss;
    }

    public void setDate_naiss(Date date_naiss) {
        this.date_naiss = date_naiss;
    }

    @Override
    public String toString() {
        return "User{" +
                "cin=" + cin +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", ville='" + ville + '\'' +
                ", num_tel=" + num_tel +
                ", date_naiss=" + date_naiss +
                ", investments=" + investments +
                ", credits=" + credits +
                '}';
    }



    public User() {
    }
    // Getters and setters
}
