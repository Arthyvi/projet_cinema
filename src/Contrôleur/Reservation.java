/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

/**
 * Classe gérant les réservations
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Reservation {

    private Seance seance;
    private double tarif;

    public Reservation() {
    }

    public Reservation(Seance s, Client c) {
        seance = s;
        switch (c.getType()) {
            case Régulier:
                tarif = seance.getPrix() * .8;
                break;
            case Sénior:
                tarif = seance.getPrix() * .6;
                break;
            case Enfant:
                tarif = seance.getPrix() * .4;
                break;
        }
    }
}
