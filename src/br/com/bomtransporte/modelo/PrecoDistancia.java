package br.com.bomtransporte.modelo;

/**
 *
 * @author JhonattanSouza_
 */
public class PrecoDistancia {
    private Integer idPrecoDistancia;
    private Double valor;
    private String origemDestinoUf;
    private Boolean ativado;
    private String dataDesativado;
    private String funcionarioQueDesativou;

    public PrecoDistancia() {
    }

    
    public PrecoDistancia(Integer idPrecoDistancia, Double valor, String origemDestinoUf, Boolean ativado, String funcionarioQueDesativou) {
        this.idPrecoDistancia = idPrecoDistancia;
        this.valor = valor;
        this.origemDestinoUf = origemDestinoUf;
        this.ativado = ativado;
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }

    /**
     * @return the idPrecoDistancia
     */
    public Integer getIdPrecoDistancia() {
        return idPrecoDistancia;
    }

    /**
     * @param idPrecoDistancia the idPrecoDistancia to set
     */
    public void setIdPrecoDistancia(Integer idPrecoDistancia) {
        this.idPrecoDistancia = idPrecoDistancia;
    }

    /**
     * @return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     * @return the origemDestinoUf
     */
    public String getOrigemDestinoUf() {
        return origemDestinoUf;
    }

    /**
     * @param origemDestinoUf the origemDestinoUf to set
     */
    public void setOrigemDestinoUf(String origemDestinoUf) {
        this.origemDestinoUf = origemDestinoUf;
    }

    /**
     * @return the ativado
     */
    public Boolean getAtivado() {
        return ativado;
    }

    /**
     * @param ativado the ativado to set
     */
    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    /**
     * @return the funcionarioQueDesativou
     */
    public String getFuncionarioQueDesativou() {
        return funcionarioQueDesativou;
    }

    /**
     * @param funcionarioQueDesativou the funcionarioQueDesativou to set
     */
    public void setFuncionarioQueDesativou(String funcionarioQueDesativou) {
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }

    /**
     * @return the dataDesativado
     */
    public String getDataDesativado() {
        return dataDesativado;
    }

    /**
     * @param dataDesativado the dataDesativado to set
     */
    public void setDataDesativado(String dataDesativado) {
        this.dataDesativado = dataDesativado;
    }
    
    
}
