/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import java.util.*;
import javax.swing.DefaultListModel;

/**
 * Classe permettant de gérer un objet de type ArrayList
 *
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Liste<T> {

    private ArrayList<T> maliste;       //liste qui sera manipulée

    /**
 *Constructeur
     */
    public Liste() {
        maliste = new ArrayList<>();
    }

    /**
     * Se positionne sur le premier élément de la liste et le renvoie. Renvoie
     * null s'il n'existe pas.
     */
    public T Premier() {
        if (!maliste.isEmpty()) {
            return maliste.get(0);
        } else {
            return null;
        }
    }

    /**
     * Se positionne sur le dernier élément de la liste et le renvoie. Renvoie
     * null s'il n'existe pas.
     */
    public T Dernier() {
        if (!maliste.isEmpty()) {
            return maliste.get(maliste.size() - 1);
        } else {
            return null;
        }
    }

    /**
     * Renvoie l'élément sur lequel on est positionné. Renvoie null s'il
     * n'existe pas.
     */
    public T Get(int position) {
        if (maliste.size() - 1 >= position) {
            return maliste.get(position);
        } else {
            return null;
        }
    }

    /**
     * Supprime l'élément sur lequel on est positionné. Renvoie null s'il
     * n'existe pas.
     */
    public void Supprimer(int position) {
        if (!maliste.isEmpty()) {
            maliste.remove(position);
        }
    }

    /**
     * Supprime l'élément entré en paramètre. Renvoie null s'il n'existe pas.
     */
    public void Supprimer(T obj) {
        if (!maliste.isEmpty()) {
            maliste.remove(obj);
        }
    }

    /**
     * Ajoute un élément à la fin.
     */
    public void Ajouter(T obj) {
        maliste.add(obj);
        Dernier();
    }

    /*
 *Renvoie la taille de la liste.
     */
    public int Taille() {
        return maliste.size();
    }

    /**
     * Renvoie un objet de type DefaultListModel
     */
    public DefaultListModel getModele() {
        DefaultListModel modele = new DefaultListModel();
        for (int i = 0; i < Taille(); i++) {
            modele.addElement(Get(i));
        }
        return modele;
    }
}
