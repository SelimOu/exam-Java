package com.humanbooster.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Reservation {
    private String id;
    private Utilisateur utilisateur;
    private BorneRecharge borne;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private StatutReservation statut;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Reservation(Utilisateur utilisateur, BorneRecharge borne, LocalDateTime dateDebut, LocalDateTime dateFin) {
        this.id = UUID.randomUUID().toString();
        this.utilisateur = utilisateur;
        this.borne = borne;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.statut = StatutReservation.EN_ATTENTE;
    }

    public double calculerCout() {
        double heures = Duration.between(dateDebut, dateFin).toMinutes() / 60.0;
        return heures * borne.getTarifHoraire();
    }

    public String getId() {
        return id;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public BorneRecharge getBorne() {
        return borne;
    }

    public LocalDateTime getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDateTime getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDateTime dateFin) {
        this.dateFin = dateFin;
    }

    public StatutReservation getStatut() {
        return statut;
    }

    public void setStatut(StatutReservation statut) {
        this.statut = statut;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id='" + id + '\'' +
                ", utilisateur=" + utilisateur.getEmail() +
                ", borne=" + borne.getId() +
                ", début=" + dateDebut.format(formatter) +
                ", fin=" + dateFin.format(formatter) +
                ", statut=" + statut +
                ", coût=" + String.format("%.2f", calculerCout()) + "€" +
                '}';
    }
}



