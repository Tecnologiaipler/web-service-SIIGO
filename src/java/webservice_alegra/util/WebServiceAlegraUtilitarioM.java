/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address.Siigo_Address;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Siigo_Customer;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Item.Siigo_Item;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.Siigo_Contact;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class WebServiceAlegraUtilitarioM {

    /**
     * Se hace este metodo para auditar solicitudes por generacion de error en
     * consecutivos de facturacion
     *
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     * @param solicitud
     */
    public void generarRegistroAuditoriaInformacionAlegra(String codigo, String solicitud) {
        String filePath = "/home/rsa-key-20240627/auditoria_alegra/webservice_alegra_auditoria_1.txt";  // Path to the file you want to append to

        // Obtener fecha y hora actual en formato legible
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       String timestamp = sdf.format(new Date());
       
           // Armar línea de log con timestamp
        String textToAppend = "[" + timestamp + "] ";
        if (codigo != null && !codigo.isEmpty()) {
            textToAppend += "[" + codigo + "] ";
        }
        textToAppend += solicitud + "\n";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(textToAppend);
            System.out.println("Text has been appended to the file.");
        } catch (IOException e) {
            System.err.println("An error occurred while appending to the file: " + e.getMessage());
        }
    }

    /**
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     * @param e
     * @return
     */
    public String getExceptionDetails(Exception e) {
        StringBuilder details = new StringBuilder();

        // Exception message
        details.append("Exception occurred: ").append(e.toString()).append("\n\n");

        // Stack trace elements with class, method, file, and line number
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (StackTraceElement element : stackTrace) {
            details.append("Class: ").append(element.getClassName()).append("\n");
            details.append("Method: ").append(element.getMethodName()).append("\n");
            details.append("File: ").append(element.getFileName()).append("\n");
            details.append("Line number: ").append(element.getLineNumber()).append("\n\n");
        }

        // Capture full stack trace to a string if needed
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        String fullStackTrace = sw.toString();

        details.append("Full stack trace:\n").append(fullStackTrace);

        return details.toString();
    }

    /**
     * De acuerdo a revison con SIIGO, SIIGO sugiere implementar esta rutina
     * para remover caracteres especiales del campos que corresponda.
     *
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     *
     * @param input
     * @return
     */
    public String removeSpecialCharacters(String input) {
        if (input == null) {
            return null;
        }
        // Replace all non-alphanumeric characters except spaces with an empty string
        return input.replaceAll("[^a-zA-Z0-9 ]", "");
    }

    /**
     * Resultado revision SIIGO factura pendiente, esta rutina se implemento porque 
     * se debia surpimir caracteres especiales
     *
     * @author
     * @param siigo_documentoinfo
     * @return
     */
    public Siigo_DocumentInfo getWithoutSpecialCharacters(Siigo_DocumentInfo siigo_documentoinfo) {
        String s = null;
        Siigo_DocumentInfo s1;
        s1 = siigo_documentoinfo;
        Siigo_Customer customer = s1.getCustomer();
        
        // Se procesa direccion
        Siigo_Address address = customer.getAddress();
        address.setAddress(this.removeSpecialCharacters((address.getAddress())));
        
        List<String> l_name = customer.getName();
        List<String> l_name2 = new ArrayList<>();

        for (String names : l_name) {
            names = this.removeSpecialCharacters(names);
            l_name2.add(names);
        }
        customer.setName(l_name2);
        customer.setAddress(address);

        List<Siigo_Contact> contacts = customer.getContacts();
        for (Siigo_Contact siigo_contacts : contacts) {
            siigo_contacts.setFirst_name(this.removeSpecialCharacters(siigo_contacts.getFirst_name()));
            siigo_contacts.setLast_name(this.removeSpecialCharacters(siigo_contacts.getLast_name()));
            siigo_contacts.setEmail(this.removeSpecialCharacters(siigo_contacts.getEmail()));
        }
        customer.setContacts(contacts);
        s1.setCustomer(customer);

        // Items o articulos
        List<Siigo_Item> items = s1.getItems();
        for (Siigo_Item siigo_Item : items) {
            siigo_Item.setDescription(this.removeSpecialCharacters(siigo_Item.getDescription()));
        }
        s1.setItems(items);

        return s1;

    }
}
