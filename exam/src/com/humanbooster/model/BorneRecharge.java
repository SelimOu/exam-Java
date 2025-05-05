package com.humanbooster.model;
import java.util.UUID;

public class BorneRecharge {
    private String id;
    private EtatBorne etat;
    private double tarifHoraire;

    public BorneRecharge(double tarifHoraire) {
        this.id = UUID.randomUUID().toString();
        this.etat = EtatBorne.DISPONIBLE;
        this.tarifHoraire = tarifHoraire;
    }

    public String getId() {
        return id;
    }

    public EtatBorne getEtat() {
        return etat;
    }

    public void setEtat(EtatBorne etat) {
        this.etat = etat;
    }

    public double getTarifHoraire() {
        return tarifHoraire;
    }

    public void setTarifHoraire(double tarifHoraire) {
        this.tarifHoraire = tarifHoraire;
    }

    @Override
    public String toString() {
        return "BorneRecharge{" +
                "id='" + id + '\'' +
                ", etat=" + etat +
                ", tarifHoraire=" + tarifHoraire +
                "€/h}";
    }
}

