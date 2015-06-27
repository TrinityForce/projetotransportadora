package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Funcionario extends Pessoa {

    /**
     * Identificador único do funcionário.
     */
    private Integer idFuncionario;
    /**
     * Cargo atribuído ao funcionário.
     */
    private String cargo;
    /**
     * Cada funcionário que acessa o sistema, terá um usuário para poder
     * autenticar-se.
     */
    private Usuario usuario;

    /**
     *
     */
    public Funcionario() {
    }

    /**
     *
     * @param idFuncionario
     * @param cargo
     * @param usuario
     */
    public Funcionario(Integer idFuncionario, String cargo, Usuario usuario) {
        this.idFuncionario = idFuncionario;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    /**
     * Identificador único do funcionário.
     *
     * @return the idFuncionario
     */
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Identificador único do funcionário.
     *
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Cargo atribuído ao funcionário.
     *
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Cargo atribuído ao funcionário.
     *
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * Cada funcionário que acessa o sistema, terá um usuário para poder
     * autenticar-se.
     *
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Cada funcionário que acessa o sistema, terá um usuário para poder
     * autenticar-se.
     *
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
