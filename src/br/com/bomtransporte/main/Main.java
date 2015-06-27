package br.com.bomtransporte.main;

import br.com.bomtransporte.formulario.FormBackground;
import br.com.bomtransporte.formulario.FormLogin;
import br.com.bomtransporte.regrasnegocio.FuncionarioRN;

/**
 *
 * @author JhonattanSouza_
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FormBackground formBg = new FormBackground();
        FormLogin formLogin = new FormLogin();
        //Forçando o tema Windows para todas as telas, para que o tema NIMBUS não volte a aparecer.
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            ex.getMessage();
        }
        formBg.setVisible(true);
        formLogin.setVisible(true);
    }
}
