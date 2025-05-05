package com.humanbooster.interfaces;

import com.humanbooster.model.Reservation;
import java.io.IOException;

public interface DocumentService {
    /**
     * Génère un reçu pour une réservation acceptée
     * @param reservation Réservation acceptée
     * @return Chemin du fichier généré
     * @throws IOException En cas d'erreur d'écriture
     */
    String genererRecu(Reservation reservation) throws IOException;
}

