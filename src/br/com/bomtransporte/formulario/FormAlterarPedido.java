package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.EnderecoDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.dao.PrecoDistanciaDao;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.modelo.Endereco;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.PrecoDistancia;
import br.com.bomtransporte.util.Tela;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class FormAlterarPedido extends javax.swing.JFrame {

    private String[] idPrecoDistancia;
    private Integer idEndereco, idPedidoSelecionado, idPrecoDistanciaInteger;
    private ClienteDao clienteDao;

    /**
     * Creates new form FormALterarPedido
     */
    public FormAlterarPedido() {
        initComponents();
        idPedidoSelecionado = FormClientePedido.idPedidoSelecionado;
        preencherCampos();
        preencherComboPreco();
        preencherCamposCli();
        desabilitarCampos(jTF_Bairro, jTF_NomeCidade, jTF_Logradouro, jTF_Uf, jTF_Protocolo, jTF_DataVenda);
        Tela.desabilitarBotoes(jBT_CadastrarCep);
        jLB_ErroCep.setVisible(false);
    }

    private void preencherStatusPedido(String statusPedido) {
        jCB_StatusPedido.removeAllItems();
        jCB_StatusPedido.addItem("Aguardando");
        jCB_StatusPedido.addItem("Saiu para entrega");
        jCB_StatusPedido.addItem("Entregue");
        jCB_StatusPedido.addItem("Extraviado");

        if (statusPedido == null || statusPedido.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao acessar o pedido!\n O status do pedido esta em branco.",
                    "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        if (statusPedido.equals("Aguardando") || statusPedido.equals("Em aguardo")) {
            jCB_StatusPedido.removeItemAt(2);
        } else if (statusPedido.equals("Saiu para entrega")) {
            jCB_StatusPedido.removeItemAt(0);

        } else if (statusPedido.equals("Entregue")) {
            jCB_StatusPedido.removeAllItems();
            jCB_StatusPedido.addItem("Entregue");
        } else {
            jCB_StatusPedido.removeItemAt(0);
        }

        jCB_StatusPedido.setSelectedItem(statusPedido);

    }

    private void preencherCamposCli() {
        try {
            clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.consultarPorId(FormClientePedido.idCliente);

            if (cliente != null) {
                jTF_IdCliente.setText(String.valueOf(cliente.getIdCliente()));
                jTF_Cpf.setText(cliente.getCpf());
                jTF_NomeCliente.setText(cliente.getNome());
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

    private void desabilitarCampos(JTextField... jTF) {
        for (JTextField jTF1 : jTF) {
            jTF1.setEnabled(false);
        }
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
                jTF_DataVenda.setText(String.valueOf(pedido.getDataVenda()));
                jTF_Protocolo.setText((pedido.getProtocolo()));
                jTF_Numero.setText(pedido.getNumero());
                idPrecoDistanciaInteger = pedido.getIdPrecoDistancia();

                endereco = enderecoDao.retornarEnderecoPorIdPedido(idPedidoSelecionado);
                jTF_Cep.setText(endereco.getCep());
                jTF_Bairro.setText(endereco.getBairro());
                jTF_Logradouro.setText(endereco.getLogradouro());
                jTF_Uf.setText(endereco.getUf());
                jTF_NomeCidade.setText(endereco.getNomeCidade());

                preencherStatusPedido(pedido.getStatusPedido());

            } catch (Exception ex) {
                Logger.getLogger(FormAlterarPedido.class.getName()).log(Level.SEVERE, null, ex);
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
                if (Objects.equals(preco.getIdPrecoDistancia(), idPrecoDistanciaInteger)) {
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
                Tela.desabilitarBotoes(jBT_CadastrarCep);
                return true;
            } else {
                jLB_ErroCep.setText("CEP não encontrado.");
                jLB_ErroCep.setVisible(true);
                Tela.habilitarBotoes(jBT_CadastrarCep);

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
        jBT_Verificar = new javax.swing.JButton();
        jCB_Rotas = new javax.swing.JComboBox();
        jCB_StatusPedido = new javax.swing.JComboBox();
        jTF_Complemento = new javax.swing.JTextField();
        jTF_Numero = new javax.swing.JTextField();
        jTF_Logradouro = new javax.swing.JTextField();
        jTF_Bairro = new javax.swing.JTextField();
        jTF_NomeCidade = new javax.swing.JTextField();
        jTF_Uf = new javax.swing.JTextField();
        jTF_Cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cep= new javax.swing.text.MaskFormatter("#####-###"); 
            jTF_Cep = new javax.swing.JFormattedTextField(cep); 
        } 
        catch (Exception e){ 
        }
        jLabel1 = new javax.swing.JLabel();
        jTF_NomeCliente = new javax.swing.JTextField();
        jTF_Cpf = new javax.swing.JTextField();
        jTF_IdCliente = new javax.swing.JTextField();
        jTF_Protocolo = new javax.swing.JTextField();
        jTF_DataVenda = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jBT_CadastrarCep = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLB_CEP1 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLB_Descricao8 = new javax.swing.JLabel();
        jLB_Descricao9 = new javax.swing.JLabel();
        jLB_Descricao10 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLB_ErroCep = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLB_Fechar4 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Alterar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/salvar-icon.png"))); // NOI18N
        jBT_Alterar.setText("Salvar Alterações");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 470, 220, 80));

        jBT_Verificar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Verificar.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jBT_Verificar.setText("Verificar CEP");
        jBT_Verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VerificarActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_Verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 290, 120, 30));

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Rotas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_RotasItemStateChanged(evt);
            }
        });
        jCB_Rotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_RotasActionPerformed(evt);
            }
        });
        getContentPane().add(jCB_Rotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 170, -1));

        jCB_StatusPedido.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_StatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_StatusPedidoActionPerformed(evt);
            }
        });
        getContentPane().add(jCB_StatusPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 170, -1));
        getContentPane().add(jTF_Complemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 430, 330, 30));
        getContentPane().add(jTF_Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 80, 30));
        getContentPane().add(jTF_Logradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 380, 550, 30));
        getContentPane().add(jTF_Bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, 180, 30));
        getContentPane().add(jTF_NomeCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 480, 180, 30));
        getContentPane().add(jTF_Uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 480, 50, 30));

        jTF_Cep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CepActionPerformed(evt);
            }
        });
        getContentPane().add(jTF_Cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 290, 180, 30));

        jLabel1.setForeground(new java.awt.Color(255, 0, 51));
        jLabel1.setText("* - Campos Obrigatórios");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jTF_NomeCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_NomeCliente.setEnabled(false);
        getContentPane().add(jTF_NomeCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 80, 260, 30));

        jTF_Cpf.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_Cpf.setEnabled(false);
        jTF_Cpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_CpfActionPerformed(evt);
            }
        });
        getContentPane().add(jTF_Cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 140, 30));

        jTF_IdCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_IdCliente.setEnabled(false);
        getContentPane().add(jTF_IdCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 50, 30));
        getContentPane().add(jTF_Protocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 180, 180, 30));
        getContentPane().add(jTF_DataVenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 220, 180, 30));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Complemento");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 430, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Número");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 430, -1, -1));

        jBT_CadastrarCep.setBackground(new java.awt.Color(0, 0, 0));
        jBT_CadastrarCep.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBT_CadastrarCep.setText("Cadastrar CEP");
        jBT_CadastrarCep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_CadastrarCepActionPerformed(evt);
            }
        });
        getContentPane().add(jBT_CadastrarCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 120, 30));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Logradouro");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, -1, -1));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel15.setText("Bairro");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel16.setText("Cidade");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, -1, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel17.setText("UF");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, -1, -1));

        jLB_CEP1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_CEP1.setText("CEP *");
        getContentPane().add(jLB_CEP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel19.setText("Destino");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, -1, -1));

        jLB_Descricao8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao8.setText("Nome Cliente");
        getContentPane().add(jLB_Descricao8, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, -1, -1));

        jLB_Descricao9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao9.setText("CPF");
        getContentPane().add(jLB_Descricao9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, -1, -1));

        jLB_Descricao10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Descricao10.setText("ID");
        getContentPane().add(jLB_Descricao10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel20.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel20.setText("Protocolo");
        getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, -1, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel21.setText("Data");
        getContentPane().add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jLB_ErroCep.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        jLB_ErroCep.setForeground(new java.awt.Color(255, 0, 0));
        jLB_ErroCep.setText("ERROR PLACEHOLDER");
        getContentPane().add(jLB_ErroCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel22.setText("Status");
        getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, -1, -1));

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

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterarpedido-bg.png"))); // NOI18N
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 530, -1, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
        List<String> listaCampos = new ArrayList<>();
        listaCampos.add(jTF_Cep.getText());
        listaCampos.add(jTF_Complemento.getText());
        listaCampos.add(jTF_Numero.getText());
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
                    pedido.setStatusPedido((String) jCB_StatusPedido.getSelectedItem());
                    pedido.setIdPrecoDistancia(Integer.valueOf(String.valueOf(jCB_Rotas.getSelectedItem()).substring(0, 1)));

                    pedidoDao.alterar(pedido);

                    JOptionPane.showMessageDialog(this, "PEDIDO ALTERADO COM SUCESSO!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "CEP inválido, por favor corrigir", "CEP INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jBT_VerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VerificarActionPerformed
        if (!jTF_Cep.getText().contains(" ")) {
            verificarCep();
        } else {
            JOptionPane.showMessageDialog(this, "O cep esta em branco !", "CAMPO VAZIO", JOptionPane.INFORMATION_MESSAGE);
            Tela.desabilitarBotoes(jBT_CadastrarCep);
        }
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

    private void jCB_RotasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_RotasItemStateChanged
        idPrecoDistancia = jCB_Rotas.getSelectedItem().toString().split(" ");
    }//GEN-LAST:event_jCB_RotasItemStateChanged

    private void jLB_Fechar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar4MouseReleased
        dispose();
    }//GEN-LAST:event_jLB_Fechar4MouseReleased

    private void jBT_CadastrarCepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_CadastrarCepActionPerformed
        FormCadastrarCep formCep = new FormCadastrarCep();
        formCep.setVisible(true);
    }//GEN-LAST:event_jBT_CadastrarCepActionPerformed

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
            @Override
            public void run() {
                new FormAlterarPedido().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_CadastrarCep;
    private javax.swing.JButton jBT_Verificar;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JComboBox jCB_StatusPedido;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_CEP1;
    private javax.swing.JLabel jLB_Descricao10;
    private javax.swing.JLabel jLB_Descricao8;
    private javax.swing.JLabel jLB_Descricao9;
    private javax.swing.JLabel jLB_ErroCep;
    private javax.swing.JLabel jLB_Fechar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTF_Bairro;
    private javax.swing.JTextField jTF_Cep;
    private javax.swing.JTextField jTF_Complemento;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_DataVenda;
    private javax.swing.JTextField jTF_IdCliente;
    private javax.swing.JTextField jTF_Logradouro;
    private javax.swing.JTextField jTF_NomeCidade;
    private javax.swing.JTextField jTF_NomeCliente;
    private javax.swing.JTextField jTF_Numero;
    private javax.swing.JTextField jTF_Protocolo;
    private javax.swing.JTextField jTF_Uf;
    // End of variables declaration//GEN-END:variables
}
