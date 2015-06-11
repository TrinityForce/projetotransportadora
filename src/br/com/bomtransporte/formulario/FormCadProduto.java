/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.EnderecoDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.dao.PrecoDistanciaDao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.Endereco;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.PrecoDistancia;
import br.com.bomtransporte.util.Datas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class FormCadProduto extends javax.swing.JFrame {

    private ClienteDao clienteDao;
    private EnderecoDao enderecoDao;
    private Integer idEndereco;
    private Integer idCliente = FormClientePedido.idCliente;
    private String[] idPrecoDistancia;

    public void imprimirObjeto(Object obj) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : obj.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            String name = field.getName();
            Object value = field.get(obj);
            System.out.printf("%s: %s%n", name, value);
        }
    }

    /**
     * Creates new form FormCadCliente
     */
    public FormCadProduto() {
        initComponents();
        // preencherTabela();
        preencherCampos();
        preencherComboPreco();
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

    private void preencherComboPreco() {
        PrecoDistanciaDao pdd = new PrecoDistanciaDao();
        try {
            jCB_Rotas.removeAllItems();
            pdd.listarTodosAtivados().forEach((PrecoDistancia preco) -> {
                //PrecoDistancia pd = (PrecoDistancia) preco;
                jCB_Rotas.addItem(preco.getIdPrecoDistancia() + " " + preco.getOrigemDestinoUf() + "-R$" + preco.getValor());
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace(System.err);
        }
    }

    private boolean verificarCampo(String campo) {
        if (campo != null && campo.trim().length() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificarCampo(Integer campo) {
        if (campo != null) {
            return true;
        } else {
            return false;
        }
    }

    private boolean verificarRadio(JRadioButton rb1, JRadioButton rb2) {
        return rb1.isSelected() == true || rb2.isSelected() == true;
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

    private void limparCampos() {
        jTF_Descricao.setText("");
        jTF_Peso.setText("");
        jTF_NomeCliente.setText("");
        jTF_Quantidade.setText("1");
    }

    private void habilitarCampos() {
        jTF_Descricao.setEnabled(true);
        jTF_Peso.setEnabled(true);
        jTF_NomeCliente.setEnabled(true);
        jTF_Quantidade.setEnabled(true);
        jTF_Bairro.setEnabled(true);
        jTF_Cep.setEnabled(true);
        jTF_Complemento.setEnabled(true);
        jTF_Logradouro.setEnabled(true);
        jTF_Numero.setEnabled(true);
        jTF_Uf.setEnabled(true);

    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void desabilitarCampos() {
        jTF_Descricao.setEnabled(false);
        jTF_Peso.setEnabled(false);
        jTF_NomeCliente.setEnabled(false);
        jTF_Quantidade.setEnabled(false);
        jTF_Bairro.setEnabled(false);
        jTF_Cep.setEnabled(false);
        jTF_Complemento.setEnabled(false);
        jTF_Logradouro.setEnabled(false);
        jTF_Numero.setEnabled(false);
        jTF_Uf.setEnabled(false);
        jBT_Salvar.setEnabled(false);

    }

    private void limparCamposCep() {
        jTF_Bairro.setText("");
        jTF_Logradouro.setText("");
        jTF_Uf.setText("");

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
                limparCamposCep();
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

        jTF_Descricao = new javax.swing.JTextField();
        jTF_Peso = new javax.swing.JTextField();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Descricao = new javax.swing.JLabel();
        jLB_Peso = new javax.swing.JLabel();
        jBT_Salvar = new javax.swing.JButton();
        jLB_Descricao1 = new javax.swing.JLabel();
        jTF_NomeCliente = new javax.swing.JTextField();
        jLB_Quantidade = new javax.swing.JLabel();
        jTF_Quantidade = new javax.swing.JTextField();
        jLB_CEP = new javax.swing.JLabel();
        jTF_Cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cep= new javax.swing.text.MaskFormatter("#####-###"); 
            jTF_Cep = new javax.swing.JFormattedTextField(cep); 
        } 
        catch (Exception e){ 
        }
        jLabel1 = new javax.swing.JLabel();
        jTF_Logradouro = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTF_Bairro = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTF_Uf = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTF_Numero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTF_Complemento = new javax.swing.JTextField();
        jLB_ErroCep = new javax.swing.JLabel();
        jBT_Verificar = new javax.swing.JButton();
        jLB_Descricao2 = new javax.swing.JLabel();
        jTF_Cpf = new javax.swing.JTextField();
        jLB_Descricao3 = new javax.swing.JLabel();
        jTF_IdCliente = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTF_NomeCidade = new javax.swing.JTextField();
        jCB_Rotas = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTF_Desconto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTF_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(jTF_Descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 250, 30));

        jTF_Peso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        getContentPane().add(jTF_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 250, 30));

        jLB_Fechar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar.setText("X");
        jLB_Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jLB_Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_FecharMouseReleased(evt);
            }
        });
        getContentPane().add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 0, 40, 40));

        jLB_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao.setText("Descricao");
        getContentPane().add(jLB_Descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, -1));

        jLB_Peso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Peso.setText("Peso");
        getContentPane().add(jLB_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setText("Finalizar ");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 430, 210, 120));

        jLB_Descricao1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao1.setText("Nome Cliente");
        getContentPane().add(jLB_Descricao1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 50, -1, -1));

        jTF_NomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente.setEnabled(false);
        getContentPane().add(jTF_NomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 50, 250, 30));

        jLB_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Quantidade.setText("Quantidade");
        getContentPane().add(jLB_Quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 110, -1, -1));

        jTF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Quantidade.setText("1");
        getContentPane().add(jTF_Quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 110, 36, 30));

        jLB_CEP.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_CEP.setText("CEP");
        getContentPane().add(jLB_CEP, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, -1, -1));

        jTF_Cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CepActionPerformed(evt);
            }
        });
        getContentPane().add(jTF_Cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 360, 210, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Logradouro");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 470, -1, -1));
        getContentPane().add(jTF_Logradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 470, 570, 30));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Bairro");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 420, -1, -1));
        getContentPane().add(jTF_Bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 420, 200, 30));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("Destino");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, -1, -1));
        getContentPane().add(jTF_Uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 100, 30));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Número");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, -1, -1));
        getContentPane().add(jTF_Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 520, 80, 30));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Complemento");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 520, -1, -1));
        getContentPane().add(jTF_Complemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 520, 330, 30));

        jLB_ErroCep.setForeground(new java.awt.Color(255, 0, 0));
        jLB_ErroCep.setText("erro");
        getContentPane().add(jLB_ErroCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 130, 20));

        jBT_Verificar.setText("Verificar");
        jBT_Verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VerificarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 360, 80, 30));

        jLB_Descricao2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao2.setText("CPF");
        getContentPane().add(jLB_Descricao2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, -1, -1));

        jTF_Cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf.setEnabled(false);
        jTF_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CpfActionPerformed(evt);
            }
        });
        getContentPane().add(jTF_Cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 250, 30));

        jLB_Descricao3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao3.setText("ID");
        getContentPane().add(jLB_Descricao3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jTF_IdCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente.setEnabled(false);
        getContentPane().add(jTF_IdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 50, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Cidade");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, -1, -1));
        getContentPane().add(jTF_NomeCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 430, 180, 30));

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Rotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_RotasActionPerformed(evt);
            }
        });
        getContentPane().add(jCB_Rotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 300, 170, -1));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel8.setText("UF");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 360, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel14.setText("Desconto");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));
        getContentPane().add(jTF_Desconto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 290, 180, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        System.exit(0);
    }//GEN-LAST:event_jLB_FecharMouseReleased

    private void jBT_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SalvarActionPerformed

        List<String> listCampos = new ArrayList<String>();
        listCampos.add(jTF_Cep.getText());
        listCampos.add(jTF_Numero.getText());
        listCampos.add(jTF_Complemento.getText());
        listCampos.add(jTF_Bairro.getText());
        listCampos.add(jTF_Logradouro.getText());
        listCampos.add(jTF_Uf.getText());
        listCampos.add(jTF_NomeCidade.getText());
        listCampos.add(jTF_Descricao.getText());
        listCampos.add(jTF_Peso.getText());
        listCampos.add(jTF_Quantidade.getText());
        listCampos.add(jTF_NomeCidade.getText());
        listCampos.add(jTF_Desconto.getText());

        try {
            //verifica se todos os valores estao preenchidos
            if (verificarCampos(listCampos) && (idCliente != null)
                    && (idEndereco != null)) {
                if (verificarCep()) {

                    PedidoDao pedidoDao = new PedidoDao();
                    Pedido pedido = new Pedido();
                    pedido.setComplemento(listCampos.get(2));
                    pedido.setDataVenda(Datas.dataAtual());
                    pedido.setDesconto(listCampos.get(11));
                    pedido.setIdEnderecoCorreios(idEndereco);
                    pedido.setNumero(listCampos.get(1));
                    pedido.setProtocolo(Datas.getCurrentDate() + idCliente);
                    pedido.setStatusPedido("Em aguardo");

                   pedido.setIdPedido(pedidoDao.insertGetKey(pedido)) ;

                    Carga carga = new Carga();
                    CargaDao cargaDao = new CargaDao();
                    carga.setDescricao(listCampos.get(7));
                    carga.setPeso(Double.valueOf(listCampos.get(8)));
                    carga.setQuantidade(Integer.valueOf(listCampos.get(9)));

                    carga.setIdCarga(cargaDao.insertGetKey(carga));

                    Cliente cliente = new Cliente();
                    cliente.setIdCliente(idCliente);

                    PrecoDistancia precoDistancia = new PrecoDistancia();
                    precoDistancia.setIdPrecoDistancia(Integer.valueOf(idPrecoDistancia[0]));

                    pedidoDao.inserirPedidoCli(cliente.getIdCliente(),
                            carga.getIdCarga(), pedido.getIdPedido(),
                            precoDistancia.getIdPrecoDistancia());

                    JOptionPane.showMessageDialog(this, "Produto incluido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this, "CEP invalido!", "Erro", JOptionPane.INFORMATION_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jBT_SalvarActionPerformed

    private void jTF_CepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CepActionPerformed

    private void jBT_VerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VerificarActionPerformed
        verificarCep();
    }//GEN-LAST:event_jBT_VerificarActionPerformed

    private void jTF_CpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_CpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_CpfActionPerformed

    private void jCB_RotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_RotasActionPerformed

        idPrecoDistancia = jCB_Rotas.getSelectedItem().toString().split(" ");
    }//GEN-LAST:event_jCB_RotasActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormCadProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormCadProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormCadProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormCadProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormCadProduto().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JButton jBT_Verificar;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JLabel jLB_CEP;
    private javax.swing.JLabel jLB_Descricao;
    private javax.swing.JLabel jLB_Descricao1;
    private javax.swing.JLabel jLB_Descricao2;
    private javax.swing.JLabel jLB_Descricao3;
    private javax.swing.JLabel jLB_ErroCep;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Peso;
    private javax.swing.JLabel jLB_Quantidade;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jTF_Bairro;
    private javax.swing.JTextField jTF_Cep;
    private javax.swing.JTextField jTF_Complemento;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_Desconto;
    private javax.swing.JTextField jTF_Descricao;
    private javax.swing.JTextField jTF_IdCliente;
    private javax.swing.JTextField jTF_Logradouro;
    private javax.swing.JTextField jTF_NomeCidade;
    private javax.swing.JTextField jTF_NomeCliente;
    private javax.swing.JTextField jTF_Numero;
    private javax.swing.JTextField jTF_Peso;
    private javax.swing.JTextField jTF_Quantidade;
    private javax.swing.JTextField jTF_Uf;
    // End of variables declaration//GEN-END:variables
}
