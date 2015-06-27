package br.com.bomtransporte.util;

import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author JhonattanSouza_
 */
public class Relatorios {

    /**
     *
     * @param relat
     * @param lista
     * @param parametros
     * @throws JRException
     * @throws Exception
     */
    public static void gerarRelatorio(String relat, List lista, Map parametros) throws JRException, Exception {
        JasperDesign design = JRXmlLoader.load(relat);
        JasperReport relatorio = JasperCompileManager.compileReport(design);

        JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, new JRBeanCollectionDataSource(lista));

        JasperViewer viewer = new JasperViewer(impressao, false);
        viewer.setVisible(true);
    }
}
