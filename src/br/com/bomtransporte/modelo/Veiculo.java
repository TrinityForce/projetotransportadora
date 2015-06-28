package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class Veiculo {
    private Integer idVeiculo;
    private String nomeVeiculo;
    private String status;

    public Veiculo() {
    }

    public Veiculo(Integer idVeiculo, String nomeVeiculo, String status) {
        this.idVeiculo = idVeiculo;
        this.nomeVeiculo = nomeVeiculo;
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
    public String getNomeVeiculo() {
        return nomeVeiculo;
    }

    /**
     * @param nomeVeiculo the nomeVeiculo to set
     */
    public void setNomeVeiculo(String nomeVeiculo) {
        this.nomeVeiculo = nomeVeiculo;
    }
    
    
}
