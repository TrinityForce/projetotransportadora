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

//CAMINHAO DO ROMILDO
public  final  class Caminhao {
    
   /**
    * valores em metro
    */
    private final Double  altura = 2.40;
    private final Double largura =2.20;
    private final Double profundidade = 3.0;
    /**
     * peso em kg
     */
    private final Double pesoTotalSuportado = 1800.0;
    private final Double pesoBrutoDoCaminhao = 3500.0;
    /**
     * dimensao em metro cubico (quatorze)
     */
      private final Double dimensaoCubica =  14.0;

    public Double getAltura() {
        return altura;
    }

    public Double getLargura() {
        return largura;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public Double getPesoTotalSuportado() {
        return pesoTotalSuportado;
    }

    public Double getPesoBrutoDoCaminhao() {
        return pesoBrutoDoCaminhao;
    }

    public Double getDimensaoCubica() {
        return dimensaoCubica;
    }
    
    

}
