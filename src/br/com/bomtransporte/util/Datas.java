package br.com.bomtransporte.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
    public static String dataAtual() {
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

    /**
     * transforma uma string em um objeto date
     * @author Gustavo Carvalho
     * @param date
     * @return dateObject
     */
    public static Date parseDate(String string) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM dd yyyy", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(string, formatter);
        Date date = java.sql.Date.valueOf(localDate);

        return date;

    }
/**
 * transforma um objeto java.util.date em java.sql.date
 * @author Gustavo Carvalho
 * @param date
 * @return 
 */
    public static java.sql.Date convertFromJAVADateToSQLDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }
}
