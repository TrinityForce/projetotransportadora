package br.com.bomtransporte.util;

import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
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

    public boolean verificarCamposCliente(Container container) {
        List<String> tempLista = new ArrayList<>();
        Boolean tudoPreenchido = false;
        for (Component c : container.getComponents()) {
            if (c instanceof JTextField) {
                JTextField tempCampo = (JTextField) c;
                tempLista.add(tempCampo.getText());
            }
        }
        if (tempLista != null){
            tudoPreenchido = true;
            for(String item : tempLista){
                if(item == null){
                    tudoPreenchido = false;
                    break;
                }
            }
        }
        return tudoPreenchido;
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
    
    public void habilitarBotao(JButton bt) {
        bt.setEnabled(true);
    }

    public void desabilitarBotao(JButton bt) {
        bt.setEnabled(false);
    }
}
