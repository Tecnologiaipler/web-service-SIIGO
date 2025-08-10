/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address;

import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address.City.Siigo_City;

/**
 * 
 * Clase para integracion con Sistema Contable SIIGO
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class Siigo_Address {
           
    private String address;
    private Siigo_City city;
    private String postal_code;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public Siigo_City getCity() {
        return city;
    }

    public void setCity(Siigo_City city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }  
     
}
