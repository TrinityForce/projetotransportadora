package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.util.Tela;
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
public class FormCrudPedido extends javax.swing.JFrame {

    private ClienteDao clienteDao;
    private Cliente clienteSelecionado;
    private CargaDao cargaDao;
    public static Integer idCliente;
    private Carga cargaSelecionada;
    private Tela t;

    public FormCrudPedido() {
        initComponents();
        preencherTabela();
        preencherCampos();
    }

    private boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

    private void habilitarCampos() {
        jTF_Descricao.setEnabled(true);
        jTF_Peso.setEnabled(true);
        jTF_Quantidade.setEnabled(true);
    }

    private void limparCampos() {
        jTF_id.setText("");
        jTF_Descricao.setText("");
        jTF_Peso.setText("");
        jTF_Quantidade.setText("");
    }

    private void desabilitarCampos() {
        jTF_id.setEnabled(false);
        jTF_Descricao.setEnabled(false);
        jTF_Peso.setEnabled(false);
        jTF_Quantidade.setEnabled(false);
        jBT_Salvar.setEnabled(false);
        jBT_Excluir.setEnabled(false);
        jBT_Alterar.setEnabled(false);
    }

    private void preencherCampos() {
        try {
            clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.consultarPorId(FormClientePedido.idCliente);

            if (cliente != null) {
                jTF_IdCliente.setText(String.valueOf(cliente.getIdCliente()));
                jTF_Cpf.setText(cliente.getCpf());
                jTF_NomeCliente.setText(cliente.getNome());
            } else {
                System.out.println("cliente n encontrado");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void preencherTabela() {
        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "DESCRIÇÃO", "PESO", "QUANTIDADE"};

        try {
            cargaDao = new CargaDao();
            final List<Object> listaCargas = cargaDao.listar();

            if (listaCargas != null && listaCargas.size() > 0) {
                listaCargas.forEach((Object cargaAtual) -> {
                    Carga carga = (Carga) cargaAtual;
                    dados.add(new Object[]{carga.getIdCarga(), carga.getDescricao(), carga.getPeso(), carga.getQuantidade()});
                });
            }

            ModeloTabela modTabela = new ModeloTabela(dados, colunas);
            jTB_Cargas.setModel(modTabela);
            jTB_Cargas.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTB_Cargas.getColumnModel().getColumn(0).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTB_Cargas.getColumnModel().getColumn(1).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTB_Cargas.getColumnModel().getColumn(2).setResizable(false);
            jTB_Cargas.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTB_Cargas.getColumnModel().getColumn(3).setResizable(false);
            jTB_Cargas.getTableHeader().setReorderingAllowed(false);
            jTB_Cargas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTB_Cargas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jTB_Cargas.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        List<Object> lista = cargaDao.listar();
                        cargaSelecionada = (Carga) lista.
                                get(jTB_Cargas.convertRowIndexToModel(jTB_Cargas.getSelectedRow()));

                        jTF_id.setText(String.valueOf(cargaSelecionada.getIdCarga()));
                        jTF_Descricao.setText(cargaSelecionada.getDescricao());
                        jTF_Peso.setText(String.valueOf(cargaSelecionada.getPeso()));
                        jTF_Quantidade.setText(String.valueOf(cargaSelecionada.getQuantidade()));

                        t = new Tela();
                        habilitarCampos();
                        habilitarBotao(jBT_Alterar);
                        habilitarBotao(jBT_Excluir);
                        desabilitarBotao(jBT_Salvar);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormCrudPedido.this, "Erro Inesperado: \n" + ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTB_CliPedido = new javax.swing.JTabbedPane();
        jPN_PesquisarCliente = new javax.swing.JPanel();
        jSC_Tabela = new javax.swing.JScrollPane();
        jTableClientes = new javax.swing.JTable();
        jBT_AdicionarCarga = new javax.swing.JButton();
        jBT_CadastrarPedido = new javax.swing.JButton();
        jBT_Alterar = new javax.swing.JButton();
        jLB_Descricao1 = new javax.swing.JLabel();
        jTF_NomeCliente = new javax.swing.JTextField();
        jLB_Descricao2 = new javax.swing.JLabel();
        jTF_Cpf = new javax.swing.JTextField();
        jLB_Descricao3 = new javax.swing.JLabel();
        jTF_IdCliente = new javax.swing.JTextField();
        jPN_CadastrarPedido = new javax.swing.JPanel();
        jBT_Salvar = new javax.swing.JButton();
        jBT_Novo = new javax.swing.JButton();
        jBT_Excluir = new javax.swing.JButton();
        jSP_Cargas = new javax.swing.JScrollPane();
        jTB_Cargas = new javax.swing.JTable();
        jBT_Alterar1 = new javax.swing.JButton();
        jTF_Quantidade = new javax.swing.JTextField();
        jTF_Peso = new javax.swing.JTextField();
        jTF_Descricao = new javax.swing.JTextField();
        jTF_id = new javax.swing.JTextField();
        jLB_Texto = new javax.swing.JLabel();
        jLB_Texto1 = new javax.swing.JLabel();
        jLB_Texto2 = new javax.swing.JLabel();
        jLB_Texto3 = new javax.swing.JLabel();
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

        jPN_PesquisarCliente.add(jSC_Tabela, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 180, 670, 260));

        jBT_AdicionarCarga.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarCarga.setText("Adicionar Carga");
        jBT_AdicionarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarCargaActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_AdicionarCarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 210, 70));

        jBT_CadastrarPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_CadastrarPedido.setText("Cadastrar Pedido");
        jBT_CadastrarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_CadastrarPedidoActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_CadastrarPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 80, 230, 70));

        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setText("Alterar Cliente");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 80, 230, 70));

        jLB_Descricao1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao1.setText("Nome Cliente");
        jPN_PesquisarCliente.add(jLB_Descricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 20, -1, -1));

        jTF_NomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente.setEnabled(false);
        jPN_PesquisarCliente.add(jTF_NomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 220, 30));

        jLB_Descricao2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao2.setText("CPF");
        jPN_PesquisarCliente.add(jLB_Descricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, -1, -1));

        jTF_Cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf.setEnabled(false);
        jTF_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CpfActionPerformed(evt);
            }
        });
        jPN_PesquisarCliente.add(jTF_Cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 150, 30));

        jLB_Descricao3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao3.setText("ID");
        jPN_PesquisarCliente.add(jLB_Descricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jTF_IdCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente.setEnabled(false);
        jPN_PesquisarCliente.add(jTF_IdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 50, 30));

        jTB_CliPedido.addTab("Pesquisar Cliente", null, jPN_PesquisarCliente, "Pesquisar cliente e selecionar uma ação.");

        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setText("Salvar");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });

        jBT_Novo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Novo.setText("Novo");
        jBT_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_NovoActionPerformed(evt);
            }
        });

        jBT_Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Excluir.setText("Excluir");
        jBT_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ExcluirActionPerformed(evt);
            }
        });

        jTB_Cargas.setModel(new javax.swing.table.DefaultTableModel(
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
        jSP_Cargas.setViewportView(jTB_Cargas);

        jBT_Alterar1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar1.setText("Alterar");
        jBT_Alterar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_Alterar1ActionPerformed(evt);
            }
        });

        jTF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTF_Peso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTF_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jTF_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_id.setEnabled(false);

        jLB_Texto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto.setText("ID");

        jLB_Texto1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto1.setText("Descrição");

        jLB_Texto2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto2.setText("Peso");

        jLB_Texto3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto3.setText("Quantidade");

        javax.swing.GroupLayout jPN_CadastrarPedidoLayout = new javax.swing.GroupLayout(jPN_CadastrarPedido);
        jPN_CadastrarPedido.setLayout(jPN_CadastrarPedidoLayout);
        jPN_CadastrarPedidoLayout.setHorizontalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 700, Short.MAX_VALUE)
            .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSP_Cargas, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLB_Texto)
                                        .addComponent(jLB_Texto1))
                                    .addGap(24, 24, 24)
                                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addComponent(jBT_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jBT_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLB_Texto2)
                                        .addComponent(jLB_Texto3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTF_Peso, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTF_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(10, 10, 10)
                                    .addComponent(jBT_Alterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jBT_Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPN_CadastrarPedidoLayout.setVerticalGroup(
            jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 470, Short.MAX_VALUE)
            .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBT_Salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBT_Novo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addComponent(jLB_Texto)
                                    .addGap(25, 25, 25)
                                    .addComponent(jLB_Texto1))
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addComponent(jTF_id, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jTF_Descricao, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(10, 10, 10)
                    .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jBT_Alterar1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jBT_Excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addGroup(jPN_CadastrarPedidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addComponent(jLB_Texto2)
                                    .addGap(25, 25, 25)
                                    .addComponent(jLB_Texto3))
                                .addGroup(jPN_CadastrarPedidoLayout.createSequentialGroup()
                                    .addComponent(jTF_Peso, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(20, 20, 20)
                                    .addComponent(jTF_Quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGap(30, 30, 30)
                    .addComponent(jSP_Cargas, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTB_CliPedido.addTab("Adicionar Carga", jPN_CadastrarPedido);

        getContentPane().add(jTB_CliPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 80, 700, 470));
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AdicionarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarCargaActionPerformed
        jTB_CliPedido.setEnabledAt(1, true);
        jTB_CliPedido.setEnabledAt(0, false);
        jTB_CliPedido.setSelectedIndex(1);
    }//GEN-LAST:event_jBT_AdicionarCargaActionPerformed

    private void jBT_CadastrarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_CadastrarPedidoActionPerformed
//        jTB_CliPedido.setEnabledAt(1, true);
        //      jTB_CliPedido.setEnabledAt(0, false);
        //    jTB_CliPedido.setSelectedIndex(1);

        FormCadastrarCarga formCard = new FormCadastrarCarga();
        formCard.setVisible(true);
        dispose();
    }//GEN-LAST:event_jBT_CadastrarPedidoActionPerformed

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
//        FormAlterarCliente frmAlterar = new FormAlterarCliente();
//        frmAlterar.setVisible(true);
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jTF_CpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CpfActionPerformed

    private void jBT_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SalvarActionPerformed
        String descricao = jTF_Descricao.getText();
        Double peso = Double.valueOf(jTF_Peso.getText());
        Integer quantidade = Integer.valueOf(jTF_Quantidade.getText());

        Boolean camposPreenchidos = verificarCampo(jTF_Descricao.getText()) && verificarCampo(jTF_Peso.getText())
                && verificarCampo(jTF_Quantidade.getText());

        if (camposPreenchidos == true) {
            Carga carga = new Carga();
            carga.setDescricao(descricao);
            carga.setPeso(peso);
            carga.setQuantidade(quantidade);

            try {
                cargaDao.inserir(carga);
                desabilitarCampos();
                limparCampos();
                preencherTabela();
                JOptionPane.showMessageDialog(this, "CARGA INCLUÍDA COM SUCESSO!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_SalvarActionPerformed

    private void jBT_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_NovoActionPerformed
        limparCampos();
        habilitarCampos();
        habilitarBotao(jBT_Salvar);
        desabilitarBotao(jBT_Alterar);
        desabilitarBotao(jBT_Excluir);
    }//GEN-LAST:event_jBT_NovoActionPerformed

    private void jBT_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ExcluirActionPerformed
        try {
            Integer opt = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja EXCLUIR esta CARGA do sistema? \nEsta alteração não poderá ser revertida.", "EXCLUIR?", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                Carga carga = new Carga();
                carga.setIdCarga(Integer.valueOf(jTF_id.getText()));

                cargaDao.excluir(carga);

                desabilitarCampos();
                limparCampos();
                preencherTabela();
                JOptionPane.showMessageDialog(this, "CARGA excluído com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_ExcluirActionPerformed

    private void jBT_Alterar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_Alterar1ActionPerformed
        String descricao = jTF_Descricao.getText();
        Double peso = Double.valueOf(jTF_Peso.getText());
        Integer quantidade = Integer.valueOf(jTF_Quantidade.getText());

        Boolean camposPreenchidos = verificarCampo(jTF_Descricao.getText()) && verificarCampo(jTF_Peso.getText())
                && verificarCampo(jTF_Quantidade.getText());

        if (camposPreenchidos == true) {
            Carga carga = new Carga();
            carga.setIdCarga(Integer.valueOf(jTF_id.getText()));
            carga.setDescricao(descricao);
            carga.setPeso(peso);
            carga.setQuantidade(quantidade);

            try {
                cargaDao.alterar(carga);
                desabilitarCampos();
                limparCampos();
                preencherTabela();
                JOptionPane.showMessageDialog(this, "CARGA ALTERADA COM SUCESSO!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_Alterar1ActionPerformed

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
            java.util.logging.Logger.getLogger(FormCrudPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormCrudPedido().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_AdicionarCarga;
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_Alterar1;
    private javax.swing.JButton jBT_CadastrarPedido;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_Novo;
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Descricao1;
    private javax.swing.JLabel jLB_Descricao2;
    private javax.swing.JLabel jLB_Descricao3;
    private javax.swing.JLabel jLB_Texto;
    private javax.swing.JLabel jLB_Texto1;
    private javax.swing.JLabel jLB_Texto2;
    private javax.swing.JLabel jLB_Texto3;
    private javax.swing.JPanel jPN_CadastrarPedido;
    private javax.swing.JPanel jPN_PesquisarCliente;
    private javax.swing.JScrollPane jSC_Tabela;
    private javax.swing.JScrollPane jSP_Cargas;
    private javax.swing.JTable jTB_Cargas;
    private javax.swing.JTabbedPane jTB_CliPedido;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_Descricao;
    private javax.swing.JTextField jTF_IdCliente;
    private javax.swing.JTextField jTF_NomeCliente;
    private javax.swing.JTextField jTF_Peso;
    private javax.swing.JTextField jTF_Quantidade;
    private javax.swing.JTextField jTF_id;
    private javax.swing.JTable jTableClientes;
    // End of variables declaration//GEN-END:variables
}
