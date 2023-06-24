/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Vue;

import Contrôleur.*;
import Modele.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JList;

/**
 * Fenêtre dialog permettant d'ajouter des acteurs et réalisateurs à un film
 * @author Arthur, Kevin, Lucas, Amna
 * @deprecated 
 */
public class Choisir_actreal extends javax.swing.JDialog {

private Ajout_Film fen;
private Connexion connection;
private MetierDuCinema metier;

    /**
     * Creates new form Choisir_actreal
     */
    public Choisir_actreal(Ajout_Film parent, MetierDuCinema met) {
        super(parent, true);
        initComponents();
        fen=parent;
metier=met;
        try
        {
            connection = new Connexion("projetpoojava", "root", "");
            ArrayList<ArrayList<String>> ListBuffer = connection.executeRequete("SELECT * FROM personne;");

            // Creation de la liste d'objet de type films
            for (ArrayList<String> row : ListBuffer) {
                jComboBox1.addItem(row.get(0)+ " " + row.get(1));
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }

        if(metier.equals(MetierDuCinema.realisateur)) {
            jList1.setModel(fen.getFilm().getRealisateur().getModele());
        } else {
            jList1.setModel(fen.getFilm().getActeurs().getModele());
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

        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 320));
        setResizable(false);
        setSize(new java.awt.Dimension(400, 320));
        getContentPane().setLayout(null);

        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(51, 211, 252, 26);

        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(51, 21, 298, 190);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(330, 258, 53, 29);

        jButton2.setText("Annuler");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(17, 258, 89, 29);

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(300, 210, 50, 29);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(metier.equals(MetierDuCinema.realisateur)) {
            fen.getFilm().getRealisateur().Ajouter((String)jComboBox1.getSelectedItem());
            jList1.setModel(fen.getFilm().getRealisateur().getModele());
        } else {
            fen.getFilm().getActeurs().Ajouter((String)jComboBox1.getSelectedItem());
            jList1.setModel(fen.getFilm().getActeurs().getModele());
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Ajout_Film window = new Ajout_Film(fen.getFilm());
        window.setVisible(true);
        fen.setVisible(false);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
/*    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 //       try {
 //           for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
 //               if ("Nimbus".equals(info.getName())) {
 //                   javax.swing.UIManager.setLookAndFeel(info.getClassName());
 //                   break;
 //               }
 //           }
 //       } catch (ClassNotFoundException ex) {
 //           java.util.logging.Logger.getLogger(Choisir_actreal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
 //       } catch (InstantiationException ex) {
 //           java.util.logging.Logger.getLogger(Choisir_actreal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
 //       } catch (IllegalAccessException ex) {
 //           java.util.logging.Logger.getLogger(Choisir_actreal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
 //       } catch (javax.swing.UnsupportedLookAndFeelException ex) {
 //           java.util.logging.Logger.getLogger(Choisir_actreal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
 //       }
        //</editor-fold>

        /* Create and display the dialog */
 /*       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Choisir_actreal dialog = new Choisir_actreal(new Ajout_Film(),);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
