package br.com.bomtransporte.formulario;

import br.com.bomtransporte.modelo.FuncionarioSingleton;


/**
 *
 * @author JhonattanSouza_
 */
public class FormFuncionario extends javax.swing.JFrame {

    /**
     * Creates new form FormFuncionario
     */
    public FormFuncionario() {
        initComponents();
        jLB_Nome.setText(FuncionarioSingleton.getFuncionario().getNome());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLB_BemVindo = new javax.swing.JLabel();
        jLB_Nome = new javax.swing.JLabel();
        jBT_Cliente = new javax.swing.JButton();
        jBT_AlterarSenha = new javax.swing.JButton();
        jBT_Pedido = new javax.swing.JButton();
        jBT_Sair = new javax.swing.JButton();
        jBT_Logout = new javax.swing.JButton();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLB_BemVindo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_BemVindo.setText("Bem-Vindo(a)");
        getContentPane().add(jLB_BemVindo, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, 40));

        jLB_Nome.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Nome.setText("NAME_PLACEHOLDER");
        getContentPane().add(jLB_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, -1, 40));

        jBT_Cliente.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Cliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Cliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/cliente-icon.png"))); // NOI18N
        jBT_Cliente.setText("Cadastro de Clientes");
        jBT_Cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ClienteActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 120, 290, 100));

        jBT_AlterarSenha.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarSenha.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/senha-icon.png"))); // NOI18N
        jBT_AlterarSenha.setText("Alterar Senha");
        jBT_AlterarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarSenhaActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_AlterarSenha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 290, 100));

        jBT_Pedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Pedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Pedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/pesquisar-icon.png"))); // NOI18N
        jBT_Pedido.setText("<html>Pesquisar Clientes/<br/>Cadastrar Pedidos</html>");
        jBT_Pedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PedidoActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Pedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 120, 290, 100));

        jBT_Sair.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Sair.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBT_Sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/sair-icon.png"))); // NOI18N
        jBT_Sair.setText("Sair do Sistema");
        jBT_Sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SairActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Sair, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 490, 180, -1));

        jBT_Logout.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Logout.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBT_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/logout-icon.png"))); // NOI18N
        jBT_Logout.setText("Logout");
        jBT_Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_LogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 490, 130, -1));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/menu-principal-background.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_ClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ClienteActionPerformed
        FormCadastrarCliente formCliente = new FormCadastrarCliente();
        formCliente.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_ClienteActionPerformed

    private void jBT_AlterarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarSenhaActionPerformed
        FormAlterarSenha formSenha = new FormAlterarSenha();
        formSenha.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_AlterarSenhaActionPerformed

    private void jBT_PedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_PedidoActionPerformed
        FormClientePedido formCli = new FormClientePedido();
        formCli.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_PedidoActionPerformed

    private void jBT_SairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jBT_SairActionPerformed

    private void jBT_LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_LogoutActionPerformed
        FormLogin formLogin = new FormLogin();
        formLogin.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_LogoutActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormFuncionario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_AlterarSenha;
    private javax.swing.JButton jBT_Cliente;
    private javax.swing.JButton jBT_Logout;
    private javax.swing.JButton jBT_Pedido;
    private javax.swing.JButton jBT_Sair;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_BemVindo;
    private javax.swing.JLabel jLB_Nome;
    // End of variables declaration//GEN-END:variables
}
