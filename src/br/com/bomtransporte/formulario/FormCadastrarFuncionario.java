package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.FuncionarioDao;
import br.com.bomtransporte.modelo.Funcionario;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Usuario;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Datas;
import br.com.bomtransporte.util.Tela;
import br.com.bomtransporte.util.ValidarEmail;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author JhonattanSouza_
 */
public class FormCadastrarFuncionario extends javax.swing.JFrame {

    private FuncionarioDao funcionarioDao;
    private Funcionario funcionarioSelecionado;
    private Integer idUsuario, idPessoa, idFuncionario;

    /**
     * Creates new form FormCadastrarFuncionario
     */
    public FormCadastrarFuncionario() {
        initComponents();
        desabilitarCampos();
        preencherTabela();
    }

    private boolean verificarRadio(JRadioButton rb1, JRadioButton rb2) {
        return rb1.isSelected() == true || rb2.isSelected() == true;
    }

    private void limparCampos() {
        jTF_Cargo.setText("");
        jTF_Login.setText("");
        jTF_Email.setText("");
        jPS_Senha.setText("");
        BG_Perfil.clearSelection();
    }

    private void habilitarCampos() {
        jTF_Nome.setEnabled(true);
        jTF_Cargo.setEnabled(true);
        jTF_Login.setEnabled(true);
        jTF_Email.setEnabled(true);
        jPS_Senha.setEnabled(true);
        jRB_Administrador.setEnabled(true);
        jRB_Funcionario.setEnabled(true);
    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void desabilitarCampos() {
        jTF_Cargo.setEnabled(false);
        jTF_Login.setEnabled(false);
        jTF_Email.setEnabled(false);
        jPS_Senha.setEnabled(false);
        jRB_Administrador.setEnabled(false);
        jRB_Funcionario.setEnabled(false);
        jBT_Salvar.setEnabled(false);
        jBT_Alterar.setEnabled(false);
        jBT_Excluir.setEnabled(false);
        jTF_Nome.setEnabled(false);
    }

    private void preencherTabela() {

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "NOME", "CARGO", "DATA CADASTRO", "E-MAIL", "LOGIN"};

        try {
            funcionarioDao = new FuncionarioDao();
            final List<Object> listaFuncionario = funcionarioDao.listar();

            if (listaFuncionario != null && listaFuncionario.size() > 0) {
                listaFuncionario.forEach((Object funcionarioAtual) -> {
                    Funcionario funcionario = (Funcionario) funcionarioAtual;
                    dados.add(new Object[]{funcionario.getIdFuncionario(), funcionario.getNome(),
                        funcionario.getCargo(), funcionario.getDataCadastro(), funcionario.getUsuario().getEmail(),
                        funcionario.getUsuario().getLogin()});
                });
            }

            ModeloTabela modTabela = new ModeloTabela(dados, colunas);
            jTableFuncionario.setModel(modTabela);
            jTableFuncionario.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableFuncionario.getColumnModel().getColumn(0).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(1).setPreferredWidth(180);
            jTableFuncionario.getColumnModel().getColumn(1).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTableFuncionario.getColumnModel().getColumn(2).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(3).setPreferredWidth(100);
            jTableFuncionario.getColumnModel().getColumn(3).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTableFuncionario.getColumnModel().getColumn(4).setResizable(false);
            jTableFuncionario.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTableFuncionario.getColumnModel().getColumn(5).setResizable(false);
            jTableFuncionario.getTableHeader().setReorderingAllowed(false);
            jTableFuncionario.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            jTableFuncionario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

            jTableFuncionario.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    try {

                        List<Object> lista = funcionarioDao.listar();
                        funcionarioSelecionado = (Funcionario) lista.
                                get(jTableFuncionario.convertRowIndexToModel(jTableFuncionario.getSelectedRow()));

                        jTF_Nome.setText(String.valueOf(funcionarioSelecionado.getNome()));
                        jTF_Cargo.setText(funcionarioSelecionado.getCargo());
                        jTF_Email.setText(funcionarioSelecionado.getUsuario().getEmail());
                        jTF_Login.setText(funcionarioSelecionado.getUsuario().getLogin());
                        Integer idPerfil = funcionarioSelecionado.getUsuario().getIdPerfil();
                        idPessoa = funcionarioSelecionado.getIdPessoa();
                        idUsuario = funcionarioSelecionado.getUsuario().getIdUsuario();
                        idFuncionario = funcionarioSelecionado.getIdFuncionario();
                        if (idPerfil == 1) {
                            jRB_Administrador.setSelected(true);
                        }
                        if (idPerfil == 2) {
                            jRB_Funcionario.setSelected(true);
                        }

                        habilitarCampos();
                        habilitarBotao(jBT_Alterar);
                        habilitarBotao(jBT_Excluir);
                        desabilitarBotao(jBT_Salvar);
                        jPS_Senha.setEnabled(false);
                        
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormCadastrarFuncionario.this, "Erro Inesperado: \n" + ex.getMessage());
                    }
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BG_Perfil = new javax.swing.ButtonGroup();
        jPN_Background = new javax.swing.JPanel();
        jSP_TabelaFuncionario = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        jBT_Novo = new javax.swing.JButton();
        jBT_Excluir = new javax.swing.JButton();
        jBT_Salvar = new javax.swing.JButton();
        jBT_Alterar = new javax.swing.JButton();
        jRB_Administrador = new javax.swing.JRadioButton();
        jRB_Funcionario = new javax.swing.JRadioButton();
        jPS_Senha = new javax.swing.JPasswordField();
        jTF_Email = new javax.swing.JTextField();
        jTF_Login = new javax.swing.JTextField();
        jTF_Cargo = new javax.swing.JTextField();
        jTF_Nome = new javax.swing.JTextField();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Texto6 = new javax.swing.JLabel();
        jLB_Texto5 = new javax.swing.JLabel();
        jLB_Texto4 = new javax.swing.JLabel();
        jLB_Texto = new javax.swing.JLabel();
        jLB_Texto1 = new javax.swing.JLabel();
        jLB_Texto2 = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTableFuncionario.setModel(new javax.swing.table.DefaultTableModel(
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
        jSP_TabelaFuncionario.setViewportView(jTableFuncionario);

        jPN_Background.add(jSP_TabelaFuncionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 380, 690, 170));

        jBT_Novo.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Novo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/new-icon.png"))); // NOI18N
        jBT_Novo.setText("Novo");
        jBT_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_NovoActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Novo, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 70, 150, 90));

