/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.EnderecoDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.dao.PrecoDistanciaDao;
import br.com.bomtransporte.modelo.Endereco;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.PrecoDistancia;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class FormAlterarPedido extends javax.swing.JFrame {

    private String[] idPrecoDistancia;
    private Integer idEndereco, idPedidoSelecionado, idPrecoDistanciaInteger;

    /**
     * Creates new form FormALterarPedido
     */
    public FormAlterarPedido() {
        initComponents();
        idPedidoSelecionado = FormClientePedido.idPedidoSelecionado;
        preencherCampos();

    }

    private void preencherCampos() {
        if (FormClientePedido.idPedidoSelecionado != null) {
            PedidoDao pedidoDao = new PedidoDao();
            Pedido pedido = new Pedido();
            Endereco endereco = new Endereco();
            EnderecoDao enderecoDao = new EnderecoDao();

            try {
                pedido = (Pedido) pedidoDao.buscarPedido(idPedidoSelecionado);

                jTF_Complemento.setText(pedido.getComplemento());
                jTF_DataVenda.setText(pedido.getDataVenda());
                jTF_Desconto.setText(String.valueOf(pedido.getDesconto()));
                jTF_Protocolo.setText((pedido.getProtocolo()));
                jTF_Numero.setText(pedido.getNumero());
                idPrecoDistanciaInteger = pedido.getIdPrecoDistania();

                endereco = enderecoDao.retornarEnderecoPorIdPedido(idPedidoSelecionado);
                jTF_Cep.setText(endereco.getCep());
                jTF_Bairro.setText(endereco.getBairro());
                jTF_Logradouro.setText(endereco.getLogradouro());
                jTF_Uf.setText(endereco.getUf());
                jTF_NomeCidade.setText(endereco.getNomeCidade());

                System.err.println("FOI" + pedido.getNumero());
                preencherComboPreco();
            } catch (Exception ex) {
                Logger.getLogger(FormAlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
                ex.printStackTrace();
            }
        }
    }

    private void preencherComboPreco() {
        PrecoDistanciaDao pdd = new PrecoDistanciaDao();
        Integer contador = 0;
        try {
            jCB_Rotas.removeAllItems();
            /* pdd.listarTodosAtivados().forEach((PrecoDistancia preco) -> {
             //PrecoDistancia pd = (PrecoDistancia) preco;
             jCB_Rotas.addItem(preco.getIdPrecoDistancia() + " " + preco.getOrigemDestinoUf() + "-R$" + preco.getValor());
             });*/
            for (PrecoDistancia preco : pdd.listarTodosAtivados()) {
                jCB_Rotas.addItem(preco.getIdPrecoDistancia() + " " + preco.getOrigemDestinoUf() + "-R$" + preco.getValor());

                if (preco.getIdPrecoDistancia() == idPrecoDistanciaInteger) {
                    jCB_Rotas.setSelectedIndex(contador);
                }
                contador++;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }

    private boolean verificarCampos(List<String> list) {
        boolean tudoPreenchido = false;

        if (list != null) {
            tudoPreenchido = true;
            for (String item : list) {
                if (item == null) {
                    tudoPreenchido = false;
                    System.err.println("Endereco vazio");
                    break;
                }
            }
        }
        System.out.println("verificarCampoEndereco OK" + tudoPreenchido);
        return tudoPreenchido;
    }

    private boolean verificarCep() {
        String cep = jTF_Cep.getText();
        EnderecoDao enderecoDao = new EnderecoDao();
        try {
            Endereco endereco = enderecoDao.retornarEndereco(cep);
            if (endereco != null) {
                jLB_ErroCep.setVisible(false);
                jTF_Bairro.setText(endereco.getBairro());
                jTF_Logradouro.setText(endereco.getLogradouro());
                jTF_Uf.setText(endereco.getUf());
                jTF_NomeCidade.setText(endereco.getNomeCidade());
                idEndereco = endereco.getId();
                return true;
            } else {
                jLB_ErroCep.setText("CEP não encontrado.");
                jLB_ErroCep.setVisible(true);

                return false;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Um erro ocorreu ao verificar o cep: " + ex.getMessage(), "ERRO", JOptionPane.INFORMATION_MESSAGE);
        }
        return false;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBT_Alterar = new javax.swing.JButton();
        jTF_Complemento = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTF_Numero = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTF_Logradouro = new javax.swing.JTextField();
        jTF_Bairro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jTF_NomeCidade = new javax.swing.JTextField();
        jTF_Uf = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jBT_Verificar = new javax.swing.JButton();
        jTF_Cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cep= new javax.swing.text.MaskFormatter("#####-###"); 
            jTF_Cep = new javax.swing.JFormattedTextField(cep); 
        } 
        catch (Exception e){ 
        }
        jLB_CEP1 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTF_Desconto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jCB_Rotas = new javax.swing.JComboBox();
        jTF_NomeCliente = new javax.swing.JTextField();
        jLB_Descricao8 = new javax.swing.JLabel();
        jTF_Cpf = new javax.swing.JTextField();
        jLB_Descricao9 = new javax.swing.JLabel();
        jTF_IdCliente = new javax.swing.JTextField();
        jLB_Descricao10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTF_Protocolo = new javax.swing.JTextField();
        jTF_DataVenda = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLB_ErroCep = new javax.swing.JLabel();
        jCB_StatusPedido = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setText("Alterar");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Complemento");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Número");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Logradouro");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Bairro");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Cidade");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("UF");

        jBT_Verificar.setText("Verificar");
        jBT_Verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VerificarActionPerformed(evt);
            }
        });

        jTF_Cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CepActionPerformed(evt);
            }
        });

        jLB_CEP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_CEP1.setText("CEP");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel18.setText("Desconto");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Destino");

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Rotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_RotasActionPerformed(evt);
            }
        });

        jTF_NomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente.setEnabled(false);

        jLB_Descricao8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao8.setText("Nome Cliente");

        jTF_Cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf.setEnabled(false);
        jTF_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CpfActionPerformed(evt);
            }
        });

        jLB_Descricao9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao9.setText("CPF");

        jTF_IdCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente.setEnabled(false);

        jLB_Descricao10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao10.setText("ID");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Protocolo");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Data venda");

        jLB_ErroCep.setForeground(new java.awt.Color(255, 0, 0));
        jLB_ErroCep.setText("erro");

        jCB_StatusPedido.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_StatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_StatusPedidoActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Status pedido");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jLabel15)
                                .addGap(14, 14, 14)
                                .addComponent(jTF_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(130, 130, 130)
                                .addComponent(jLabel16)
                                .addGap(5, 5, 5)
                                .addComponent(jTF_NomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(6, 6, 6)
                                .addComponent(jTF_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(35, 35, 35)
                                .addComponent(jTF_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(jLabel11)
                                .addGap(6, 6, 6)
                                .addComponent(jTF_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLB_CEP1)
                                .addGap(40, 40, 40)
                                .addComponent(jTF_Cep, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jBT_Verificar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(50, 50, 50)
                                        .addComponent(jLabel17))
                                    .addComponent(jLB_ErroCep))
                                .addGap(19, 19, 19)
                                .addComponent(jTF_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 11, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLB_Descricao10)
                                    .addGap(12, 12, 12)
                                    .addComponent(jTF_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(jLB_Descricao9)
                                    .addGap(10, 10, 10)
                                    .addComponent(jTF_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(40, 40, 40)
                                    .addComponent(jLB_Descricao8)
                                    .addGap(23, 23, 23)
                                    .addComponent(jTF_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel20)
                                    .addGap(14, 14, 14)
                                    .addComponent(jTF_Protocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36)
                                    .addComponent(jLabel21)
                                    .addGap(14, 14, 14)
                                    .addComponent(jTF_DataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(10, 10, 10)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel22)
                                            .addGap(9, 9, 9)
                                            .addComponent(jCB_StatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel18)
                                            .addGap(14, 14, 14)
                                            .addComponent(jTF_Desconto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(160, 160, 160)
                                            .addComponent(jLabel19)
                                            .addGap(9, 9, 9)
                                            .addComponent(jCB_Rotas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jBT_Alterar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 95, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLB_Descricao10)
                    .addComponent(jTF_IdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLB_Descricao9)
                    .addComponent(jTF_Cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLB_Descricao8)
                    .addComponent(jTF_NomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jTF_Desconto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19)
                            .addComponent(jCB_Rotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jCB_StatusPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jTF_Protocolo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21)
                    .addComponent(jTF_DataVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLB_CEP1)
                            .addComponent(jTF_Cep, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jTF_Uf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jTF_Bairro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jTF_NomeCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBT_Verificar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLB_ErroCep)))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jTF_Logradouro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jTF_Numero, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jTF_Complemento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(jBT_Alterar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
        List<String> listaCampos = new ArrayList<>();
        listaCampos.add(jTF_Cep.getText());
        listaCampos.add(jTF_Complemento.getText());
        listaCampos.add(jTF_Numero.getText());
        listaCampos.add(jTF_Desconto.getText());
        listaCampos.add(String.valueOf(idPedidoSelecionado));

        if (verificarCampos(listaCampos)) {
            if (verificarCep()) {
                try {
                    Pedido pedido = new Pedido();
                    PedidoDao pedidoDao = new PedidoDao();
                    pedido.setIdPedido(Integer.valueOf(listaCampos.get(4)));
                    pedido.setComplemento(listaCampos.get(1));
                    pedido.setNumero(listaCampos.get(2));
                    pedido.setDesconto(Integer.valueOf(listaCampos.get(3)));
                    pedido.setIdPrecoDistania(Integer.valueOf(String.valueOf(jCB_Rotas.getSelectedItem()).substring(0, 1)));

                    pedidoDao.alterar(pedido);

                    JOptionPane.showMessageDialog(this, "Pedido alteradocom sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(this, "CEP inválido, por favor corrigir", "CEP INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jBT_VerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VerificarActionPerformed
        verificarCep();
    }//GEN-LAST:event_jBT_VerificarActionPerformed

    private void jTF_CepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CepActionPerformed

    private void jCB_RotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_RotasActionPerformed

        idPrecoDistancia = jCB_Rotas.getSelectedItem().toString().split(" ");
    }//GEN-LAST:event_jCB_RotasActionPerformed

    private void jTF_CpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CpfActionPerformed

    private void jCB_StatusPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_StatusPedidoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCB_StatusPedidoActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormAlterarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormAlterarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormAlterarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormAlterarPedido.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormAlterarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_Verificar;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JComboBox jCB_StatusPedido;
    private javax.swing.JLabel jLB_CEP1;
    private javax.swing.JLabel jLB_Descricao10;
    private javax.swing.JLabel jLB_Descricao8;
    private javax.swing.JLabel jLB_Descricao9;
    private javax.swing.JLabel jLB_ErroCep;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JTextField jTF_Bairro;
    private javax.swing.JTextField jTF_Cep;
    private javax.swing.JTextField jTF_Complemento;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_DataVenda;
    private javax.swing.JTextField jTF_Desconto;
    private javax.swing.JTextField jTF_IdCliente;
    private javax.swing.JTextField jTF_Logradouro;
    private javax.swing.JTextField jTF_NomeCidade;
    private javax.swing.JTextField jTF_NomeCliente;
    private javax.swing.JTextField jTF_Numero;
    private javax.swing.JTextField jTF_Protocolo;
    private javax.swing.JTextField jTF_Uf;
    // End of variables declaration//GEN-END:variables
}
