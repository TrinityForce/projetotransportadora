package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.EnderecoDao;
import br.com.bomtransporte.modelo.Endereco;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.Cliente;
import br.com.bomtransporte.regrasnegocio.ClienteRN;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Datas;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author Andre
 */
public class FormAlterarCliente extends javax.swing.JFrame {

    private Integer idEndereco, idContato, idPessoa, idCliente;

    /**
     * Creates new form FormCadastrarCliente
     */
    public FormAlterarCliente() {
        initComponents();
        desabilitarCamposCep();
        preencherCampos();
        jLB_ErroCep.setVisible(false);
        jTF_Cpf.setEnabled(false);
    }

    private void preencherCampos() {
        try {
            ClienteDao clienteDao = new ClienteDao();
            Cliente cliente = clienteDao.consultarPorId(FormClientePedido.idCliente);
            if (cliente != null) {
                EnderecoDao enderecoDao = new EnderecoDao();
                Endereco endereco = enderecoDao.retornarEnderecoPorId(cliente.getIdCliente());
                if (endereco != null) {
                    jTF_Bairro.setText(endereco.getBairro());
                    jTF_Celular.setText(cliente.getCelular());
                    jTF_Cep.setText(endereco.getCep());
                    jTF_Complemento.setText(cliente.getComplemento());
                    jTF_Cpf.setText(cliente.getCpf());
                    jTF_Logradouro.setText(endereco.getLogradouro());
                    jTF_Nome.setText(cliente.getNome());
                    jTF_Numero.setText(cliente.getNumeroCasa());
                    jTF_Telefone.setText(cliente.getTelefone());
                    jTF_Telefone2.setText(cliente.getTelefone2());
                    jTF_Uf.setText(endereco.getUf());
                    idContato = cliente.getIdContato();
                    idPessoa = cliente.getIdPessoa();
                    idCliente = cliente.getIdCliente();
                } else {
                    System.out.println("endereco n encontrado");
                }
            } else {
                System.out.println("cliente n encontrado");
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void desabilitarCampos() {
        jTF_Nome.setEnabled(false);
        jTF_Cpf.setEnabled(false);
        jTF_Cep.setEnabled(false);
        jTF_Telefone2.setEnabled(false);
        jTF_Telefone.setEnabled(false);
        jTF_Celular.setEnabled(false);
        jTF_Complemento.setEnabled(false);
        jTF_Bairro.setEnabled(false);
        jTF_Logradouro.setEnabled(false);
        jTF_Uf.setEnabled(false);
        jTF_Numero.setEnabled(false);
        jBT_Verificar.setEnabled(false);
        jTF_NomeCidade.setEnabled(false);
    }

    private void habilitarCampos() {
        jTF_Nome.setEnabled(true);
        jTF_Numero.setEnabled(true);
        jTF_Cpf.setEnabled(true);
        jTF_Cep.setEnabled(true);
        jTF_Telefone2.setEnabled(true);
        jTF_Telefone.setEnabled(true);
        jTF_Celular.setEnabled(true);
        jTF_Complemento.setEnabled(true);
        jBT_Verificar.setEnabled(true);
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
                desabilitarCamposCep();
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

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private String ajustarCpf(String cpf) {
        String cpf2 = cpf.replace("-", "");
        cpf2 = cpf2.replace(".", "");
        return cpf2;
    }

    private void desabilitarCamposCep() {
        jTF_Bairro.setEnabled(false);
        jTF_Logradouro.setEnabled(false);
        jTF_Uf.setEnabled(false);
        jTF_NomeCidade.setEnabled(false);
    }

    private void limparCamposCep() {
        jTF_Bairro.setText("");
        jTF_Logradouro.setText("");
        jTF_Uf.setText("");
        jTF_NomeCidade.setText("");
        jTF_Complemento.setText("");
        jTF_Numero.setText("");
        jTF_Cep.setText("");

    }

    private void limparCamposCliente() {
        jTF_Cpf.setText("");
        jTF_Nome.setText("");
        jTF_Telefone.setText("");
        jTF_Telefone2.setText("");

    }
    
    /**
     *
     * @param str
     * @return
     */
    public String capitalize(String str){
        return WordUtils.capitalizeFully(str.trim());
    }

    //verifica se todos os campos da lista estao preenchidos e retorna true
    private boolean verificarCamposVazios(List<String> list) {
        Boolean vazio = true;
        if (list != null && !list.isEmpty()) {
            for (String campo : list) {
                if (campo == null || campo.trim().length() == 0) {
                    vazio = false;
                }
            }
        } else {
            vazio = false;
        }
        return vazio;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPN_Background = new javax.swing.JPanel();
        jBT_Salvar = new javax.swing.JButton();
        jBT_Verificar = new javax.swing.JButton();
        jTF_Nome = new javax.swing.JTextField();
        jTF_Cpf = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cpf= new javax.swing.text.MaskFormatter("###.###.###-##"); 
            jTF_Cpf = new javax.swing.JFormattedTextField(cpf); 
        } 
        catch (Exception e){ 
        }
        jTF_Telefone = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter telefone= new javax.swing.text.MaskFormatter("(##)####-####"); 
            jTF_Telefone = new javax.swing.JFormattedTextField(telefone); 
        } 
        catch (Exception e){ 
        }
        jTF_Cep = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter cep= new javax.swing.text.MaskFormatter("#####-###"); 
            jTF_Cep = new javax.swing.JFormattedTextField(cep); 
        } 
        catch (Exception e){ 
        }
        jTF_Bairro = new javax.swing.JTextField();
        jTF_NomeCidade = new javax.swing.JTextField();
        jTF_Uf = new javax.swing.JTextField();
        jTF_Celular = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter celular= new javax.swing.text.MaskFormatter("(##)#####-####"); 
            jTF_Celular = new javax.swing.JFormattedTextField(celular); 
        } 
        catch (Exception e){ 
        }
        jTF_Complemento = new javax.swing.JTextField();
        jTF_Numero = new javax.swing.JTextField();
        jTF_Logradouro = new javax.swing.JTextField();
        jTF_Telefone2 = new javax.swing.JTextField();
        try{ 
            javax.swing.text.MaskFormatter telefone2= new javax.swing.text.MaskFormatter("(##)####-####"); 
            jTF_Telefone2 = new javax.swing.JFormattedTextField(telefone2); 
        } 
        catch (Exception e){ 
        }
        jLabel13 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLB_Fechar = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLB_ErroCep = new javax.swing.JLabel();
        jLB_Obrigatorio6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        jPN_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Salvar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_Salvar.setText("Salvar Alterações");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 480, -1, 70));

        jBT_Verificar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Verificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jBT_Verificar.setText("Verificar CEP");
        jBT_Verificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VerificarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Verificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 270, 120, 30));

        jTF_Nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_NomeActionPerformed(evt);
            }
        });
        jPN_Background.add(jTF_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 210, 30));
        jPN_Background.add(jTF_Cpf, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 210, 30));
        jPN_Background.add(jTF_Telefone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 210, 30));
        jPN_Background.add(jTF_Cep, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 210, 30));
        jPN_Background.add(jTF_Bairro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, 200, 30));
        jPN_Background.add(jTF_NomeCidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, 130, 30));
        jPN_Background.add(jTF_Uf, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 340, 100, 30));

        jTF_Celular.setName("jTF_Celular"); // NOI18N
        jPN_Background.add(jTF_Celular, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 210, 30));

        jTF_Complemento.setName("jTF_Complemento"); // NOI18N
        jPN_Background.add(jTF_Complemento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, 330, 30));
        jPN_Background.add(jTF_Numero, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 80, 30));
        jPN_Background.add(jTF_Logradouro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 390, 570, 30));

        jTF_Telefone2.setName("jTF_Telefone2"); // NOI18N
        jPN_Background.add(jTF_Telefone2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 210, 30));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Cidade");
        jPN_Background.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 340, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("Celular");
        jPN_Background.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 160, -1, -1));

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
        jPN_Background.add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Telefone 2");
        jPN_Background.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 110, -1, -1));

        jLB_ErroCep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLB_ErroCep.setForeground(new java.awt.Color(255, 0, 0));
        jLB_ErroCep.setText("ERROR_PLACEHOLDER");
        jPN_Background.add(jLB_ErroCep, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, 160, 20));

        jLB_Obrigatorio6.setForeground(new java.awt.Color(255, 0, 51));
        jLB_Obrigatorio6.setText("* - Campos Obrigatórios");
        jPN_Background.add(jLB_Obrigatorio6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 180, -1));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel9.setText("Complemento");
        jPN_Background.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 440, -1, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Número *");
        jPN_Background.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, -1, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel7.setText("UF");
        jPN_Background.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 340, -1, -1));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Bairro");
        jPN_Background.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Logradouro");
        jPN_Background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, -1, -1));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("CEP *");
        jPN_Background.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Telefone *");
        jPN_Background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("CPF *");
        jPN_Background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Nome *");
        jPN_Background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, -1, -1));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterar-cliente-bg.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_NomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_NomeActionPerformed

    }//GEN-LAST:event_jTF_NomeActionPerformed

    private void jBT_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SalvarActionPerformed
        String telefone2, celular, complemento;
        List<String> listaCampos = new ArrayList<>();

        //campos com o preenchimento opcional
        telefone2 = jTF_Telefone2.getText();
        celular = jTF_Celular.getText();
        complemento = jTF_Complemento.getText();

        //cria uma lista com os campos que sao de preenchimento obrigatorio
        listaCampos.add(jTF_Nome.getText());
        listaCampos.add(jTF_Telefone.getText());
        listaCampos.add(jTF_Cpf.getText());
        listaCampos.add(jTF_Numero.getText());
        listaCampos.add(jTF_Cep.getText());

        //verifica se algum campo obrigatorio esta vazio
        if (verificarCamposVazios(listaCampos)) {
            //apresenta uma msg de erro caso o cep nao esteja cadastrado no banco
            if (verificarCep()) {
                //apresenta uma mensagem de erro caso o cpf esteja invalido
                if (ClienteRN.validarCPF(listaCampos.get(2))) {
                    try {
                        //preenche o objeto cliente
                        Cliente cliente = new Cliente();
                        ClienteDao clienteDao = new ClienteDao();
                        cliente.setNome(capitalize(listaCampos.get(0)));
                        cliente.setTelefone(listaCampos.get(1));
                        cliente.setCpf(ajustarCpf(listaCampos.get(2)));
                        cliente.setNumeroCasa(listaCampos.get(3).trim());
                        cliente.setComplemento(complemento.trim());
                        cliente.setIdEndereco(idEndereco);
                        cliente.setDataCadastro(Datas.dataAtual());
                        cliente.setTelefone2(telefone2);
                        cliente.setCelular(celular);
                        cliente.setIdPessoa(idPessoa);
                        cliente.setIdCliente(idCliente);
                        cliente.setIdContato(idContato);

                        clienteDao.alterar(cliente);
                        JOptionPane.showMessageDialog(this, "Cliente " + listaCampos.get(0) + " ALTERADO COM SUCESSO!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "CPF inválido, por favor corrigir", "CPF INVÁLIDO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "CEP inválido, por favor corrigir", "CEP INVÁLIDO", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_SalvarActionPerformed

    private void jBT_VerificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VerificarActionPerformed
        verificarCep();
    }//GEN-LAST:event_jBT_VerificarActionPerformed

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        FormClientePedido formCli = new FormClientePedido();
        formCli.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLB_FecharMouseReleased

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
            java.util.logging.Logger.getLogger(FormAlterarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormAlterarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JButton jBT_Verificar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_ErroCep;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Obrigatorio6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JTextField jTF_Bairro;
    private javax.swing.JTextField jTF_Celular;
    private javax.swing.JTextField jTF_Cep;
    private javax.swing.JTextField jTF_Complemento;
    private javax.swing.JTextField jTF_Cpf;
    private javax.swing.JTextField jTF_Logradouro;
    private javax.swing.JTextField jTF_Nome;
    private javax.swing.JTextField jTF_NomeCidade;
    private javax.swing.JTextField jTF_Numero;
    private javax.swing.JTextField jTF_Telefone;
    private javax.swing.JTextField jTF_Telefone2;
    private javax.swing.JTextField jTF_Uf;
    // End of variables declaration//GEN-END:variables
}
