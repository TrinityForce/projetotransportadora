package br.com.bomtransporte.formulario;

import br.com.bomtransporte.dao.ClienteDao;
import br.com.bomtransporte.dao.FuncionarioDao;
import br.com.bomtransporte.modelo.FuncionarioSingleton;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JhonattanSouza_
 */
public class FormRelatorio extends javax.swing.JFrame {

    private ClienteDao clienteDao;
    private FuncionarioDao funcionarioDao;
    
    public FormRelatorio() {
        initComponents();
    }

    public void gerarRelatorio(String relat, List lista, Map parametros) throws JRException, Exception {
        JasperDesign design = JRXmlLoader.load(relat);
        JasperReport relatorio = JasperCompileManager.compileReport(design);

        JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, new JRBeanCollectionDataSource(lista));

        JasperViewer viewer = new JasperViewer(impressao, false);
        viewer.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPN_Background = new javax.swing.JPanel();
        jBT_Voltar = new javax.swing.JButton();
        jBT_Imprimir = new javax.swing.JButton();
        jSP_Relatorios = new javax.swing.JScrollPane();
        jLT_Relatorios = new javax.swing.JList();
        jLB_Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPN_Background.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jBT_Voltar.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Voltar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/retornar-icon.png"))); // NOI18N
        jBT_Voltar.setText("Voltar");
        jBT_Voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_VoltarActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Voltar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 490, -1, 60));

        jBT_Imprimir.setBackground(new java.awt.Color(0, 0, 0));
        jBT_Imprimir.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jBT_Imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/icones/imprimir-icon.png"))); // NOI18N
        jBT_Imprimir.setText("Imprimir");
        jBT_Imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBT_ImprimirActionPerformed(evt);
            }
        });
        jPN_Background.add(jBT_Imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 490, -1, 60));

        jLT_Relatorios.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLT_Relatorios.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "RELATÓRIO DE CLIENTES POR ID", "RELATÓRIO DE FUNCIONÁRIOS POR ID ", "RELATÓRIO DE VENDAS", "RELATÓRIO DE CARGAS" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jLT_Relatorios.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jSP_Relatorios.setViewportView(jLT_Relatorios);

        jPN_Background.add(jSP_Relatorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 90, 650, 390));

        jLB_Background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/relatorios-bg.png"))); // NOI18N
        jPN_Background.add(jLB_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        getContentPane().add(jPN_Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jBT_ImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_ImprimirActionPerformed
        Integer itemSelecionado = jLT_Relatorios.getSelectedIndex();
        List lista;
        try {
            switch (itemSelecionado) {
                case 0:
                    clienteDao = new ClienteDao();
                    lista = clienteDao.listar();
                    gerarRelatorio("relatoriocliente.jrxml",lista,null);
                    break;
                case 1:
                    funcionarioDao = new FuncionarioDao();
                    lista = funcionarioDao.listar();
                    gerarRelatorio("relatoriofuncionario.jrxml",lista,null);
                    break;
                default:
                    JOptionPane.showMessageDialog(this,"SELECIONAR UM ITEM DA LISTA","ATENÇÃO!",JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }//GEN-LAST:event_jBT_ImprimirActionPerformed

    private void jBT_VoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBT_VoltarActionPerformed
        FuncionarioRN.chamarTela(FuncionarioSingleton.getFuncionario().getUsuario().getIdPerfil(), this);
    }//GEN-LAST:event_jBT_VoltarActionPerformed

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
            java.util.logging.Logger.getLogger(FormRelatorio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormRelatorio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBT_Imprimir;
    private javax.swing.JButton jBT_Voltar;
    private javax.swing.JLabel jLB_Background;
    private javax.swing.JList jLT_Relatorios;
    private javax.swing.JPanel jPN_Background;
    private javax.swing.JScrollPane jSP_Relatorios;
    // End of variables declaration//GEN-END:variables
}
