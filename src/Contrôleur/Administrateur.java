/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import Contrôleur.Film;

/**
 * Classe identifiant les employés pouvant modifier le site
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Administrateur extends Utilisateur {

    public Administrateur() {
        super();
    }

    public Administrateur(Utilisateur u) {
        super(u.prenom, u.nom, u.email,u.password);
    }

/**
 * Méthode permettant d'ajouter un film à une liste de films
 * @param mov objet de type film
 * @param listemov liste d'objets de type film
 */
    public void Ajouter(Film mov, Liste listemov) {
        listemov.Ajouter(mov);
    }

}
