package com.humanbooster.services;

import com.humanbooster.interfaces.BorneService;
import com.humanbooster.interfaces.ReservationService;
import com.humanbooster.model.BorneRecharge;
import com.humanbooster.model.EtatBorne;
import com.humanbooster.model.LieuRecharge;
import com.humanbooster.model.Reservation;
import com.humanbooster.model.StatutReservation;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BorneServiceImpl implements BorneService {
    private List<LieuRecharge> lieux;
    private ReservationService reservationService;

    public BorneServiceImpl(ReservationService reservationService) {
        this.lieux = new ArrayList<>();
        this.reservationService = reservationService;
    }

    @Override
    public LieuRecharge ajouterLieu(String nom, String adresse) {
        LieuRecharge nouveauLieu = new LieuRecharge(nom, adresse);
        lieux.add(nouveauLieu);
        return nouveauLieu;
    }

    @Override
    public boolean modifierLieu(String id, String nom, String adresse) {
        LieuRecharge lieu = trouverLieuParId(id);
        if (lieu != null) {
            if (nom != null) lieu.setNom(nom);
            if (adresse != null) lieu.setAdresse(adresse);
            return true;
        }
        return false;
    }

    @Override
    public BorneRecharge ajouterBorne(String lieuId, double tarifHoraire) {
        LieuRecharge lieu = trouverLieuParId(lieuId);
        if (lieu != null) {
            BorneRecharge nouvelleBorne = new BorneRecharge(tarifHoraire);
            lieu.ajouterBorne(nouvelleBorne);
            return nouvelleBorne;
        }
        return null;
    }

    @Override
    public boolean modifierBorne(String borneId, EtatBorne etat, double tarifHoraire) {
        BorneRecharge borne = trouverBorneParId(borneId);
        if (borne != null) {
            if (etat != null) borne.setEtat(etat);
            if (tarifHoraire >= 0) borne.setTarifHoraire(tarifHoraire);
            return true;
        }
        return false;
    }

    @Override
    public boolean supprimerBorne(String borneId) {
        if (reservationService.ReservationsFutures(borneId)) {
            return false;
        }
        
        LieuRecharge lieu = trouverLieuParBorneId(borneId);
        if (lieu != null) {
            return lieu.supprimerBorne(borneId);
        }
        return false;
    }

    @Override
    public List<BorneRecharge> trouverBornesDisponibles(LocalDateTime debut, LocalDateTime fin) {
        List<BorneRecharge> bornesDisponibles = new ArrayList<>();
        
        for (LieuRecharge lieu : lieux) {
            for (BorneRecharge borne : lieu.getBornes()) {
                if (borne.getEtat() == EtatBorne.DISPONIBLE) {
                    boolean estDisponible = true;
                    List<Reservation> reservationsBorne = reservationService.trouverReservationsBorne(borne.getId());
                    
                    for (Reservation res : reservationsBorne) {
                        if (res.getStatut() == StatutReservation.ACCEPTEE || 
                            res.getStatut() == StatutReservation.EN_ATTENTE) {
                            if (!(fin.isBefore(res.getDateDebut()) || debut.isAfter(res.getDateFin()))) {
                                estDisponible = false;
                                break;
                            }
                        }
                    }
                    
                    if (estDisponible) {
                        bornesDisponibles.add(borne);
                    }
                }
            }
        }
        
        return bornesDisponibles;
    }

    @Override
    public List<LieuRecharge> getLieux() {
        return new ArrayList<>(lieux);
    }

    @Override
    public LieuRecharge trouverLieuParId(String lieuId) {
        return lieux.stream()
                .filter(l -> l.getId().equals(lieuId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public BorneRecharge trouverBorneParId(String borneId) {
        for (LieuRecharge lieu : lieux) {
            for (BorneRecharge borne : lieu.getBornes()) {
                if (borne.getId().equals(borneId)) {
                    return borne;
                }
            }
        }
        return null;
    }

    @Override
    public LieuRecharge trouverLieuParBorneId(String borneId) {
        return lieux.stream()
                .filter(lieu -> lieu.getBornes().stream()
                        .anyMatch(borne -> borne.getId().equals(borneId)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void ajouterLieu(LieuRecharge lieu2) {
        throw new UnsupportedOperationException("Unimplemented method 'ajouterLieu'");
    }
}

