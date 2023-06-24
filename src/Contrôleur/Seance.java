/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Classe gérant les séances
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Seance {

    private Calendar date;
    private int salle;
    private Film film;
    private final double prix=8.0;

    public Seance() {
        film = new Film();
salle=0;
    }

    public Seance(Film f, int s, Calendar d) {
        film = new Film();
        salle = s;
        date = d;
    }

public Seance(Calendar d) {
date = d;
}

public double getPrix() {
return prix;
}

/**
* Renvoie la date sous forme d'un string
* @return date en string
*/
public String getDate() {
return date.get(date.DATE) + " " + Utilitaires.getMonth(date.get(date.MONTH)) + " " + date.get(date.YEAR);
}

/**
*Renvoie l'heure de la séance sous forme d'un string
* @return heure en string
*/
public String getTime() {
DecimalFormat df = new DecimalFormat("00");
return date.get(date.HOUR_OF_DAY) + ":" + df.format(date.get(date.MINUTE));
}

public int getSalle() {
return salle;
}

public void setFilm(Film movie) 
{
film=movie;
}

public Film getFilm() {
return film;
}

}
