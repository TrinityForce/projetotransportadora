package br.com.bomtransporte.util;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author JhonattanSouza_
 */
public class Tela {

    public boolean verificarCampo(String campo) {
        return campo != null && campo.trim().length() > 0;
    }

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

    public void limparCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                JTextField jtf = (JTextField) c;
                jtf.setText("");
            }
        }
    }
    
    public static void desabilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(false);
        }
    }

    public static void habilitarBotoes(JButton... bt) {
        for (JButton bt1 : bt) {
            bt1.setEnabled(true);
        }
    }

    
        public static void habilitarBotao(JComboBox jcb) {
        jcb.setEnabled(true);
    }

    public static void desabilitarBotao(JComboBox jcb) {
        jcb.setEnabled(false);
    }   
}
