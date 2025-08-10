/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo;

/**
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */


import java.util.List;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Currency.Siigo_Currency;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Siigo_Customer;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Document.Document;
import webservice_alegra.entities.vo.siigo.DocumentInfo.GlobalDiscount.Siigo_GlobalDiscount;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Item.Siigo_Item;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Mail.Siigo_Mail;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Payments.Siigo_Payment;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Stamp.Siigo_Stamp;
import webservice_alegra.util.Utils;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */

public class Siigo_DocumentInfo {

    private Document document;
    private String date;
    private Long number;
    private Siigo_Customer customer;
    private Long seller;
    private Siigo_Stamp stamp;
    private Siigo_Mail mail;
    private String observations;
    private List<Long> retentions;
    private Double advancePayment;
    private Long cost_center;
    private Siigo_Currency currency;
    private List<Siigo_Item> items;
    private List<Siigo_Payment> payments;
    private List<Siigo_GlobalDiscount> global_discounts;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    

    
    
    // Getters and setters...


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Siigo_Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Siigo_Customer customer) {
        this.customer = customer;
    }

    public Long getSeller() {
        return seller;
    }

    public void setSeller(Long seller) {
        this.seller = seller;
    }

    public Siigo_Stamp getStamp() {
        return stamp;
    }

    public void setStamp(Siigo_Stamp stamp) {
        this.stamp = stamp;
    }

    public Siigo_Mail getMail() {
        return mail;
    }

    public void setMail(Siigo_Mail mail) {
        this.mail = mail;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public List<Long> getRetentions() {
        return retentions;
    }

    public void setRetentions(List<Long> retentions) {
        this.retentions = retentions;
    }

    public Double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(Double advancePayment) {
        this.advancePayment = advancePayment;
    }

    public Long getCost_center() {
        return cost_center;
    }

    public void setCost_center(Long cost_center) {
        this.cost_center = cost_center;
    }

    

    public Siigo_Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Siigo_Currency currency) {
        this.currency = currency;
    }

    public List<Siigo_Item> getItems() {
        return items;
    }

    public void setItems(List<Siigo_Item> items) {
        this.items = items;
    }

    public List<Siigo_Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Siigo_Payment> payments) {
        this.payments = payments;
    }

    public List<Siigo_GlobalDiscount> getGlobal_discounts() {
        return global_discounts;
    }

    public void setGlobal_discounts(List<Siigo_GlobalDiscount> global_discounts) {
        this.global_discounts = global_discounts;
    }


    
    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
 }










