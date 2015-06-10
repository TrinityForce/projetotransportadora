package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Carga {
    private Integer idCarga;
    private String descricao;
    private Double peso;
    private Integer quantidade;

    public Carga() {
    }

    public Carga(Integer idCarga, String descricao, Double peso, Integer quantidade) {
        this.idCarga = idCarga;
        this.descricao = descricao;
        this.peso = peso;
        this.quantidade = quantidade;
    }

    /**
     * @return the idCarga
     */
    public Integer getIdCarga() {
        return idCarga;
    }

    /**
     * @param idCarga the idCarga to set
     */
    public void setIdCarga(Integer idCarga) {
        this.idCarga = idCarga;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the peso
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
