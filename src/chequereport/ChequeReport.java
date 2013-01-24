package chequereport;

import chequereport.CantidadesEnLetraUtils;
import chequereport.ChequeReportDTO;
import chequereport.FechaHoraUtils;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.json.JSONSerializer;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author jpurata
 */
public class ChequeReport {

    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static JasperPrintManager jasperPrintManager;
    public static String reportTemplateUrl = "/home/jpurata/printCheque.jrxml";
    public static String myJsonFile = "/home/jpurata/myjson.json";

    public static String getReportFile() {
        return reportTemplateUrl;
    }

    public static Map getReportParameter() {
        Map parameters = new HashMap();
        parameters.put("ReporteCheque", 1);
        return parameters;
    }

    public static List<ChequeReportDTO> dataChequeReport() {
        double monto = 10.16;
        String paridad = "USD";
        String textMonto = CantidadesEnLetraUtils.convertNumberToLetter(monto, paridad, true);
        List<ChequeReportDTO> dato = new ArrayList<ChequeReportDTO>();

        ChequeReportDTO chequeReportDTO = new ChequeReportDTO();
        chequeReportDTO.setFolio(1);
        chequeReportDTO.setFecha(FechaHoraUtils.obtenerFechaActual());
        chequeReportDTO.setMonto(monto);
        chequeReportDTO.setRazonSocial("BenQ México S. de R.L de C.V");
        chequeReportDTO.setTextoMonto(textMonto);
        chequeReportDTO.setConcepto("Pago de Facturas de Proveedores de Bienes".toUpperCase());
        dato.add(chequeReportDTO);
        return dato;
    }
    
    public static List<Cheque> cheques() {
        List<Cheque> list = new ArrayList<Cheque>();
        Iterator it = list.iterator();
        Cheque c1 = null;

        String sociales[] = {"Banco Molas", "Promologistics", "NextH5"};
        String paridades[] = {"MX", "USD", "EU"};
        double montos[] = {134.50, 900.45, 455.30};
        String conceptos[] = {"Pago de Factura", "Pago de las alitas", "Pago de un monitor"};


        for (int i = 0; i < 2; i++) {
            c1 = new Cheque();
            String textmontos = CantidadesEnLetraUtils.convertNumberToLetter(montos[i], paridades[i], true);
            c1.setFolio(i);
            c1.setFecha(FechaHoraUtils.obtenerFechaActual());
            c1.setMonto(montos[i]);
            c1.setParidad(paridades[i]);
            c1.setRazonSocial(sociales[i]);
            c1.setTextoMonto(textmontos);
            c1.setConcepto(conceptos[0]);
            list.add(c1);
        }        
        System.out.println("JSONArray ::" + JSONSerializer.toJSON(list));
        return list;
    }

    public static void generateReportPDF() {
        try {
            //obtenemos el reporte y lo cargamos al diseño
            jasperDesign = JRXmlLoader.load(getReportFile());
            //System.out.print(jasperDesign.getFields());
            //compila el jasper
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            //prepara el reporte con los datos y parametros
            jasperPrint = JasperFillManager.fillReport(jasperReport, null, new JRBeanCollectionDataSource(cheques()));

            //mostramos el reporte generado usando la clase JasperViewer            
            //JasperViewer.viewReport(jasperPrint);
            //Muestra la ventana de las impresoras, lista para imprimir
            //el segundo parametro, es para decir si se quiere o no mostrar la ventana
            //o mandar a imprimir directo
            jasperPrintManager.printReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();
        }
        //return jasperPrintManager;
    }

    public static void main(String[] args) {
        //System.out.print("Se esta imprimiendo el fucking reporte :)");
        //ChequeReport.generateReportPDF();
        //ChequeReport.cheques();        
        List<Cheque> list = new ArrayList<Cheque>();
        Iterator it = list.iterator();
        Cheque c1 = null;

        String sociales[] = {"Banco Molas", "Promologistics", "NextH5"};
        String paridades[] = {"MX", "USD", "EU"};
        double montos[] = {134.50, 900.45, 455.30};
        String conceptos[] = {"Pago de Factura", "Pago de las alitas", "Pago de un monitor"};


        for (int i = 0; i < 3; i++) {
            c1 = new Cheque();
            String textmontos = CantidadesEnLetraUtils.convertNumberToLetter(montos[i], paridades[i], true);
            c1.setFolio(i);
            c1.setFecha(FechaHoraUtils.obtenerFechaActual());
            c1.setMonto(montos[i]);
            c1.setParidad(paridades[i]);
            c1.setRazonSocial(sociales[i]);
            c1.setTextoMonto(textmontos);
            c1.setConcepto(conceptos[0]);
            list.add(c1);
        }  
        
        Map m = new HashMap();
        for (int q = 0; q < list.size(); q++) {
            m.put("cheque" + q, list.get(q));
        };
        
        JSONParser parser = new JSONParser();
        try {
            JSONArray a = (JSONArray) parser.parse(new FileReader(myJsonFile));
            
        } catch (Exception e) {
            
        }
        
        //Object o = new Gson().fromJson(json, Object.class);
        //System.out.println(o);
        //System.out.println("JSONArray ::" + JSONSerializer.toJSON(m));
        
    }
}
