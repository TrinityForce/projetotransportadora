package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Usuario {

    /**
     * Identificador único para usuário.
     */
    private Integer idUsuario;
    /**
     * Login para autenticação no sistema.
     */
    private String login;
    /**
     * Senha para acesso ao sistema.
     */
    private String senha;
    /**
     * Id do perfil do funcionário para autenticação. Administrador/Funcionário
     */
    private Integer idPerfil;
    /**
     * Verifica se é a primeira vez que ele está acessando o sistema, caso sim ,
     * precisa alterar a senha.
     */
    private Boolean alterarSenha;
    /**
     * E-mail do funcionário para operações como alteração de senha caso tenha
     * esquecido da mesma.
     */
    private String email;

    /**
     * Construtor vazio
     */
    public Usuario() {
    }

    /**
     * Construtor padrão
     *
     * @param idUsuario
     * @param login
     * @param senha
     * @param idPerfil
     * @param alterarSenha
     * @param email
     */
    public Usuario(Integer idUsuario, String login, String senha, Integer idPerfil, Boolean alterarSenha, String email) {
        this.idUsuario = idUsuario;
        this.login = login;
        this.senha = senha;
        this.idPerfil = idPerfil;
        this.alterarSenha = alterarSenha;
        this.email = email;
    }

    /**
     * @return the idUsuario
     */
    public Integer getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the idPerfil
     */
    public Integer getIdPerfil() {
        return idPerfil;
    }

    /**
     * @param idPerfil the idPerfil to set
     */
    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    /**
     * @return the alterarSenha
     */
    public Boolean getAlterarSenha() {
        return alterarSenha;
    }

    /**
     * @param alterarSenha the alterarSenha to set
     */
    public void setAlterarSenha(Boolean alterarSenha) {
        this.alterarSenha = alterarSenha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
