/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo;

import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Contacts.ContactPhone;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class Siigo_Contact {
    
    private String first_name;
    private String last_name;
    private String email;
    private ContactPhone phone;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }




    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContactPhone getPhone() {
        return phone;
    }

    public void setPhone(ContactPhone phone) {
        this.phone = phone;
    }
    
    
    
    

    
}
