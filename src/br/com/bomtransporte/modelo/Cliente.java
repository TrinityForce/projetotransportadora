package br.com.bomtransporte.modelo;

/**
 *
 * @author Andre
 */
public class Cliente extends Pessoa {

    private Integer idCliente;
    private String cpf;
    private Integer idEndereco;
    private String numeroCasa;
    private String complemento;
    private String telefone;
    private String telefone2;
    private String  celular;
    private Integer idContato;

    public Cliente() {
    }

    public Cliente(Integer idCliente, String cpf, Integer idEndereco, String numeroCasa, String complemento, String telefone, String telefone2, String celular, Integer idContato) {
        this.idCliente = idCliente;
        this.cpf = cpf;
        this.idEndereco = idEndereco;
        this.numeroCasa = numeroCasa;
        this.complemento = complemento;
        this.telefone = telefone;
        this.telefone2 = telefone2;
        this.celular = celular;
        this.idContato = idContato;
    }

    /**
     * @return the idCliente
     */
    public Integer getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the idEndereco
     */
    public Integer getIdEndereco() {
        return idEndereco;
    }

    /**
     * @param idEndereco the idEndereco to set
     */
    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    /**
     * @return the numeroCasa
     */
    public String getNumeroCasa() {
        return numeroCasa;
    }

    /**
     * @param numeroCasa the numeroCasa to set
     */
    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the telefone2
     */
    public String getTelefone2() {
        return telefone2;
    }

    /**
     * @param telefone2 the telefone2 to set
     */
    public void setTelefone2(String telefone2) {
        this.telefone2 = telefone2;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

    /**
     * @return the idContato
     */
    public Integer getIdContato() {
        return idContato;
    }

    /**
     * @param idContato the idContato to set
     */
    public void setIdContato(Integer idContato) {
        this.idContato = idContato;
    }

 

}
