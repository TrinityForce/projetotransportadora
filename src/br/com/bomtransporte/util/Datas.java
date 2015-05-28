package br.com.bomtransporte.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author JhonattanSouza_
 * @author Gustavo Carvalho
 */
public class Datas {
    /**
     * @author JhonattanSouza_
     * @return DataAtual Dia/Mes/Ano
     */
    public static String dataAtual(){
        Date data = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.valueOf(sdf.format(data));
    }
    
    /**
     * @author Gustavo Carvalho
     * @return Data,Ano,Mês,Dia,Hora,Minuto,Segundo
     */
    public static String getCurrentDate() {
        Date date = Calendar.getInstance().getTime();
        //formato da data ano, mes, dia, hora, minuto, segundo
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        return sdf.format(date);
    }
}
