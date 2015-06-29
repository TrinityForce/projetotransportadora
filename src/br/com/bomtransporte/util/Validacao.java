package br.com.bomtransporte.util;

import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author JhonattanSouza_
 */
public class Validacao {

    /**
     *
     * @param cpf Que será validado
     * @return Se o cpf estiver ok retorna true, senão , retorna false
     */
    public static boolean validarCPF(String cpf) {
        String tempCpf;
        tempCpf = cpf.replace(".", "");
        tempCpf = tempCpf.replace("-", "");

        if (tempCpf.equals("00000000000") || tempCpf.equals("11111111111")
                || tempCpf.equals("22222222222") || tempCpf.equals("33333333333")
                || tempCpf.equals("44444444444") || tempCpf.equals("55555555555")
                || tempCpf.equals("66666666666") || tempCpf.equals("77777777777")
                || tempCpf.equals("88888888888") || tempCpf.equals("99999999999")
                || (tempCpf.length() != 11)) {
            return false;
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                num = (int) (tempCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);

            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48);
            }

            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (tempCpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            if ((dig10 == tempCpf.charAt(9)) && (dig11 == tempCpf.charAt(10))) {
                return true;
            } else {
                return false;
            }
        } catch (InputMismatchException imex) {
            return false;
        }
    }

    /**
     *
     * @param list
     * @return
     */
    public static boolean verificarCamposVazios(List<String> list) {
        Boolean vazio = true;
        if (list != null && !list.isEmpty()) {
            for (String campo : list) {
                if (campo == null || campo.trim().length() == 0) {
                    vazio = false;
                    return vazio;
                }
            }
        } else {
            vazio = false;
        }
        return vazio;
    }

    public static String retornarDestinoUF(String str) {

        String destino = str.substring(Math.max(str.length() - 2, 0));
        return destino;
    }
}
