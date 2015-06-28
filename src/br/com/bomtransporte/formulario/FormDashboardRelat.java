package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.FuncionarioDao;
import br.com.bomtransporte.dao.PedidoDao;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import br.com.bomtransporte.util.Relatorios;
import br.com.bomtransporte.util.Tela;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;

/**
 *
 * @author JhonattanSouza_
 */
public class FormDashboardRelat extends javax.swing.JFrame {
    private List lista;
    private ClienteDao clienteDao;
    private FuncionarioDao funcionarioDao;
    private PedidoDao pedidoDao;
    
    /**
     * Creates new form FormDashboardRelat
     */
    public FormDashboardRelat() {
        initComponents();
        Tela.adicionarHover(jBT_Cli,jBT_Func,jBT_Relat,jBT_Relat1,jBT_Relat2,jBT_Relat3,jBT_Relat4,jBT_Vendas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPN_Background = new javax.swing.JPanel();
        jLB_Fechar4 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jBT_Relat4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jBT_Relat3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jBT_Relat2 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jBT_Relat1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jBT_Relat = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jBT_Vendas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jBT_Func = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jBT_Cli = new javax.swing.JLabel();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/relato-icon.png"))); // NOI18N
        jPN_Background.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 330, -1, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Relatório de Clientes");
        jPN_Background.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 130, 30));

        jBT_Relat4.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Relat4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Relat4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relat4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_Relat4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_Relat4MouseExited(evt);
            }
        });
        jPN_Background.add(jBT_Relat4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 150, 150));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/pesquisar-icon.png"))); // NOI18N
        jPN_Background.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 330, -1, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("Relatório de Clientes");
        jPN_Background.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 390, 130, 30));

        jBT_Relat3.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Relat3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Relat3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relat3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_Relat3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_Relat3MouseExited(evt);
            }
        });
        jPN_Background.add(jBT_Relat3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 290, 150, 150));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/transito-icon.png"))); // NOI18N
        jPN_Background.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, -1, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Relatório de Clientes");
        jPN_Background.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, 130, 30));

        jBT_Relat2.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Relat2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Relat2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relat2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_Relat2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_Relat2MouseExited(evt);
            }
        });
        jPN_Background.add(jBT_Relat2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 150, 150));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Relatório de Clientes");
        jPN_Background.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 390, 130, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/relat-icon.png"))); // NOI18N
        jPN_Background.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 330, -1, -1));

        jBT_Relat1.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Relat1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Relat1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relat1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_Relat1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_Relat1MouseExited(evt);
            }
        });
        jPN_Background.add(jBT_Relat1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 290, 150, 150));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Relatório de Clientes");
        jPN_Background.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 130, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/salvar-icon.png"))); // NOI18N
        jPN_Background.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 170, -1, -1));

        jBT_Relat.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Relat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Relat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Relat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Relat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_RelatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_RelatMouseExited(evt);
            }
        });
        jPN_Background.add(jBT_Relat, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 130, 150, 150));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Relatório de Clientes");
        jPN_Background.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 230, 130, 30));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/senha-icon.png"))); // NOI18N
        jPN_Background.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 170, -1, -1));

        jBT_Vendas.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Vendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Vendas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Vendas.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Vendas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_VendasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_VendasMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBT_VendasMouseReleased(evt);
            }
        });
        jPN_Background.add(jBT_Vendas, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, 150, 150));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("<html>Relatório de <br/>Funcionários</html>");
        jPN_Background.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 130, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/funcionario-icon.png"))); // NOI18N
        jPN_Background.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 170, -1, -1));

        jBT_Func.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Func.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Func.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Func.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Func.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_FuncMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_FuncMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBT_FuncMouseReleased(evt);
            }
        });
        jPN_Background.add(jBT_Func, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 130, 150, 150));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/cliente-icon.png"))); // NOI18N
        jPN_Background.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Relatório de Clientes");
        jPN_Background.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 130, 30));

        jBT_Cli.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png"))); // NOI18N
        jBT_Cli.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jBT_Cli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jBT_Cli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jBT_CliMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jBT_CliMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jBT_CliMouseReleased(evt);
            }
        });
        jPN_Background.add(jBT_Cli, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 130, 150, 150));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/alterarpedido-bg.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_CliMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_CliMouseEntered
