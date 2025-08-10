/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Contacts;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class ContactPhone {
    
    private String indicative;  
    private String number;
    private String extension;

    public String getIndicative() {
        return indicative;
    }

    public void setIndicative(String indicative) {
        this.indicative = indicative;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    
    
    
    
}
