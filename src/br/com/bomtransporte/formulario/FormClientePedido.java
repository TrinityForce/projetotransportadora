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
        verificarAba();
    }

    private void desabilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(false);
        }
    }

    private void habilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(true);
        }
    }

    private void verificarAba() {

        jTB_CliPedido.setEnabledAt(0, true);
        jTB_CliPedido.setEnabledAt(1, false);
        jTB_CliPedido.setSelectedIndex(0);

        desabilitarBotoes(jBT_ListarPedidos);

    }

    private void preencherTabelaPedido() {

        ArrayList dados = new ArrayList();

        String[] colunas = new String[]{"ID", "PROTOCOLO", "DATA VENDA", "DESCONTO", "STATUS"};
        desabilitarBotoes(jBT_AdicionarCarga, jBT_AlterarPedido, jBT_AlterarStatusPedido);
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

                        if ((idPedidoSelecionado != null) && (idPedido_CliSelecionado) != null) {

                            habilitarBotoes(jBT_AlterarPedido, jBT_AlterarStatusPedido);

                            if (pedidoSelecionado.getStatusPedido().equals("Em aguardo")) {
                                habilitarBotoes(jBT_AdicionarCarga);

                            } else {
                                desabilitarBotoes(jBT_AdicionarCarga);
                            }
                        } else {
                            desabilitarBotoes(jBT_AdicionarCarga, jBT_AlterarPedido, jBT_AlterarStatusPedido);

                        }

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
            desabilitarBotoes(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido, jBT_AdicionarCarga);
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

                        habilitarBotoes(jBT_ListarPedidos, jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);

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
        jBT_AlterarPedido = new javax.swing.JButton();
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

        jPN_PesquisarCliente.add(jSC_Tabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 670, 260));

        jBT_Excluir.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/excluir-icon.png"))); // NOI18N
        jBT_Excluir.setText("Excluir Cliente");
        jBT_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ExcluirActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 10, 200, 70));

        jBT_CadastrarPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_CadastrarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_CadastrarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/new-icon.png"))); // NOI18N
        jBT_CadastrarPedido.setText("Cadastrar Pedido");
        jBT_CadastrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_CadastrarPedidoActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_CadastrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, 230, 70));

        jBT_Alterar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_Alterar.setText("Alterar Cliente");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 200, 70));

        jBT_Pesquisar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/Search-icon.png"))); // NOI18N
        jBT_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PesquisarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 70, 70));

        jTF_Consulta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jPN_PesquisarCliente.add(jTF_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 340, 70));

        jBT_ListarPedidos.setBackground(new java.awt.Color(0, 0, 0));
        jBT_ListarPedidos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_ListarPedidos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/listar-icon.png"))); // NOI18N
        jBT_ListarPedidos.setText("Listar Pedidos");
        jBT_ListarPedidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ListarPedidosActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_ListarPedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 90, 200, 70));

        jTB_CliPedido.addTab("Pesquisar Cliente", null, jPN_PesquisarCliente, "Pesquisar cliente e selecionar uma ação.");

        jPN_CadastrarPedido.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPN_CadastrarPedido.add(jSP_Pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 675, 229));

        jBT_AlterarPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_AlterarPedido.setText("Alterar Pedido");
        jBT_AlterarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarPedidoActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AlterarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, 80));

        jBT_AdicionarCarga.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AdicionarCarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/add-icon.png"))); // NOI18N
        jBT_AdicionarCarga.setText("Adicionar Carga");
        jBT_AdicionarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarCargaActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AdicionarCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 220, 80));

        jBT_AlterarStatusPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarStatusPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarStatusPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar2-icon.png"))); // NOI18N
        jBT_AlterarStatusPedido.setText("Alterar Status");
        jBT_AlterarStatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarStatusPedidoActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AlterarStatusPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 220, 80));

        jTB_CliPedido.addTab("Cadastrar Pedido", jPN_CadastrarPedido);

        getContentPane().add(jTB_CliPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 700, 510));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterar-cliente-bg.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

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
        desabilitarBotoes(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido);
    }//GEN-LAST:event_jBT_PesquisarActionPerformed

    private void jBT_ListarPedidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ListarPedidosActionPerformed
        if (idCliente != null) {
            jTB_CliPedido.setEnabledAt(1, true);
            jTB_CliPedido.setEnabledAt(0, false);
            jTB_CliPedido.setSelectedIndex(1);
            preencherTabelaPedido();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Selecione um cliente!", "Erro", JOptionPane.INFORMATION_MESSAGE);
        }
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

    private void jBT_AlterarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarPedidoActionPerformed

        FormAlterarPedido formAltPedido = new FormAlterarPedido();
        formAltPedido.setVisible(true);
    }//GEN-LAST:event_jBT_AlterarPedidoActionPerformed

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
    private javax.swing.JButton jBT_AlterarPedido;
    private javax.swing.JButton jBT_AlterarStatusPedido;
    private javax.swing.JButton jBT_CadastrarPedido;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_ListarPedidos;
    private javax.swing.JButton jBT_Pesquisar;
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
