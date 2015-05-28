package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Cidade {
    private Integer idCidade;
    private String nome;
    private String uf;

    public Cidade() {
    }

    public Cidade(Integer idCidade, String nome, String uf) {
        this.idCidade = idCidade;
        this.nome = nome;
        this.uf = uf;
    }

    /**
     * @return the idCidade
     */
    public Integer getIdCidade() {
        return idCidade;
    }

    /**
     * @param idCidade the idCidade to set
     */
    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
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
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }
    
    
}
