/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Contrôleur;

/**
 * Classe désignant un utilisateur, qui peut être client ou employé
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Utilisateur {

    protected String prenom;
    protected String nom;
    protected String email;
    protected String password;

    public Utilisateur() 
    {
        prenom = "NoAccountUser";
        nom = "";
        email = "noMail";
        password = "noPassword";
    }

    public Utilisateur(String p, String n, String mail,String pw) {
        prenom = p;
        nom = n;
        email = mail;
        password = pw;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getEmail() {
        return email;
    }
    
    public String getPassword() {
        return password;
    }
    
    
    
    
    public void setPrenom(String enter) {
        prenom = enter;
    }

    public void setNom(String enter) {
        nom = enter;
    }

    public void setEmail(String enter) {
        email= enter;
    }
    
    
    public void setPassword(String enter) {
        password = enter;
    }

}
