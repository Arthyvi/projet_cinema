/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modele;


/*
 * 
 * Librairies importées
 */
import Contrôleur.Film;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author kevin
 */
public class Connexion 
{
     /**
     * Attributs prives : connexion JDBC, statement, ordre requete et resultat
     * requete
     */
    private final Connection conn;
    private final Statement stmt;
    private ResultSet rset;
    private ResultSetMetaData rsetMeta;
    
     /**
     * Constructeur avec 3 paramètres : nom, login et password de la BDD locale
     *
     * @param nameDatabase
     * @param loginDatabase
     * @param passwordDatabase
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Connexion(String nameDatabase, String loginDatabase, String passwordDatabase) throws SQLException, ClassNotFoundException{
        // chargement driver "com.mysql.jdbc.Driver"
        Class.forName("com.mysql.jdbc.Driver");

        // url de connexion "jdbc:mysql://localhost:3305/usernameECE"
        String urlDatabase = "jdbc:mysql://localhost:3309/" + nameDatabase;
       // String urlDatabase = "jdbc:mysql://localhost:3308/jps?characterEncoding=latin1";

        //création d'une connexion JDBC à la base 
        conn = DriverManager.getConnection(urlDatabase, loginDatabase, passwordDatabase);

        // création d'un ordre SQL (statement)
        stmt = conn.createStatement();
    }
    
    
    public ArrayList<ArrayList<String>> executeRequete(String requete)throws SQLException 
    {
        stmt.clearBatch();
        rset =null;
        rsetMeta = null;
            
        ArrayList<ArrayList<String>> result = new ArrayList<>();
        
        // récupération de l'ordre de la requete
       rset = stmt.executeQuery(requete);

       // récupération du résultat de l'ordre
        rsetMeta = rset.getMetaData();  
        
        int nbColonne = rsetMeta.getColumnCount();

        
        while(rset.next())
        {
            ArrayList<String> row = new ArrayList<>();
            
            for(int I=0; I< nbColonne; I++)
            {
                  row.add(rset.getString(I+1));
            }
            
            result.add(row);
        }
        
        return result;
      
    }
    
    public void executeUpdate(String requete)throws SQLException
    {
        stmt.executeUpdate(requete);
    }
    
    public boolean loginAlreadyExist(String Login, boolean IsClient)
    {
        boolean result = true;
        
        ArrayList<ArrayList<String>> buffer;
        
        try
        {
             if (IsClient)
            {
               buffer =  executeRequete("SELECT * FROM clients WHERE email='"+ Login +"'");
            }
            else
            {
               buffer = executeRequete("SELECT * FROM users WHERE email='"+ Login +"'");
            }
             
            if(buffer.isEmpty())
            {
                result = false;
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
      
        return result;
    }
    
}