        jBT_Excluir.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/excluir-icon.png"))); // NOI18N
        jBT_Excluir.setText("Excluir");
        jBT_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ExcluirActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 150, 90));

        jBT_Salvar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/salvar-icon.png"))); // NOI18N
        jBT_Salvar.setText("Salvar");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 150, 90));

        jBT_Alterar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_Alterar.setText("Alterar");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 180, 150, 90));

        BG_Perfil.add(jRB_Administrador);
        jRB_Administrador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRB_Administrador.setText("Administrador");
        jPN_Background.add(jRB_Administrador, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 290, -1, -1));

        BG_Perfil.add(jRB_Funcionario);
        jRB_Funcionario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRB_Funcionario.setText("Funcionário");
        jPN_Background.add(jRB_Funcionario, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, -1, -1));
        jPN_Background.add(jPS_Senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 250, 30));

        jTF_Email.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Email, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 250, 30));

        jTF_Login.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 250, 30));

        jTF_Cargo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Cargo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 250, 30));

        jTF_Nome.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Nome, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 250, 30));

        jLB_Fechar.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLB_Fechar.setForeground(new java.awt.Color(255, 255, 255));
        jLB_Fechar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLB_Fechar.setText("X");
        jLB_Fechar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLB_Fechar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jLB_FecharMouseReleased(evt);
            }
        });
        jPN_Background.add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLB_Texto6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto6.setText("E-mail");
        jPN_Background.add(jLB_Texto6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, -1, -1));

        jLB_Texto5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto5.setText("Perfil");
        jPN_Background.add(jLB_Texto5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 290, -1, -1));

        jLB_Texto4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto4.setText("Senha");
        jPN_Background.add(jLB_Texto4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, -1, -1));

        jLB_Texto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto.setText("Nome");
        jPN_Background.add(jLB_Texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        jLB_Texto1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto1.setText("Cargo");
        jPN_Background.add(jLB_Texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        jLB_Texto2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto2.setText("Login");
        jPN_Background.add(jLB_Texto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/cadastrar-funcionario-bg.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_FecharMouseReleased

    private void jBT_NovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_NovoActionPerformed
        limparCampos();
        habilitarCampos();
        habilitarBotao(jBT_Salvar);
        desabilitarBotao(jBT_Alterar);
        desabilitarBotao(jBT_Excluir);
    }//GEN-LAST:event_jBT_NovoActionPerformed

    private void jBT_SalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_SalvarActionPerformed
        String nome = jTF_Nome.getText();
        String cargo = jTF_Cargo.getText();
        String login = jTF_Login.getText();
        String senha = jPS_Senha.getText();
        String email = jTF_Email.getText();
        String dataCadastro = Datas.dataAtual();
        Integer idPerfil;
        
        try {
            if (Tela.verificarCampos(jPN_Background) && verificarRadio(jRB_Administrador, jRB_Funcionario)) {
                ValidarEmail ve = new ValidarEmail();
                if (ve.validar(email)) {
                    idPerfil = (jRB_Administrador.isSelected()) ? 1 : 2;

                    Funcionario funcionario = new Funcionario();
                    Usuario usuario = new Usuario();

                    funcionario.setUsuario(usuario);
                    funcionario.setNome(nome);
                    funcionario.setDataCadastro(dataCadastro);
                    funcionario.getUsuario().setLogin(login);
                    funcionario.getUsuario().setSenha(senha);
                    funcionario.getUsuario().setAlterarSenha(true);
                    funcionario.getUsuario().setIdPerfil(idPerfil);
                    funcionario.setCargo(cargo);
                    funcionario.getUsuario().setEmail(email);

                    funcionarioDao.inserir(funcionario);

                    desabilitarCampos();
                    limparCampos();
                    preencherTabela();

                    JOptionPane.showMessageDialog(this, "Funcionário incluido com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "E-mail inválido, por favor corrigir", "E-MAIL INVÁLIDO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_SalvarActionPerformed

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
        String nome = jTF_Nome.getText();
        String cargo = jTF_Cargo.getText();
        String login = jTF_Login.getText();
        String email = jTF_Email.getText();
        Integer idPerfil;

        try {
            if (Tela.verificarCampos(jPN_Background) && verificarRadio(jRB_Administrador, jRB_Funcionario)) {
                ValidarEmail ve = new ValidarEmail();
                if (ve.validar(email)) {
                    idPerfil = (jRB_Administrador.isSelected()) ? 1 : 2;

                    Funcionario funcionario = new Funcionario();
                    Usuario usuario = new Usuario();

                    funcionario.setUsuario(usuario);
                    funcionario.getUsuario().setIdUsuario(idUsuario);
                    funcionario.setIdPessoa(idPessoa);
                    funcionario.setIdFuncionario(idFuncionario);
                    funcionario.setNome(nome);
                    funcionario.getUsuario().setLogin(login);
                    funcionario.getUsuario().setIdPerfil(idPerfil);
                    funcionario.setCargo(cargo);
                    funcionario.getUsuario().setEmail(email);

                    funcionarioDao.alterar(funcionario);

                    desabilitarCampos();
                    limparCampos();
                    preencherTabela();

                    JOptionPane.showMessageDialog(this, "Funcionário alterado com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "E-mail inválido, por favor corrigir", "E-MAIL INVÁLIDO", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos necessários em branco.", "CAMPOS EM BRANCO", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_AlterarActionPerformed

    private void jBT_ExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ExcluirActionPerformed
        try {
            Integer opt = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja EXCLUIR este funcionário do sistema? \nEsta alteração não poderá ser revertida.", "EXCLUIR?", JOptionPane.YES_NO_OPTION);
            if (opt == JOptionPane.YES_OPTION) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(idFuncionario);

                funcionarioDao.excluir(funcionario);

                desabilitarCampos();
                limparCampos();
                preencherTabela();
                JOptionPane.showMessageDialog(this, "Funcionário excluído com sucesso!", "SUCESSO", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente" + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_ExcluirActionPerformed

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
            java.util.logging.Logger.getLogger(FormCadastrarFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormCadastrarFuncionario().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BG_Perfil;
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_Novo;
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Texto;
    private javax.swing.JLabel jLB_Texto1;
    private javax.swing.JLabel jLB_Texto2;
    private javax.swing.JLabel jLB_Texto4;
    private javax.swing.JLabel jLB_Texto5;
    private javax.swing.JLabel jLB_Texto6;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JPasswordField jPS_Senha;
    private javax.swing.JRadioButton jRB_Administrador;
    private javax.swing.JRadioButton jRB_Funcionario;
    private javax.swing.JScrollPane jSP_TabelaFuncionario;
    private javax.swing.JTextField jTF_Cargo;
    private javax.swing.JTextField jTF_Email;
    private javax.swing.JTextField jTF_Login;
    private javax.swing.JTextField jTF_Nome;
    private javax.swing.JTable jTableFuncionario;
    // End of variables declaration//GEN-END:variables
}
