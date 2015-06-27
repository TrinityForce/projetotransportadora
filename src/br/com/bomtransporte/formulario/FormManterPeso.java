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
        jLB_Fechar5 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_Fechar5MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar5MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar5MouseReleased

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
    private javax.swing.JPanel jPN_Background;
    // End of variables declaration//GEN-END:variables
}
