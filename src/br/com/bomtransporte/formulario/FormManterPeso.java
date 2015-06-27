package br.com.bomtransporte.formulario;

import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;

/**
 *
 * @author JhonattanSouza_
 */
public class FormManterPeso extends javax.swing.JFrame {

    /**
     * Creates new form FormManterPeso
     */
    public FormManterPeso() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPN_Background = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLB_Fechar5 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextField1.setText("jTextField1");
        jPN_Background.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 440, 180, 30));

        jTextField2.setText("jTextField1");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPN_Background.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 180, 30));

        jTextField3.setText("jTextField1");
        jPN_Background.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 280, 180, 30));

        jTextField4.setText("jTextField1");
        jPN_Background.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 330, 180, 30));

        jTextField6.setText("jTextField1");
        jPN_Background.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 380, 180, 30));

        jTextField5.setText("jTextField1");
        jPN_Background.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 140, 180, 30));

        jTextField7.setText("jTextField1");
        jPN_Background.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 180, 30));

        jLabel1.setBackground(new java.awt.Color(0, 204, 204));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("VALORES");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPN_Background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 180, -1));

        jLabel9.setBackground(new java.awt.Color(0, 204, 204));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setText("FAIXA DE PESO");
        jLabel9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jLabel9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPN_Background.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, 180, -1));

        jLabel2.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel2.setText("101KG ATÉ 200KG");
        jPN_Background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel3.setText("201KG ATÉ 300KG");
        jPN_Background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 290, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel4.setText("ACIMA DE 501KG");
        jPN_Background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 440, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel5.setText("0KG ATÉ 50KG");
        jPN_Background.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel6.setText("51KG ATÉ 100KG");
        jPN_Background.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel7.setText("301KG ATÉ 400KG");
        jPN_Background.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, -1, -1));

        jLabel8.setFont(new java.awt.Font("Segoe WP", 0, 18)); // NOI18N
        jLabel8.setText("304KG ATÉ 500KG");
        jPN_Background.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, -1, -1));

        jLB_Fechar5.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar5.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar5.setText("X");
        jLB_Fechar5.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLB_Fechar5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_Fechar5MouseReleased(evt);
            }
        });
        jPN_Background.add(jLB_Fechar5, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/peso-bg.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_Fechar5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar5MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar5MouseReleased

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Windows look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormManterPeso.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormManterPeso().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
