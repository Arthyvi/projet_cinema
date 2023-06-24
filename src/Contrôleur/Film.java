/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import Modele.Connexion;
import java.awt.Image;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.JLabel;

/**
 * Classe désignant un film.
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Film {

    private int note;
    private String nom, description, pays;
    private Liste<String> realisateur;
    private Liste<String> acteurs;
    private Liste<Seance> listeDesSeances;
    private int duree;
    private Calendar date;
    private ImageIcon affiche;
    private String genre;
    private int salle;
    private String ID;

    public Film() {
        acteurs = new Liste<>();
        listeDesSeances = new Liste<>();
    }

    public Film(String theNom, String TheGenre, String TheDuree, String TheNote, String desc, String ThePays, String real, String a, Date d, int sal) {
        nom = theNom;
        date = Calendar.getInstance();
        date.setTime(d);
        description = desc;
        pays = ThePays;
        realisateur = new Liste<String>();
        realisateur.Ajouter(real);

            // Rajoute la liste d'acteurs
        acteurs = new Liste<>();
        String[] listeActeurName = a.split(",");

        for (String acteurName : listeActeurName) {
            acteurs.Ajouter(acteurName);
        }

        note = (int) Integer.parseInt(TheNote);
        duree = (int) Integer.parseInt(TheDuree);
        listeDesSeances = new Liste<>();
        genre = TheGenre;
        salle = sal;

        affiche = new ImageIcon("C:\\Users\\kevin\\Documents\\GitHub\\Cinema\\PROJET CINEMA\\src\\images\\fast&furious.jpg");

    }
    
    public Film(ArrayList<String> row,Connexion maconnexion) {
        nom = row.get(0);
        genre = row.get(1);
        duree = (int) Integer.parseInt(row.get(2));
        note = (int) Integer.parseInt(row.get(3));
        description = row.get(4);
        pays = row.get(5);
        date = Calendar.getInstance();
        date.setTime(new Date(row.get(6)));
        salle = (int) Integer.parseInt(row.get(7));
        ID = row.get(8);
        
       // On trouve les réalisateur
       realisateur = new Liste<String>();
       try {
            ArrayList< ArrayList<String>> BufferSQL = maconnexion.executeRequete("SELECT prenom,nom FROM relationfilmpersonne p1 INNER JOIN personne p2 ON p1.IDpersonne = p2.IDpersonne WHERE IDfilm = '"+row.get(8)+"' AND MetierLien = 'realisateur';");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row2 : BufferSQL) 
            {
                realisateur.Ajouter(row2.get(0) +" "+ row2.get(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
       
       // On trouve les acteurs
       acteurs = new Liste<String>();
       try {
            ArrayList< ArrayList<String>> BufferSQL2 = maconnexion.executeRequete("SELECT prenom,nom FROM relationfilmpersonne p1 INNER JOIN personne p2 ON p1.IDpersonne = p2.IDpersonne WHERE IDfilm = '"+row.get(8)+"' AND MetierLien = 'acteur';");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row3 : BufferSQL2) 
            {
                acteurs.Ajouter(row3.get(0) +" "+ row3.get(1));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
       
        // Connexion de la bonne affiche du film
        affiche = new ImageIcon(getClass().getResource("/images/Films/"+nom+".jpg"));

        listeDesSeances = new Liste<>();
    }
    
    
    public String toString() {
        return nom + " (" + date.YEAR + "), " + realisateur.toString();
    }

/// Méthodes
/**
 * remplit un panel avec les informations du film
 * @param labels remplis ensuite avec les infos.
 */
    public void remplirPannelFilm(JLabel titre, JLabel image, JLabel dateGenreDuree, JLabel real, JLabel acte, JLabel Note, JLabel country, JLabel advertise) {
        
        // Variables
        String Buff = "";
        int compt = 0;
        int taille = 0;
        
        // Remplissage du pannel
        titre.setText(nom);
        image.setIcon(affiche);
        SimpleDateFormat theDf = new SimpleDateFormat("dd MMMMM yyyy", new Locale("fr", "FR"));
        dateGenreDuree.setText(theDf.format(date.getTime()) + " / " + duree + "min / " + genre);
        
        // Pour la liste de Realisateur
        taille = realisateur.Taille();
        for(int I=0; I<taille; I++)
        {
            Buff += realisateur.Get(I);
            
            if(compt != (realisateur.Taille()-1))
            {
                Buff += ", ";
            }
                
           compt++;     
        }
        
        real.setText("De : " + Buff);
        
        // Pour la liste d'Acteur
        // Initialisation de nouveau
        Buff = "";
        compt = 0;
        taille = acteurs.Taille();
        for(int I=0; I<taille; I++)
        {
            Buff += acteurs.Get(I);
            
            if(compt != (acteurs.Taille()-1))
            {
                Buff += ", ";
            }
                
           compt++;     
        }
        
        acte.setText("Avec : " + Buff);
        
        
        Note.setText("Note : " + note + "/5");
        country.setText("Pays : " + pays);
        
        
        advertise.show();
    }

///getter
    public String getNom() {
        return nom;
    }

    public Calendar getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getPays() {
        return pays;
    }

    public Liste<String> getRealisateur() {
        return realisateur;
    }

    public Liste<String> getActeurs() {
        return acteurs;
    }

    public int getNote() {
        return note;
    }

    public int getDuree() {
        return duree;
    }

    public Liste<Seance> getSeances() {
        return listeDesSeances;
    }

    public ImageIcon getAffiche() {
        return affiche;
    }

    public String getGenre() {
        return genre;
    }
    
    public int getSalle() {
        return salle+1;
    }
    
    public String getID()
    {
        return ID;
    }

///setters
    public void setNom(String n) {
        nom = n;
    }

    public void setDate(Calendar d) {
        date = d;
    }

    public void setDescription(String d) {
        description = d;
    }

    public void setPays(String p) {
        pays = p;
    }

    public void setRealisateur(Liste<String> r) {
        realisateur = r;
    }

    public void setActeurs(String a, String b, String c) {
        acteurs.Ajouter(a);
        acteurs.Ajouter(b);
        acteurs.Ajouter(c);
    }

    public void setNote(int n) {
        note = n;
    }

    public void setDuree(int d) {
        duree = d;
    }

    public void setSeances(Liste<Seance> s) {
        listeDesSeances = s;
    }

    public void setAffiche(String lien) {
        affiche = new ImageIcon(lien);
    }

    public void setAffiche(ImageIcon image) {
        affiche = image;
    }

    public void setGenre(String gen) {
        genre = gen;
    }

}
