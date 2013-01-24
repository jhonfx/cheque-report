package chequereport;

/**
 * Proyecto: Host To Host Corporativo
 *
 * Archivo: FechaUtils.java
 *
 * Creado: 31/08/2007
 *
 * (c) Grupo Altec Mexico. Todos los derechos reservados - 2007
 */

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.omg.CORBA.portable.Streamable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utileria para fechas y horas en la aplicacion.
 *
 * @author Rafael Antonio Guti&eacute;rrez Turullols
 * @author Oscar Vazquez Vazquez
 */
public class FechaHoraUtils {

    /**
     * Variable para el logger
     */    
    /**
     * indica la hora de inicio de dia
     */
    public static final String INICIO_DIA = "00:00:00";
    /**
     * indica la hora de fin de dia
     */
    public static final String FINAL_DIA = "23:59:59";

    /**
     * Constante de siglo
     */
    private static final String siglo = "20";
    /**
     * Dias por semana
     */
    private static final int DIAS_X_SEMANA = 7;
    /**
     * formateo de fechas
     */
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Enumeracion con los diversos formatos de las fechas.
     *
     * @author Rafael Antonio Guti&eacute;rrez Turullos
     */
    public enum FormatoFecha {
        ddMMyyyy, yyyyMMdd, MMddyyyy,
        ddMMyy, yyMMdd, MMddyy,
        HHmmss, HHmm,
        ddMMyyyy_diagonal, yyyyMMdd_diagonal,
        MMddyyyy_diagonal, ddMMyy_diagonal,
        yyMMdd_diagonal, MMddyy_diagonal,
        ddMMyyyy_guionMedio, yyyyMMdd_guionMedio,
        MMddyyyy_guionMedio, ddMMyy_guionMedio,
        yyMMdd_guionMedio, MMddyy_guionMedio,
        ddMMyyyy_punto, yyyyMMdd_punto,
        MMddyyyy_punto, ddMMyy_punto,
        yyMMdd_punto, MMddyy_punto,
        yyyyMMddHHss
    }

    /**
     * Tipo de diferencia en el metodo que da la diferencia entre dos fechas.
     */
    public enum TipoDiferencia {
        HORAS, MINUTOS, SEGUNDOS, DIAS, MESES, ANHOS
    }

    /**
     * Enumeracion con los diversos formatos de cadenas para formatear fechas.
     *
     * @author Rafael Antonio Guti&eacute;rrez Turullols
     */
    public enum FormatoFechaCadena {
        ddMMyyyy("ddMMyyyy"), yyyyMMdd("yyyyMMdd"), MMddyyyy("MMddyyyy"),
        ddMMyy("ddMMyy"), yyMMdd("yyMMdd"), MMddyy("MMddyy"),
        ddMMyyyy_diagonal("dd/MM/yyyy"), yyyyMMdd_diagonal("yyyy/MM/dd"),
        MMddyyyy_diagonal("MM/dd/yyyy"),
        ddMMyy_diagonal("dd/MM/yy"), yyMMdd_diagonal("yy/MM/dd"),
        MMddyy_diagonal("MM/dd/yy"),
        ddMMyyyy_guionMedio("dd-MM-yyyy"), yyyyMMdd_guionMedio("yyyy-MM-dd"),
        MMddyyyy_guionMedio("MM-dd-yyyy"),
        ddMMyy_guionMedio("dd-MM-yy"), yyMMdd_guionMedio("yy-MM-dd"),
        MMddyy_guionMedio("MM-dd-yy"),
        ddMMyyyy_punto("dd.MM.yyyy"), yyyyMMdd_punto("yyyy.MM.dd"),
        MMddyyyy_punto("MM.dd.yyyy"),
        ddMMyy_punto("dd.MM.yy"), yyMMdd_punto("yy.MM.dd"),
        MMddyy_punto("MM.dd.yy"),
        HHmmss("HHmmss"), HHmmss_puntosMedios("HH:mm:ss"),
        HHmm("HHmm"),
        yyyyMMddHHmmss("yyyyMMddHHmmss");

        /**
         * Formato.
         */
        private String formato;

        /**
         * Constructor que recibe el formato.
         *
         * @param str Cadena de formato
         */
        FormatoFechaCadena(String str) {
            this.formato = str;
        }

