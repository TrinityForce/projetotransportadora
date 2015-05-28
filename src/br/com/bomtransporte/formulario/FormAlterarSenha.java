package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.FuncionarioDao;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import javax.swing.JOptionPane;

/**
 *
 * @author JhonattanSouza_
 */
public class FormAlterarSenha extends javax.swing.JFrame {

    /**
     * Creates new form FormAlterarSenha
     */
    public FormAlterarSenha() {
        initComponents();
        jLB_Erro.setVisible(false);
    }

    /**
     * Verifica se o campo está devidamente preenchido.
     *
     * @param campo Que será verificado
     * @return
     */
    private boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

    private void exibirErro(javax.swing.JLabel label, String mensagem) {
        label.setVisible(true);
        label.setText(mensagem);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBT_Alterar = new javax.swing.JButton();
        jTF_ConfirmarSenha = new javax.swing.JPasswordField();
        jTF_SenhaNova = new javax.swing.JPasswordField();
        jTF_SenhaAtual = new javax.swing.JPasswordField();
        jLB_Erro = new javax.swing.JLabel();
        jLB_Fechar3 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 400));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Alterar.setBorder(null);
        jBT_Alterar.setBorderPainted(false);
        jBT_Alterar.setContentAreaFilled(false);
        jBT_Alterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Alterar.setFocusPainted(false);
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 340, 40));

        jTF_ConfirmarSenha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_ConfirmarSenha.setBorder(null);
        jTF_ConfirmarSenha.setOpaque(false);
        getContentPane().add(jTF_ConfirmarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 210, 250, 20));

        jTF_SenhaNova.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_SenhaNova.setBorder(null);
        jTF_SenhaNova.setOpaque(false);
        getContentPane().add(jTF_SenhaNova, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 170, 250, 30));

        jTF_SenhaAtual.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_SenhaAtual.setBorder(null);
        jTF_SenhaAtual.setOpaque(false);
        getContentPane().add(jTF_SenhaAtual, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 250, 30));

        jLB_Erro.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLB_Erro.setForeground(new java.awt.Color(204, 0, 0));
        jLB_Erro.setText("ERROR_PLACEHOLDER");
        getContentPane().add(jLB_Erro, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 250, 270, -1));

        jLB_Fechar3.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar3.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLB_Fechar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_Fechar3MouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Fechar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 40, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterar-senha-bg.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
        try {
            FuncionarioDao funcionarioDao = new FuncionarioDao();
            String senhaAtual = FuncionarioRN.criptografarMd5(jTF_SenhaAtual.getText());
            String senhaNova = jTF_SenhaNova.getText();
            String confirmarSenha = jTF_ConfirmarSenha.getText();
            if (senhaAtual != null && senhaAtual.trim().length() > 0) {
                if (senhaNova != null && senhaNova.trim().length() > 0) {
                    if (confirmarSenha != null && confirmarSenha.trim().length() > 0) {
                        if (senhaNova.equals(confirmarSenha)) {
                            if (senhaAtual.equals(FuncionarioSingleton.getFuncionario().getUsuario().getSenha())) {
                                Integer opt = JOptionPane.showConfirmDialog(this, "TEM CERTEZA DE QUE DESEJA ALTERAR A SENHA?");
                                if (opt == JOptionPane.YES_OPTION) {
                                    funcionarioDao.alterarSenha(FuncionarioSingleton.getFuncionario(), senhaNova, false);
                                    JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
                                    FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), FormAlterarSenha.this);
                                    dispose();
                                } else if (opt == JOptionPane.CANCEL_OPTION) {
                                    dispose();
                                }
                            } else {
                                exibirErro(jLB_Erro, "Senha atual inválida.");
                            }
                        } else {
                            exibirErro(jLB_Erro, "Senhas não coincidem.");
                        }
                    } else {
                        exibirErro(jLB_Erro, "Campo REPITA SENHA não pode estar em branco.");
                    }
                } else {
                    exibirErro(jLB_Erro, "Campo NOVA SENHA não pode estar em branco.");
                }
            } else {
                exibirErro(jLB_Erro, "Campo SENHA ATUAL não pode estar em branco.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro inesperado. Por favor tente novamente." + ex.getMessage());
        }
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jLB_Fechar3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar3MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar3MouseReleased

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAlterarSenha.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormAlterarSenha().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Erro;
    private javax.swing.JLabel jLB_Fechar3;
    private javax.swing.JPasswordField jTF_ConfirmarSenha;
    private javax.swing.JPasswordField jTF_SenhaAtual;
    private javax.swing.JPasswordField jTF_SenhaNova;
    // End of variables declaration//GEN-END:variables
}
