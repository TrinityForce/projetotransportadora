/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.bomtransporte.modelo;

/**
 * 
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */
public class PrecoPeso {

    private Integer idPrecoPeso;
    private String peso;
    private Double valor;
    private Boolean ativado;
    private String dataDesativado;
    private String funcionarioQueDesativou;

    /**
     *
     */
    public PrecoPeso() {
    }

    /**
     *
     * @param idPrecoPeso
     * @param peso
     * @param valor
     * @param ativado
     * @param dataDesativado
     * @param funcionarioQueDesativou
     */
    public PrecoPeso(Integer idPrecoPeso, String peso, Double valor, Boolean ativado, String dataDesativado, String funcionarioQueDesativou) {
        this.idPrecoPeso = idPrecoPeso;
        this.peso = peso;
        this.valor = valor;
        this.ativado = ativado;
        this.dataDesativado = dataDesativado;
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }

    /**
     *
     * @return
     */
    public Integer getIdPrecoPeso() {
        return idPrecoPeso;
    }

    /**
     *
     * @param idPrecoPeso
     */
    public void setIdPrecoPeso(Integer idPrecoPeso) {
        this.idPrecoPeso = idPrecoPeso;
    }

    /**
     *
     * @return
     */
    public String getPeso() {
        return peso;
    }

    /**
     *
     * @param peso
     */
    public void setPeso(String peso) {
        this.peso = peso;
    }

    /**
     *
     * @return
     */
    public Double getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public Boolean getAtivado() {
        return ativado;
    }

    /**
     *
     * @param ativado
     */
    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    /**
     *
     * @return
     */
    public String getDataDesativado() {
        return dataDesativado;
    }

    /**
     *
     * @param dataDesativado
     */
    public void setDataDesativado(String dataDesativado) {
        this.dataDesativado = dataDesativado;
    }

    /**
     *
     * @return
     */
    public String getFuncionarioQueDesativou() {
        return funcionarioQueDesativou;
    }

    /**
     *
     * @param funcionarioQueDesativou
     */
    public void setFuncionarioQueDesativou(String funcionarioQueDesativou) {
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }
    
    
}
