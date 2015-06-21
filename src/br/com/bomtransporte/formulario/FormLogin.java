package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.FuncionarioDao;
import br.com.bomtransporte.modelo.Funcionario;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import javax.swing.JOptionPane;

/**
 *
 * @author JhonattanSouza_
 */
public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        initComponents();
        this.getRootPane().setDefaultButton(jBT_Autenticar);
        ocultarTudo();
    }

    /**
     * Exibe as mensagens de erro da tela de LOGIN.
     *
     * @param label Recebe a label.
     * @param mensagem Recebe a mensagem que essa label exibirá.
     */
    private void exibirErro(javax.swing.JLabel label, String mensagem) {
        label.setVisible(true);
        label.setText(mensagem);
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

    /**
     * Retira os 2 erros restantes, caso 1 campo esteja errado.
     *
     * @param label
     * @param label2
     */
    private void ocultarErros(javax.swing.JLabel label, javax.swing.JLabel label2) {
        label.setVisible(false);
        label2.setVisible(false);
    }

    //Retira todos os erros da tela.
    private void ocultarTudo() {
        jLB_ErroUsuario.setVisible(false);
        jLB_ErroSenha.setVisible(false);
        jLB_ErroAutenticacao.setVisible(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBT_Autenticar = new javax.swing.JButton();
        jTF_Senha = new javax.swing.JPasswordField();
        jTF_Usuario = new javax.swing.JTextField();
        jLB_EsqueceuSenha = new javax.swing.JLabel();
        jLB_ErroAutenticacao = new javax.swing.JLabel();
        jLB_ErroSenha = new javax.swing.JLabel();
        jLB_ErroUsuario = new javax.swing.JLabel();
        jLB_Minimizar = new javax.swing.JLabel();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(750, 400));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Autenticar.setBorder(null);
        jBT_Autenticar.setBorderPainted(false);
        jBT_Autenticar.setContentAreaFilled(false);
        jBT_Autenticar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Autenticar.setFocusPainted(false);
        jBT_Autenticar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AutenticarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Autenticar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 340, 40));

        jTF_Senha.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_Senha.setBorder(null);
        jTF_Senha.setOpaque(false);
        getContentPane().add(jTF_Senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 250, 30));

        jTF_Usuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTF_Usuario.setBorder(null);
        jTF_Usuario.setOpaque(false);
        getContentPane().add(jTF_Usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 250, 30));

        jLB_EsqueceuSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLB_EsqueceuSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_EsqueceuSenhaMouseReleased(evt);
            }
        });
        getContentPane().add(jLB_EsqueceuSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 240, 150, 20));

        jLB_ErroAutenticacao.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLB_ErroAutenticacao.setForeground(new java.awt.Color(204, 0, 0));
        jLB_ErroAutenticacao.setText("ERROR_PLACEHOLDER");
        getContentPane().add(jLB_ErroAutenticacao, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 150, -1));

        jLB_ErroSenha.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLB_ErroSenha.setForeground(new java.awt.Color(204, 0, 0));
        jLB_ErroSenha.setText("ERROR_PLACEHOLDER");
        getContentPane().add(jLB_ErroSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 150, -1));

        jLB_ErroUsuario.setFont(new java.awt.Font("Arial", 1, 11)); // NOI18N
        jLB_ErroUsuario.setForeground(new java.awt.Color(204, 0, 0));
        jLB_ErroUsuario.setText("ERROR_PLACEHOLDER");
        getContentPane().add(jLB_ErroUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 240, 150, -1));

        jLB_Minimizar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLB_Minimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_MinimizarMouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Minimizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 10, 30, 20));

        jLB_Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLB_Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_FecharMouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 0, 30, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/login-background.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 750, 400));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        System.exit(0);
    }//GEN-LAST:event_jLB_FecharMouseReleased

    private void jLB_MinimizarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_MinimizarMouseReleased
        setExtendedState(ICONIFIED);
    }//GEN-LAST:event_jLB_MinimizarMouseReleased

    private void jBT_AutenticarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AutenticarActionPerformed
        try {
            FuncionarioDao fDao = new FuncionarioDao();
            Funcionario funcionario;

            String usuario = jTF_Usuario.getText();
            String senha = jTF_Senha.getText();

            if (verificarCampo(usuario)) {
                if (verificarCampo(senha)) {
                    funcionario = fDao.autenticar(usuario, senha);
                    if (funcionario != null) {
                        FuncionarioSingleton.setFuncionario(funcionario);
                        if (FuncionarioSingleton.getFuncionario().getUsuario().getAlterarSenha()) {
                            FormAlterarSenha formAS = new FormAlterarSenha();
                            formAS.setVisible(true);
                            dispose();
                        } else {
                            FuncionarioRN.chamarTela(funcionario.getUsuario().getIdPerfil(), this);
                        }
                    } else {
                        ocultarErros(jLB_ErroUsuario, jLB_ErroSenha);
                        exibirErro(jLB_ErroAutenticacao, "Login/Senha inválido(s).");
                    }
                } else {
                    ocultarErros(jLB_ErroUsuario, jLB_ErroAutenticacao);
                    exibirErro(jLB_ErroSenha, "Preencher campo senha.");
                }
            } else {
                ocultarErros(jLB_ErroAutenticacao, jLB_ErroSenha);
                exibirErro(jLB_ErroUsuario, "Preencher campo usuário.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro inesperado, por favor tente novamente.\n" + e.getMessage());
        }
    }//GEN-LAST:event_jBT_AutenticarActionPerformed

    private void jLB_EsqueceuSenhaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_EsqueceuSenhaMouseReleased
        FormRecuperarSenha frs = new FormRecuperarSenha();
        frs.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLB_EsqueceuSenhaMouseReleased

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
            java.util.logging.Logger.getLogger(FormLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Autenticar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_ErroAutenticacao;
    private javax.swing.JLabel jLB_ErroSenha;
    private javax.swing.JLabel jLB_ErroUsuario;
    private javax.swing.JLabel jLB_EsqueceuSenha;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Minimizar;
    private javax.swing.JPasswordField jTF_Senha;
    private javax.swing.JTextField jTF_Usuario;
    // End of variables declaration//GEN-END:variables
}
