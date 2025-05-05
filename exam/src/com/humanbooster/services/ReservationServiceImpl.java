package com.humanbooster.services;

import com.humanbooster.interfaces.ReservationService;
import com.humanbooster.model.BorneRecharge;
import com.humanbooster.model.Reservation;
import com.humanbooster.model.StatutReservation;
import com.humanbooster.model.Utilisateur;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReservationServiceImpl implements ReservationService {
    private List<Reservation> reservations;

    public ReservationServiceImpl() {
        this.reservations = new ArrayList<>();
    }

    @Override
    public Reservation creerReservation(Utilisateur utilisateur, BorneRecharge borne, LocalDateTime debut, LocalDateTime fin) {
        Reservation nouvelleReservation = new Reservation(utilisateur, borne, debut, fin);
        reservations.add(nouvelleReservation);
        return nouvelleReservation;
    }

    @Override
    public boolean changerStatutReservation(String reservationId, StatutReservation nouveauStatut) {
        for (Reservation reservation : reservations) {
            if (reservation.getId().equals(reservationId)) {
                reservation.setStatut(nouveauStatut);
                return true; 
        }
    }
    return false;
}

    @Override
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations);
    }

    @Override
    public List<Reservation> trouverReservationsUtilisateur(String utilisateurId) {
        return reservations.stream()
                .filter(r -> r.getUtilisateur().getId().equals(utilisateurId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> trouverReservationsBorne(String borneId) {
        return reservations.stream()
                .filter(r -> r.getBorne().getId().equals(borneId))
                .collect(Collectors.toList());
    }

    @Override
    public boolean ReservationsFutures(String borneId) {
        LocalDateTime maintenant = LocalDateTime.now();
        return reservations.stream()
                .anyMatch(r -> r.getBorne().getId().equals(borneId) &&
                        (r.getStatut() == StatutReservation.ACCEPTEE || 
                        r.getStatut() == StatutReservation.EN_ATTENTE) &&
                        r.getDateFin().isAfter(maintenant));
    }

    @Override
    public Reservation trouverReservationParId(String reservationId) {
        return reservations.stream()
                .filter(r -> r.getId().equals(reservationId))
                .findFirst()
                .orElse(null);
    }
}
