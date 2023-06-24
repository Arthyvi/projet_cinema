/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import Contrôleur.Film;

/**
 * Classe identifiant les clients avec un compte
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Client extends Utilisateur {

    private Liste listeFilms;
    private Liste listeSeances;
    private TypeDeClient type;

    public Client() {
        super();
        listeFilms = new Liste<>();
        listeSeances = new Liste<>();
        type = TypeDeClient.Régulier;
    }
    
    public Client(String prenom,String nom,String email,String password,String ListFilms, String ListeSeance, String type) {
        super(prenom, nom, email, password);
        this.listeFilms = new Liste<>();
        this.listeSeances = new Liste<>();
        this.type = TypeDeClient.valueOf(type);
    }

    public Client(Client c) {
        super(c.prenom, c.nom, c.email,c.password);
        this.listeFilms = c.listeFilms;
        this.listeSeances = c.listeSeances;
        this.type = c.type;
    }
    

    public void acheter(Film mov) {
        listeFilms.Ajouter(mov);
    }

    public void annuler(Film mov) {
        listeFilms.Supprimer(mov);
    }

/**
 * retourne le type du client (junios, regulier, senior)
 */
    public TypeDeClient getType() {
        return type;
    }

}
