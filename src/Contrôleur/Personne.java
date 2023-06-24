/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import Modele.Connexion;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Classe contenant une personne, comme un acteur ou un réalisateur
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Personne {

    private String prenom;
    private String nom;
    private MetierDuCinema metier;
    private Liste<String> filmographie;
    private Calendar dateDeNaissance;
    private ImageIcon photo;
private String id;

    public Personne() {
        filmographie = new Liste<>();
    }

    public Personne(String p, String n, Liste f) 
    {
        prenom = p;
        nom = n;
        //metier = m;
        
        
        filmographie = f;
    }
    
    
    public Personne(ArrayList<String> row,Connexion maconnexion) {
        
        String buff ="";
        
        prenom = row.get(0);
        nom = row.get(1);
        dateDeNaissance = Calendar.getInstance();
        dateDeNaissance.setTime(new Date(row.get(3)));
        
       // On trouve la filmographie
       filmographie = new Liste<String>();
       try {
            ArrayList< ArrayList<String>> BufferSQL = maconnexion.executeRequete("SELECT f.titre,rfp.MetierLien  FROM films f INNER JOIN relationfilmpersonne rfp ON f.IDfilm = rfp.IDfilm  WHERE IDpersonne = '"+ row.get(2) +"';");

            for (ArrayList<String> row2 : BufferSQL) 
            {
                filmographie.Ajouter(row2.get(0) +" ("+ row2.get(1) + ")");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        
        // Recuperation de la phot de la personalité
        buff = prenom.trim()+nom.trim();
        buff = buff.replace(" ", "");
       // System.out.println(buff);
        photo = new ImageIcon(getClass().getResource("/images/Personnes/"+buff+".jpg"));

        id=row.get(2);
        
       
    }
    
    
    /// Méthodes
/**
* Méthode permettant de remplir dans un panel les informations d'une personne
* @param panels qui seront remplis par les infos
*/
    public void remplirPannelFilm(JLabel titre, JLabel image, JLabel dateGenreDuree, JLabel real, JLabel acte, JLabel Note, JLabel country,JLabel advertise) {
        
        // Variables
        String Buff = "";
        int compt = 0;
        int taille = 0;
        
        // Remplissage du pannel
        titre.setText(prenom.trim() +" "+nom.trim());
        image.setIcon(photo);
        SimpleDateFormat theDf = new SimpleDateFormat("dd MMMMM yyyy", new Locale("fr", "FR"));
        dateGenreDuree.setText("Naissance : " + theDf.format(dateDeNaissance.getTime()));
        
        // Pour la filmographie
        taille = filmographie.Taille();
        for(int I=0; I<taille; I++)
        {
            Buff += filmographie.Get(I);
            
            if(compt != (filmographie.Taille()-1))
            {
                Buff +=",  ";
            }
                
           compt++;     
        }

        
        real.setText("Filmographie  :  " + Buff);
        
        acte.setText("");
        Note.setText("");
        country.setText("");
        
        advertise.hide();
    }
    
    
    
/**
* Ajoute un film à la filmographie de la personne
*/
    public void ajouterFilm(String mov) {
        filmographie.Ajouter(mov);
    }

    
    public String getMetier() {
        if(metier==MetierDuCinema.acteur) {
            return "Acteur";
        } else {
            return "Réalisateur";
        }
    }

/**
* renvoie un string avec la date de naissance de la personne
*/
    public String getDate() {
        return dateDeNaissance.get(dateDeNaissance.DATE) + " " + Utilitaires.getMonth(dateDeNaissance.get(dateDeNaissance.MONTH)) + " " + dateDeNaissance.get(dateDeNaissance.YEAR);
    }

    public ImageIcon getPhoto() {
        return photo;
    }

public String getPrenom() {
return prenom;
}

public String getNom() {
return nom;
}

public Calendar getDateCalendar() {
return dateDeNaissance;
}

public String getID() {
return id;
}

    @Override
    public String toString() {
        return prenom + " " + nom;
    }
}
