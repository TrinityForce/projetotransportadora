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

    public PrecoPeso() {
    }

    public PrecoPeso(Integer idPrecoPeso, String peso, Double valor, Boolean ativado, String dataDesativado, String funcionarioQueDesativou) {
        this.idPrecoPeso = idPrecoPeso;
        this.peso = peso;
        this.valor = valor;
        this.ativado = ativado;
        this.dataDesativado = dataDesativado;
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }

    public Integer getIdPrecoPeso() {
        return idPrecoPeso;
    }

    public void setIdPrecoPeso(Integer idPrecoPeso) {
        this.idPrecoPeso = idPrecoPeso;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Boolean getAtivado() {
        return ativado;
    }

    public void setAtivado(Boolean ativado) {
        this.ativado = ativado;
    }

    public String getDataDesativado() {
        return dataDesativado;
    }

    public void setDataDesativado(String dataDesativado) {
        this.dataDesativado = dataDesativado;
    }

    public String getFuncionarioQueDesativou() {
        return funcionarioQueDesativou;
    }

    public void setFuncionarioQueDesativou(String funcionarioQueDesativou) {
        this.funcionarioQueDesativou = funcionarioQueDesativou;
    }
    
    
}
