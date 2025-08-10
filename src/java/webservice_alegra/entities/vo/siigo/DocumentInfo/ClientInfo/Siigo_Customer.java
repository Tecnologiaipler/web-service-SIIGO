/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
import java.util.List;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address.Siigo_Address;
import webservice_alegra.entities.vo.siigo.Siigo_Contact;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Phone.Siigo_Phone;
import webservice_alegra.entities.vo.siigo.DocumentInfo.FiscalResponsibility.Siigo_FiscalResponsibility;
import webservice_alegra.entities.vo.siigo.DocumentInfo.RelatedUsers.Siigo_RelatedUsers;

public class Siigo_Customer {
    
    private String type = "Customer";  // Default value
    private String person_type;  // Required, "person" or "company"
    private String id_type;  // Required
    private String identification;  // Required, max 50 chars, no special chars
    private String checkDigit;  // Optional, numeric 0-9
    private List<String> name;  // Required, max 100 chars per name part
    private String commercialName;  // Optional, alphanumeric
    private int branchOffice = 0;  // Optional, default 0
    private boolean active = true;  // Optional, default true
    private boolean vatResponsible = false;  // Optional, default false
    private Siigo_FiscalResponsibility fiscal_responsibilities;  // Required
    private Siigo_Address address;  // Required
    private List<Siigo_Phone> phones;  // Optional
    private List<Siigo_Contact> contacts;  // Optional
    private String comments;  // Optional, max 4000 chars
    private Siigo_RelatedUsers relatedUsers;  // Optional

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPerson_type() {
        return person_type;
    }

    public void setPerson_type(String person_type) {
        this.person_type = person_type;
    }

    public String getId_type() {
        return id_type;
    }

    public void setId_type(String id_type) {
        this.id_type = id_type;
    }


    
    
    public String getIdType() {
        return id_type;
    }

    public void setIdType(String idType) {
        this.id_type = idType;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCheckDigit() {
        return checkDigit;
    }

    public void setCheckDigit(String checkDigit) {
        this.checkDigit = checkDigit;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public int getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(int branchOffice) {
        this.branchOffice = branchOffice;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isVatResponsible() {
        return vatResponsible;
    }

    public void setVatResponsible(boolean vatResponsible) {
        this.vatResponsible = vatResponsible;
    }

    public Siigo_FiscalResponsibility getFiscal_responsibilities() {
        return fiscal_responsibilities;
    }

    public void setFiscal_responsibilities(Siigo_FiscalResponsibility fiscal_responsibilities) {
        this.fiscal_responsibilities = fiscal_responsibilities;
    }


    
    public Siigo_Address getAddress() {
        return address;
    }

    public void setAddress(Siigo_Address address) {
        this.address = address;
    }

    public List<Siigo_Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Siigo_Phone> phones) {
        this.phones = phones;
    }

    public List<Siigo_Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Siigo_Contact> contacts) {
        this.contacts = contacts;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Siigo_RelatedUsers getRelatedUsers() {
        return relatedUsers;
    }

    public void setRelatedUsers(Siigo_RelatedUsers relatedUsers) {
        this.relatedUsers = relatedUsers;
    }
    
    
    
}
