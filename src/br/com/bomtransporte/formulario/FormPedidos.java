package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.CargaDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.dao.VeiculoDao;
import br.com.bomtransporte.modelo.Caminhao;
import br.com.bomtransporte.modelo.Carga;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.modelo.ModeloTabela;
import br.com.bomtransporte.modelo.Pedido;
import br.com.bomtransporte.modelo.Veiculo;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Calc;
import br.com.bomtransporte.util.Validacao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Romildo
 */
public class FormPedidos extends javax.swing.JFrame {

    public static Integer idPedido;
    private PedidoDao pedidoDao;
    private Pedido pedidoSelecionado;
    private String statusPedidoSelecionado;
    private Carga carga;
    private CargaDao cargaDao;
    private VeiculoDao veiculoDao;

    /**
     *
     */
    public FormPedidos() {
        initComponents();
        preencherTabelaPedido();
        desabilitarBotao(jBT_AdicionarPedidoNoCaminhao);
        desabilitarBotao(jBT_VisualizarCargas);
        jBT_Protocolo.setVisible(false);
        jTF_Protocolo.setVisible(false);
        verificarVeiculos();
    }

    private void verificarVeiculos() {
        try {
            veiculoDao = new VeiculoDao();
            if (veiculoDao.listarVeiculosAtivos() == null || veiculoDao.listarVeiculosAtivos().size() <= 0) {
                jLB_Mensagem.setText("Veículos Disponíveis na Garagem: 0");
            } else {
                jLB_Mensagem.setText("Veículos Disponíveis na Garagem: " + veiculoDao.listarVeiculosAtivos().size());
            }
        } catch (Exception ex) {

        }
    }

