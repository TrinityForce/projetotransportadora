package br.com.bomtransporte.formulario;

import br.com.bomtransporte.modelo.FuncionarioSingleton;

/**
 *
 * @author JhonattanSouza_
 */
public class FormAdministrador extends javax.swing.JFrame {

    /**
     *
     */
    public FormAdministrador() {
        initComponents();
        jLB_Nome.setText(FuncionarioSingleton.getFuncionario().getNome());
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBT_Cliente = new javax.swing.JButton();
        jBT_EnviarPedidos = new javax.swing.JButton();
        jBT_Peso = new javax.swing.JButton();
        jBT_Relatorios = new javax.swing.JButton();
        jBT_AlterarSenha = new javax.swing.JButton();
        jBT_Funcionario = new javax.swing.JButton();
        jBT_Sair = new javax.swing.JButton();
        jBT_Logout = new javax.swing.JButton();
        jBT_PesqCliente = new javax.swing.JButton();
        jBT_Rota = new javax.swing.JButton();
        jLB_Nome = new javax.swing.JLabel();
        jLB_BemVindo = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Cliente.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Cliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/cliente-icon.png"))); // NOI18N
        jBT_Cliente.setText("Cadastro de Clientes");
        jBT_Cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 90, 290, 100));

        jBT_EnviarPedidos.setBackground(new java.awt.Color(0, 0, 0));
        jBT_EnviarPedidos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_EnviarPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/transito-icon.png"))); // NOI18N
        jBT_EnviarPedidos.setText("<html>Enviar<br/>Pedidos</html>");
        jBT_EnviarPedidos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_EnviarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_EnviarPedidosActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_EnviarPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 190, 170, 100));

        jBT_Peso.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Peso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Peso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/peso-icon.png"))); // NOI18N
        jBT_Peso.setText("<html>Gerenciar<br>Peso/<br/>Volume</html>");
        jBT_Peso.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Peso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PesoActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 290, 170, 100));

        jBT_Relatorios.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relatorios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Relatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/relat-icon.png"))); // NOI18N
        jBT_Relatorios.setText("<html>Gerar<br/>Relatórios</html>");
        jBT_Relatorios.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_RelatoriosActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 190, 250, 100));

        jBT_AlterarSenha.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/senha-icon.png"))); // NOI18N
        jBT_AlterarSenha.setText("<html>Alterar<br/>Senha</html>");
        jBT_AlterarSenha.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_AlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_AlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 170, 100));

        jBT_Funcionario.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Funcionario.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Funcionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/funcionario-icon.png"))); // NOI18N
        jBT_Funcionario.setText("Administrar Funcionários");
        jBT_Funcionario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Funcionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_FuncionarioActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Funcionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 300, 100));

        jBT_Sair.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Sair.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBT_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/sair-icon.png"))); // NOI18N
        jBT_Sair.setText("Sair do Sistema");
        jBT_Sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SairActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 440, 180, -1));

        jBT_Logout.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Logout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBT_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/logout-icon.png"))); // NOI18N
        jBT_Logout.setText("Logout");
        jBT_Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_LogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 440, 130, -1));

        jBT_PesqCliente.setBackground(new java.awt.Color(0, 0, 0));
        jBT_PesqCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_PesqCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/pesquisar-icon.png"))); // NOI18N
        jBT_PesqCliente.setText("<html>Pesquisar Clientes/<br/>Cadastrar Pedidos</html>");
        jBT_PesqCliente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_PesqCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PesqClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_PesqCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 290, 250, 100));

        jBT_Rota.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Rota.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Rota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/money-icon.png"))); // NOI18N
        jBT_Rota.setText("<html>Administrar Rotas e <br>Valores</html>");
        jBT_Rota.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Rota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_RotaActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Rota, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 290, 170, 100));

        jLB_Nome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Nome.setText("NAME_PLACEHOLDER");
        getContentPane().add(jLB_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 220, 40));

        jLB_BemVindo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_BemVindo.setText("Bem-Vindo(a)");
        getContentPane().add(jLB_BemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, -1, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/menu-principal-background.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_FuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_FuncionarioActionPerformed
        FormCadastrarFuncionario formFuncionario = new FormCadastrarFuncionario();
        formFuncionario.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_FuncionarioActionPerformed

    private void jBT_AlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarSenhaActionPerformed
        FormAlterarSenha formSenha = new FormAlterarSenha();
        formSenha.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_AlterarSenhaActionPerformed

    private void jBT_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ClienteActionPerformed
        FormCadastrarCliente formCliente = new FormCadastrarCliente();
        formCliente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_ClienteActionPerformed

    private void jBT_RotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_RotaActionPerformed
        FormCadastrarRota fcr = new FormCadastrarRota();
        fcr.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_RotaActionPerformed

    private void jBT_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBT_SairActionPerformed

    private void jBT_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_LogoutActionPerformed
        FormLogin formLogin = new FormLogin();
        formLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_LogoutActionPerformed

    private void jBT_PesqClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_PesqClienteActionPerformed
        FormClientePedido formCliente = new FormClientePedido();
        formCliente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_PesqClienteActionPerformed

    private void jBT_RelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_RelatoriosActionPerformed
        FormDashboardRelat formDash = new FormDashboardRelat();
        formDash.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_RelatoriosActionPerformed

    private void jBT_EnviarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_EnviarPedidosActionPerformed
        FormPedidos formPedidos = new FormPedidos();
        formPedidos.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_EnviarPedidosActionPerformed

    private void jBT_PesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_PesoActionPerformed
        FormManterPeso formPeso = new FormManterPeso();
        formPeso.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_PesoActionPerformed

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
            java.util.logging.Logger.getLogger(FormAdministrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormAdministrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_AlterarSenha;
    private javax.swing.JButton jBT_Cliente;
    private javax.swing.JButton jBT_EnviarPedidos;
    private javax.swing.JButton jBT_Funcionario;
    private javax.swing.JButton jBT_Logout;
    private javax.swing.JButton jBT_Peso;
    private javax.swing.JButton jBT_PesqCliente;
    private javax.swing.JButton jBT_Relatorios;
    private javax.swing.JButton jBT_Rota;
    private javax.swing.JButton jBT_Sair;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_BemVindo;
    private javax.swing.JLabel jLB_Nome;
    // End of variables declaration//GEN-END:variables
}
