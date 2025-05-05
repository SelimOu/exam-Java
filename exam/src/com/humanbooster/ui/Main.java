package com.humanbooster.ui;

import com.humanbooster.interfaces.AuthentificationService;
import com.humanbooster.interfaces.BorneService;
import com.humanbooster.model.LieuRecharge;
import com.humanbooster.model.StatutReservation;
import com.humanbooster.services.AuthentificationServiceImpl;
import com.humanbooster.services.BorneServiceImpl;
import com.humanbooster.services.DocumentServiceImpl;
import com.humanbooster.services.ReservationServiceImpl;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static AuthentificationService authentificationService = new AuthentificationServiceImpl();
    private static BorneService borneService = new BorneServiceImpl(new ReservationServiceImpl());
    private static ReservationServiceImpl reservationService = new ReservationServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        displayMenu();
    }

    private static void displayMenu() {
        while (true) {
            System.out.println("=== Electricity Business ===");
            System.out.println("1. S'inscrire");
            System.out.println("2. Valider l'inscription");
            System.out.println("3. Se connecter");
            System.out.println("4. Rechercher & réserver une borne");
            System.out.println("5. Gérer mes réservations");
            System.out.println("6. Administration (lieux / bornes)");
            System.out.println("0. Quitter");

            System.out.print("Choisissez une option : ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    inscrire();
                    break;
                case 2:
                    validerInscription();
                    break;
                case 3:
                    seConnecter();
                    break;
                case 4:
                    rechercherEtReserverBorne();
                    break;
                case 5:
                    gererMesReservations();
                    break;
                case 6:
                    administration();
                    break;
                case 0:
                    System.out.println("Au revoir !");
                    return;
                default:
                    System.out.println("Option invalide. Veuillez réessayer.");
            }
        }
    }

    private static void inscrire() {
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();

        var utilisateur = authentificationService.inscrire(email, motDePasse);
        if (utilisateur != null) {
            System.out.println("Inscription réussie. Un code de validation a été envoyé à votre adresse e-mail simulée.");
            System.out.println("Code de validation : " + utilisateur.getCodeValidation());
        } else {
            System.out.println("Erreur : un compte avec cet email existe déjà.");
        }
    }

    private static void validerInscription() {
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez le code de validation : ");
        String codeValidation = scanner.nextLine();

        boolean isValid = authentificationService.validerCompte(email, codeValidation);
        if (isValid) {
            System.out.println("Validation réussie. Votre compte est maintenant actif.");
        } else {
            System.out.println("Validation échouée. Veuillez vérifier votre email et votre code de validation.");
        }
    }

    private static void seConnecter() {
        System.out.print("Entrez votre email : ");
        String email = scanner.nextLine();
        System.out.print("Entrez votre mot de passe : ");
        String motDePasse = scanner.nextLine();

        boolean isLoggedIn = authentificationService.connecter(email, motDePasse) != null;
        if (isLoggedIn) {
            System.out.println("Connexion réussie.");
        } else {
            System.out.println("Connexion échouée. Veuillez vérifier votre email et votre mot de passe.");
        }
    }

    private static void rechercherEtReserverBorne() {
        System.out.println("=== Recherche de bornes disponibles ===");
        System.out.print("Entrez la date de début (format : yyyy-MM-dd HH:mm) : ");
        String dateDebutStr = scanner.nextLine();
        System.out.print("Entrez la date de fin (format : yyyy-MM-dd HH:mm) : ");
        String dateFinStr = scanner.nextLine();
    
        try {
            var dateDebut = java.time.LocalDateTime.parse(dateDebutStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            var dateFin = java.time.LocalDateTime.parse(dateFinStr, java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    
            var bornesDisponibles = borneService.trouverBornesDisponibles(dateDebut, dateFin);
            if (bornesDisponibles.isEmpty()) {
                System.out.println("Aucune borne disponible pour ce créneau.");
                return;
            }
    
            System.out.println("Bornes disponibles :");
            for (int i = 0; i < bornesDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + bornesDisponibles.get(i));
            }
    
            System.out.print("Choisissez une borne (numéro) pour réserver : ");
            int choix = scanner.nextInt();
            scanner.nextLine();
    
            if (choix < 1 || choix > bornesDisponibles.size()) {
                System.out.println("Choix invalide.");
                return;
            }
    
            var borneChoisie = bornesDisponibles.get(choix - 1);
            System.out.print("Entrez votre email : ");
            String email = scanner.nextLine();
            var utilisateur = authentificationService.trouverParEmail(email);
    
            if (utilisateur == null || !utilisateur.isEstValide()) {
                System.out.println("Utilisateur non trouvé ou non validé.");
                return;
            }
    
            var reservation = reservationService.creerReservation(utilisateur, borneChoisie, dateDebut, dateFin);
            if (reservation != null) {
                System.out.println("Réservation créée avec succès : " + reservation);
            } else {
                System.out.println("Erreur lors de la création de la réservation.");
            }
        } catch (Exception e) {
            System.out.println("Erreur : format de date invalide ou autre problème.");
        }
    }    

private static void gererMesReservations() {
    System.out.println("=== Gestion de mes réservations ===");
    System.out.print("Entrez votre email : ");
    String email = scanner.nextLine();

    var utilisateur = authentificationService.trouverParEmail(email);
    if (utilisateur == null) {
        System.out.println("Utilisateur non trouvé.");
        return;
    }

    var reservations = reservationService.trouverReservationsUtilisateur(utilisateur.getId());
    if (reservations.isEmpty()) {
        System.out.println("Aucune réservation trouvée pour cet utilisateur.");
        return;
    }

    System.out.println("Vos réservations :");
    for (int i = 0; i < reservations.size(); i++) {
        System.out.println((i + 1) + ". " + reservations.get(i));
    }

    System.out.println("1. Annuler une réservation");
    System.out.println("2. Accepter une réservation");
    System.out.println("0. Retour");
    System.out.print("Choisissez une option : ");
    int choix = scanner.nextInt();
    scanner.nextLine();

    if (choix == 2) {
        System.out.print("Entrez le numéro de la réservation à accepter : ");
        int numReservation = scanner.nextInt();
        scanner.nextLine();

        if (numReservation < 1 || numReservation > reservations.size()) {
            System.out.println("Choix invalide.");
            return;
        }

        var reservation = reservations.get(numReservation - 1);
        boolean success = reservationService.changerStatutReservation(reservation.getId(), StatutReservation.ACCEPTEE);
        if (success) {
            System.out.println("Réservation acceptée avec succès.");

            // Générer le reçu
            try {
                DocumentServiceImpl documentService = new DocumentServiceImpl();
                String cheminRecu = documentService.genererRecu(reservation);
                System.out.println("Reçu généré : " + cheminRecu);
            } catch (IOException e) {
                System.out.println("Erreur lors de la génération du reçu : " + e.getMessage());
            }
        } else {
            System.out.println("Erreur lors de l'acceptation de la réservation.");
        }
    }
}

    private static void administration() {
        System.out.println("=== Administration ===");
        System.out.println("1. Ajouter un lieu");
        System.out.println("2. Ajouter une borne à un lieu");
        System.out.println("0. Retour");

        System.out.print("Choisissez une option : ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                ajouterLieu();
                break;
            case 2:
                ajouterBorne();
                break;
            case 0:
                return;
            default:
                System.out.println("Option invalide. Veuillez réessayer.");
        }
    }

    private static void ajouterLieu() {
        System.out.print("Entrez le nom du lieu : ");
        String nom = scanner.nextLine();
        System.out.print("Entrez l'adresse du lieu : ");
        String adresse = scanner.nextLine();

        LieuRecharge lieu = borneService.ajouterLieu(nom, adresse);
        System.out.println("Lieu ajouté avec succès : " + lieu);
    }

    private static void ajouterBorne() {
        System.out.print("Entrez l'ID du lieu : ");
        String lieuId = scanner.nextLine();
        System.out.print("Entrez le tarif horaire de la borne : ");
        double tarifHoraire = scanner.nextDouble();
        scanner.nextLine();

        var borne = borneService.ajouterBorne(lieuId, tarifHoraire);
        if (borne != null) {
            System.out.println("Borne ajoutée avec succès : " + borne);
        } else {
            System.out.println("Erreur : lieu introuvable.");
        }
    }
}