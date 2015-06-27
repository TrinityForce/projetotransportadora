package br.com.bomtransporte.util;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author JhonattanSouza_
 */
public class Tela {

    /**
     * @author JhonattanSouza_
     * Adiciona o efeito de hover nas labels que serão utilizadas no dashboard.
     * @param lb jLabelRecebida
     */
    public static void hoverEffect(final JLabel lb){
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard.png")));
            }
        });
        
        lb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                lb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/bomtransporte/imagem/btn-dashboard2.png")));
            }
        });
    }
    
    /**
     * @author JhonattanSouza_
     * Verifica todas as labels que tem na tela para ser adicionado o efeito de hover.
     * @param lb jLabelRecebida
     */
    public static void adicionarHover(JLabel... lb) {
        for (JLabel jlb1 : lb) {
            hoverEffect(jlb1);
        }
    }
    
    /**
     *
     * @param campo
     * @return
     */
    public boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

    /**
     *
     * @param container
     */
    public static void desabilitarCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField
                    || c instanceof JRadioButton) {
                c.setEnabled(false);
            }
        }
    }

    private boolean verificarNomeCampo(JTextField campo, String nomeCampo) {
        return campo.getName() != null && campo.getName().equals(nomeCampo) && !verificarCampo(campo.getText());
    }

    /**
     *
     * @param container
     * @return
     */
    public static Boolean verificarCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField) {
                JTextField jtf = (JTextField) c;
                JPasswordField jps = (JPasswordField) c;
                return jtf.getText() != null && jtf.getText().trim().length() > 0 && jps.getText() != null && jps.getText().trim().length() > 0;
            }
        }
        return false;
    }

    /**
     *
     * @param container
     */
    public void limparCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                JTextField jtf = (JTextField) c;
                jtf.setText("");
            }
        }
    }
    
    /**
     *
     * @param bt
     */
    public static void desabilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(false);
        }
    }

    /**
     *
     * @param bt
     */
    public static void habilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(true);
        }
    }

    /**
     *
     * @param jcb
     */
    public static void habilitarBotao(JComboBox jcb) {
        jcb.setEnabled(true);
    }

    /**
     *
     * @param jcb
     */
    public static void desabilitarBotao(JComboBox jcb) {
        jcb.setEnabled(false);
    }   
}
