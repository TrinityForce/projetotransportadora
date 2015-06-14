package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Tela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
public class FormManterCarga extends javax.swing.JFrame {

    private CargaDao cargaDao;
    private Carga cargaSelecionada;
    private Tela t;

    public FormManterCarga() {
        initComponents();
        desabilitarCampos();
        preencherTabela();
    }

    private boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void limparCampos() {
        jTF_id.setText("");
        jTF_Descricao.setText("");
        jTF_Peso.setText("");
        jTF_Quantidade.setText("");
    }

    private void habilitarCampos() {
        jTF_Descricao.setEnabled(true);
        jTF_Peso.setEnabled(true);
        jTF_Quantidade.setEnabled(true);
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
                        JOptionPane.showMessageDialog(FormManterCarga.this, "Erro Inesperado: \n" + ex.getMessage());
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

        jPN_Background = new javax.swing.JPanel();
        jSP_Cargas = new javax.swing.JScrollPane();
        jTB_Cargas = new javax.swing.JTable();
        jTF_id = new javax.swing.JTextField();
        jTF_Descricao = new javax.swing.JTextField();
        jTF_Peso = new javax.swing.JTextField();
        jTF_Quantidade = new javax.swing.JTextField();
        jBT_Alterar = new javax.swing.JButton();
        jBT_Salvar = new javax.swing.JButton();
        jBT_Novo = new javax.swing.JButton();
        jBT_Excluir = new javax.swing.JButton();
        jLB_Texto = new javax.swing.JLabel();
        jLB_Texto1 = new javax.swing.JLabel();
        jLB_Texto2 = new javax.swing.JLabel();
        jLB_Texto3 = new javax.swing.JLabel();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPN_Background.add(jSP_Cargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 700, 240));

        jTF_id.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTF_id.setEnabled(false);
        jPN_Background.add(jTF_id, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 40, 30));

        jTF_Descricao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Descricao, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 250, 30));

        jTF_Peso.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Peso, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 250, 30));

        jTF_Quantidade.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Quantidade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 250, 30));

        jBT_Alterar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Alterar.setText("Alterar");
        jBT_Alterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 190, 150, 90));

        jBT_Salvar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Salvar.setText("Salvar");
        jBT_Salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_SalvarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Salvar, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 80, 150, 90));

        jBT_Novo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Novo.setText("Novo");
        jBT_Novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_NovoActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Novo, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 150, 90));

        jBT_Excluir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Excluir.setText("Excluir");
        jBT_Excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ExcluirActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Excluir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 190, 150, 90));

        jLB_Texto.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto.setText("ID");
        jPN_Background.add(jLB_Texto, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        jLB_Texto1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto1.setText("Descrição");
        jPN_Background.add(jLB_Texto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, -1, -1));

        jLB_Texto2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto2.setText("Peso");
        jPN_Background.add(jLB_Texto2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        jLB_Texto3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Texto3.setText("Quantidade");
        jPN_Background.add(jLB_Texto3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, 100, -1));

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
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarActionPerformed
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
    }//GEN-LAST:event_jBT_AlterarActionPerformed

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

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_FecharMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the WINDOWS look and feel */
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
            java.util.logging.Logger.getLogger(FormManterCarga.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormManterCarga().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Alterar;
    private javax.swing.JButton jBT_Excluir;
    private javax.swing.JButton jBT_Novo;
    private javax.swing.JButton jBT_Salvar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JLabel jLB_Texto;
    private javax.swing.JLabel jLB_Texto1;
    private javax.swing.JLabel jLB_Texto2;
    private javax.swing.JLabel jLB_Texto3;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JScrollPane jSP_Cargas;
    private javax.swing.JTable jTB_Cargas;
    private javax.swing.JTextField jTF_Descricao;
    private javax.swing.JTextField jTF_Peso;
    private javax.swing.JTextField jTF_Quantidade;
    private javax.swing.JTextField jTF_id;
    // End of variables declaration//GEN-END:variables
}
