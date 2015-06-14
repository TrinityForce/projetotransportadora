package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Pedido;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private PedidoDao pedidoDao;
    private Pedido pedidoSelecionado;
    public static Integer idCliente, ativarAba, idPedido_CliSelecionado,
            idPedidoSelecionado;

    public FormClientePedido() {
        initComponents();
        preencherTabela();
        desabilitarBotao(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);
        desabilitarBotao(jBT_AdicionarCarga);
    }

    private void habilitarBotao(JButton bt, JButton bt2, JButton bt3) {
        bt.setEnabled(true);
        bt2.setEnabled(true);
        bt3.setEnabled(true);
    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt, JButton bt2, JButton bt3) {
        bt.setEnabled(false);
        bt2.setEnabled(false);
        bt3.setEnabled(false);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void preencherTabelaPedido() {

        ArrayList dados = new ArrayList();

        String[] colunas = new String[]{"ID", "PROTOCOLO", "DATA VENDA", "DESCONTO", "STATUS"};

        try {
            pedidoDao = new PedidoDao();
            final List<Object> listaPedido = pedidoDao.listarPedidos(idCliente);

            if (listaPedido != null && listaPedido.size() > 0) {
                listaPedido.forEach(pedidoAtual -> {
                    Pedido pedido = (Pedido) pedidoAtual;
                    dados.add(new Object[]{pedido.getIdPedido(), pedido.getProtocolo(), pedido.getDataVenda(), pedido.getDesconto(), pedido.getStatusPedido()});
                });
            }

            ModeloTabela modTabela = new ModeloTabela(dados, colunas);
            jTB_Pedidos.setModel(modTabela);
            jTB_Pedidos.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTB_Pedidos.getColumnModel().getColumn(0).setResizable(false);
            jTB_Pedidos.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTB_Pedidos.getColumnModel().getColumn(1).setResizable(false);
            jTB_Pedidos.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTB_Pedidos.getColumnModel().getColumn(2).setResizable(false);
            jTB_Pedidos.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTB_Pedidos.getColumnModel().getColumn(3).setResizable(false);
            jTB_Pedidos.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTB_Pedidos.getColumnModel().getColumn(4).setResizable(false);

            jTB_Pedidos.getTableHeader().setReorderingAllowed(false);
            jTB_Pedidos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTB_Pedidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jTB_Pedidos.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    try {
                        List<Object> lista = pedidoDao.listarPedidos(idCliente);
                        pedidoSelecionado = (Pedido) lista.
                                get(jTB_Pedidos.convertRowIndexToModel(jTB_Pedidos.getSelectedRow()));

                        idPedido_CliSelecionado = pedidoSelecionado.getIdPedido_Cli();
                        idPedidoSelecionado = pedidoSelecionado.getIdPedido();
                        System.err.println("pedidocli val " + idPedido_CliSelecionado);
                        if (pedidoSelecionado.getStatusPedido().equals("Em aguardo")) {
                            habilitarBotao(jBT_AdicionarCarga);
                        } else {
                            desabilitarBotao(jBT_AdicionarCarga);
                        }

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
                        System.err.println("idCliente " + idCliente);

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
        jBT_ListarPedidos = new javax.swing.JButton();
        jPN_CadastrarPedido = new javax.swing.JPanel();
        jSP_Pedidos = new javax.swing.JScrollPane();
        jTB_Pedidos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jBT_AdicionarCarga = new javax.swing.JButton();
        jBT_AlterarStatusPedido = new javax.swing.JButton();
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
        jPN_PesquisarCliente.add(jBT_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 150, 70));

        jBT_CadastrarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_CadastrarPedido.setText("Cadastrar Pedido");
        jBT_CadastrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_CadastrarPedidoActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_CadastrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 170, 70));

        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setText("Alterar Cliente");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 150, 70));

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

        jBT_ListarPedidos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_ListarPedidos.setText("Listar Pedidos");
        jBT_ListarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ListarPedidosActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_ListarPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 150, 70));

        jTB_CliPedido.addTab("Pesquisar Cliente", null, jPN_PesquisarCliente, "Pesquisar cliente e selecionar uma ação.");

        jTB_Pedidos.setModel(new javax.swing.table.DefaultTableModel(
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
        jSP_Pedidos.setViewportView(jTB_Pedidos);

        jButton1.setText("Alterar Pedido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jBT_AdicionarCarga.setText("Adicionar Carga");
        jBT_AdicionarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarCargaActionPerformed(evt);
            }
        });

        jBT_AlterarStatusPedido.setText("Alterar Status");
        jBT_AlterarStatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarStatusPedidoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPN_CadastrarPedidoLayout = new javax.swing.GroupLayout(jPN_CadastrarPedido);
        jPN_CadastrarPedido.setLayout(jPN_CadastrarPedidoLayout);
        jPN_CadastrarPedidoLayout.setHorizontalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSP_Pedidos, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE)
                    .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBT_AdicionarCarga, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jBT_AlterarStatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPN_CadastrarPedidoLayout.setVerticalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPN_CadastrarPedidoLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                        .addComponent(jBT_AlterarStatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jBT_AdicionarCarga, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jSP_Pedidos, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
        );

        jTB_CliPedido.addTab("Cadastrar Pedido", jPN_CadastrarPedido);

        getContentPane().add(jTB_CliPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 700, 470));
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
//jhonatan n sabe usar git
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

        FormCadastrarCarga formCad = new FormCadastrarCarga();
        formCad.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_CadastrarPedidoActionPerformed

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
        FormAlterarCliente frmAlterar = new FormAlterarCliente();
        frmAlterar.setVisible(true);
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jBT_PesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_PesquisarActionPerformed
        preencherTabela();
        desabilitarBotao(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);
    }//GEN-LAST:event_jBT_PesquisarActionPerformed

    private void jBT_ListarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ListarPedidosActionPerformed
        jTB_CliPedido.setEnabledAt(1, true);
        jTB_CliPedido.setEnabledAt(0, false);
        jTB_CliPedido.setSelectedIndex(1);
        preencherTabelaPedido();
    }//GEN-LAST:event_jBT_ListarPedidosActionPerformed

    private void jBT_AdicionarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarCargaActionPerformed
        ativarAba = 2;
        FormCadastrarCarga formCarga = new FormCadastrarCarga();
        formCarga.setVisible(true);
    }//GEN-LAST:event_jBT_AdicionarCargaActionPerformed

    private void jBT_AlterarStatusPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarStatusPedidoActionPerformed
        final String[] statusPedido = {"Em aguardo", "Saiu para entrega", "Entregue"};

        String input = (String) JOptionPane.showInputDialog(this, "Escolha uma das opcoes",
                "Mudar status do pedido", JOptionPane.QUESTION_MESSAGE, null,
                statusPedido,
                statusPedido[1]);
        System.err.println("opcao escolhida is %s.\n " + input);

        if (input != null) {
            try {
                pedidoDao.update(pedidoSelecionado.getIdPedido(), input);
                preencherTabelaPedido();
            } catch (Exception ex) {
                Logger.getLogger(FormClientePedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jBT_AlterarStatusPedidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        FormAlterarPedido formAltPedido = new FormAlterarPedido();
        formAltPedido.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JButton jBT_AdicionarCarga;
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_AlterarStatusPedido;
    private javax.swing.JButton jBT_CadastrarPedido;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_ListarPedidos;
    private javax.swing.JButton jBT_Pesquisar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JPanel jPN_CadastrarPedido;
    private javax.swing.JPanel jPN_PesquisarCliente;
    private javax.swing.JScrollPane jSC_Tabela;
    private javax.swing.JScrollPane jSP_Pedidos;
    private javax.swing.JTabbedPane jTB_CliPedido;
    private javax.swing.JTable jTB_Pedidos;
    private javax.swing.JTextField jTF_Consulta;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
