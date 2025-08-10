/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.api.endpoints.siigo.API;

import javax.xml.ws.http.HTTPException;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class SiigoAPIException  extends HTTPException {
    
    private String message;

    public SiigoAPIException(int statusCode) {
        super(statusCode);
    }
    
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
}
