package com.humanbooster.model;

import java.util.UUID;

public class Utilisateur {
    private String id;
    private String email;
    private String motDePasse;
    private String codeValidation;
    private boolean estValide;

    public Utilisateur(String email, String motDePasse) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.motDePasse = motDePasse;
        this.codeValidation = generateValidationCode();
        this.estValide = false;
    }

    private String generateValidationCode() {
        return String.format("%06d", (int) (Math.random() * 1000000));
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getCodeValidation() {
        return codeValidation;
    }

    public void setCodeValidation(String codeValidation) {
        this.codeValidation = codeValidation;
    }

    public boolean isEstValide() {
        return estValide;
    }

    public void setEstValide(boolean estValide) {
        this.estValide = estValide;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", estValide=" + estValide +
                '}';
    }
}