//        jBT_Cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_CliMouseEntered

    private void jBT_CliMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_CliMouseExited
//        jBT_Cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_CliMouseExited

    private void jBT_FuncMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_FuncMouseEntered
//        jBT_Func.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_FuncMouseEntered

    private void jBT_FuncMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_FuncMouseExited
//        jBT_Func.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_FuncMouseExited

    private void jBT_VendasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_VendasMouseEntered
//        jBT_Vendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_VendasMouseEntered

    private void jBT_VendasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_VendasMouseExited
//        jBT_Vendas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_VendasMouseExited

    private void jBT_RelatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_RelatMouseEntered
//        jBT_Relat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_RelatMouseEntered

    private void jBT_RelatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_RelatMouseExited
//        jBT_Relat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_RelatMouseExited

    private void jBT_Relat1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat1MouseEntered
//        jBT_Relat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_Relat1MouseEntered

    private void jBT_Relat1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat1MouseExited
//        jBT_Relat1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_Relat1MouseExited

    private void jBT_Relat2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat2MouseEntered
//        jBT_Relat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_Relat2MouseEntered

    private void jBT_Relat2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat2MouseExited
//        jBT_Relat2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_Relat2MouseExited

    private void jBT_Relat3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat3MouseEntered
//        jBT_Relat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_Relat3MouseEntered

    private void jBT_Relat3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat3MouseExited
//        jBT_Relat3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_Relat3MouseExited

    private void jBT_Relat4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat4MouseEntered
//        jBT_Relat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
    }//GEN-LAST:event_jBT_Relat4MouseEntered

    private void jBT_Relat4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_Relat4MouseExited
//        jBT_Relat4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
    }//GEN-LAST:event_jBT_Relat4MouseExited

    private void jLB_Fechar4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLB_Fechar4MouseReleased
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jLB_Fechar4MouseReleased

    private void jBT_CliMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_CliMouseReleased
        try {
            clienteDao = new ClienteDao();
            lista = clienteDao.listar();
            Relatorios.gerarRelatorio("relatoriocliente.jrxml",lista,null);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_jBT_CliMouseReleased

    private void jBT_FuncMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_FuncMouseReleased
        try {
            funcionarioDao = new FuncionarioDao();
            lista = funcionarioDao.listar();
            Relatorios.gerarRelatorio("relatoriofuncionario.jrxml",lista,null);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        }    
    }//GEN-LAST:event_jBT_FuncMouseReleased

    private void jBT_VendasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBT_VendasMouseReleased
        try {
            pedidoDao = new PedidoDao();
            lista = pedidoDao.listar();
            Relatorios.gerarRelatorio("relatoriopedidos.jrxml",lista,null);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "ERRO INESPERADO: " + ex.getMessage(), "ERRO INESPERADO.", JOptionPane.WARNING_MESSAGE);
        }    
    }//GEN-LAST:event_jBT_VendasMouseReleased

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
            java.util.logging.Logger.getLogger(FormDashboardRelat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormDashboardRelat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jBT_Cli;
    private javax.swing.JLabel jBT_Func;
    private javax.swing.JLabel jBT_Relat;
    private javax.swing.JLabel jBT_Relat1;
    private javax.swing.JLabel jBT_Relat2;
    private javax.swing.JLabel jBT_Relat3;
    private javax.swing.JLabel jBT_Relat4;
    private javax.swing.JLabel jBT_Vendas;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JLabel jLB_Fechar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPN_Background;
    // End of variables declaration//GEN-END:variables
}
