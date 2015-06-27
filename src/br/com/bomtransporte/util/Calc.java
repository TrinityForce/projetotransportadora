package br.com.bomtransporte.util;

/**
 *
 * @author JhonattanSouza_
 */
public class Calc {

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static Double calcDimenCubica(Double a, Double b, Double c) {
        return a * b * c;
    }

    /**
     *
     * @param peso
     * @return
     */
    public static  Double calcPesoCarga(String peso) {
        String pesoString = null;
        if (peso != null && peso.length() >= 2) {
            pesoString= peso.substring(peso.length() - 2);
        }
        return Double.parseDouble(pesoString);
    }
}
