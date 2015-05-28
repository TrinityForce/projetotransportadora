package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Pessoa {

    /**
     * Identificador único da pessoa.
     */
    private Integer idPessoa;
    /**
     * Nome da pessoa.
     */
    private String nome;
    /**
     * Data de cadastro da pessoa.
     */
    private String dataCadastro;

    /**
     * Construtor vazio.
     */
    public Pessoa() {
    }

    /**
     * Construtor padrão.
     * @param idPessoa
     * @param nome
     * @param dataCadastro 
     */
    public Pessoa(Integer idPessoa, String nome, String dataCadastro) {
        this.idPessoa = idPessoa;
        this.nome = nome;
        this.dataCadastro = dataCadastro;
    }

    /**
     * @return the idPessoa
     */
    public Integer getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(Integer idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dataCadastro
     */
    public String getDataCadastro() {
        return dataCadastro;
    }

    /**
     * @param dataCadastro the dataCadastro to set
     */
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
