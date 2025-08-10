/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.dao.siigo;

import com.google.gson.JsonObject;
import java.io.IOException;
import webservice_alegra.api.endpoints.siigo.endpoints.SiigoInvoiceEndpoint;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.EmailListOfContactsVO;

/**
 * DAO (Data Access Object) para gestionar facturas en la API de Siigo.
 * 
 * Esta clase actúa como capa intermedia entre la lógica de negocio y los 
 * endpoints de Siigo, encapsulando la creación de facturas y el envío de 
 * las mismas por correo electrónico.
 * 
 * <p>Usa internamente la clase {@link SiigoInvoiceEndpoint} para realizar
 * las llamadas HTTP a la API de Siigo.</p>
 * 
 * <p><b>Ejemplo de uso:</b></p>
 * <pre>
 * Siigo_InvoiceDAO dao = new Siigo_InvoiceDAO();
 * Siigo_DocumentInfo factura = new Siigo_DocumentInfo();
 * // ... setear datos de factura ...
 * JsonObject respuesta = dao.create(factura);
 * </pre>
 * 
 * @author 
 *   Brayan Bernal
 * @since 
 *   10 Julio 2025
 */
public class Siigo_InvoiceDAO {
    
   /**
    * METODO TERMINADO
    *
    * @author Brayan Bernal
    * @since 10 Julio 2025 10:31 PM
    * @param documentInfo
    * @return
    * @throws Exception 
    */
    
    public JsonObject create(Siigo_DocumentInfo documentInfo) throws Exception {
        return SiigoInvoiceEndpoint.create(documentInfo);
    }
    
    /**
     *
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     * @param invoiceId
     * @param to 
     */
    
    public JsonObject sendByEmail(String invoiceId,EmailListOfContactsVO emailListOfContactsVO) throws IOException{
        return SiigoInvoiceEndpoint.sendByEmail(invoiceId, emailListOfContactsVO);
    }
}
