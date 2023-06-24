/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vue;

import Modele.Connexion;
import java.awt.Dimension;
import static java.lang.Math.abs;
import java.sql.SQLException;
import java.util.*;
import javax.swing.*;
import Contrôleur.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Arthur
 */
public class TousLesFilms extends javax.swing.JFrame {

    private int[] nbJourParMois = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private Calendar calendar;

    private int[] comptBuff = {0, 0};

    private Connexion maconnexion;

    private ArrayList<Film> FilmList;
    private ArrayList<Personne> PersonneList;
    
    private Client User;

    /**
     * Creates new form TousLesFilms
     */
    public TousLesFilms() {
        initComponents();

        //Init size
        this.setSize(new Dimension(900, 700));

        // Change default spinner printing of the thousands with a space as a separetion
        jSpinnerDuree1.setEditor(new JSpinner.NumberEditor(jSpinnerDuree1, "#"));
        jSpinnerDuree2.setEditor(new JSpinner.NumberEditor(jSpinnerDuree2, "#"));
        jSpinnerJour.setEditor(new JSpinner.NumberEditor(jSpinnerJour, "#"));
        jSpinnerMois.setEditor(new JSpinner.NumberEditor(jSpinnerMois, "#"));
        jSpinnerAnnee.setEditor(new JSpinner.NumberEditor(jSpinnerAnnee, "#"));

        // Init spinner de l'année
        //Add second label-spinner pair.
        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        jSpinnerAnnee.setValue(0);

        // Init spinner des mois
        jSpinnerMois.setValue(0);

        // Init spinner des jours
        jSpinnerJour.setValue(0);

        // Init buffer list of film
        ArrayList<ArrayList<String>> ListBuffer = null;

        // Init the real list of film
        FilmList = new ArrayList<Film>();

        // Init the real list of Personne
        PersonneList = new ArrayList<Personne>();

        // Chargement de tout les films présent dans la base de données
        try {
            maconnexion = new Connexion("projetpoojava", "root", "");

            ListBuffer = maconnexion.executeRequete("SELECT * FROM films;");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : ListBuffer) {

                FilmList.add(new Film(row, maconnexion));
            }

            // On vide le buffer pour les prochaines possibles utilisation
            ListBuffer.clear();

            // Creation de la liste d'objet de type personne
            ListBuffer = maconnexion.executeRequete("SELECT * FROM personne;");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : ListBuffer) {

                PersonneList.add(new Personne(row, maconnexion));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Initialisation des combo box des genres et pays en fonction de ceux afficher dans la liste
        //ComboBox Genre
        jComboBoxGenre.removeAllItems();
        ListBuffer = null;

        jComboBoxGenre.addItem(""); // Met un élement null au minimum
        try {
            ListBuffer = maconnexion.executeRequete("SELECT DISTINCT genre FROM films;");

            for (ArrayList<String> row : ListBuffer) {
                jComboBoxGenre.addItem(row.get(0));
            }

            // On vide le buffer pour les prochaines possibles utilisation
            ListBuffer.clear();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //ComboBox Pays
        jComboBoxPays.removeAllItems();
        ListBuffer = null;

        jComboBoxPays.addItem(""); // Met un élement null au minimum
        try {
            ListBuffer = maconnexion.executeRequete("SELECT DISTINCT pays FROM films;");

            for (ArrayList<String> row : ListBuffer) {
                jComboBoxPays.addItem(row.get(0));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //Première initialisation
        initPanelApresRequete();

        // Cache le panel de recherche des acteurs/ realisateur pour l'initialisation
        jPanel2.hide();
        
        // Init User 
        User = new Client();

    }

    public TousLesFilms(int deci,Client theUser) {
        initComponents();

        //Init size
        this.setSize(new Dimension(900, 700));

        // Change default spinner printing of the thousands with a space as a separetion
        jSpinnerDuree1.setEditor(new JSpinner.NumberEditor(jSpinnerDuree1, "#"));
        jSpinnerDuree2.setEditor(new JSpinner.NumberEditor(jSpinnerDuree2, "#"));
        jSpinnerJour.setEditor(new JSpinner.NumberEditor(jSpinnerJour, "#"));
        jSpinnerMois.setEditor(new JSpinner.NumberEditor(jSpinnerMois, "#"));
        jSpinnerAnnee.setEditor(new JSpinner.NumberEditor(jSpinnerAnnee, "#"));

        // Init spinner de l'année
        //Add second label-spinner pair.
        calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        jSpinnerAnnee.setValue(0);

        // Init spinner des mois
        jSpinnerMois.setValue(0);

        // Init spinner des jours
        jSpinnerJour.setValue(0);

        // Init buffer list of film
        ArrayList<ArrayList<String>> ListBuffer = null;

        // Init the real list of film
        FilmList = new ArrayList<Film>();

        // Init the real list of Personne
        PersonneList = new ArrayList<Personne>();

        // Chargement de tout les films présent dans la base de données
        try {
            maconnexion = new Connexion("projetpoojava", "root", "");

            ListBuffer = maconnexion.executeRequete("SELECT * FROM films;");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : ListBuffer) {

                FilmList.add(new Film(row, maconnexion));
            }

            // On vide le buffer pour les prochaines possibles utilisation
            ListBuffer.clear();

            switch (deci) {
                case 0:
                    // Cache le panel de recherche des acteurs/ realisateur pour l'initialisation
                    jPanel2.hide();
                    jPanel1.show();
                    break;

                case 1:

                    // Cache le panel de recherche des film pour l'initialisation
                    jPanel1.hide();
                    jPanel2.show();

                    jRadioButtonFilms.setSelected(false);
                    jRadioButtonPersonnes.setSelected(true);
                    
                    // Creation de la liste d'objet de type personne
                  //  ListBuffer = maconnexion.executeRequete("SELECT per.prenom,per.nom,per.IDpersonne,per.DateNaissance FROM personne per INNER JOIN relationfilmpersonne reps ON per.INpersonne = reps.INpersonne WHERE reps.MetierLien = 'acteur';");
                    
                    break;

                case 2:

                    // Cache le panel de recherche des film pour l'initialisation
                    jPanel1.hide();
                    jPanel2.show();

                    jRadioButtonFilms.setSelected(false);
                    jRadioButtonPersonnes.setSelected(true);

                    jRadioButtonActeurs.setSelected(false);
                    jRadioButtonRealisateur.setSelected(true);
                    
                   // Creation de la liste d'objet de type personne
                   // ListBuffer = maconnexion.executeRequete("SELECT per.prenom,per.nom,per.IDpersonne,per.DateNaissance FROM personne per INNER JOIN relationfilmpersonne reps ON per.INpersonne = reps.INpersonne WHERE reps.MetierLien = 'realisateur';");
                    break;
            }

            

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : ListBuffer) {

                PersonneList.add(new Personne(row, maconnexion));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Initialisation des combo box des genres et pays en fonction de ceux afficher dans la liste
        //ComboBox Genre
        jComboBoxGenre.removeAllItems();
        ListBuffer = null;

        jComboBoxGenre.addItem(""); // Met un élement null au minimum
        try {
            ListBuffer = maconnexion.executeRequete("SELECT DISTINCT genre FROM films;");

            for (ArrayList<String> row : ListBuffer) {
                jComboBoxGenre.addItem(row.get(0));
            }

            // On vide le buffer pour les prochaines possibles utilisation
            ListBuffer.clear();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        //ComboBox Pays
        jComboBoxPays.removeAllItems();
        ListBuffer = null;

        jComboBoxPays.addItem(""); // Met un élement null au minimum
        try {
            ListBuffer = maconnexion.executeRequete("SELECT DISTINCT pays FROM films;");

            for (ArrayList<String> row : ListBuffer) {
                jComboBoxPays.addItem(row.get(0));
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Activation première requete pour les personnes
         doSQLRequestSearchPersonne();
        
        //Première initialisation
        initPanelApresRequete();
        
        // Init User 
        User = new Client(theUser);
        
        //Change Option for user
         if ((User.getPrenom() == "NoAccountUser") && (User.getNom() == "") && (User.getEmail() == "noMail") && (User.getPassword() == "noPassword")) {
            jComboBox1.removeAllItems();
            jComboBox1.addItem("Se déconnecter");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollBar1 = new javax.swing.JScrollBar();
        jPanelUpperPart = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jTextFieldRealisateurActor = new javax.swing.JTextField();
        jLabelFamilyName = new javax.swing.JLabel();
        jTextFieldFamilyName = new javax.swing.JTextField();
        jButtonOkActorReal = new javax.swing.JButton();
        jLabelFirstName = new javax.swing.JLabel();
        jTextFieldFirstName = new javax.swing.JTextField();
        jRadioButtonRealisateur = new javax.swing.JRadioButton();
        jRadioButtonActeurs = new javax.swing.JRadioButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBoxGenre = new javax.swing.JComboBox<>();
        jComboBoxPays = new javax.swing.JComboBox<>();
        jSpinnerDuree1 = new javax.swing.JSpinner();
        jTextFieldRealisateur = new javax.swing.JTextField();
        jSpinnerMois = new javax.swing.JSpinner();
        jSpinnerAnnee = new javax.swing.JSpinner();
        jSpinnerJour = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSpinnerDuree2 = new javax.swing.JSpinner();
        jLabelDevantBarreDeRecherche = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextFieldTitreRecherche = new javax.swing.JTextField();
        jButtonOk = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jTextFieldtheActeurs = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabelGrosTitre = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel19 = new javax.swing.JLabel();
        jLabelNumberResult = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jRadioButtonPersonnes = new javax.swing.JRadioButton();
        jRadioButtonFilms = new javax.swing.JRadioButton();
        jPanelLowerPart = new javax.swing.JPanel();
        jPanelFilm1 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jPanelFilm2 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(null);

        jScrollBar1.addAdjustmentListener(new java.awt.event.AdjustmentListener() {
            public void adjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {
                jScrollBar1AdjustmentValueChanged(evt);
            }
        });
        getContentPane().add(jScrollBar1);
        jScrollBar1.setBounds(850, 340, 17, 290);

        jPanelUpperPart.setLayout(null);

        jButton4.setText("Voir les films");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jButton4);
        jButton4.setBounds(0, 90, 225, 25);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        jLabel35.setText("Titre de film :");
        jPanel2.add(jLabel35);
        jLabel35.setBounds(510, 60, 120, 16);

        jLabel37.setText("RECHERCHE  ACTEURS :");
        jPanel2.add(jLabel37);
        jLabel37.setBounds(320, 0, 210, 16);

        jTextFieldRealisateurActor.setToolTipText("");
        jTextFieldRealisateurActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRealisateurActorActionPerformed(evt);
            }
        });
        jPanel2.add(jTextFieldRealisateurActor);
        jTextFieldRealisateurActor.setBounds(590, 60, 170, 22);

        jLabelFamilyName.setText("Nom :");
        jPanel2.add(jLabelFamilyName);
        jLabelFamilyName.setBounds(230, 60, 40, 16);

        jTextFieldFamilyName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFamilyNameActionPerformed(evt);
            }
        });
        jTextFieldFamilyName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldFamilyNameKeyPressed(evt);
            }
        });
        jPanel2.add(jTextFieldFamilyName);
        jTextFieldFamilyName.setBounds(270, 60, 130, 25);

        jButtonOkActorReal.setText("OK");
        jButtonOkActorReal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActorRealActionPerformed(evt);
            }
        });
        jPanel2.add(jButtonOkActorReal);
        jButtonOkActorReal.setBounds(710, 110, 60, 25);

        jLabelFirstName.setText("Prenom :");
        jPanel2.add(jLabelFirstName);
        jLabelFirstName.setBounds(20, 60, 60, 16);

        jTextFieldFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFirstNameActionPerformed(evt);
            }
        });
        jTextFieldFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldFirstNameKeyPressed(evt);
            }
        });
        jPanel2.add(jTextFieldFirstName);
        jTextFieldFirstName.setBounds(70, 60, 130, 25);

        jRadioButtonRealisateur.setText("Realisateurs");
        jRadioButtonRealisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonRealisateurActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButtonRealisateur);
        jRadioButtonRealisateur.setBounds(120, 10, 100, 25);

        jRadioButtonActeurs.setSelected(true);
        jRadioButtonActeurs.setText("Acteurs");
        jRadioButtonActeurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonActeursActionPerformed(evt);
            }
        });
        jPanel2.add(jRadioButtonActeurs);
        jRadioButtonActeurs.setBounds(20, 10, 80, 25);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel2.add(jSeparator3);
        jSeparator3.setBounds(470, 20, 130, 110);

        jPanelUpperPart.add(jPanel2);
        jPanel2.setBounds(50, 150, 800, 150);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel6.setText("Genre :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(30, 60, 70, 16);

        jLabel8.setText("Pays :");
        jPanel1.add(jLabel8);
        jLabel8.setBounds(30, 110, 60, 16);

        jLabel9.setText("et");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(370, 60, 20, 16);

        jLabel10.setText("Réalisateur :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(570, 50, 80, 16);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("/");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(450, 110, 30, 20);

        jLabel13.setText("RECHERCHE  FILMS :");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(320, 0, 210, 16);

        jComboBoxGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxGenre);
        jComboBoxGenre.setBounds(80, 60, 120, 22);

        jComboBoxPays.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jPanel1.add(jComboBoxPays);
        jComboBoxPays.setBounds(80, 110, 120, 22);

        jSpinnerDuree1.setPreferredSize(new java.awt.Dimension(31, 25));
        jSpinnerDuree1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerDuree1StateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerDuree1);
        jSpinnerDuree1.setBounds(300, 60, 60, 25);

        jTextFieldRealisateur.setToolTipText("");
        jTextFieldRealisateur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldRealisateurActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldRealisateur);
        jTextFieldRealisateur.setBounds(650, 50, 130, 22);

        jSpinnerMois.setPreferredSize(new java.awt.Dimension(31, 25));
        jSpinnerMois.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerMoisStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerMois);
        jSpinnerMois.setBounds(390, 110, 50, 25);

        jSpinnerAnnee.setPreferredSize(new java.awt.Dimension(31, 25));
        jSpinnerAnnee.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerAnneeStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerAnnee);
        jSpinnerAnnee.setBounds(460, 110, 70, 25);

        jSpinnerJour.setPreferredSize(new java.awt.Dimension(31, 25));
        jSpinnerJour.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerJourStateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerJour);
        jSpinnerJour.setBounds(320, 110, 50, 25);

        jLabel12.setText("Date de parution :");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(220, 110, 110, 16);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("/");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(380, 110, 30, 20);

        jLabel14.setText("Durée :  entre");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(220, 60, 80, 16);

        jLabel15.setText("min");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(460, 60, 50, 16);

        jSpinnerDuree2.setPreferredSize(new java.awt.Dimension(31, 25));
        jSpinnerDuree2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerDuree2StateChanged(evt);
            }
        });
        jPanel1.add(jSpinnerDuree2);
        jSpinnerDuree2.setBounds(390, 60, 60, 25);

        jLabelDevantBarreDeRecherche.setText("Recherchez votre film...");
        jLabelDevantBarreDeRecherche.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDevantBarreDeRechercheMouseClicked(evt);
            }
        });
        jPanel1.add(jLabelDevantBarreDeRecherche);
        jLabelDevantBarreDeRecherche.setBounds(140, 20, 540, 25);

        jLabel7.setText("Titre :");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(90, 23, 40, 16);

        jTextFieldTitreRecherche.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTitreRechercheActionPerformed(evt);
            }
        });
        jTextFieldTitreRecherche.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTitreRechercheKeyPressed(evt);
            }
        });
        jPanel1.add(jTextFieldTitreRecherche);
        jTextFieldTitreRecherche.setBounds(130, 20, 540, 25);

        jButtonOk.setText("OK");
        jButtonOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOkActionPerformed(evt);
            }
        });
        jPanel1.add(jButtonOk);
        jButtonOk.setBounds(710, 110, 60, 25);

        jLabel42.setText("Acteur :");
        jPanel1.add(jLabel42);
        jLabel42.setBounds(590, 80, 80, 16);

        jTextFieldtheActeurs.setToolTipText("");
        jTextFieldtheActeurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtheActeursActionPerformed(evt);
            }
        });
        jPanel1.add(jTextFieldtheActeurs);
        jTextFieldtheActeurs.setBounds(650, 80, 130, 22);

        jPanelUpperPart.add(jPanel1);
        jPanel1.setBounds(50, 150, 800, 150);

        jButton1.setText("Voir les acteurs");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jButton1);
        jButton1.setBounds(220, 90, 225, 25);

        jButton3.setText("Voir les réalisateurs");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jButton3);
        jButton3.setBounds(440, 90, 225, 25);

        jButton2.setText("Accueil");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jButton2);
        jButton2.setBounds(660, 90, 225, 25);

        jLabelGrosTitre.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabelGrosTitre.setText("FILMS");
        jPanelUpperPart.add(jLabelGrosTitre);
        jLabelGrosTitre.setBounds(20, 10, 620, 73);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mon compte", "Voir mon profil", "Mes films ", "Prochaines séances", "Se déconnecter" }));
        jComboBox1.setToolTipText("");
        jComboBox1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox1MouseClicked(evt);
            }
        });
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jComboBox1);
        jComboBox1.setBounds(660, 40, 220, 22);

        jLabel19.setText("Nombre de résultats : ");
        jPanelUpperPart.add(jLabel19);
        jLabel19.setBounds(40, 300, 130, 16);

        jLabelNumberResult.setForeground(new java.awt.Color(255, 51, 51));
        jLabelNumberResult.setText("Number");
        jPanelUpperPart.add(jLabelNumberResult);
        jLabelNumberResult.setBounds(160, 300, 170, 16);

        jLabel20.setText("Type du résultat :");
        jPanelUpperPart.add(jLabel20);
        jLabel20.setBounds(210, 125, 200, 16);

        jRadioButtonPersonnes.setText("Personnes");
        jRadioButtonPersonnes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonPersonnesActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jRadioButtonPersonnes);
        jRadioButtonPersonnes.setBounds(490, 120, 120, 25);

        jRadioButtonFilms.setSelected(true);
        jRadioButtonFilms.setText("Films");
        jRadioButtonFilms.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonFilmsActionPerformed(evt);
            }
        });
        jPanelUpperPart.add(jRadioButtonFilms);
        jRadioButtonFilms.setBounds(350, 120, 80, 25);

        getContentPane().add(jPanelUpperPart);
        jPanelUpperPart.setBounds(0, 10, 890, 320);
        getContentPane().add(jPanelLowerPart);
        jPanelLowerPart.setBounds(0, 630, 850, 60);

        jPanelFilm1.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFilm1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelFilm1MouseClicked(evt);
            }
        });
        jPanelFilm1.setLayout(null);

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel21.setText("Titre du film");
        jPanelFilm1.add(jLabel21);
        jLabel21.setBounds(310, 10, 490, 40);

        jLabel22.setText("Affiche du film");
        jLabel22.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFilm1.add(jLabel22);
        jLabel22.setBounds(50, 20, 200, 260);

        jLabel1.setText("Avec : acteur1, acteur 2, acteur 3");
        jPanelFilm1.add(jLabel1);
        jLabel1.setBounds(280, 110, 530, 40);

        jLabel2.setText("De : Realisateur");
        jPanelFilm1.add(jLabel2);
        jLabel2.setBounds(280, 90, 480, 20);

        jLabel3.setText("Date/ Durée / Genre");
        jPanelFilm1.add(jLabel3);
        jLabel3.setBounds(280, 60, 530, 16);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel17.setText("Note : truc/5");
        jPanelFilm1.add(jLabel17);
        jLabel17.setBounds(650, 230, 200, 30);

        jLabel18.setText("Pays : ");
        jPanelFilm1.add(jLabel18);
        jLabel18.setBounds(280, 150, 300, 16);

        jLabel23.setForeground(new java.awt.Color(51, 255, 255));
        jLabel23.setText("Clique pour le détail et réserver !!");
        jPanelFilm1.add(jLabel23);
        jLabel23.setBounds(350, 260, 190, 16);

        getContentPane().add(jPanelFilm1);
        jPanelFilm1.setBounds(10, 340, 830, 290);

        jPanelFilm2.setBackground(new java.awt.Color(255, 255, 255));
        jPanelFilm2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelFilm2MouseClicked(evt);
            }
        });
        jPanelFilm2.setLayout(null);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel24.setText("Titre du film");
        jPanelFilm2.add(jLabel24);
        jLabel24.setBounds(310, 10, 490, 40);

        jLabel25.setText("Affiche du film");
        jLabel25.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanelFilm2.add(jLabel25);
        jLabel25.setBounds(50, 20, 200, 260);

        jLabel26.setText("Avec : acteur1, acteur 2, acteur 3");
        jPanelFilm2.add(jLabel26);
        jLabel26.setBounds(280, 110, 530, 40);

        jLabel27.setText("De : Realisateur");
        jPanelFilm2.add(jLabel27);
        jLabel27.setBounds(280, 90, 480, 20);

        jLabel28.setText("Date/ Durée / Genre");
        jPanelFilm2.add(jLabel28);
        jLabel28.setBounds(280, 60, 530, 16);

        jLabel29.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel29.setText("Note : truc/5");
        jPanelFilm2.add(jLabel29);
        jLabel29.setBounds(650, 230, 200, 30);

        jLabel30.setText("Pays : ");
        jPanelFilm2.add(jLabel30);
        jLabel30.setBounds(280, 150, 300, 16);

        jLabel31.setForeground(new java.awt.Color(51, 255, 255));
        jLabel31.setText("Clique pour le détail et réserver !!");
        jPanelFilm2.add(jLabel31);
        jLabel31.setBounds(350, 260, 190, 16);

        getContentPane().add(jPanelFilm2);
        jPanelFilm2.setBounds(10, 340, 830, 290);
        getContentPane().add(jSeparator1);
        jSeparator1.setBounds(420, 490, 0, 2);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(10, 630, 830, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       TousLesFilms filo = new  TousLesFilms(0,User);
        filo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Accueil_Client window = new Accueil_Client(User);
        window.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextFieldRealisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRealisateurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRealisateurActionPerformed

    private void jTextFieldTitreRechercheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTitreRechercheActionPerformed

    }//GEN-LAST:event_jTextFieldTitreRechercheActionPerformed

    private void jSpinnerDuree1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerDuree1StateChanged
        if (((int) jSpinnerDuree1.getValue()) < 0) {
            jSpinnerDuree1.setValue(0); //On le met à 0
        }

    }//GEN-LAST:event_jSpinnerDuree1StateChanged

    private void jSpinnerDuree2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerDuree2StateChanged
        if (((int) jSpinnerDuree2.getValue()) < 0) {
            jSpinnerDuree2.setValue(0); //On le met à 0
        }
    }//GEN-LAST:event_jSpinnerDuree2StateChanged

    private void jSpinnerJourStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerJourStateChanged

        int value = ((int) jSpinnerJour.getValue());
        int BufferValue = 28;

        if (value < 0) {
            jSpinnerJour.setValue(0);

        } else {

            if (((int) jSpinnerMois.getValue()) != 0) {
                BufferValue = nbJourParMois[((int) jSpinnerMois.getValue()) - 1];
            }

            if (value > BufferValue) {
                jSpinnerJour.setValue(BufferValue);

            }

        }


    }//GEN-LAST:event_jSpinnerJourStateChanged

    private void jSpinnerMoisStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerMoisStateChanged
        int value = ((int) jSpinnerMois.getValue());

        if (value < 0) {
            jSpinnerMois.setValue(0);
        } else {
            if (value > 12) {
                jSpinnerMois.setValue(12);
            }
        }

        // Active les changements pour les jours max en conséquence
        if (comptBuff[1] == 1) {
            if (((int) jSpinnerJour.getValue()) != 1) {
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) - 1);
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) + 1);
            } else {
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) + 1);
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) - 1);
            }

        } else {
            comptBuff[1] = 1;
        }

    }//GEN-LAST:event_jSpinnerMoisStateChanged

    private void jSpinnerAnneeStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerAnneeStateChanged

        int value = ((int) jSpinnerAnnee.getValue());
        int currentYear = (int) calendar.get(Calendar.YEAR);

        if (value < 1900) {

            if ((int) jSpinnerAnnee.getValue() == 1) {
                jSpinnerAnnee.setValue(1900);
            } else {
                jSpinnerAnnee.setValue(0);
            }
        } else {
            if (value > currentYear) {
                jSpinnerAnnee.setValue(currentYear);
            }
        }

        //Savoir si l'année est bissextile
        if (abs(2020 - value) % 4 == 0) {
            nbJourParMois[1] = 29;
        } else {
            nbJourParMois[1] = 28;
        }

        // Active les changements pour le 29 Fevrier au cas où
        if (comptBuff[0] == 1) {
            if (((int) jSpinnerJour.getValue()) != 1) {
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) - 1);
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) + 1);
            } else {
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) + 1);
                jSpinnerJour.setValue(((int) jSpinnerJour.getValue()) - 1);
            }
        } else {
            comptBuff[0] = 1;
        }


    }//GEN-LAST:event_jSpinnerAnneeStateChanged

    private void jScrollBar1AdjustmentValueChanged(java.awt.event.AdjustmentEvent evt) {//GEN-FIRST:event_jScrollBar1AdjustmentValueChanged

        int valueScroll = jScrollBar1.getValue();

        int spacePerPannel = jPanelFilm1.getHeight() + 20;
        int restScroll = valueScroll % spacePerPannel;
        int NumberPannelPassed = (valueScroll - restScroll) / spacePerPannel;

        int Base = 340 + (NumberPannelPassed) * spacePerPannel;

        // Changement de position des pannels en fonction du slider 
        jPanelFilm1.setLocation(10, Base - valueScroll);
        jPanelFilm2.setLocation(10, Base - valueScroll + spacePerPannel);

        if (jRadioButtonPersonnes.isSelected()) {

            /// Remplissage des pannels avec les bons films dedans
            // Remplisage premier panel déroulant
            PersonneList.get(NumberPannelPassed).remplirPannelFilm(jLabel21, jLabel22, jLabel3, jLabel2, jLabel1, jLabel17, jLabel18, jLabel23);

            // Si l'on n'est pas deja arriver au dernier film de la list avec le premier pannel
            if (NumberPannelPassed != (PersonneList.size() - 1)) {
                // Remplisage deuxième panel déroulant
                PersonneList.get(NumberPannelPassed + 1).remplirPannelFilm(jLabel24, jLabel25, jLabel28, jLabel27, jLabel26, jLabel29, jLabel30, jLabel31);

            }

        } else {
            /// Remplissage des pannels avec les bons films dedans
            // Remplisage premier panel déroulant
            FilmList.get(NumberPannelPassed).remplirPannelFilm(jLabel21, jLabel22, jLabel3, jLabel2, jLabel1, jLabel17, jLabel18, jLabel23);

            // Si l'on n'est pas deja arriver au dernier film de la list avec le premier pannel
            if (NumberPannelPassed != (FilmList.size() - 1)) {
                // Remplisage deuxième panel déroulant
                FilmList.get(NumberPannelPassed + 1).remplirPannelFilm(jLabel24, jLabel25, jLabel28, jLabel27, jLabel26, jLabel29, jLabel30, jLabel31);

            }

        }

    }//GEN-LAST:event_jScrollBar1AdjustmentValueChanged

    private void jPanelFilm1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelFilm1MouseClicked
        
        int valueScroll = jScrollBar1.getValue();

        int spacePerPannel = jPanelFilm1.getHeight() + 20;
        int restScroll = valueScroll % spacePerPannel;
        int NumberPannelPassed = (valueScroll - restScroll) / spacePerPannel;

        int Base = 340 + (NumberPannelPassed) * spacePerPannel;
        
        if (!jRadioButtonPersonnes.isSelected()) 
        {
            Film_Infos infos = new Film_Infos(FilmList.get(NumberPannelPassed),User);
            infos.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_jPanelFilm1MouseClicked

    private void jPanelFilm2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelFilm2MouseClicked
      
        int valueScroll = jScrollBar1.getValue();

        int spacePerPannel = jPanelFilm1.getHeight() + 20;
        int restScroll = valueScroll % spacePerPannel;
        int NumberPannelPassed = (valueScroll - restScroll) / spacePerPannel;

        int Base = 340 + (NumberPannelPassed) * spacePerPannel;
        
        if (!jRadioButtonPersonnes.isSelected()) 
        {
            Film_Infos infos = new Film_Infos(FilmList.get(NumberPannelPassed+1),User);
            infos.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jPanelFilm2MouseClicked

    private void jLabelDevantBarreDeRechercheMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDevantBarreDeRechercheMouseClicked
        jLabelDevantBarreDeRecherche.hide();
        jTextFieldTitreRecherche.requestFocus();
    }//GEN-LAST:event_jLabelDevantBarreDeRechercheMouseClicked

    private void doSQLRequestSearchFilm() {
        ArrayList<ArrayList<String>> BufferSQL = null;

        int compteur = 0;

        /// CREATION DE LA REQUETE PRECISE
        String request = "SELECT DISTINCT f.titre,f.genre,f.durée,f.note,f.description,f.pays,f.date,f.salle,f.IDfilm FROM films f INNER JOIN relationfilmpersonne rfp ON f.IDfilm = rfp.IDfilm INNER JOIN personne per ON rfp.IDpersonne = per.IDpersonne";

        boolean first = false;

        if (!jTextFieldTitreRecherche.getText().equals("")) {

            request += " WHERE f.titre LIKE '" + jTextFieldTitreRecherche.getText() + "%'";
            first = true;
        }

        if (!jComboBoxGenre.getSelectedItem().equals("")) {
            if (first == false) {
                first = true;
                request += " WHERE ";
            } else {
                request += " AND ";
            }

            request += "f.genre = '" + ((String) jComboBoxGenre.getSelectedItem()) + "'";

        }

        if (!jComboBoxPays.getSelectedItem().equals("")) {
            if (first == false) {
                first = true;
                request += " WHERE ";
            } else {
                request += " AND ";
            }

            request += "f.pays = '" + ((String) jComboBoxPays.getSelectedItem()) + "'";
        }

        if (((int) jSpinnerDuree1.getValue() != 0) || ((int) jSpinnerDuree2.getValue() != 0)) {
            if (first == false) {
                first = true;
                request += " WHERE ";
            } else {
                request += " AND ";
            }

            request += "f.durée BETWEEN '" + ((int) jSpinnerDuree1.getValue()) + "' AND '" + ((int) jSpinnerDuree2.getValue()) + "'";
        }

        if (((int) jSpinnerJour.getValue() != 0) && ((int) jSpinnerMois.getValue() != 0) && ((int) jSpinnerAnnee.getValue() != 0)) {
            if (first == false) {
                first = true;
                request += " WHERE ";
            } else {
                request += " AND ";
            }

            request += "f.date = '";

            if ((int) jSpinnerMois.getValue() < 10) {
                request += "0";
            }

            request += (int) jSpinnerMois.getValue() + "/";

            if ((int) jSpinnerJour.getValue() < 10) {
                request += "0";
            }
            request += (int) jSpinnerJour.getValue() + "/" + (int) jSpinnerAnnee.getValue() + "'";

        }

        if (!jTextFieldRealisateur.getText().equals("")) {

            compteur++;
        }

        if (!jTextFieldtheActeurs.getText().equals("")) {

            compteur++;
        }

        // Switch des acteurs et réalisateur
        switch (compteur) {
            case 0:
                break;

            case 1:
                if (!jTextFieldRealisateur.getText().equals("")) {
                    if (first == false) {
                        first = true;
                        request += " WHERE ";
                    } else {
                        request += " AND ";
                    }

                    request += "(rfp.MetierLien = 'realisateur' AND CONCAT(per.prenom , per.nom) LIKE '%" + jTextFieldRealisateur.getText() + "%')";

                } else {

                    if (first == false) {
                        first = true;
                        request += " WHERE ";
                    } else {
                        request += " AND ";
                    }

                    request += "(rfp.MetierLien = 'acteur' AND CONCAT(per.prenom , per.nom) LIKE '%" + jTextFieldtheActeurs.getText() + "%')";

                }

                break;

            case 2:

                if (first == false) {
                    first = true;
                    request += " WHERE ";
                } else {
                    request += " AND ";
                }

                request += "(((rfp.MetierLien = 'realisateur') AND ( CONCAT(per.prenom , per.nom) LIKE '%" + jTextFieldRealisateur.getText() + "%')) OR ((rfp.MetierLien = 'acteur') AND (CONCAT(per.prenom , per.nom) LIKE '%" + jTextFieldtheActeurs.getText() + "%')))";

                //request += " (EXISTS(SELECT DISTINCT * FROM films f INNER JOIN relationfilmpersonne rfp ON f.IDfilm = rfp.IDfilm INNER JOIN personne per ON rfp.IDpersonne = per.IDpersonne WHERE ) AND  EXISTS())";
                break;

        }

        request += " ORDER BY f.titre ASC;";

        //System.out.println(request);
        /// ENVOIE ET TRAITEMENT DES RESULTATS DE LA REQUETE (changement de la liste de film)
        FilmList.clear(); //Clear the List
        try {
            // maconnexion.executeRequete("SELECT * FROM films;");
            BufferSQL = maconnexion.executeRequete(request);

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : BufferSQL) {
                FilmList.add(new Film(row, maconnexion));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Initialisation en consequence de l'affichage
        initPanelApresRequete();

        // System.out.println((int)jSpinnerDuree1.getValue());
    }

    private void doSQLRequestSearchPersonne() {
        ArrayList<ArrayList<String>> BufferSQL = null;

        int compteur = 0;

        /// CREATION DE LA REQUETE PRECISE
        String request = "SELECT DISTINCT per.prenom,per.nom,per.IDpersonne,per.DateNaissance FROM films f INNER JOIN relationfilmpersonne rfp ON f.IDfilm = rfp.IDfilm INNER JOIN personne per ON rfp.IDpersonne = per.IDpersonne";

        if (jRadioButtonActeurs.isSelected()) {
            request += " WHERE rfp.MetierLien = 'acteur'";
        } else {
            request += " WHERE rfp.MetierLien = 'realisateur'";
        }

        if (!jTextFieldFirstName.getText().equals("")) {

            request += " AND per.prenom LIKE '%" + jTextFieldFirstName.getText() + "%'";

        }

        if (!jTextFieldFamilyName.getText().equals("")) {

            request += " AND per.nom LIKE '%" + jTextFieldFamilyName.getText() + "%'";

        }

        if (!jTextFieldRealisateurActor.getText().equals("")) {
            request += " AND f.titre LIKE '%" + jTextFieldRealisateurActor.getText() + "%'";

        }

        request += " ORDER BY per.prenom ASC;";

        //System.out.println(request);
        /// ENVOIE ET TRAITEMENT DES RESULTATS DE LA REQUETE (changement de la liste de personnes)
        PersonneList.clear(); //Clear the List
        try {
            // maconnexion.executeRequete("SELECT * FROM films;");
            BufferSQL = maconnexion.executeRequete(request);

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : BufferSQL) {
                PersonneList.add(new Personne(row, maconnexion));

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // Initialisation en consequence de l'affichage
        initPanelApresRequete();

        // System.out.println((int)jSpinnerDuree1.getValue());
    }

    private void initPanelApresRequete() {
        // Initialization des position des panels après une requete SQL
        int heightPannels = jPanelFilm1.getHeight();
        int theSize = 0;
        if (jRadioButtonPersonnes.isSelected()) {
            theSize = PersonneList.size();
        } else {
            theSize = FilmList.size();
        }

        //Initialise la position de depart des panels
        jPanelFilm1.setLocation(10, 340);
        jPanelFilm2.setLocation(10, 340 + 20 + heightPannels);

        // Init le nombre de résultat trouver
        jLabelNumberResult.setText(Integer.toString(theSize));

        if (theSize == 0) {
            jPanelFilm1.hide();
            jPanelFilm2.hide();

            jScrollBar1.hide();

        } else {
            jPanelFilm1.show();
            jPanelFilm2.show();

            jScrollBar1.show();

            if (theSize == 1) {
                jScrollBar1.hide();

                if (jRadioButtonPersonnes.isSelected()) {

                    PersonneList.get(0).remplirPannelFilm(jLabel21, jLabel22, jLabel3, jLabel2, jLabel1, jLabel17, jLabel18, jLabel23);

                } else {
                    FilmList.get(0).remplirPannelFilm(jLabel21, jLabel22, jLabel3, jLabel2, jLabel1, jLabel17, jLabel18, jLabel23);

                }

            }

            // Init value slider
            jScrollBar1.setValue(0);

            // Calcul taille nécessaire du slider en fonction du nombre d'éléments
            jScrollBar1.setMaximum((theSize - 1) * jPanelFilm1.getHeight() + (theSize - 2) * 20 + 30);

        }

    }


    private void jTextFieldTitreRechercheKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTitreRechercheKeyPressed

    }//GEN-LAST:event_jTextFieldTitreRechercheKeyPressed

    private void jButtonOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActionPerformed

        // Activation de la recherche
        doSQLRequestSearchFilm();
    }//GEN-LAST:event_jButtonOkActionPerformed

    private void jRadioButtonPersonnesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonPersonnesActionPerformed

        if (jRadioButtonPersonnes.isSelected()) {
            jRadioButtonFilms.setSelected(!jRadioButtonFilms.isSelected());
            jLabelGrosTitre.setText("Acteurs/Realisateurs");

            jPanel1.hide();
            jPanel2.show();

            // Activation de la recherche personne
            doSQLRequestSearchPersonne();
        }

        if ((!jRadioButtonPersonnes.isSelected()) && (!jRadioButtonFilms.isSelected())) {
            jRadioButtonPersonnes.setSelected(true);
        }

    }//GEN-LAST:event_jRadioButtonPersonnesActionPerformed

    private void jRadioButtonFilmsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonFilmsActionPerformed

        if (jRadioButtonFilms.isSelected()) {
            jRadioButtonPersonnes.setSelected(!jRadioButtonPersonnes.isSelected());
            jLabelGrosTitre.setText("FILMS");

            jPanel1.show();
            jPanel2.hide();

            // Activation de la recherche film
            doSQLRequestSearchFilm();
        }

        if ((!jRadioButtonPersonnes.isSelected()) && (!jRadioButtonFilms.isSelected())) {
            jRadioButtonFilms.setSelected(true);
        }
    }//GEN-LAST:event_jRadioButtonFilmsActionPerformed

    private void jTextFieldRealisateurActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldRealisateurActorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldRealisateurActorActionPerformed

    private void jTextFieldFamilyNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFamilyNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFamilyNameActionPerformed

    private void jTextFieldFamilyNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFamilyNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFamilyNameKeyPressed

    private void jButtonOkActorRealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOkActorRealActionPerformed

        // Activation de la recherche personne
        doSQLRequestSearchPersonne();
    }//GEN-LAST:event_jButtonOkActorRealActionPerformed

    private void jTextFieldFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameActionPerformed

    private void jTextFieldFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldFirstNameKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldFirstNameKeyPressed

    private void jTextFieldtheActeursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtheActeursActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtheActeursActionPerformed

    private void jRadioButtonActeursActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonActeursActionPerformed

        if (jRadioButtonActeurs.isSelected()) {
            jRadioButtonRealisateur.setSelected(!jRadioButtonRealisateur.isSelected());
            jLabel37.setText("RECHERCHE  ACTEURS :");

            doSQLRequestSearchPersonne();
        }

        if ((!jRadioButtonActeurs.isSelected()) && (!jRadioButtonRealisateur.isSelected())) {
            jRadioButtonActeurs.setSelected(true);

        }
    }//GEN-LAST:event_jRadioButtonActeursActionPerformed

    private void jRadioButtonRealisateurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonRealisateurActionPerformed

        if (jRadioButtonRealisateur.isSelected()) {
            jRadioButtonActeurs.setSelected(!jRadioButtonActeurs.isSelected());
            jLabel37.setText("RECHERCHE  REALISATEURS :");

            doSQLRequestSearchPersonne();
        }

        if ((!jRadioButtonActeurs.isSelected()) && (!jRadioButtonRealisateur.isSelected())) {
            jRadioButtonRealisateur.setSelected(true);

        }


    }//GEN-LAST:event_jRadioButtonRealisateurActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       TousLesFilms filo = new  TousLesFilms(1,User);
        filo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        TousLesFilms filo = new  TousLesFilms(2,User);
        filo.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       
     if (jComboBox1.getSelectedIndex() != 0) {
            switch (jComboBox1.getSelectedIndex()) {
                case 1: // voir mon profil
                    Profil window = new Profil(User);
                    window.setVisible(true);
                    this.dispose();
                    break;
                case 2: // voir mes films
                    VoirMesFilms window2 = new VoirMesFilms(User);
                    window2.setVisible(true);
                    this.dispose();
                    break;
                case 3: // voir mes scéances
                  //  MesSeances window3 = new MesSeances();
                  //  window3.setVisible(true);
                   // this.dispose();
                    break;
                    
                case 4: // deconnexion
                    
                    Identification theWindow3 = new Identification();
                    theWindow3.setVisible(true);

                    this.dispose();
                    break;
                    
                default:
                    break;
            }
        }
        
        
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox1MouseClicked
         if ((User.getPrenom() == "NoAccountUser") && (User.getNom() == "") && (User.getEmail() == "noMail") && (User.getPassword() == "noPassword")) {
            Identification theWindow3 = new Identification();
            theWindow3.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jComboBox1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TousLesFilms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TousLesFilms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TousLesFilms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TousLesFilms.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TousLesFilms().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButtonOk;
    private javax.swing.JButton jButtonOkActorReal;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBoxGenre;
    private javax.swing.JComboBox<String> jComboBoxPays;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelDevantBarreDeRecherche;
    private javax.swing.JLabel jLabelFamilyName;
    private javax.swing.JLabel jLabelFirstName;
    private javax.swing.JLabel jLabelGrosTitre;
    private javax.swing.JLabel jLabelNumberResult;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelFilm1;
    private javax.swing.JPanel jPanelFilm2;
    private javax.swing.JPanel jPanelLowerPart;
    private javax.swing.JPanel jPanelUpperPart;
    private javax.swing.JRadioButton jRadioButtonActeurs;
    private javax.swing.JRadioButton jRadioButtonFilms;
    private javax.swing.JRadioButton jRadioButtonPersonnes;
    private javax.swing.JRadioButton jRadioButtonRealisateur;
    private javax.swing.JScrollBar jScrollBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSpinner jSpinnerAnnee;
    private javax.swing.JSpinner jSpinnerDuree1;
    private javax.swing.JSpinner jSpinnerDuree2;
    private javax.swing.JSpinner jSpinnerJour;
    private javax.swing.JSpinner jSpinnerMois;
    private javax.swing.JTextField jTextFieldFamilyName;
    private javax.swing.JTextField jTextFieldFirstName;
    private javax.swing.JTextField jTextFieldRealisateur;
    private javax.swing.JTextField jTextFieldRealisateurActor;
    private javax.swing.JTextField jTextFieldTitreRecherche;
    private javax.swing.JTextField jTextFieldtheActeurs;
    // End of variables declaration//GEN-END:variables
}
