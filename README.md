# Electricity Business


### Partie **Core** (terminée) :
1. **Gestion des comptes** :
   - Inscription avec génération d'un code de validation.
   - Validation du compte via le code de validation.
   - Connexion et déconnexion des utilisateurs.

2. **Gestion des bornes et lieux** :
   - Ajout et modification de lieux de recharge.
   - Ajout et modification de bornes de recharge.
   - Suppression d'une borne si aucune réservation future n'existe.

3. **Gestion des réservations** :
   - Recherche de bornes disponibles pour un créneau horaire donné.
   - Création de réservations avec un statut initial `EN_ATTENTE`.
   - Annulation des réservations.
   - Acceptation des réservations.

4. **Interface utilisateur (IHM console)** :
   - Menu principal clair et interactif.
   - Validation des entrées utilisateur.

---

### Bonus (implémentés) :
1. **Documents** :
   - Génération d'un reçu texte (`.txt`) lors de l'acceptation d'une réservation.

2. **Administration avancée** :
   - Gestion des lieux et bornes via un menu dédié.

---

## Structure du projet

### Packages
- **`com.humanbooster.model`** : Contient les classes représentant les entités principales (`Utilisateur`, `LieuRecharge`, `BorneRecharge`, `Reservation`, etc.).
- **`com.humanbooster.interfaces`** : Contient les interfaces des services (`AuthentificationService`, `BorneService`, `ReservationService`, `DocumentService`).
- **`com.humanbooster.services`** : Contient les implémentations des services.
- **`com.humanbooster.ui`** : Contient la classe principale `Main` pour l'interface utilisateur.
---

## Auteur
- **Selim Ounoughi**