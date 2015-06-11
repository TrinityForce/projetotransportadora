package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.ModeloTabela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author JhonattanSouza_
 */
public class FormClientePedido extends javax.swing.JFrame {

    private ClienteDao clienteDao;
    private Cliente clienteSelecionado;
    public static Integer idCliente;

    public FormClientePedido() {
        initComponents();
        preencherTabela();
        desabilitarBotao(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);
    }

    private void habilitarBotao(JButton bt, JButton bt2, JButton bt3) {
        bt.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt, JButton bt2, JButton bt3) {
        bt.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
    }

    private void preencherTabela() {

        ArrayList dados = new ArrayList();

        String[] colunas = new String[]{"ID", "NOME", "DATA CADASTRO", "CPF", "TELEFONE"};

        try {
            clienteDao = new ClienteDao();
            final List<Object> listaCliente = clienteDao.consultarCliente(jTF_Consulta.getText());

            //Verifica se a lista está preenchida
            if (listaCliente != null && listaCliente.size() > 0) {
                //Percorre a lista
                listaCliente.forEach(clienteAtual -> {
                    Cliente cliente = (Cliente) clienteAtual;
                    dados.add(new Object[]{cliente.getIdCliente(), cliente.getNome(), cliente.getDataCadastro(), cliente.getCpf(), cliente.getTelefone()});
                });
            }

            ModeloTabela modTabela = new ModeloTabela(dados, colunas);
            jTableClientes.setModel(modTabela);
            jTableClientes.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableClientes.getColumnModel().getColumn(0).setResizable(false);
            jTableClientes.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTableClientes.getColumnModel().getColumn(1).setResizable(false);
            jTableClientes.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableClientes.getColumnModel().getColumn(2).setResizable(false);
            jTableClientes.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableClientes.getColumnModel().getColumn(3).setResizable(false);
            jTableClientes.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTableClientes.getColumnModel().getColumn(4).setResizable(false);

            jTableClientes.getTableHeader().setReorderingAllowed(false);
            jTableClientes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jTableClientes.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        List<Object> lista = clienteDao.listar();
                        clienteSelecionado = (Cliente) lista.
                                get(jTableClientes.convertRowIndexToModel(jTableClientes.getSelectedRow()));
                        idCliente = clienteSelecionado.getIdCliente();

                        habilitarBotao(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);

                    } catch (SQLException sqlex) {
                        JOptionPane.showMessageDialog(FormClientePedido.this, "Erro no Banco de Dados: " + sqlex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormClientePedido.this, "Erro genérico1: " + ex.getMessage());
                    }
                }
            });

        } catch (SQLException sqle) {
            JOptionPane.showMessageDialog(this, "Erro no Banco de Dados: " + sqle.getMessage());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro genérico2: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTB_CliPedido = new javax.swing.JTabbedPane();
        jPN_PesquisarCliente = new javax.swing.JPanel();
        jSC_Tabela = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jBT_Excluir = new javax.swing.JButton();
        jBT_CadastrarPedido = new javax.swing.JButton();
        jBT_Alterar = new javax.swing.JButton();
        jBT_Pesquisar = new javax.swing.JButton();
        jTF_Consulta = new javax.swing.JTextField();
        jPN_CadastrarPedido = new javax.swing.JPanel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_PesquisarCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPN_PesquisarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jSC_Tabela.setViewportView(jTableClientes);

        jPN_PesquisarCliente.add(jSC_Tabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 171, 670, 260));

        jBT_Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Excluir.setText("Excluir Cliente");
        jBT_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ExcluirActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 210, 70));

        jBT_CadastrarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_CadastrarPedido.setText("Cadastrar Pedido");
        jBT_CadastrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_CadastrarPedidoActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_CadastrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 230, 70));

        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setText("Alterar Cliente");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 10, 230, 70));

        jBT_Pesquisar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Pesquisar.setText("PESQUISAR");
        jBT_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PesquisarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 200, 60));

        jTF_Consulta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPN_PesquisarCliente.add(jTF_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 340, 60));

        jTB_CliPedido.addTab("Pesquisar Cliente", null, jPN_PesquisarCliente, "Pesquisar cliente e selecionar uma ação.");

        javax.swing.GroupLayout jPN_CadastrarPedidoLayout = new javax.swing.GroupLayout(jPN_CadastrarPedido);
        jPN_CadastrarPedido.setLayout(jPN_CadastrarPedidoLayout);
        jPN_CadastrarPedidoLayout.setHorizontalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPN_CadastrarPedidoLayout.setVerticalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );

        jTB_CliPedido.addTab("Cadastrar Pedido", jPN_CadastrarPedido);

        getContentPane().add(jTB_CliPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 700, 470));
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ExcluirActionPerformed
        try {
            Integer opt = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja EXCLUIR este cliente do sistema? \nEsta alteração não poderá ser revertida.", "EXCLUIR?", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(idCliente);

                clienteDao.excluir(cliente);

                preencherTabela();
                JOptionPane.showMessageDialog(this, "Cliente excluído com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_jBT_ExcluirActionPerformed

    private void jBT_CadastrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_CadastrarPedidoActionPerformed
//        jTB_CliPedido.setEnabledAt(1, true);
  //      jTB_CliPedido.setEnabledAt(0, false);
    //    jTB_CliPedido.setSelectedIndex(1);
        
        FormCadProduto formCard = new FormCadProduto();
       formCard.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_CadastrarPedidoActionPerformed

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
//        FormAlterarCliente frmAlterar = new FormAlterarCliente();
//        frmAlterar.setVisible(true);
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jBT_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_PesquisarActionPerformed
        preencherTabela();
        desabilitarBotao(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);
    }//GEN-LAST:event_jBT_PesquisarActionPerformed

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
            java.util.logging.Logger.getLogger(FormClientePedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormClientePedido().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_CadastrarPedido;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_Pesquisar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JPanel jPN_CadastrarPedido;
    private javax.swing.JPanel jPN_PesquisarCliente;
    private javax.swing.JScrollPane jSC_Tabela;
    private javax.swing.JTabbedPane jTB_CliPedido;
    private javax.swing.JTextField jTF_Consulta;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
