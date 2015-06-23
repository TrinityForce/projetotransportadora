package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Carga {

    private Integer idCarga;
    private String descricao;
    private String peso;
    private Integer quantidade;
    private Integer idPedido;
    private Integer idPedido_Cli;
    private Integer idPrecoPeso;
    private Double valor;
    private Double dimensaoCubica;
    private String status;

    public Carga() {
    }

    public Carga(Integer idCarga, String descricao, String peso, Integer quantidade, Integer idPedido, Integer idPedido_Cli, Integer idPrecoPeso, Double valor, Double dimensaoCubica, String status) {
        this.idCarga = idCarga;
        this.descricao = descricao;
        this.peso = peso;
        this.quantidade = quantidade;
        this.idPedido = idPedido;
        this.idPedido_Cli = idPedido_Cli;
        this.idPrecoPeso = idPrecoPeso;
        this.valor = valor;
        this.dimensaoCubica = dimensaoCubica;
        this.status = status;
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
    public String getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(String peso) {
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

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdPedido_Cli() {
        return idPedido_Cli;
    }

    public void setIdPedido_Cli(Integer idPedido_Cli) {
        this.idPedido_Cli = idPedido_Cli;
    }

    public Integer getIdPrecoPeso() {
        return idPrecoPeso;
    }

    public void setIdPrecoPeso(Integer idPrecoPeso) {
        this.idPrecoPeso = idPrecoPeso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getDimensaoCubica() {
        return dimensaoCubica;
    }

    public void setDimensaoCubica(Double dimensaoCubica) {
        this.dimensaoCubica = dimensaoCubica;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
