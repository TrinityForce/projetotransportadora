package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CidadeDao;
import br.com.bomtransporte.dao.PrecoDistanciaDao;
import br.com.bomtransporte.modelo.Cidade;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.PrecoDistancia;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Datas;
import javax.swing.JOptionPane;

/**
 *
 * @author JhonattanSouza_
 */
public class FormCadastrarRota extends javax.swing.JFrame {

    public FormCadastrarRota() {
        initComponents();
        preencherComboBoxSudeste();
        preencherComboPreco();
    }

    private String origemDestino(String origem, String destino) {
        return origem + "/" + destino;
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

    private void preencherComboBoxSudeste() {
        CidadeDao cidadeDao = new CidadeDao();
        try {
            limparComboBox();
            cidadeDao.listarUf().forEach(cid -> {
                Cidade cidade = (Cidade) cid;
                String uf = cidade.getUf();
                if (uf.contains("SP") || uf.contains("RJ") || uf.contains("MG") || uf.contains("ES")) {
                    jCB_Origem.addItem(cidade.getUf());
                    jCB_Destino.addItem(cidade.getUf());
                }
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void preencherComboBoxBrasil() {
        CidadeDao cidadeDao = new CidadeDao();
        try {
            limparComboBox();
            cidadeDao.listarUf().forEach(cid -> {
                Cidade cidade = (Cidade) cid;
                jCB_Origem.addItem(cidade.getUf());
                jCB_Destino.addItem(cidade.getUf());
            });
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limparComboBox() {
        jCB_Origem.removeAllItems();
        jCB_Destino.removeAllItems();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BTG_Estados = new javax.swing.ButtonGroup();
        jPN_Cadastrar = new javax.swing.JPanel();
        jFTF_Valor1 = new javax.swing.JFormattedTextField();
        Origem1 = new javax.swing.JLabel();
        Origem3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        Valor1 = new javax.swing.JLabel();
        jCB_Rotas = new javax.swing.JComboBox();
        jPN_Alterar = new javax.swing.JPanel();
        jRB_Sudeste = new javax.swing.JRadioButton();
        jRB_Brasil = new javax.swing.JRadioButton();
        Origem = new javax.swing.JLabel();
        Destino = new javax.swing.JLabel();
        Valor = new javax.swing.JLabel();
        Origem2 = new javax.swing.JLabel();
        jCB_Origem = new javax.swing.JComboBox();
        jCB_Destino = new javax.swing.JComboBox();
        jBT_Adicionar = new javax.swing.JButton();
        jFTF_Valor = new javax.swing.JFormattedTextField();
        jLB_Fechar = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Cadastrar.setBackground(new java.awt.Color(204, 204, 255));
        jPN_Cadastrar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPN_Cadastrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPN_Cadastrar.add(jFTF_Valor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 130, 30));

        Origem1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Origem1.setText("Rotas cadastradas:");
        jPN_Cadastrar.add(Origem1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, -1, -1));

        Origem3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Origem3.setText("EXCLUIR E ALTERAR ROTAS");
        jPN_Cadastrar.add(Origem3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Excluir Rota");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPN_Cadastrar.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 240, 190, 70));

        jButton2.setBackground(new java.awt.Color(0, 0, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton2.setText("Alterar Valor");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPN_Cadastrar.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 190, 70));

        Valor1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Valor1.setText("Valor:");
        jPN_Cadastrar.add(Valor1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, -1, -1));

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jPN_Cadastrar.add(jCB_Rotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 170, 40));

        getContentPane().add(jPN_Cadastrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, 340, 330));

        jPN_Alterar.setBackground(new java.awt.Color(204, 204, 255));
        jPN_Alterar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPN_Alterar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jRB_Sudeste.setBackground(new java.awt.Color(153, 153, 255));
        BTG_Estados.add(jRB_Sudeste);
        jRB_Sudeste.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jRB_Sudeste.setText("Rotas -> Sudeste");
        jRB_Sudeste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_SudesteActionPerformed(evt);
            }
        });
        jPN_Alterar.add(jRB_Sudeste, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 140, -1));

