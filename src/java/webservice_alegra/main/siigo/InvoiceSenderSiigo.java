/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.main.siigo;

import com.google.gson.JsonObject;
import java.io.IOException;
import webservice_alegra.entities.dao.siigo.Siigo_InvoiceDAO;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.EmailListOfContactsVO;
import webservice_alegra.util.WebServiceAlegraUtilitarioM;

/**
 * Clase utilitaria para la gestión y envío de facturas a través de la API de Siigo.
 * 
 * Esta clase centraliza la creación y el envío por correo electrónico de facturas,
 * interactuando directamente con el DAO encargado de la comunicación con Siigo.
 * 
 * <p>Funciones principales:</p>
 * <ul>
 *   <li>Enviar una factura nueva a Siigo.</li>
 *   <li>Enviar por correo electrónico una factura ya registrada en Siigo.</li>
 * </ul>
 * 
 * @author Brayan Bernal
 * @since 10 Julio 2025
 */

public class InvoiceSenderSiigo {
    
        /**
     * Envía una nueva factura a la plataforma de Siigo para su creación.
     *
     * @param date               Fecha de emisión de la factura (formato esperado: YYYY-MM-DD).
     * @param dueDate            Fecha de vencimiento de la factura (formato esperado: YYYY-MM-DD).
     * @param siigo_DocumentInfo Objeto con la información completa del documento/factura a registrar.
     * @param anotation          Observaciones o anotaciones adicionales sobre la factura.
     * @param sellerEmail        Correo electrónico del vendedor asociado a la factura.
     * 
     * @return JsonObject con la respuesta de la API de Siigo, incluyendo datos de la factura creada
     *         (como ID, número, estado, etc.).
     * 
     * @throws Exception si ocurre un error durante la comunicación con Siigo o si los datos enviados son inválidos.
     */   
    
    public static JsonObject sendInvoiceToSiigo(String date, String dueDate, Siigo_DocumentInfo siigo_DocumentInfo, String anotation, String sellerEmail) throws Exception {
        Siigo_InvoiceDAO siigo_InvoiceDAO = new Siigo_InvoiceDAO();
        WebServiceAlegraUtilitarioM webServiceAlegraUtilitarioMCG = new WebServiceAlegraUtilitarioM();
        JsonObject invoiceCreated = siigo_InvoiceDAO.create(siigo_DocumentInfo);
        return invoiceCreated;
    }
    
    
    
    /**
     * Envía por correo electrónico una factura previamente registrada en Siigo.
     *
     * @param invoiceId             Identificador único de la factura en Siigo.
     * @param emailListOfContactsVO Objeto que contiene la lista de correos electrónicos de los destinatarios.
     * 
     * @return JsonObject con la respuesta de la API de Siigo indicando el resultado del envío por correo.
     * 
     * @throws IOException si ocurre un error de entrada/salida durante la comunicación con Siigo.
     */
    public static JsonObject sendByEmail(String invoiceId,EmailListOfContactsVO emailListOfContactsVO) throws IOException{
        Siigo_InvoiceDAO siigo_InvoiceDAO = new Siigo_InvoiceDAO();
        JsonObject objectCreated = siigo_InvoiceDAO.sendByEmail(invoiceId, emailListOfContactsVO);
        return objectCreated;
    }
    
}
