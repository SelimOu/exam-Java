package com.humanbooster.services;

import com.humanbooster.interfaces.DocumentService;
import com.humanbooster.model.Reservation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DocumentServiceImpl implements DocumentService {
    private static final String EXPORT_DIR = "exports";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public DocumentServiceImpl() {
        File dir = new File(EXPORT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    @Override
    public String genererRecu(Reservation reservation) throws IOException {
        String nomFichier = EXPORT_DIR + "/recu_" + reservation.getId() + ".txt";
        
        try (FileWriter writer = new FileWriter(nomFichier)) {
            writer.write("==================================\n");
            writer.write("     REÇU DE RÉSERVATION\n");
            writer.write("==================================\n\n");
            writer.write("N° de réservation: " + reservation.getId() + "\n");
            writer.write("Date d'émission: " + LocalDateTime.now().format(formatter) + "\n\n");
            writer.write("CLIENT\n");
            writer.write("------\n");
            writer.write("Email: " + reservation.getUtilisateur().getEmail() + "\n\n");
            writer.write("DÉTAILS DE LA RÉSERVATION\n");
            writer.write("------------------------\n");
            writer.write("Borne ID: " + reservation.getBorne().getId() + "\n");
            writer.write("Tarif horaire: " + String.format("%.2f", reservation.getBorne().getTarifHoraire()) + " €/h\n\n");
            writer.write("Date et heure de début: " + reservation.getDateDebut().format(formatter) + "\n");
            writer.write("Date et heure de fin: " + reservation.getDateFin().format(formatter) + "\n\n");
            writer.write("PAIEMENT\n");
            writer.write("--------\n");
            writer.write("Montant total: " + String.format("%.2f", reservation.calculerCout()) + " €\n\n");
            writer.write("==================================\n");
            writer.write("Merci d'avoir choisi Electricity Business!\n");
            writer.write("==================================\n");
        }
        
        return nomFichier;
    }
}