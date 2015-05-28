package br.com.bomtransporte.util;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author JhonattanSouza_
 */
public class Tela {
    
    private boolean verificarCampo(String campo) {
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

    
    
    public Boolean verificarCamposCliente(Container container){
        for(Component c : container.getComponents()){
            if(c instanceof JTextField){
                JTextField camp = (JTextField) c;
                return camp.getText() != null && camp.getText().trim().length() > 0;
            }
        }
        return false;
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

    public static void limparCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField || c instanceof JPasswordField) {
                c.setEnabled(false);
            }
        }
    }

    public static void habilitarCampos(Container container) {
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                c.setEnabled(false);
            }
        }
    }
}
