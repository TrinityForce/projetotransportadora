package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.modelo.Caminhao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.util.Calc;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
public class FormPedidos extends javax.swing.JFrame {

    private PedidoDao pedidoDao;
    private Pedido pedidoSelecionado;
    private String statusPedidoSelecionado;
    private Carga carga;
    private CargaDao cargaDao;

    public FormPedidos() {
        initComponents();
        preencherTabelaPedido();
        desabilitarBotao(jBT_AlterarStatusPedido);
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void preencherTabelaPedido() {

        ArrayList dados = new ArrayList();

        String[] colunas = new String[]{"ID", "PROTOCOLO", "DATA VENDA", "DESCONTO", "STATUS"};
        List<Object> listaPedido = null;
        final List<Object> listaSelecionada;
        try {
            pedidoDao = new PedidoDao();
            switch (jCB_Status.getSelectedIndex()) {
                case 0:
                    listaPedido = pedidoDao.listar();
                    break;
                case 1:
                    listaPedido = pedidoDao.listarPedidos("Aguardando");
                    break;
                case 2:
                    listaPedido = pedidoDao.listarPedidos("Saiu para Entrega");
                    break;
                case 3:
                    listaPedido = pedidoDao.listarPedidos("Entregue");
                    break;
                case 4:
                    listaPedido = pedidoDao.listarPedidos("Carga Extraviada");
            }

            listaSelecionada = listaPedido;

            if (listaPedido != null && listaPedido.size() > 0) {
                for (Object pedidoAtual : listaPedido) {
                    Pedido pedido = (Pedido) pedidoAtual;
                    dados.add(new Object[]{pedido.getIdPedido(), pedido.getProtocolo(), pedido.getDataVenda(), pedido.getDesconto(), pedido.getStatusPedido()});
                }
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
                        List<Object> lista = listaSelecionada;
                        pedidoSelecionado = (Pedido) lista.get(jTB_Pedidos.convertRowIndexToModel(jTB_Pedidos.getSelectedRow()));
                        statusPedidoSelecionado = pedidoSelecionado.getStatusPedido();

                        habilitarBotao(jBT_AlterarStatusPedido);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormPedidos.this, "Erro genérico1: " + ex.getMessage());
                    }
                }

            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro genérico2: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPN_Background = new javax.swing.JPanel();
        jSP_Pedidos = new javax.swing.JScrollPane();
        jTB_Pedidos = new javax.swing.JTable();
        jCB_Rotas = new javax.swing.JComboBox();
        jLB_Status1 = new javax.swing.JLabel();
        jCB_Status = new javax.swing.JComboBox();
        jBT_AlterarStatusPedido = new javax.swing.JButton();
        jBT_Voltar = new javax.swing.JButton();
        jLB_Status = new javax.swing.JLabel();
        jBT_AdicionarPedidoNoCaminhao = new javax.swing.JButton();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPN_Background.add(jSP_Pedidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 700, 320));

