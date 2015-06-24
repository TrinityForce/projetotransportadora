package br.com.bomtransporte.modelo;

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
     * Peso total do Caminhão.
     */
    private static final Double pesoTotalCaminhao = 3500.0;
    /**
     * Dimensão cúbica total do caminhão em centimetro
     */
    private static final Double dimensaoCubica =15840.0;

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
     * @return the pesoTotalCaminhao
     */
    public static Double getPesoTotalCaminhao() {
        return pesoTotalCaminhao;
    }

    /**
     * @return the dimensaoCubica
     */
    public static Double getDimensaoCubica() {
        return dimensaoCubica;
    }
}
