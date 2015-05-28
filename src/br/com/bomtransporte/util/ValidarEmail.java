package br.com.bomtransporte.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarEmail {

    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public ValidarEmail() {
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Método que valida o e-mail.
     *
     * @param email E-mail para validação.
     * @return TRUE - Para E-mail Válido, False - Para E-mail inválido.
     */
    public boolean validar(final String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
    
    
    
}