        jCB_Rotas.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jPN_Background.add(jCB_Rotas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 190, 40));

        jLB_Status1.setText("rotas");
        jPN_Background.add(jLB_Status1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, -1, -1));

        jCB_Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Aguardando", "Saiu para Entrega", "Entregue", "Carga Extraviada" }));
        jCB_Status.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jCB_StatusItemStateChanged(evt);
            }
        });
        jCB_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCB_StatusActionPerformed(evt);
            }
        });
        jPN_Background.add(jCB_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 120, 190, 40));

        jBT_AlterarStatusPedido.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AlterarStatusPedido.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AlterarStatusPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_AlterarStatusPedido.setText("Alterar Status");
        jBT_AlterarStatusPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AlterarStatusPedidoActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_AlterarStatusPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 150, 220, 70));

        jBT_Voltar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Voltar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/retornar-icon.png"))); // NOI18N
        jBT_Voltar.setText("Voltar");
        jBT_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VoltarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, -1, 60));

        jLB_Status.setText("Status:");
        jPN_Background.add(jLB_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jBT_AdicionarPedidoNoCaminhao.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AdicionarPedidoNoCaminhao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarPedidoNoCaminhao.setText("Add ao caminhao");
        jBT_AdicionarPedidoNoCaminhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarPedidoNoCaminhaoActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_AdicionarPedidoNoCaminhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 220, 70));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/relat-background.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_AlterarStatusPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AlterarStatusPedidoActionPerformed

        if (statusPedidoSelecionado == null || statusPedidoSelecionado.trim().length() == 0) {
            JOptionPane.showMessageDialog(this, "Ocorreu um erro ao acessar o pedido!\n O status do pedido esta em branco.", "Erro", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        //cria uma list com os status do pedido
        List<String> optionList = new ArrayList<>();

        optionList.add("Saiu para entrega");
        optionList.add("Entregue");
        optionList.add("Extraviado");

        //mostra somente as opcoes necessarias de acordo com a regra de negocio
        switch (statusPedidoSelecionado) {
            case "Aguardando":
                optionList.remove(1);
                break;
            case "Saiu para entrega":
                optionList.remove(0);
                break;
            case "Extraviado":
                optionList.remove(2);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Esse pedido já foi entregue!", "PEDIDO ENTREGUE", JOptionPane.INFORMATION_MESSAGE);
                return;
        }

        Object[] options = optionList.toArray();

        //pega o status que o usuario selecionou
        Object value = JOptionPane.showInputDialog(this, "Escolha uma das opcoes",
                "Mudar status do pedido", JOptionPane.QUESTION_MESSAGE, null,
                options,
                options[0]);
        int index = optionList.indexOf(value);

        //atualiza o status no banco
        if (optionList.get(index) != null) {
            try {
                pedidoDao.update(pedidoSelecionado.getIdPedido(), optionList.get(index));
                preencherTabelaPedido();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Ocorreu um erro ao atualizar o status no banco: " + ex.getMessage(), "Erro", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBT_AlterarStatusPedidoActionPerformed

    private void jCB_StatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_StatusItemStateChanged
        desabilitarBotao(jBT_AlterarStatusPedido);
        preencherTabelaPedido();
    }//GEN-LAST:event_jCB_StatusItemStateChanged

    private void jBT_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VoltarActionPerformed

    }//GEN-LAST:event_jBT_VoltarActionPerformed

    private void jBT_AdicionarPedidoNoCaminhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarPedidoNoCaminhaoActionPerformed

        /* 
         *quantidade de cargas que nao se foi possivel adicionar ao caminhao
         */
        Integer qtdCarga = 0;

        cargaDao = new CargaDao();

        try {
            List<Object> list = cargaDao.listarCargasPorStatus(pedidoSelecionado.getIdPedido_Cli(), "Aguardando");

            //para o metodo caso nao tenha nenhuma carga com status aguardando
            if (list == null || list.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nao ha nenhuma carga para ser enviada",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            for (Object carga1 : list) {
                carga = (Carga) carga1;

                //verifica se o caminhao pode comportar a dimensao cubica da carga 
                if (carga.getDimensaoCubica() + Caminhao.getDimensaoCubicaPreenchida()< Caminhao.getDimensaoCubica()) {
                    //verifica se o caminhao pode comportar o peso da carga
                    if ((Calc.calcPesoCarga(carga.getPeso()) + Caminhao.getPesoDasCargasNoCaminhao())
                            < Caminhao.getPesoTotalSuportado()) {

                        //seta o peso da carga no caminhao
                        Caminhao.setPesoDasCargasNoCaminhao(Calc.calcPesoCarga(carga.getPeso()));
                        Caminhao.setDimensaoCubicaPreenchida(carga.getDimensaoCubica());
                        carga.setStatus("Saiu para entrega");

                        //altera o status da carga
                        cargaDao.alterar(carga);
                        System.out.println("total peso no caminhao " + Caminhao.getPesoDasCargasNoCaminhao());
                    } else {
                        JOptionPane.showMessageDialog(this, "O limite de peso do caminhao foi atingido",
                                "Erro", JOptionPane.INFORMATION_MESSAGE);
                        System.out.println("cami peso " + Caminhao.getPesoDasCargasNoCaminhao());
                        return;
                    }
                } else {
                    //aumenta o valor para cada carga que nao foi enviada para o caminhao
                    qtdCarga++;
                }
            }
            System.err.println("qtd carga "+qtdCarga);
            if (qtdCarga == 0) {
                pedidoDao.update(pedidoSelecionado.getIdPedido(), "Saiu para entrega");
                JOptionPane.showMessageDialog(this, "Todas as cargas foram adicionadas ao caminhao com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, qtdCarga + " Carga(s) nao foram adicionadas ao caminhao  !", "Erro", JOptionPane.INFORMATION_MESSAGE);
                pedidoDao.update(pedidoSelecionado.getIdPedido(), "Enviado parcialmente");
            }
            preencherTabelaPedido();
        } catch (Exception ex) {
            Logger.getLogger(FormPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBT_AdicionarPedidoNoCaminhaoActionPerformed

    private void jCB_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCB_StatusActionPerformed

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
            java.util.logging.Logger.getLogger(FormPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPedidos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_AdicionarPedidoNoCaminhao;
    private javax.swing.JButton jBT_AlterarStatusPedido;
    private javax.swing.JButton jBT_Voltar;
    private javax.swing.JComboBox jCB_Rotas;
    private javax.swing.JComboBox jCB_Status;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Status;
    private javax.swing.JLabel jLB_Status1;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JScrollPane jSP_Pedidos;
    private javax.swing.JTable jTB_Pedidos;
    // End of variables declaration//GEN-END:variables
}
