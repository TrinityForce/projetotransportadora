package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Veiculo {

    private Integer idVeiculo;
    private String tipoVeiculo;
    private String status;
    private String destino;
    private Double totalPreenchido;
    private Double pesoPreenchido;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo, String tipoVeiculo, String status, String destino, Double totalPreenchido, Double pesoPreenchido) {
        this.idVeiculo = idVeiculo;
        this.tipoVeiculo = tipoVeiculo;
        this.status = status;
        this.destino = destino;
        this.totalPreenchido = totalPreenchido;
        this.pesoPreenchido = pesoPreenchido;
    }

    /**
     * @return the idVeiculo
     */
    public Integer getIdVeiculo() {
        return idVeiculo;
    }

    /**
     * @param idVeiculo the idVeiculo to set
     */
    public void setIdVeiculo(Integer idVeiculo) {
        this.idVeiculo = idVeiculo;
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

    /**
     * @return the nomeVeiculo
     */
    public String tipoVeiculo() {
        return tipoVeiculo;
    }

    /**
     * @param nomeVeiculo the nomeVeiculo to set
     */
    public void tipoVeiculo(String nomeVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setTipoVeiculo(String tipoVeiculo) {
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getTotalPreenchido() {
        return totalPreenchido;
    }

    public void setTotalPreenchido(Double totalPreenchido) {
        this.totalPreenchido = totalPreenchido;
    }

    public Double getPesoPreenchido() {
        return pesoPreenchido;
    }

    public void setPesoPreenchido(Double pesoPreenchido) {
        this.pesoPreenchido  = pesoPreenchido;
    }

}
