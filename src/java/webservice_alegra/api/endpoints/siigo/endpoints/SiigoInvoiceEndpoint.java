/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.api.endpoints.siigo.endpoints;

import com.google.gson.JsonObject;
import java.io.IOException;
import webservice_alegra.api.endpoints.siigo.API.SiigoAPI;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.EmailListOfContactsVO;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class SiigoInvoiceEndpoint {
    
    
    /**
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     * @param invoice
     * @return
     * @throws Exception 
     */
    
    public static JsonObject create(Siigo_DocumentInfo invoice) throws Exception {
        String functionName = "invoices";
        String method = "POST";
        String postData = invoice.toJson();
        JsonObject jsonObject = SiigoAPI.callSiigoAPI(functionName, method, null, postData).getAsJsonObject();
        return jsonObject;
    }
    
    /**
     * 
     * @param invoiceId
     * @param to 
     */
    
    public static JsonObject sendByEmail(String invoiceId,EmailListOfContactsVO emailListOfContactsVO) throws IOException{
        String functionName = "invoices/"+invoiceId+"/mail";
        String method = "POST";
        String postData = emailListOfContactsVO.toJson();
        JsonObject jsonObject = SiigoAPI.callSiigoAPI(functionName, method, null, postData).getAsJsonObject();
        return jsonObject;  
    }
    
    /**
     * @author Brayan Bernal
     * @since 10 Julio 2025 10:31 PM
     * @param jsonObject
     * @return 
     */
    
    public static Siigo_DocumentInfo convertJsonObjectToSiigoDocumentInfo(JsonObject jsonObject) {
        Siigo_DocumentInfo siigo_DocumentInfo = new Siigo_DocumentInfo();
        
        return siigo_DocumentInfo;
    }
    
}
