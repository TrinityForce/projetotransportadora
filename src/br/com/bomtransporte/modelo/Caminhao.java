/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.bomtransporte.modelo;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 
 * @author Gustavo Carvalho <gustavo.carvalho.costa@outlook.com>
 */

//CAMINHAO DO ROMILDO
public  final  class Caminhao {
    
   /**
    * valores em metro
    */
    private final BigDecimal   altura = new BigDecimal(2.40);
    private final BigDecimal largura = new BigDecimal(2.20);
    private final BigDecimal profundidade = new BigDecimal(3.0);
    /**
     * peso em kg
     */
    private final BigDecimal pesoTotalSuportado = new BigDecimal(1800);
    private final BigDecimal pesoBrutoDoCaminhao = new BigDecimal(3500) ;
    /**
     * dimensao em metro cubico (quatorze)
     */
      private final BigDecimal dimensaoCubica = new  BigDecimal(14);

    public BigDecimal getAltura() {
        return altura;
    }

    public BigDecimal getLargura() {
        return largura;
    }

    public BigDecimal getProfundidade() {
        return profundidade;
    }

    public BigDecimal getPesoTotalSuportado() {
        return pesoTotalSuportado;
    }

    public BigDecimal getPesoBrutoDoCaminhao() {
        return pesoBrutoDoCaminhao;
    }

    public BigDecimal getDimensaoCubica() {
        return dimensaoCubica;
    }


    
    

}