    private void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }

    private void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    private void preencherTabelaPedido() {

        ArrayList dados = new ArrayList();
        veiculoDao = new VeiculoDao();
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
                    break;
                case 5:
                    listaPedido = pedidoDao.listarPedidosProtocolo(jTF_Protocolo.getText());
                    break;
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
                        idPedido = pedidoSelecionado.getIdPedido_Cli();
                        habilitarBotao(jBT_VisualizarCargas);
                        if (veiculoDao.listarVeiculosAtivos().size() > 0 && veiculoDao.listarVeiculosAtivos() != null) {
                            habilitarBotao(jBT_AdicionarPedidoNoCaminhao);
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(FormPedidos.this, "Erro genérico1: " + ex.getMessage());
                        ex.printStackTrace(System.err);
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
        jLB_Mensagem = new javax.swing.JLabel();
        jSP_Pedidos = new javax.swing.JScrollPane();
        jTB_Pedidos = new javax.swing.JTable();
        jBT_VisualizarCargas = new javax.swing.JButton();
        jLB_Fechar4 = new javax.swing.JLabel();
        jCB_Status = new javax.swing.JComboBox();
        jLB_Status = new javax.swing.JLabel();
        jBT_AdicionarPedidoNoCaminhao = new javax.swing.JButton();
        jTF_Protocolo = new javax.swing.JTextField();
        jBT_Protocolo = new javax.swing.JButton();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLB_Mensagem.setFont(new java.awt.Font("Segoe WP Light", 0, 18)); // NOI18N
        jLB_Mensagem.setForeground(new java.awt.Color(204, 0, 51));
        jLB_Mensagem.setText("Veículos Disponíveis na Garagem: 0");
        jPN_Background.add(jLB_Mensagem, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 80, 290, 40));

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

        jBT_VisualizarCargas.setBackground(new java.awt.Color(0, 0, 0));
        jBT_VisualizarCargas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_VisualizarCargas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/alterar-icon.png"))); // NOI18N
        jBT_VisualizarCargas.setText("Visualizar Cargas");
        jBT_VisualizarCargas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VisualizarCargasActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_VisualizarCargas, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 130, 220, 70));

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
        jPN_Background.add(jLB_Fechar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 0, 40, 40));

        jCB_Status.setFont(new java.awt.Font("Segoe WP SemiLight", 0, 18)); // NOI18N
        jCB_Status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Aguardando", "Saiu para Entrega", "Entregue", "Carga Extraviada", "Protocolo" }));
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
        jPN_Background.add(jCB_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 190, 40));

        jLB_Status.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLB_Status.setText("Status:");
        jPN_Background.add(jLB_Status, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        jBT_AdicionarPedidoNoCaminhao.setBackground(new java.awt.Color(0, 0, 0));
        jBT_AdicionarPedidoNoCaminhao.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_AdicionarPedidoNoCaminhao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/add-icon.png"))); // NOI18N
        jBT_AdicionarPedidoNoCaminhao.setText("<html>Adicionar Carga <br/>ao Caminhão</html>");
        jBT_AdicionarPedidoNoCaminhao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_AdicionarPedidoNoCaminhaoActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_AdicionarPedidoNoCaminhao, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 130, 220, 70));

        jTF_Protocolo.setFont(new java.awt.Font("Segoe WP", 0, 14)); // NOI18N
        jPN_Background.add(jTF_Protocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 180, 120, 40));

        jBT_Protocolo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/search-pequeno-icon.png"))); // NOI18N
        jBT_Protocolo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ProtocoloActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Protocolo, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 70, 40));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/relat-background.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jCB_StatusItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jCB_StatusItemStateChanged
        preencherTabelaPedido();
        if (jCB_Status.getSelectedIndex() == 5){
            jBT_Protocolo.setVisible(true);
            jTF_Protocolo.setVisible(true);
        }else{
            jBT_Protocolo.setVisible(false);
            jTF_Protocolo.setVisible(false);
        }
    }//GEN-LAST:event_jCB_StatusItemStateChanged

    private void jBT_AdicionarPedidoNoCaminhaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_AdicionarPedidoNoCaminhaoActionPerformed
        /* 
         *quantidade de cargas que nao se foi possivel adicionar ao caminhao
         */
        Integer qtdCarga = 0;

        //quantidade de veiculos que tem o mesmo destino que a carga
        Integer cont = 0;

        cargaDao = new CargaDao();

        try {
            //lista com todas as cargas do pedido com status "aguardando"
            List<Object> listCarga = cargaDao.listarCargasPorStatus(pedidoSelecionado.getIdPedido_Cli(), "Aguardando");
            //lista com todos os veiculos ativos (status:arguardando)
            List<Object> listVeiculo = veiculoDao.listarVeiculosAtivos();

            //para o metodo caso nao tenha nenhuma carga com status aguardando
            if (listCarga == null || listCarga.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nao ha nenhuma carga para ser enviada",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //para o metodo caso nao tenha nenhum caminhao  com status aguardando
            if (listVeiculo == null || listVeiculo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nao ha nenhum caminhao ativo!",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //verifica se tem algum veiculo que vai para o mesmo destino do pedido
            for (Object v : listVeiculo) {
                Veiculo veiculo;
                veiculo = (Veiculo) v;
                if (veiculo.getDestino() != null) {

                    System.err.println(Validacao.retornarDestinoUF(pedidoSelecionado.getDestinoUF()));
                    if (veiculo.getDestino().equals(Validacao.retornarDestinoUF(pedidoSelecionado.getDestinoUF()))) {
                        cont++;
                    }
                }
                if (veiculo.getDestino() == null) {
                    veiculoDao.updateDestino(Validacao.retornarDestinoUF(pedidoSelecionado.getDestinoUF()), veiculo.getIdVeiculo());
                    cont++;
                }

            }
            //se nao tiver nenhum veiculo que vai para o mesmo destino do pedido mostra a msg de erro
            if (cont == 0) {
                JOptionPane.showMessageDialog(this, "Nao ha nenhum veiculo que vai para este destino",
                        "Erro", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            //percorre a lista de carga
            for (Object v : listVeiculo) {

                Veiculo veiculo = (Veiculo) v;

                for (Iterator<Object> iter = listCarga.iterator(); iter.hasNext();) {
                    carga = (Carga) iter.next();

                    //verifica se o caminhao pode comportar a dimensao cubica da carga 
                    if (carga.getDimensaoCubica() + veiculo.getTotalPreenchido() < Caminhao.getDimensaoCubica()) {
                        //verifica se o caminhao pode comportar o peso da carga
                        if ((Calc.calcPesoCarga(carga.getPeso()) + veiculo.getPesoPreenchido())
                                < Caminhao.getPesoTotalSuportado()) {

                            carga.setIdVeiculo(veiculo.getIdVeiculo());
                            carga.setStatus("Saiu para entrega");

                            veiculo.setPesoPreenchido(Calc.calcPesoCarga(carga.getPeso()) + veiculo.getPesoPreenchido());
                            veiculo.setTotalPreenchido(carga.getDimensaoCubica() + veiculo.getTotalPreenchido());

                            cargaDao.alterar(carga);
                            veiculoDao = new VeiculoDao();
                            veiculoDao.alterar(veiculo);
                            pedidoDao.update(pedidoSelecionado.getIdPedido(), "Saiu para entrega");
//                            System.out.println("id do veiculo " + veiculo.getIdVeiculo());
//                            System.out.println("bagulho concluido com sucesso");
                            JOptionPane.showMessageDialog(this, "CARGAS SAÍNDO PRA ENTREGA COM SUCESSO!","SUCESSO",JOptionPane.INFORMATION_MESSAGE);
                        }
                    }

                }

            }

//            //verifica se o caminhao pode comportar a dimensao cubica da carga 
//            if (carga.getDimensaoCubica() + Caminhao.getDimensaoCubicaPreenchida() < Caminhao.getDimensaoCubica()) {
//                //verifica se o caminhao pode comportar o peso da carga
//                if ((Calc.calcPesoCarga(carga.getPeso()) + Caminhao.getPesoDasCargasNoCaminhao())
//                        < Caminhao.getPesoTotalSuportado()) {
//
//                    //seta o peso da carga no caminhao
//                    Caminhao.setPesoDasCargasNoCaminhao(Calc.calcPesoCarga(carga.getPeso()));
//                    Caminhao.setDimensaoCubicaPreenchida(carga.getDimensaoCubica());
//                    carga.setStatus("Saiu para entrega");
//
//                    //altera o status da carga
//                    cargaDao.alterar(carga);
//                    System.out.println("total peso no caminhao " + Caminhao.getPesoDasCargasNoCaminhao());
//                } else {
//                    JOptionPane.showMessageDialog(this, "O limite de peso do caminhao foi atingido",
//                            "Erro", JOptionPane.INFORMATION_MESSAGE);
//                    System.out.println("cami peso " + Caminhao.getPesoDasCargasNoCaminhao());
//                    return;
//
//                }
//            } else {
//                //aumenta o valor para cada carga que nao foi enviada para o caminhao
//                qtdCarga++;
//            }
//       System.err.println("qtd carga " + qtdCarga);
//            
//        if (qtdCarga == 0) {
//            pedidoDao.update(pedidoSelecionado.getIdPedido(), "Saiu para entrega");
//            JOptionPane.showMessageDialog(this, "Todas as cargas foram adicionadas ao caminhao com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
//        } else {
//            JOptionPane.showMessageDialog(this, qtdCarga + " Carga(s) nao foram adicionadas ao caminhao  !", "Erro", JOptionPane.INFORMATION_MESSAGE);
//            pedidoDao.update(pedidoSelecionado.getIdPedido(), "Enviado parcialmente");
//        }
            preencherTabelaPedido();
        } catch (Exception ex) {
            Logger.getLogger(FormPedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBT_AdicionarPedidoNoCaminhaoActionPerformed

    private void jCB_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCB_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCB_StatusActionPerformed

    private void jLB_Fechar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar4MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar4MouseReleased

    private void jBT_VisualizarCargasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VisualizarCargasActionPerformed
        FormVisualizarCargas formCarga = new FormVisualizarCargas();
        formCarga.setVisible(true);
    }//GEN-LAST:event_jBT_VisualizarCargasActionPerformed

    private void jBT_ProtocoloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ProtocoloActionPerformed
        preencherTabelaPedido();
    }//GEN-LAST:event_jBT_ProtocoloActionPerformed

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
            java.util.logging.Logger.getLogger(FormPedidos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JButton jBT_Protocolo;
    private javax.swing.JButton jBT_VisualizarCargas;
    private javax.swing.JComboBox jCB_Status;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar4;
    private javax.swing.JLabel jLB_Mensagem;
    private javax.swing.JLabel jLB_Status;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JScrollPane jSP_Pedidos;
    private javax.swing.JTable jTB_Pedidos;
    private javax.swing.JTextField jTF_Protocolo;
    // End of variables declaration//GEN-END:variables
}
