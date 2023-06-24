/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

/**
 * Différentes fonctions générales utiles
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Utilitaires {

    /**
 * prend un tableau de carcatères en paramètres et renvoie un string 
* @param c tableau de char
* @return str un string
     */
    public static String charToString(char[] c) {
        String str = "";
        for (int i = 0; i < c.length; i++) {
            str += c[i];
        }
        return str;
    }

/**
* Convertit un nombre en mois
* @param mois int
* @return le mois en string
*/
    public static String getMonth(int mois) {
        switch (mois) {
            case 0:
                return "Janvier";
            case 1:
                return "Fevrier";
            case 2:
                return "Mars";
            case 3:
                return "Avril";
            case 4:
                return "Mai";
            case 5:
                return "Juin";
            case 6:
                return "Juuillet";
            case 7:
                return "Août";
            case 8:
                return "Septembre";
            case 9:
                return "Octobre";
            case 10:
                return "Novembre";
            default:
                return "Décembre";

        }
    }

}