        /**
         * Regresa la cadena.
         *
         * @return Una cadena.
         */
        public String toString() {
            return formato;
        }

        /**
         * Metodo para obtener el formato de fecha a utilizar
         *
         * @param formato Formato a buscar
         * @return Formato a utilizar
         */
        public static FormatoFechaCadena obtenerFormato(String formato) {
            if (ddMMyyyy.toString().equals(formato)) {
                return ddMMyyyy;
            }
            if (yyyyMMdd.toString().equals(formato)) {
                return yyyyMMdd;
            }
            if (MMddyyyy.toString().equals(formato)) {
                return MMddyyyy;
            }
            if (ddMMyy.toString().equals(formato)) {
                return ddMMyy;
            }
            if (yyMMdd.toString().equals(formato)) {
                return yyMMdd;
            }
            if (MMddyy.toString().equals(formato)) {
                return MMddyy;
            }

            if (ddMMyyyy_diagonal.toString().equals(formato)) {
                return ddMMyyyy_diagonal;
            }
            if (yyyyMMdd_diagonal.toString().equals(formato)) {
                return yyyyMMdd_diagonal;
            }
            if (MMddyyyy_diagonal.toString().equals(formato)) {
                return MMddyyyy_diagonal;
            }
            if (ddMMyy_diagonal.toString().equals(formato)) {
                return ddMMyy_diagonal;
            }
            if (yyMMdd_diagonal.toString().equals(formato)) {
                return yyMMdd_diagonal;
            }
            if (MMddyy_diagonal.toString().equals(formato)) {
                return MMddyy_diagonal;
            }

            if (ddMMyyyy_guionMedio.toString().equals(formato)) {
                return ddMMyyyy_guionMedio;
            }
            if (yyyyMMdd_guionMedio.toString().equals(formato)) {
                return yyyyMMdd_guionMedio;
            }
            if (MMddyyyy_guionMedio.toString().equals(formato)) {
                return MMddyyyy_guionMedio;
            }
            if (ddMMyy_guionMedio.toString().equals(formato)) {
                return ddMMyy_guionMedio;
            }
            if (yyMMdd_guionMedio.toString().equals(formato)) {
                return yyMMdd_guionMedio;
            }
            if (MMddyy_guionMedio.toString().equals(formato)) {
                return MMddyy_guionMedio;
            }

            if (ddMMyyyy_punto.toString().equals(formato)) {
                return ddMMyyyy_punto;
            }
            if (yyyyMMdd_punto.toString().equals(formato)) {
                return yyyyMMdd_punto;
            }
            if (MMddyyyy_punto.toString().equals(formato)) {
                return MMddyyyy_punto;
            }
            if (ddMMyy_punto.toString().equals(formato)) {
                return ddMMyy_punto;
            }
            if (yyMMdd_punto.toString().equals(formato)) {
                return yyMMdd_punto;
            }
            if (MMddyy_punto.toString().equals(formato)) {
                return MMddyy_punto;
            }

            if (HHmmss.toString().equals(formato)) {
                return HHmmss;
            }
            if (HHmmss_puntosMedios.toString().equals(formato)) {
                return HHmmss_puntosMedios;
            }
            if (HHmm.toString().equals(formato)) {
                return HHmm;
            }
            if (yyyyMMddHHmmss.toString().equals(formato)) {
                return yyyyMMddHHmmss;
            }

            return ddMMyyyy;
        }
    }

    /**
     * Toma una fecha en cadena y la transforma a objeto.
     *
     * @param dateStr Cadena
     * @param formato Formato
     * @return La fecha
     * @throws java.text.ParseException Si ocurre una excepcion
     */
    public static Date fromStringToDate(String dateStr, FormatoFechaCadena formato)
        throws ParseException {
        SimpleDateFormat df = null;
        df = new SimpleDateFormat(formato.toString());
        return df.parse(dateStr);
    }

