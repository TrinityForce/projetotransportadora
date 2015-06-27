package br.com.bomtransporte.regrasnegocio;

import br.com.bomtransporte.formulario.FormDashAdmin;
import br.com.bomtransporte.formulario.FormFuncionario;
import br.com.bomtransporte.modelo.Funcionario;
import java.security.MessageDigest;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author JhonattanSouza_
 */
public class FuncionarioRN {

    /**
     *
     * @param perfil Perfil do usuário que será utilizado para chamar a tela de
     * acordo com o id deste perfil
     * @param jframe O frame que será invocado de acordo com o perfil
     */
    public static void chamarTela(Integer perfil, JFrame jframe) {
        switch (perfil) {
            case 1:
                FormDashAdmin formAdmin = new FormDashAdmin();
                formAdmin.setVisible(true);
                jframe.dispose();
                break;
            case 2:
                FormFuncionario formFun = new FormFuncionario();
                formFun.setVisible(true);
                jframe.dispose();
                break;
            default:
                JOptionPane.showMessageDialog(jframe, "VOCÊ NÃO POSSUI ACESSO À ESTE SISTEMA. PARA MAIS INFORMAÇÕES"
                        + "ENTRE EM CONTATO COM SEU ADMNISTRADOR.");
                break;
        }
    }

    /**
     *
     * @param msg Senha que irá ser transformada em hash md5
     * @return String devidamente criptografada
     */
    public static String criptografarMd5(String msg) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(msg.getBytes());
            byte byteData[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    /**
     *
     * @param usuarioEmail
     * @param usuarioNome
     * @param novaSenha
     */
    public void enviarEmail(String usuarioEmail, String usuarioNome, String novaSenha) {
        try {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.live.com");
            email.setSslSmtpPort("587");
            email.setStartTLSRequired(true);
            email.setSSLOnConnect(true);
            email.setDebug(true);
            email.setAuthentication("trinityforceprojeto@hotmail.com", "Banana22");
            email.setFrom("trinityforceprojeto@hotmail.com", "SAC@BOMTRANSPORTE.COM");
            email.setSubject("RECUPERAÇÃO DE SENHA.");
            email.setMsg("Sua nova senha é: " + novaSenha + "\nVocê será solicitado para alterá-la no próximo login que fizer.");
            email.addTo(usuarioEmail, usuarioNome);
            email.send();
        } catch (EmailException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     *
     * @param obj
     * @return
     */
    public String gerarSenha(Object obj) {
        String senha;

        Funcionario f = (Funcionario) obj;

        senha = f.getNome().substring(0, 3);
        senha += f.getIdPessoa();
        senha += f.getIdFuncionario();
        senha += f.getCargo().substring(0, 3);

        return senha;
    }
}
