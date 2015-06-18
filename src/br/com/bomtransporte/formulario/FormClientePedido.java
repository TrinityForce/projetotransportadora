package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
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
    private String statusPedidoSelecionado;

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

    public static boolean isNumeric(String string) {
        return string.matches("^[-+]?\\d+(\\.\\d+)?$");
    }

    private void preencherTotal() {
        if (idPedido_CliSelecionado != null) {
            try {
                CargaDao cargaDao = new CargaDao();
                Double valorTotal = 0.0;

                final List<Object> listaCarga = cargaDao.listarCargas(idPedido_CliSelecionado);
                System.out.println("dentro do try");
                for (Object carga : listaCarga) {
                    Carga cargaAtual = (Carga) carga;
                    valorTotal += cargaAtual.getValor();
                }
                jTF_Total.setText(String.valueOf(valorTotal));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro inesperado: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pedido Nulo.");
        }
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
                        statusPedidoSelecionado = pedidoSelecionado.getStatusPedido();
                        preencherTotal();
                        if ((idPedidoSelecionado != null) && (idPedido_CliSelecionado) != null) {

                            habilitarBotoes(jBT_AlterarPedido, jBT_AlterarStatusPedido);

                            if (pedidoSelecionado.getStatusPedido().equals("Em aguardo")) {
                                habilitarBotoes(jBT_AdicionarCarga);

                            } else {
                                desabilitarBotoes(jBT_AdicionarCarga, jBT_AlterarPedido);
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
        final List<Object> listaCliente;
        try {
            clienteDao = new ClienteDao();
            if (isNumeric(jTF_Consulta.getText())) {
                listaCliente = clienteDao.consultarClientePeloCpf(jTF_Consulta.getText());
            } else {
                listaCliente = clienteDao.consultarCliente(jTF_Consulta.getText());
            }

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
        jBT_Pesquisar = new javax.swing.JButton();
        jBT_Alterar = new javax.swing.JButton();
        jTF_Consulta = new javax.swing.JTextField();
        jBT_ListarPedidos = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPN_CadastrarPedido = new javax.swing.JPanel();
        jSP_Pedidos = new javax.swing.JScrollPane();
        jTB_Pedidos = new javax.swing.JTable();
        jBT_AlterarPedido = new javax.swing.JButton();
        jBT_AdicionarCarga = new javax.swing.JButton();
        jBT_AlterarStatusPedido = new javax.swing.JButton();
        jBT_Voltar = new javax.swing.JButton();
        jLB_Descricao4 = new javax.swing.JLabel();
        jTF_Total = new javax.swing.JTextField();
        jLB_Fechar4 = new javax.swing.JLabel();
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

        jPN_PesquisarCliente.add(jSC_Tabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 670, 260));

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

        jBT_Pesquisar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Pesquisar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/Search-icon.png"))); // NOI18N
        jBT_Pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_PesquisarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Pesquisar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 70, 70));

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

        jTF_Consulta.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jPN_PesquisarCliente.add(jTF_Consulta, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 370, 70));

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

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("Pesquisar pelo nome ou CPF");
        jPN_PesquisarCliente.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, -1));

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

        jPN_CadastrarPedido.add(jSP_Pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 675, 229));

        jBT_AlterarPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar2-icon.png"))); // NOI18N
        jBT_AlterarPedido.setText("Alterar Pedido");
        jBT_AlterarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarPedidoActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AlterarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 200, 80));

        jBT_AdicionarCarga.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AdicionarCarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarCarga.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/add-icon.png"))); // NOI18N
        jBT_AdicionarCarga.setText("Adicionar Carga");
        jBT_AdicionarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarCargaActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AdicionarCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, 230, 80));

        jBT_AlterarStatusPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarStatusPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarStatusPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_AlterarStatusPedido.setText("Alterar Status");
        jBT_AlterarStatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarStatusPedidoActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_AlterarStatusPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 70, 220, 80));

        jBT_Voltar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Voltar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/retornar-icon.png"))); // NOI18N
        jBT_Voltar.setText("Voltar");
        jBT_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VoltarActionPerformed(evt);
            }
        });
        jPN_CadastrarPedido.add(jBT_Voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, -1, 60));

        jLB_Descricao4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao4.setText("Total:");
        jPN_CadastrarPedido.add(jLB_Descricao4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, -1, -1));

        jTF_Total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_CadastrarPedido.add(jTF_Total, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 430, 160, 30));

        jTB_CliPedido.addTab("Cadastrar Pedido", jPN_CadastrarPedido);

        getContentPane().add(jTB_CliPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 700, 510));

        jLB_Fechar4.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar4.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar4.setText("X");
        jLB_Fechar4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLB_Fechar4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_Fechar4MouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Fechar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterar-cliente-bg.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

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
        desabilitarBotoes(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido, jBT_ListarPedidos);
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
        if (statusPedidoSelecionado == null || statusPedidoSelecionado.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao acessar o pedido!\n O status do pedido esta em branco.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        List<String> optionList = new ArrayList<String>();

        optionList.add("Saiu para entrega");
        optionList.add("Entregue");
        optionList.add("Extraviado");

        if (statusPedidoSelecionado.equals("Em aguardo") || statusPedidoSelecionado.equals("Aguardando")) {
            optionList.remove(1);
        } else if (statusPedidoSelecionado.equals("Saiu para entrega")) {
            optionList.remove(0);
        } else if (statusPedidoSelecionado.equals("Extraviado")) {
            optionList.remove(2);
        } else {
            JOptionPane.showMessageDialog(this, "Esse pedido ja foi entregado!", "Pedido entregue", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        Object[] options = optionList.toArray();

        Object value = JOptionPane.showInputDialog(this, "Escolha uma das opcoes",
                "Mudar status do pedido", JOptionPane.QUESTION_MESSAGE, null,
                options,
                options[0]);
        int index = optionList.indexOf(value);

        System.err.println("opcao escolhida is %s.\n " + optionList.get(index));

        if (optionList.get(index) != null) {
            try {
                pedidoDao.update(pedidoSelecionado.getIdPedido(), optionList.get(index));
                preencherTabelaPedido();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_jBT_AlterarStatusPedidoActionPerformed

    private void jBT_AlterarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarPedidoActionPerformed
        FormAlterarPedido formAltPedido = new FormAlterarPedido();
        formAltPedido.setVisible(true);
    }//GEN-LAST:event_jBT_AlterarPedidoActionPerformed

    private void jBT_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VoltarActionPerformed
        jTB_CliPedido.setEnabledAt(0, true);
        jTB_CliPedido.setEnabledAt(1, false);
        jTB_CliPedido.setSelectedIndex(0);
        preencherTabela();
        desabilitarBotoes(jBT_Alterar, jBT_Excluir, jBT_CadastrarPedido, jBT_ListarPedidos);
    }//GEN-LAST:event_jBT_VoltarActionPerformed

    private void jLB_Fechar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar4MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar4MouseReleased

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
    private javax.swing.JButton jBT_Voltar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Descricao4;
    private javax.swing.JLabel jLB_Fechar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPN_CadastrarPedido;
    private javax.swing.JPanel jPN_PesquisarCliente;
    private javax.swing.JScrollPane jSC_Tabela;
    private javax.swing.JScrollPane jSP_Pedidos;
    private javax.swing.JTabbedPane jTB_CliPedido;
    private javax.swing.JTable jTB_Pedidos;
    private javax.swing.JTextField jTF_Consulta;
    private javax.swing.JTextField jTF_Total;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
