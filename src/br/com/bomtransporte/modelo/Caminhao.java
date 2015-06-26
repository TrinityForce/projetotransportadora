package br.com.bomtransporte.modelo;

import java.util.List;

/**
 *
 * @author Gustavo Carvalho
 */
public class Caminhao {

    /**
     * Altura do caminhão em centímetros.
     */
    private static final Double altura = 240.0;
    /**
     * Larguda do caminhão em centímetros.
     */
    private static final Double largura = 220.0;
    /**
     * Profundidade do caminhão em centímetros.
     */
    private static final Double profundidade = 300.0;
    /**
     * Peso total suportado 1800KG.
     */
    private static final Double pesoTotalSuportado = 1800.0;

    /**
     * peso das cargas que estao no caminhao.
     */
    private static Double pesoDasCargasNoCaminhao = 0.0;

    /**
     * Dimensão cúbica total do caminhão em centimetro.
     */
    private static final Double dimensaoCubica = 1584000.0;

    /**
     * total de dimensao cubica das cargas no caminhao.
     */
    private static Double dimensaoCubicaPreenchida = 0.0;

    /**
     * lista com ids das cargas que estao no caminhao.
     */
    private static List<Integer> listaCarga;

    private static String rota;

    public Caminhao() {
    }

    /**
     * @return the altura
     */
    public static Double getAltura() {
        return altura;
    }

    /**
     * @return the largura
     */
    public static Double getLargura() {
        return largura;
    }

    /**
     * @return the profundidade
     */
    public static Double getProfundidade() {
        return profundidade;
    }

    /**
     * @return the pesoTotalSuportado
     */
    public static Double getPesoTotalSuportado() {
        return pesoTotalSuportado;
    }

    /**
     * @return the dimensaoCubica
     */
    public static Double getDimensaoCubica() {
        return dimensaoCubica;
    }

    public static List<Integer> getListaCarga() {
        return listaCarga;
    }

    public static void setListaCarga(List<Integer> listaCarga) {
        Caminhao.listaCarga = listaCarga;
    }

    public static String getRota() {
        return rota;
    }

    public static void setRota(String rota) {
        Caminhao.rota = rota;
    }

    public static Double getPesoDasCargasNoCaminhao() {
        return pesoDasCargasNoCaminhao;
    }

    public static void setPesoDasCargasNoCaminhao(Double pesoDasCargasNoCaminhao) {
        Caminhao.pesoDasCargasNoCaminhao += pesoDasCargasNoCaminhao;
    }

    public static Double getDimensaoCubicaPreenchida() {
        return dimensaoCubicaPreenchida;
    }

    public static void setDimensaoCubicaPreenchida(Double dimensaoCubicaPreenchida) {
        Caminhao.dimensaoCubicaPreenchida += dimensaoCubicaPreenchida;
    }
    
    

}