    /**
     * Toma una fecha en cadena y la transforma a objeto.
     *
     * @param dateStr      Cadena de fecha
     * @param formato      Formato de fecha a aplicar.
     * @param charToRemove Caracter a remover de la cadena
     * @return Fecha
     * @throws java.text.ParseException Si ocurre una excepcion
     */
    public static Date fromStringToDate(String dateStr, FormatoFechaCadena formato, char charToRemove)
        throws ParseException {
        SimpleDateFormat df = null;
        df = new SimpleDateFormat(formato.toString());
        return df.parse(StringUtils.remove(dateStr, charToRemove));
    }

    /**
     * Toma una fecha y la transforma a cadena.
     *
     * @param date    Fecha
     * @param formato Formato
     * @return Cadena
     */
    public static String fromDateToString(Date date, FormatoFechaCadena formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato.toString());
        return sdf.format(date);
    }

    /**
     * obtener la fecha de manana
     *
     * @return un objeto Date
     */
    public static Date obtenerFechaManhana() {
        Calendar calmahana = Calendar.getInstance();
        calmahana.setTimeInMillis(System.currentTimeMillis());
        calmahana.set(Calendar.DAY_OF_YEAR, calmahana.get(Calendar.DAY_OF_YEAR) + 1);
        return calmahana.getTime();
    }

    /**
     * obtener la fecha de manana
     *
     * @return un objeto Date
     */
    public static Date obtenerFechaSiguiente() {
        Calendar calSiguiente = Calendar.getInstance();
        calSiguiente.setTime(obtenerFechaManhana());
        int dayOfWeek = calSiguiente.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == dayOfWeek) {
            calSiguiente.set(Calendar.DAY_OF_YEAR, calSiguiente.get(Calendar.DAY_OF_YEAR) + 1);
        }
        return calSiguiente.getTime();
    }

    /**
     * obtener la fecha de hoy
     *
     * @return un objeto Date
     */
    public static Date obtenerFechaHoy() {
        Calendar calmahana = Calendar.getInstance();
        calmahana.setTimeInMillis(System.currentTimeMillis());
        return calmahana.getTime();
    }

    /**
     * Metodo que convierte una cadena del formato proporcionado por el usuario al formato
     * utilizado por CECOBAN
     *
     * @param fechaEntrada Cadena de la fecha introducida por el usuario
     * @param formato      Formato de la fecha introducida por el usuario
     * @return String Cadena con la fecha con formato aceptado por CECOBAN
     * @throws Exception Si ocurre una excepcion
     */
    public static String aplicaFormatoS(String fechaEntrada, String formato) throws Exception {        
        String cadenaConFormato = "";
        String yyyy = "";
        String MM = "";
        String dd = "";

        if (formato.equals("ddMMyyyy")) {

            yyyy = fechaEntrada.substring(4, 8);
            MM = fechaEntrada.substring(2, 4);
            dd = fechaEntrada.substring(0, 2);
            cadenaConFormato = yyyy + MM + dd;
        } else if (formato.equals("yyyyMMdd")) {

            yyyy = fechaEntrada.substring(0, 4);
            MM = fechaEntrada.substring(4, 6);
            dd = fechaEntrada.substring(6, 8);
            cadenaConFormato = yyyy + MM + dd;
        } else if (formato.equals("MMddyyyy")) {

            yyyy = fechaEntrada.substring(4, 8);
            MM = fechaEntrada.substring(0, 2);
            dd = fechaEntrada.substring(2, 4);
            cadenaConFormato = yyyy + MM + dd;
        } else if (formato.equals("yyyyddMM")) {
            yyyy = fechaEntrada.substring(0, 4);
            MM = fechaEntrada.substring(6, 8);
            dd = fechaEntrada.substring(4, 6);
            cadenaConFormato = yyyy + MM + dd;

        } else if (formato.equals("ddMMyy")) {
            yyyy = siglo + fechaEntrada.substring(4, 6);
            MM = fechaEntrada.substring(2, 4);
            dd = fechaEntrada.substring(0, 2);
            cadenaConFormato = yyyy + MM + dd;
        } else if (formato.equals("yyMMdd")) {
            yyyy = siglo + fechaEntrada.substring(0, 2);
            MM = fechaEntrada.substring(2, 4);
            dd = fechaEntrada.substring(4, 6);
            cadenaConFormato = yyyy + MM + dd;
        } else if (formato.equals("MMddyy")) {
            yyyy = siglo + fechaEntrada.substring(4, 6);
            MM = fechaEntrada.substring(0, 2);
            dd = fechaEntrada.substring(2, 4);
            cadenaConFormato = yyyy + MM + dd;

        } else if (formato.equals("yyddMM")) {
            yyyy = siglo + fechaEntrada.substring(0, 2);
            MM = fechaEntrada.substring(4, 6);
            dd = fechaEntrada.substring(2, 4);
            cadenaConFormato = yyyy + MM + dd;
        } else {
            throw new Exception(
                "No fue posible modificar el formato de la fecha");
        }
        
        return cadenaConFormato;
    }

    /**
     * Obtiene la diferencia dependiendo el tipo entre la fecha 1 y la fecha 2.
     *
     * @param date1 Fecha uno.
     * @param date2 Fecha dos.
     * @return el resto resultado de la diferencia
     */
    public static long obtenerDiferencia(TipoDiferencia tipo, Date date1, Date date2) {
        // obtener los milisegundos de diferencia
        long diffMilisecs = Math.abs(date1.getTime() - date2.getTime());

        if (tipo.equals(TipoDiferencia.ANHOS)) {
            return diffMilisecs / (365 * 24 * 60 * 60 * 1000);
        }

        // devolver la diferencia en dias
        if (tipo.equals(TipoDiferencia.DIAS)) {
            return diffMilisecs / (24 * 60 * 60 * 1000);
        }

        // devolver la diferencia en horas
        if (tipo.equals(TipoDiferencia.HORAS)) {
            return diffMilisecs / (60 * 60 * 1000);
        }

        // devolver la diferencia en minutos
        if (tipo.equals(TipoDiferencia.MINUTOS)) {
            return diffMilisecs / (60 * 1000);
        }

        // devolver la diferencia en segundos
        return diffMilisecs / (1000);
    }

    /**
     * Metodo que devuelve la fecha de un dia anterior
     *
     * @return un objeto Date que representa la fecha del dia de ayer
     */
    public static Date obtenerFechaDiaAnterior() {
        Calendar diaAyer = Calendar.getInstance();
        if (diaAyer.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            diaAyer.add(Calendar.DAY_OF_YEAR, -1);
        }
        diaAyer.add(Calendar.DAY_OF_YEAR, -1);
        return diaAyer.getTime();
    }

    /**
     * Metodo que devuelve la fecha de un dia anterior
     *
     * @return un objeto Date que representa la fecha del dia de ayer
     */
    public static Date obtenerFechaDiaAnterior(Date dia) {
        Calendar diaAyer = Calendar.getInstance();
        diaAyer.setTime(dia);
        if (diaAyer.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            diaAyer.add(Calendar.DAY_OF_YEAR, -1);
        }
        diaAyer.add(Calendar.DAY_OF_YEAR, -1);
        return diaAyer.getTime();
    }

    /**
     * Pregunta si las dos fechas tienen el mismo año.
     *
     * @param date1 fecha 1.
     * @param date2 fecha 2.
     * @return true si lo tienen, false de lo contrario.
     */
    public static boolean isMismoAnho(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR);
    }

    /**
     * Pregunta si las dos fechas tienen el mismo mes.
     *
     * @param date1 fecha 1.
     * @param date2 fecha 2.
     * @return true si lo tienen, false de lo contrario.
     */
    public static boolean isMismoMes(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH);
    }

    /**
     * Pregunta si las dos fechas tienen el mismo dia.
     *
     * @param date1 fecha 1.
     * @param date2 fecha 2.
     * @return true si lo tienen, false de lo contrario.
     */
    public static boolean isMismoDia(Date date1, Date date2) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        return calendar1.get(Calendar.DATE) == calendar2.get(Calendar.DATE);
    }

    /**
     * obtiene el objeto date con el inicio del dia actual
     *
     * @return el inicio del dia
     */
    public final static Date obtenerInicioDia() {
        Calendar inicioDia = Calendar.getInstance();
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);
        return inicioDia.getTime();
    }

    /**
     * obtiene el objeto date con el inicio del dia actual
     *
     * @return el inicio del dia
     */
    public final static Date obtenerFinDia(Date dia) {
        Calendar finDia = Calendar.getInstance();
        finDia.setTime(dia);
        finDia.set(Calendar.HOUR_OF_DAY, 23);
        finDia.set(Calendar.MINUTE, 59);
        finDia.set(Calendar.SECOND, 59);
        return finDia.getTime();
    }

    /**
     * obtiene el objeto date con el inicio del dia actual
     *
     * @return el inicio del dia
     */
    public final static Date obtenerInicioDia(Date dia) {
        Calendar inicioDia = Calendar.getInstance();
        inicioDia.setTime(dia);
        inicioDia.set(Calendar.HOUR_OF_DAY, 0);
        inicioDia.set(Calendar.MINUTE, 0);
        inicioDia.set(Calendar.SECOND, 0);
        return inicioDia.getTime();
    }

    /**
     * obtiene el objeto date con el inicio del dia actual
     *
     * @return el inicio del dia
     */
    public final static Date obtenerDiaAtrasadoXSemana(Date dia, int numeroSemanas) {
        Calendar inicioDia = Calendar.getInstance();
        if (inicioDia.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            inicioDia.add(Calendar.DAY_OF_YEAR, -1);
        }
        inicioDia.setTime(dia);
        inicioDia.add(Calendar.DAY_OF_YEAR, -(numeroSemanas * DIAS_X_SEMANA));
        return inicioDia.getTime();
    }

    /**
     * obtiene la fecha menos los dias indicados como parametro
     *
     * @param numeroDiasAtras el numero de dias hacia atras
     * @return la fecha calculada
     */
    public final static Date obtenerFechaMenosDias(int numeroDiasAtras) {
        Calendar hoy = Calendar.getInstance();
        if (hoy.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            hoy.add(Calendar.DAY_OF_YEAR, -1);
        }
        hoy.add(Calendar.DAY_OF_YEAR, -numeroDiasAtras);
        return hoy.getTime();
    }

    /**
     * obtiene la fecha menos los dias indicados como parametro
     *
     * @param numeroDiasAdelante el numero de dias hacia atras
     * @return la fecha calculada
     */
    public final static Date obtenerFechaMasDias(int numeroDiasAdelante) {
        Calendar hoy = Calendar.getInstance();
        if (hoy.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            hoy.add(Calendar.DAY_OF_YEAR, 1);
        }
        hoy.add(Calendar.DAY_OF_YEAR, numeroDiasAdelante);
        return hoy.getTime();
    }


    /**
     * obtiene el inicio del mes actual
     *
     * @return la fecha con el inicio del mes
     */
    public final static Date obtenerInicioMesActual() {
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.DAY_OF_MONTH, 1);
        return hoy.getTime();
    }

    /**
     * obtiene la fecha final del mes actual
     *
     * @return el ultimo dia del mes
     */
    public final static Date obtenerFinMesActual() {
        Calendar hoy = Calendar.getInstance();
        int lastDate = hoy.getActualMaximum(Calendar.DATE);
        hoy.set(Calendar.DAY_OF_MONTH, lastDate);
        return hoy.getTime();
    }

    /**
     * Obtiene el primer dia del mes
     * @param mes
     * @param anio
     * @return
     */
    public final static Date obtenerInicioMes(Integer mes, Integer anio) {
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.DAY_OF_MONTH, 1);
        hoy.set(Calendar.MONTH, mes);
        hoy.set(Calendar.YEAR, anio);
        return hoy.getTime();
    }

    /**
     * Obtiene el ultimo dia del mes
     * @param mes
     * @param anio
     * @return
     */
    public final static Date obtenerFinMes(Integer mes, Integer anio) {
        Calendar hoy = Calendar.getInstance();
        hoy.set(Calendar.DAY_OF_MONTH, 1);
        hoy.set(Calendar.MONTH, mes);
        hoy.set(Calendar.YEAR, anio);
        int lastDate = hoy.getActualMaximum(Calendar.DATE);
        hoy.set(Calendar.DAY_OF_MONTH, lastDate);
        return hoy.getTime();
    }

    /**
     * crea la condicion between por fecha del campo enviado como parametro
     *
     * @param campo       el campo de la tabla para hacer el between
     * @param fechaInicio la fecha de inicio de la condicion
     * @param fechaFin    la fecha fin de la condicion
     * @return la condicion del campo
     */
    public final static String campoEntreFechas(String campo, String fechaInicio, String fechaFin) {
        StringBuilder sbFecha = new StringBuilder();
        sbFecha.append(" and ");
        sbFecha.append(campo);
        sbFecha.append(" between '");
        sbFecha.append(fechaInicio);
        sbFecha.append(" ");
        sbFecha.append(INICIO_DIA);
        sbFecha.append("' and '");
        sbFecha.append(fechaFin);
        sbFecha.append(" ");
        sbFecha.append(FINAL_DIA);
        sbFecha.append("'");
        return sbFecha.toString();
    }

    /**
     * obtiene la fecha dada mas N dias adicionales
     *
     * @param fecha
     * @param numeroDiasAdelante
     * @return
     */
    public final static Date sumarDiasAFecha(Date fecha, int numeroDiasAdelante) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, numeroDiasAdelante);
        return calendario.getTime();
    }

    /**
     * obtiene la fecha dada mas N dias antes
     *
     * @param fecha
     * @param numeroDiasAtras
     * @return
     */
    public final static Date restarDiasAFecha(Date fecha, int numeroDiasAtras) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, -numeroDiasAtras);
        return calendario.getTime();
    }

    /**
     * obtiene el dia de la semana para una fecha dada
     *
     * @param fecha
     * @return entero 1=Domingo, 2= Lunes ... etc
     */
    public final static int obtenerDiaSemana(Date fecha) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        return calendario.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Obtiene una fecha nueva que tenga como dia de la semana el siguiente diaSemana
     * de la fecha introducida
     *
     * @param fecha
     * @param diaSemana
     * @return
     */
    public final static Date nuevaFechaXDiaSemana(Date fecha, int diaSemana) {
        int diaFecha = FechaHoraUtils.obtenerDiaSemana(fecha);
        int sumarDias = (diaSemana - diaFecha) <= 0 ? (diaSemana - diaFecha) + 7 : (diaSemana - diaFecha);
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DATE, +sumarDias);
        return calendario.getTime();
    }

    /**
     * Convierte una fecha al formato yyyy-MM-dd
     *
     * @param fecha la fecha a convetir
     * @return la fecha convertida
     */
    public final static String formatearFecha(Date fecha) {
        return FORMATTER.format(fecha);
    }

    public final static Integer obtenerNumeroPeriodosEntreFechas(Date fechaInicio, Date fechaVencimiento) {        
        Calendar calendarioInicio = Calendar.getInstance();
        calendarioInicio.setTime(fechaInicio);
        int mesInicio = calendarioInicio.get(Calendar.MONTH);
        Calendar calendarioFin = Calendar.getInstance();
        calendarioFin.setTime(fechaVencimiento);
        int mesFin = calendarioFin.get(Calendar.MONTH);
        if (calendarioInicio.get(Calendar.YEAR) != calendarioFin.get(Calendar.YEAR)) {
            mesFin = calendarioFin.get(Calendar.MONTH) + 11;
        }


        Integer numeroDePeriodos = (mesFin - mesInicio) + 1;        
        return numeroDePeriodos;

    }

    public final static Date calcularFechaFinalPeriodo(Date fechaInicio) {        
        Calendar calInicio = Calendar.getInstance();
        int dayOne = 1;
        calInicio.setTime(fechaInicio);
        calInicio.set(Calendar.DATE, dayOne);
        int ultimateDay = calInicio.getActualMaximum(Calendar.DAY_OF_MONTH);
                
        calInicio.set(Calendar.DATE, ultimateDay);        
        return calInicio.getTime();

    }

    /**
     * Genera la fecha en formato dd/mes(Textual)/yyyy para presentarla en los reportes
     *
     * @return
     */
    public final static String obtenerFechaActual() {

        Calendar fecha = Calendar.getInstance();

        String mesesArray[] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        String mes = mesesArray[fecha.get(Calendar.MONTH)];
        int anio = fecha.get(Calendar.YEAR);

        return dia + "-" + mes + "-" + anio;

    }

    /**
     * Genera la hora actual para presentarla en los reportes
     *
     * @return
     */
    public static String obtenerHoraActual() {
        Date today = new Date();
        String timestamp = DateFormatUtils.format(today, "HH:mm:ss");
        return timestamp;
    }
}
