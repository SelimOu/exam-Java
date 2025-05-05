package com.humanbooster.interfaces;


import com.humanbooster.model.BorneRecharge;
import com.humanbooster.model.Reservation;
import com.humanbooster.model.StatutReservation;
import com.humanbooster.model.Utilisateur;
import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    /**
     * Crée une nouvelle réservation
     * @param utilisateur Utilisateur qui réserve
     * @param borne Borne à réserver
     * @param debut Date et heure de début
     * @param fin Date et heure de fin
     * @return Réservation créée
     */
    Reservation creerReservation(Utilisateur utilisateur, BorneRecharge borne, LocalDateTime debut, LocalDateTime fin);
    
    /**
     * Change le statut d'une réservation (accepter/refuser)
     * @param reservationId ID de la réservation
     * @param statut Nouveau statut
     * @return true si changé, false sinon
     */
    boolean changerStatutReservation(String reservationId, StatutReservation statut);
    
    /**
     * Obtient toutes les réservations
     * @return Liste des réservations
     */
    List<Reservation> getReservations();
    
    /**
     * Trouve les réservations d'un utilisateur
     * @param utilisateurId ID de l'utilisateur
     * @return Liste des réservations
     */
    List<Reservation> trouverReservationsUtilisateur(String utilisateurId);
    
    /**
     * Trouve les réservations pour une borne
     * @param borneId ID de la borne
     * @return Liste des réservations
     */
    List<Reservation> trouverReservationsBorne(String borneId);
    
    /**
     * Vérifie si une borne a des réservations futures
     * @param borneId ID de la borne
     * @return true si des réservations existent, false sinon
     */
    boolean ReservationsFutures(String borneId);
    
    /**
     * Trouve une réservation par son ID
     * @param reservationId ID de la réservation
     * @return Réservation trouvée ou null
     */
    Reservation trouverReservationParId(String reservationId);


    
}


