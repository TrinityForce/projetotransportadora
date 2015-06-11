/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetobt.formulario;

import br.com.projetobt.dao.FuncionarioDao;
import br.com.projetobt.modelos.Funcionario;
import br.com.projetobt.modelos.ModeloTabela;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author JhonattanSouza_
 */
public class FormPesquisa extends javax.swing.JFrame {
    private FuncionarioDao funcionarioDao;
    private Funcionario funcionarioSelecionado;
    private Integer idUsuario, idPessoa, idFuncionario;
    
    /**
     * Creates new form FormPesquisa
     */
    public FormPesquisa() {
        initComponents();
        preencherTabela();
    }

    private void preencherTabela() {

        ArrayList dados = new ArrayList();
        String[] colunas = new String[]{"ID", "NOME", "CARGO", "DATA CADASTRO", "E-MAIL", "LOGIN"};

        try {
            funcionarioDao = new FuncionarioDao();
            final List<Object> listaFuncionario = funcionarioDao.consultarFuncionarioPorNome(txtNome.getText());

            //Verifica se a lista está preenchida
            if (listaFuncionario != null && listaFuncionario.size() > 0) {
                //Percorre a lista
                for (Object funcionarioAtual : listaFuncionario) {
                    Funcionario funcionario = (Funcionario) funcionarioAtual;
                    dados.add(new Object[]{funcionario.getIdFuncionario(), funcionario.getNome(), funcionario.getCargo(), funcionario.getDataCadastro(), funcionario.getUsuario().getEmail(), funcionario.getUsuario().getLogin()});
                }
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
                     
                        Integer idPerfil = funcionarioSelecionado.getUsuario().getPerfil().getIdPerfil();
                        idPessoa = funcionarioSelecionado.getIdPessoa();
                        idUsuario = funcionarioSelecionado.getUsuario().getIdUsuario();
                        idFuncionario = funcionarioSelecionado.getIdFuncionario();
                        

                    } catch (SQLException sqlex) {
                        JOptionPane.showMessageDialog(FormPesquisa.this, "Erro no Banco de Dados: " + sqlex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormPesquisa.this, "Erro genérico1: " + ex.getMessage());
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

        jSP_Teste = new javax.swing.JScrollPane();
        jTableFuncionario = new javax.swing.JTable();
        txtNome = new javax.swing.JTextField();
        btnConsultar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
        jSP_Teste.setViewportView(jTableFuncionario);

        btnConsultar.setText("jButton1");
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConsultar))
                    .addComponent(jSP_Teste, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar))
                .addGap(18, 18, 18)
                .addComponent(jSP_Teste, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        try {
            preencherTabela();
        } catch (Exception ex) {
            Logger.getLogger(FormPesquisa.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

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
            java.util.logging.Logger.getLogger(FormPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPesquisa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPesquisa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsultar;
    private javax.swing.JScrollPane jSP_Teste;
    private javax.swing.JTable jTableFuncionario;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
