package chequereport;


import java.util.Locale;
/**
 * Esta clase provee la funcionalidad de convertir un numero representado en
 * digitos a una representacion en letras. Mejorado para leer centavos
 * @author Juan Carlos Robles Cervantes
 * @version 1.0
 */
public abstract class CantidadesEnLetraUtils {    

    /**
     * unidades
     */
    private static final String[] UNIDADES = {"", "UN ", "DOS ", "TRES ", "CUATRO ", "CINCO ",
        "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ", "ONCE ", "DOCE ", "TRECE ", "CATORCE ",
        "QUINCE ", "DIECISEIS ", "DIECISIETE ", "DIECIOCHO ", "DIECINUEVE ", "VEINTE " };
    /**
     * decenas
     */
    private static final String[] DECENAS = {"VEINTE Y ", "TREINTA ", "CUARENTA ", "CINCUENTA ",
        "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ", "CIEN " };
    /**
     * Centenas
     */
    private static final String[] CENTENAS = {"CIENTO ", "DOSCIENTOS ", "TRESCIENTOS ",
        "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ", "SETECIENTOS ", "OCHOCIENTOS ",
        "NOVECIENTOS " };
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int UNO = 1;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int DOS = 2;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int TRES = 3;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int CUATRO = 4;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int CINCO = 5;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int SEIS = 6;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int SIETE = 7;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int OCHO = 8;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int CERO = 0;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int VEINTE = 20;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int TREINTA = 30;
    /**
     * CONSTANTE POR CHECKSTYLE
     */
    private static final int CUARENTA_OCHO = 48;


    /**
     * Convierte a letras un numero de la forma $123,456.32 (StoreMath)
     * <p>
     * @param number
     *            Numero en representacion texto
     * @return Numero en letras
     * @since 1.0
     */
    public static String convertNumberToLetter(double number, String moneda, boolean conCentavos) {
        return convertNumberToLetter(doubleToString(number), moneda, conCentavos);
    }

    /**
     * Convertimos el numero double a String, agregando formato para que sea procesado
     * El numero de decimales esta determinado por  %.2f
     * ej. %10.2f (10 posiciones enteras y 2 decimales)
     * si no se pone el primer valor por default toma el valor entero completo
     * @param numero de double
     * @return numero  notacion cientifica
     **/
    private static String doubleToString(double numero) {
        return String.format(new Locale("es", "MX"), "%.2f", numero);
    }

    /**
     * Convierte un numero en representacion numerica a uno en representacion de
     * texto. El numero es valido si esta entre 0 y 999'999.999
     * <p>
     * @param number
     *            Numero a convertir
     * @return Numero convertido a texto
     *             Si el numero esta fuera del rango
     * @since 1.0
     */
    public static String convertNumberToLetter(String number, String moneda, boolean conCentavos) {
        String converted = new String();
        
        // Validamos que sea un numero legal
               /*
               double doubleNumber = Double.parseDouble(number.replace(".",""));
               if (doubleNumber > 999999999)
                       throw new NumberFormatException(
                                       "El numero es mayor de 999'999.999, "
                                                       + "no es posible convertirlo");*/
        String [] splitNumber = number.replace('.', '#').split("#");
        // Descompone el trio de millones - �SGT!

        int millon = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], OCHO)) +
                        String.valueOf(getDigitAt(splitNumber[0], SIETE)) +
                        String.valueOf(getDigitAt(splitNumber[0], SEIS)));
        if (millon == UNO) {
            converted = "UN MILLON ";
        }
        if (millon > UNO) {
            converted = convertNumber(String.valueOf(millon)) + "MILLONES ";
        }
        // Descompone el trio de miles - �SGT!
        int miles = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], CINCO)) +
                        String.valueOf(getDigitAt(splitNumber[0], CUATRO)) +
                        String.valueOf(getDigitAt(splitNumber[0], TRES)));
        if (miles == UNO) {
            converted += "MIL ";
        }
        if (miles > UNO) {
            converted += convertNumber(String.valueOf(miles)) + "MIL ";
        }
        // Descompone el ultimo trio de unidades - �SGT!
        int cientos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[0], DOS)) +
                        String.valueOf(getDigitAt(splitNumber[0], UNO)) +
                        String.valueOf(getDigitAt(splitNumber[0], CERO)));
        if (cientos == UNO) {
            converted += "UN ";
        }
        if (millon + miles + cientos == 0) {
            converted += "CERO ";
        }
        if (cientos > UNO) {
            converted += convertNumber(String.valueOf(cientos));
        }

        if (moneda != null) {
            converted += moneda;
        }

        // Descompone los centavos - Camilo
        int centavos = Integer.parseInt(String.valueOf(getDigitAt(splitNumber[1], DOS)) +
                        String.valueOf(getDigitAt(splitNumber[1], UNO)) +
                        String.valueOf(getDigitAt(splitNumber[1], CERO)));
        if (conCentavos) {
            converted += " " + (centavos > 9 ? "" : "0") + centavos + "/100 M.N";
        }
        else {
            if (centavos == UNO) {
                converted += " CON UN";
            }
            if (centavos > UNO) {
                if (!(converted.charAt(converted.length() - 1) == ' ')) {
                    converted += ' ';
                }
                converted += "PUNTO " + convertNumber(String.valueOf(centavos));
            }
        }
        return converted;
    }

     /**
      * Convierte los trios de numeros que componen las unidades, las decenas y
      * las centenas del numero.
      * <p>
      * @param number
      *            Numero a convetir en digitos
      * @return Numero convertido en letras
      * @since 1.0
      */
    private static String convertNumber(String number) {
        if (number.length() > TRES) {
            throw new NumberFormatException("La longitud maxima debe ser 3 digitos");
        }
        String output = new String();
        if (getDigitAt(number, 2) != CERO) {
            output = CENTENAS[getDigitAt(number, 2) - 1];
        }
        int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1)) +
                             String.valueOf(getDigitAt(number, 0)));
        if (k <= VEINTE) {
            output += UNIDADES[k];
        }
        else {
            if (k > TREINTA && getDigitAt(number, 0) != CERO) {
                output += DECENAS[getDigitAt(number, 1) - 2] + "Y " +
                    UNIDADES[getDigitAt(number, 0)];
            }
            else {
                output += DECENAS[getDigitAt(number, 1) - 2] + UNIDADES[getDigitAt(number, 0)];
            }
        }
             // Caso especial con el 100
        if (getDigitAt(number, DOS) == 1 && k == CERO) {
            output = "CIEN ";
        }
        return output;
    }
     /**
      * Retorna el digito numerico en la posicion indicada de derecha a izquierda
      * <p>
      * @param origin
      *            Cadena en la cual se busca el digito
      * @param position
      *            Posicion de derecha a izquierda a retornar
      * @return Digito ubicado en la posicion indicada
      * @since 1.0
      */
    private static int getDigitAt(String origin, int position) {
        if (origin.length() > position && position >= CERO) {
            return origin.charAt(origin.length() - position - 1) - CUARENTA_OCHO;
        }
        return 0;
    }

}
