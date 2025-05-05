package com.humanbooster.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LieuRecharge {
    private String id;
    private String nom;
    private String adresse;
    private List<BorneRecharge> bornes;

    public LieuRecharge(String nom, String adresse) {
        this.id = UUID.randomUUID().toString();
        this.nom = nom;
        this.adresse = adresse;
        this.bornes = new ArrayList<>();
    }

    public void ajouterBorne(BorneRecharge borne) {
        bornes.add(borne);
    }

    public boolean supprimerBorne(String borneId) {
        return bornes.removeIf(b -> b.getId().equals(borneId));
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<BorneRecharge> getBornes() {
        return bornes;
    }

    @Override
    public String toString() {
        return "LieuRecharge{" +
                "id='" + id + '\'' +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", nombreBornes=" + bornes.size() +
                '}';
    }
}



