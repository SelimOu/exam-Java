package com.humanbooster.services;

import com.humanbooster.interfaces.AuthentificationService;
import com.humanbooster.model.Utilisateur;
import java.util.ArrayList;
import java.util.List;

public class AuthentificationServiceImpl implements AuthentificationService {
    private List<Utilisateur> utilisateurs;
    private Utilisateur utilisateurConnecte;

    public AuthentificationServiceImpl() {
        this.utilisateurs = new ArrayList<>();
    }

    @Override
    public Utilisateur inscrire(String email, String motDePasse) {
        if (trouverParEmail(email) != null) {
            return null;
        }
        
        Utilisateur nouvelUtilisateur = new Utilisateur(email, motDePasse);
        utilisateurs.add(nouvelUtilisateur);
        return nouvelUtilisateur;
    }

    @Override
    public boolean validerCompte(String email, String code) {
        Utilisateur utilisateur = trouverParEmail(email);
        if (utilisateur != null && !utilisateur.isEstValide() && 
            utilisateur.getCodeValidation().equals(code)) {
            utilisateur.setEstValide(true);
            return true;
        }
        return false;
    }

    @Override
    public Utilisateur connecter(String email, String motDePasse) {
        Utilisateur utilisateur = trouverParEmail(email);
        if (utilisateur != null && utilisateur.isEstValide() && 
            utilisateur.getMotDePasse().equals(motDePasse)) {
            utilisateurConnecte = utilisateur;
            return utilisateur;
        }
        return null;
    }

    @Override
    public List<Utilisateur> getUtilisateurs() {
        return new ArrayList<>(utilisateurs);
    }

    @Override
    public Utilisateur trouverParEmail(String email) {
        return utilisateurs.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElse(null);
    }
    
    public Utilisateur getUtilisateurConnecte() {
        return utilisateurConnecte;
    }
    
    public void deconnecter() {
        utilisateurConnecte = null;
    }
}

