/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vue;

import java.awt.*;
import javax.swing.*;
import Contrôleur.*;
import java.text.DecimalFormat;
import java.util.*;
import java.time.*;

/**
 * Permet de réserver sa séance
 * @author Arthur, Kevin, Lucas, Amna
 */
public class Reserver extends javax.swing.JFrame {

    private Seance maSeance;
    private Liste<Seance> toutesLesSeances;
    private Client User;
    public Film theMovie;
    private boolean seanceSelected;

    /**
     * Creates new form Reserver
     */
    public Reserver() {
        initComponents();
        InitSeances();
    }

    public Reserver(Film movie, Client TheUser) {
        initComponents();
        theMovie = movie;
        InitSeances();
        
        //Init size
        this.setSize(new Dimension(900, 700));
        
        toutesLesSeances.Get(0).setFilm(movie);
        toutesLesSeances.Get(1).setFilm(movie);
        toutesLesSeances.Get(2).setFilm(movie);

        jLabel2.setText("Film : " + movie.getNom());

        User = TheUser;

        

        seanceSelected = false;
        
        // Affichage du tarif
        int reduction = 0;
        if ((User.getPrenom() == "NoAccountUser") && (User.getNom() == "") && (User.getEmail() == "noMail") && (User.getPassword() == "noPassword")) 
        {
             jLabel15.setText("- 0% ");
              jLabel14.setText(" = 8€ ");
        }
        else
        {
            switch(User.getType())
            {
                case Régulier:
                    reduction = 20;
                    break;
                    
                case Sénior:
                    reduction = 40;
                    break;
                    
                case  Enfant:
                    reduction = 60;
                    break;
    
            }
            jLabel15.setText("- "+reduction+"% ");
            
            jLabel14.setText(" = "+ ((float)(8.0/100.0)*reduction)+"€ ");
             
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(900, 600));
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("RESERVER MA PLACE");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 30, 510, 90);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Film : NOM DU FILM");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 140, 560, 29);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Séances : ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(30, 200, 150, 22);

        jPanel1.setBackground(new java.awt.Color(255, 204, 51));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel1MouseExited(evt);
            }
        });
        jPanel1.setLayout(null);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Heure de la séance");
        jLabel4.setToolTipText("");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(0, 10, 220, 22);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Date de la séance");
        jLabel5.setToolTipText("");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(0, 40, 220, 22);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Salle");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(0, 100, 220, 22);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(60, 250, 220, 170);

        jPanel2.setBackground(new java.awt.Color(255, 204, 51));
        jPanel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel2MouseExited(evt);
            }
        });
        jPanel2.setLayout(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Heure de la séance");
        jLabel7.setToolTipText("");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(0, 10, 220, 22);

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Date de la séance");
        jLabel8.setToolTipText("");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(0, 40, 220, 22);

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Salle");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(0, 100, 220, 22);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(340, 250, 220, 170);

        jPanel3.setBackground(new java.awt.Color(255, 204, 51));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanel3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanel3MouseExited(evt);
            }
        });
        jPanel3.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Heure de la séance");
        jLabel10.setToolTipText("");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(0, 10, 220, 22);

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Date de la séance");
        jLabel11.setToolTipText("");
        jPanel3.add(jLabel11);
        jLabel11.setBounds(0, 40, 220, 22);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("Salle");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(0, 100, 220, 22);

        getContentPane().add(jPanel3);
        jPanel3.setBounds(620, 250, 220, 170);

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 33)); // NOI18N
        jLabel14.setText("nouveau prix");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(270, 430, 250, 60);

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel13.setText("Prix : 8€");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(30, 450, 100, 22);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 0, 0));
        jLabel15.setText("- réduction% = ");
        getContentPane().add(jLabel15);
        jLabel15.setBounds(130, 450, 190, 22);

        jButton1.setBackground(new java.awt.Color(255, 204, 51));
        jButton1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton1.setText("Payer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(700, 499, 90, 40);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(110, 500, 100, 40);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void InitSeances() {
        toutesLesSeances = new Liste<>();
        Calendar s1 = Calendar.getInstance();
        Calendar s2 = Calendar.getInstance();
        Calendar s3 = Calendar.getInstance();
        s1.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), 10, 0);
        s2.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), 15, 0);
        s3.set(LocalDate.now().getYear(), LocalDate.now().getMonthValue() - 1, LocalDate.now().getDayOfMonth(), 20, 0);
        toutesLesSeances.Ajouter(new Seance(s1));
        toutesLesSeances.Ajouter(new Seance(s2));
        toutesLesSeances.Ajouter(new Seance(s3));

        DecimalFormat df = new DecimalFormat("00");

        jLabel4.setText(toutesLesSeances.Get(0).getTime());
        jLabel5.setText(toutesLesSeances.Get(0).getDate());
        jLabel6.setText("Salle n° " + theMovie.getSalle());

        jLabel7.setText(toutesLesSeances.Get(1).getTime());
        jLabel8.setText(toutesLesSeances.Get(1).getDate());
        jLabel9.setText("Salle n° " + theMovie.getSalle());

        jLabel10.setText(toutesLesSeances.Get(2).getTime());
        jLabel11.setText(toutesLesSeances.Get(2).getDate());
        jLabel12.setText("Salle n° " + theMovie.getSalle());
    }

    public Seance getSeance() {
        return maSeance;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (seanceSelected == true) {
            Paiement payer = new Paiement(this,User);
            payer.setVisible(true);
            this.setVisible(false);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jPanel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseEntered
        jPanel1.setBorder(BorderFactory.createLoweredBevelBorder());
    }//GEN-LAST:event_jPanel1MouseEntered

    private void jPanel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseExited
        jPanel1.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_jPanel1MouseExited

    private void jPanel2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseEntered
        jPanel2.setBorder(BorderFactory.createLoweredBevelBorder());
    }//GEN-LAST:event_jPanel2MouseEntered

    private void jPanel2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseExited
        jPanel2.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_jPanel2MouseExited

    private void jPanel3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseEntered
        jPanel3.setBorder(BorderFactory.createLoweredBevelBorder());
    }//GEN-LAST:event_jPanel3MouseEntered

    private void jPanel3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseExited
        jPanel3.setBorder(BorderFactory.createEmptyBorder());
    }//GEN-LAST:event_jPanel3MouseExited

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jPanel1.setBackground(new Color(255, 100, 54));
        jPanel2.setBackground(new Color(255, 204, 54));
        jPanel3.setBackground(new Color(255, 204, 54));

        maSeance = toutesLesSeances.Get(0);

        seanceSelected = true;
    }//GEN-LAST:event_jPanel1MouseClicked

    private void jPanel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel2MouseClicked
        jPanel1.setBackground(new Color(255, 204, 54));
        jPanel2.setBackground(new Color(255, 100, 54));
        jPanel3.setBackground(new Color(255, 204, 54));

        maSeance = toutesLesSeances.Get(1);

        seanceSelected = true;
    }//GEN-LAST:event_jPanel2MouseClicked

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        jPanel1.setBackground(new Color(255, 204, 54));
        jPanel2.setBackground(new Color(255, 204, 54));
        jPanel3.setBackground(new Color(255, 100, 54));

        maSeance = toutesLesSeances.Get(2);

        seanceSelected = true;
    }//GEN-LAST:event_jPanel3MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        Film_Infos window4 = new Film_Infos(theMovie, User);
        window4.setVisible(true);
        this.dispose();


    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Reserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reserver.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reserver().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}