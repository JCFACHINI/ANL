/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package analisenlinear;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

/**
 *
 * @author jc
 */
public class frmAnaliseNLinear extends javax.swing.JDialog {

    /**
     * Creates new form frmAnaliseNLinear
     */
    Sox sox;
    Utils util;
    public frmAnaliseNLinear(java.awt.Frame parent, boolean modal, Sox sox) {
        super(parent, modal);
        initComponents();
        setParametros(false);
        //valor inicial, mínimo, máximo, incremento/decremento...
        numN.setModel(new SpinnerNumberModel(1, 1, 100, 1)); 
        numTal.setModel(new SpinnerNumberModel(1, 1, 100, 1)); 
        util = new Utils();
        this.sox = sox;
        pnlTrecho.setBorder(new TitledBorder("Selecione o trecho da amostra em que será"
                + " realizada a análise não linear (em segundos)"));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTrecho = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        ttbIni = new java.awt.TextField();
        label2 = new java.awt.Label();
        ttbFim = new java.awt.TextField();
        btnGerar = new javax.swing.JButton();
        pnlParametros = new javax.swing.JPanel();
        label3 = new java.awt.Label();
        label4 = new java.awt.Label();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        numN = new javax.swing.JSpinner();
        numTal = new javax.swing.JSpinner();
        chkParametros = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlTrecho.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlTrecho.setLayout(null);

        label1.setText("A partir de");
        pnlTrecho.add(label1);
        label1.setBounds(20, 20, 70, 19);
        pnlTrecho.add(ttbIni);
        ttbIni.setBounds(90, 20, 60, 19);

        label2.setName(""); // NOI18N
        label2.setText("Pegar");
        pnlTrecho.add(label2);
        label2.setBounds(170, 20, 40, 19);
        pnlTrecho.add(ttbFim);
        ttbFim.setBounds(210, 20, 60, 19);

        getContentPane().add(pnlTrecho);
        pnlTrecho.setBounds(10, 20, 750, 90);

        btnGerar.setText("Gerar");
        btnGerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGerar);
        btnGerar.setBounds(660, 350, 110, 70);

        pnlParametros.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlParametros.setLayout(null);

        label3.setName(""); // NOI18N
        label3.setText("Tempo de atraso (tal)");
        pnlParametros.add(label3);
        label3.setBounds(20, 20, 140, 19);

        label4.setName(""); // NOI18N
        label4.setText("Dimensão de imersão (n)");
        pnlParametros.add(label4);
        label4.setBounds(270, 20, 160, 19);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Caso não queria defini-los o programa os calculará automaticamente.");
        pnlParametros.add(jLabel1);
        jLabel1.setBounds(20, 80, 630, 30);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Esses parâmetros serão utilizados na geração do gráfico e influenciam em sua qualidade.");
        pnlParametros.add(jLabel2);
        jLabel2.setBounds(20, 50, 720, 30);
        pnlParametros.add(numN);
        numN.setBounds(430, 10, 50, 30);
        pnlParametros.add(numTal);
        numTal.setBounds(160, 10, 50, 30);

        getContentPane().add(pnlParametros);
        pnlParametros.setBounds(10, 160, 750, 120);

        chkParametros.setText("Deseja definir os parâmetros manualmente?");
        chkParametros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkParametrosActionPerformed(evt);
            }
        });
        getContentPane().add(chkParametros);
        chkParametros.setBounds(10, 130, 340, 24);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-790)/2, (screenSize.height-464)/2, 790, 464);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerarActionPerformed
        boolean result = sox.exe(Double.parseDouble(ttbIni.getText()),
                Double.parseDouble(ttbFim.getText()), false);
        int tal , n;
        tal = n = -1;
        if(numN.isEnabled())
            n = Integer.parseInt(numN.getValue().toString());
        if(numTal.isEnabled())
            tal = Integer.parseInt(numTal.getValue().toString());
        Tisean t = new Tisean(tal, n);
        if (result) {
            Plot p;
            try {
                p = new Plot(util.isWindows());
                p.exe("", "3D", t.getTal(), t.getN());
            } catch (IOException ex) {
                Logger.getLogger(frmAnaliseNLinear.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnGerarActionPerformed

    private void setParametros(boolean isTrue){
        if(isTrue){
            pnlParametros.setEnabled(true);
            numN.setEnabled(true);
            numTal.setEnabled(true);
        }else{
            pnlParametros.setEnabled(false);
            numN.setEnabled(false);
            numTal.setEnabled(false);
        } 
    }
private void chkParametrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkParametrosActionPerformed
    if (chkParametros.isSelected())
        setParametros(true);
    else 
        setParametros(false);
}//GEN-LAST:event_chkParametrosActionPerformed

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
            java.util.logging.Logger.getLogger(frmAnaliseNLinear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmAnaliseNLinear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmAnaliseNLinear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmAnaliseNLinear.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frmAnaliseNLinear dialog = new frmAnaliseNLinear(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGerar;
    private javax.swing.JCheckBox chkParametros;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label3;
    private java.awt.Label label4;
    private javax.swing.JSpinner numN;
    private javax.swing.JSpinner numTal;
    private javax.swing.JPanel pnlParametros;
    private javax.swing.JPanel pnlTrecho;
    private java.awt.TextField ttbFim;
    private java.awt.TextField ttbIni;
    // End of variables declaration//GEN-END:variables
}
