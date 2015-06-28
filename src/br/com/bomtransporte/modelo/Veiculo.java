package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Veiculo {
    private Integer idVeiculo;
    private String tipoVeiculo;
    private String status;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo, String tipoVeiculo, String status) {
        this.idVeiculo = idVeiculo;
        this.tipoVeiculo = tipoVeiculo;
        this.status = status;
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
    
    
}