        jRB_Brasil.setBackground(new java.awt.Color(153, 153, 255));
        BTG_Estados.add(jRB_Brasil);
        jRB_Brasil.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jRB_Brasil.setText("Rotas -> Brasil");
        jRB_Brasil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRB_BrasilActionPerformed(evt);
            }
        });
        jPN_Alterar.add(jRB_Brasil, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 140, -1));

        Origem.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Origem.setText("CADASTRAR NOVA ROTA");
        jPN_Alterar.add(Origem, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        Destino.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Destino.setText("Destino:");
        jPN_Alterar.add(Destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, -1, -1));

        Valor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Valor.setText("Valor:");
        jPN_Alterar.add(Valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        Origem2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Origem2.setText("Origem:");
        jPN_Alterar.add(Origem2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jCB_Origem.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jPN_Alterar.add(jCB_Origem, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 130, -1));

        jCB_Destino.setFont(new java.awt.Font("Segoe WP Light", 0, 18)); // NOI18N
        jPN_Alterar.add(jCB_Destino, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, 130, -1));

        jBT_Adicionar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Adicionar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Adicionar.setText("Adicionar");
        jBT_Adicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarActionPerformed(evt);
            }
        });
        jPN_Alterar.add(jBT_Adicionar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 170, 60));
        jPN_Alterar.add(jFTF_Valor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 130, 30));

        getContentPane().add(jPN_Alterar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 340, 330));

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
        getContentPane().add(jLB_Fechar, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jLB_Background.setText("jLabel1");
        jLB_Background.setMaximumSize(new java.awt.Dimension(800, 600));
        jLB_Background.setMinimumSize(new java.awt.Dimension(800, 600));
        jLB_Background.setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarActionPerformed

        String origem = jCB_Origem.getSelectedItem().toString();
        String destino = jCB_Destino.getSelectedItem().toString();
        String origemDestino = origemDestino(origem, destino);
        String valor = jFTF_Valor.getText();
        try {
            if (valor != null && origemDestino != null) {
                PrecoDistancia precoDistancia = new PrecoDistancia();
                PrecoDistanciaDao pdd = new PrecoDistanciaDao();
                PrecoDistancia pdv = pdd.verificarSeExiste(origemDestino);
                if (pdv == null) {
                    precoDistancia.setAtivado(true);
                    precoDistancia.setOrigemDestinoUf(origemDestino);
                    precoDistancia.setValor(Double.parseDouble(valor));
                    pdd.inserir(precoDistancia);
                    JOptionPane.showMessageDialog(this, "ROTA CADASTRADA COM SUCESSO!");
                    preencherComboPreco();
                } else {
                    JOptionPane.showMessageDialog(this, "Rota já cadastrada no banco, para alterar o valor vá para lá ->");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Campos Necessários Vazios.");
            }
        }catch(NumberFormatException nex){
            JOptionPane.showMessageDialog(this, "CAMPO VALOR VAZIO", "CAMPO VAZIO", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jBT_AdicionarActionPerformed

    private void jRB_SudesteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_SudesteActionPerformed
        preencherComboBoxSudeste();
    }//GEN-LAST:event_jRB_SudesteActionPerformed

    private void jRB_BrasilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRB_BrasilActionPerformed
        preencherComboBoxBrasil();
    }//GEN-LAST:event_jRB_BrasilActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String[] idRota;
        idRota = jCB_Rotas.getSelectedItem().toString().split(" ");

        PrecoDistancia pcd = new PrecoDistancia();
        pcd.setAtivado(false);
        pcd.setDataDesativado(Datas.dataAtual());
        pcd.setFuncionarioQueDesativou(FuncionarioSingleton.getFuncionario().getNome());
        pcd.setIdPrecoDistancia(Integer.valueOf(idRota[0]));
        Integer opt = JOptionPane.showConfirmDialog(this, "TEM CERTEZA DE QUE DESEJA EXCLUIR ESTA ROTA?", "EXCLUIR ROTA?", JOptionPane.YES_NO_OPTION);
        if (opt == JOptionPane.YES_OPTION) {
            PrecoDistanciaDao pdd = new PrecoDistanciaDao();
            try {
                pdd.alterarExcluir(pcd);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro Inesperado. Por favor tente novamente: " + ex.getMessage(), "ERRO INESPERADO", JOptionPane.ERROR_MESSAGE);
            }
            preencherComboPreco();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLB_FecharMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_FecharMouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
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
            java.util.logging.Logger.getLogger(FormCadastrarRota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormCadastrarRota().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BTG_Estados;
    private javax.swing.JLabel Destino;
    private javax.swing.JLabel Origem;
    private javax.swing.JLabel Origem1;
    private javax.swing.JLabel Origem2;
    private javax.swing.JLabel Origem3;
    private javax.swing.JLabel Valor;
    private javax.swing.JLabel Valor1;
    private javax.swing.JButton jBT_Adicionar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jCB_Destino;
    private javax.swing.JComboBox jCB_Origem;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JFormattedTextField jFTF_Valor;
    private javax.swing.JFormattedTextField jFTF_Valor1;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar;
    private javax.swing.JPanel jPN_Alterar;
    private javax.swing.JPanel jPN_Cadastrar;
    private javax.swing.JRadioButton jRB_Brasil;
    private javax.swing.JRadioButton jRB_Sudeste;
    // End of variables declaration//GEN-END:variables
}
