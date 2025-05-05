package com.humanbooster.interfaces;

import com.humanbooster.model.Utilisateur;
import java.util.List;

public interface AuthentificationService {
    /**
     * Crée un nouvel utilisateur et génère son code de validation
     * @param email Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return Nouvel utilisateur créé
     */
    Utilisateur inscrire(String email, String motDePasse);
    
    /**
     * Valide un compte utilisateur avec le code fourni
     * @param email Email de l'utilisateur
     * @param code Code de validation
     * @return true si la validation a réussi, false sinon
     */
    boolean validerCompte(String email, String code);
    
    /**
     * Connecte un utilisateur
     * @param email Email de l'utilisateur
     * @param motDePasse Mot de passe de l'utilisateur
     * @return Utilisateur connecté ou null si échec
     */
    Utilisateur connecter(String email, String motDePasse);
    
    /**
     * Obtient tous les utilisateurs du système
     * @return Liste des utilisateurs
     */
    List<Utilisateur> getUtilisateurs();
    
    /**
     * Recherche un utilisateur par son email
     * @param email Email de l'utilisateur
     * @return Utilisateur trouvé ou null
     */
    Utilisateur trouverParEmail(String email);
}



