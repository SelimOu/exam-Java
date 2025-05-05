package com.humanbooster.interfaces;

import com.humanbooster.model.BorneRecharge;
import com.humanbooster.model.EtatBorne;
import com.humanbooster.model.LieuRecharge;
import java.time.LocalDateTime;
import java.util.List;

public interface BorneService {
    /**
     * Ajoute un nouveau lieu de recharge
     * @param nom Nom du lieu
     * @param adresse Adresse du lieu
     * @return Lieu créé
     */
    LieuRecharge ajouterLieu(String nom, String adresse);
    
    /**
     * Modifie un lieu existant
     * @param id ID du lieu
     * @param nom Nouveau nom (ou null si inchangé)
     * @param adresse Nouvelle adresse (ou null si inchangée)
     * @return true si modifié, false sinon
     */
    boolean modifierLieu(String id, String nom, String adresse);
    
    /**
     * Ajoute une borne à un lieu
     * @param lieuId ID du lieu
     * @param tarifHoraire Tarif horaire de la borne
     * @return Borne créée ou null si lieu non trouvé
     */
    BorneRecharge ajouterBorne(String lieuId, double tarifHoraire);
    
    /**
     * Modifie une borne existante
     * @param borneId ID de la borne
     * @param etat Nouvel état (ou null si inchangé)
     * @param tarifHoraire Nouveau tarif (ou -1 si inchangé)
     * @return true si modifié, false sinon
     */
    boolean modifierBorne(String borneId, EtatBorne etat, double tarifHoraire);
    
    /**
     * Supprime une borne (si elle n'a pas de réservations futures)
     * @param borneId ID de la borne
     * @return true si supprimé, false sinon
     */
    boolean supprimerBorne(String borneId);
    
    /**
     * Trouve les bornes disponibles pour un créneau horaire
     * @param debut Date et heure de début
     * @param fin Date et heure de fin
     * @return Liste des bornes disponibles
     */
    List<BorneRecharge> trouverBornesDisponibles(LocalDateTime debut, LocalDateTime fin);
    
    /**
     * Obtient tous les lieux
     * @return Liste des lieux
     */
    List<LieuRecharge> getLieux();
    
    /**
     * Trouve un lieu par son ID
     * @param lieuId ID du lieu
     * @return Lieu trouvé ou null
     */
    LieuRecharge trouverLieuParId(String lieuId);
    
    /**
     * Trouve une borne par son ID
     * @param borneId ID de la borne
     * @return Borne trouvée ou null
     */
    BorneRecharge trouverBorneParId(String borneId);
    
    /**
     * Trouve le lieu qui contient une borne spécifique
     * @param borneId ID de la borne
     * @return Lieu contenant la borne ou null
     */
    LieuRecharge trouverLieuParBorneId(String borneId);

    void ajouterLieu(LieuRecharge lieu2);
}
