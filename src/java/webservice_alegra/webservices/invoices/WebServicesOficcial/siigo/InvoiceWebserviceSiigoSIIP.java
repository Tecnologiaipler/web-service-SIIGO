/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.webservices.invoices.WebServicesOficcial.siigo;

import com.google.gson.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.codehaus.jettison.json.JSONObject;
import webservice_alegra.api.endpoints.siigo.API.SiigoAPIException;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.EmailListOfContactsVO;
import webservice_alegra.main.siigo.InvoiceSenderSiigo;
import webservice_alegra.util.Utils;
import webservice_alegra.util.WebServiceAlegraUtilitarioM;
import webservice_alegra.webservices.invoices.GetItemsManagers.siigo.InvoiceWebserviceGetItemsFromJSONSIIPSiigo;

/**
 * Servicio REST para procesar y enviar facturas a SIIGO desde SIIP.
 * 
 * @author Brayan Bernal
 * @since 09 Julio 2025 10:01 AM
 */
  
@Path("send-siip-invoice-siigo")
public class InvoiceWebserviceSiigoSIIP {
    
    /**
     * @author Brayan Bernal
     * @since 09 Julio 2025 10:01 AM
     * @param content
     * @return 
     */
    private final WebServiceAlegraUtilitarioM util = new WebServiceAlegraUtilitarioM();

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response postJson(String content) {
        Response response = procesarFactura(content);
        return response;
    }
    
   /**
     * Procesa el JSON recibido, construye la factura y la envía a SIIGO.
     * Además, envía el correo con los detalles de la factura (de forma asíncrona
     * para no retrasar la respuesta).
     * 
     * @param content JSON con la información de la factura.
     * @return Response con el resultado del procesamiento.
     */
    public Response procesarFactura(String content) {

        Response response = new Response();
        try {
            JSONObject json = new JSONObject(content);

            // EXTRAER DATOS DEL JSON
            String date = json.getString("date");
            String dueDate = json.getString("date"); 
            String anotations = json.getString("anotations");
            String sellerEmail = json.getString("seller-email");

            InvoiceWebserviceGetItemsFromJSONSIIPSiigo invoiceWebserviceGetItemsFromJSONSIIPSiigo = new InvoiceWebserviceGetItemsFromJSONSIIPSiigo();
            Siigo_DocumentInfo siigo_DocumentInfo = invoiceWebserviceGetItemsFromJSONSIIPSiigo.getSiigo_DocumentInfoFromJson(json);
            JsonObject invoice = InvoiceSenderSiigo.sendInvoiceToSiigo(date, dueDate, siigo_DocumentInfo, anotations, sellerEmail);
            
            String invoiceId = invoice.get("id").getAsString();
            // REGISTRA auditoría y responde inmediatamente
            util.generarRegistroAuditoriaInformacionAlegra("200", "Factura enviada al SIIP " );
            response.setCode(200);
            response.setMessage(Utils.jsonObjectToPrettyString(invoice));
            
            // ENVÍO DE CORREO CON RETRASO Y SEGURO (NO ROMPE RESPUESTA)

                    try {
                        EmailListOfContactsVO emailData = new EmailListOfContactsVO();
                        emailData.setMail_to("contabilidad@ipler.edu.co");
                        emailData.setCopy_to("tecnologia@ipler.edu.co");

                        JsonObject result = InvoiceSenderSiigo.sendByEmail(invoiceId, emailData);
                        util.generarRegistroAuditoriaInformacionAlegra("", "Correo enviado exitosamente para factura ID: " + invoiceId);
                        util.generarRegistroAuditoriaInformacionAlegra("", Utils.jsonObjectToPrettyString(result));
                    } catch (Exception ex) {
                        util.generarRegistroAuditoriaInformacionAlegra("", "Fallo al enviar correo para factura ID " + invoiceId + ": " + ex.getMessage());
                        util.generarRegistroAuditoriaInformacionAlegra("", util.getExceptionDetails(ex));
                    }
        } catch (SiigoAPIException ex) {
            util.generarRegistroAuditoriaInformacionAlegra("", "Genera excepcion desde SiigoAPIException... " + ex.toString());
            util.generarRegistroAuditoriaInformacionAlegra("", util.getExceptionDetails(ex));
            response.setCode(ex.getStatusCode());
            response.setMessage(ex.getMessage());
        } catch (Exception ex) {
            util.generarRegistroAuditoriaInformacionAlegra("", "Genera excepcion desde Exception... " + ex.toString());
            util.generarRegistroAuditoriaInformacionAlegra("", util.getExceptionDetails(ex));
            response.setCode(400);
            response.setMessage(ex.getMessage());
        }

        return response;
    }
   
}
    