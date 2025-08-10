package webservice_alegra.entities.vo.alegra;

import java.util.ArrayList;
import webservice_alegra.util.Utils;

/**
 *
 * @author Yohan Romero
 */
public class Alegra_InvoiceAlegraVO {

    private String date;
    private String dueDate;
    private String observations;
    private String anotation;
    private String status;
    private int client;
    private Alegra_NumberTemplateVO numberTemplate;
    private ArrayList<Alegra_ItemVO> items;
    private Alegra_CurrencyVO currency;
    private int seller;
    private ArrayList<Alegra_PaymentVO> payments;
    private String paymentMethod;
    private String paymentForm;
    private Alegra_StampVO stamp;
    public static final String STATUS_OPEN = "open";
    public static final String STATUS_DRAFT = "draft";
    public static final String PAYMENT_FORM_CASH = "CASH";
    public static final String PAYMENT_FORM_CREDIT = "CREDIT";
    public static final String PAYMENT_METHOD_BANK_DEPOSIT = "BANK_DEPOSIT";

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getAnotation() {
        return anotation;
    }

    public void setAnotation(String anotation) {
        this.anotation = anotation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClient() {
        return client;
    }

    public void setClient(int client) {
        this.client = client;
    }

    public Alegra_NumberTemplateVO getNumberTemplate() {
        return numberTemplate;
    }

    public void setNumberTemplate(Alegra_NumberTemplateVO numberTemplate) {
        this.numberTemplate = numberTemplate;
    }

    public ArrayList<Alegra_ItemVO> getItems() {
        return items;
    }

    public void setItems(ArrayList<Alegra_ItemVO> items) {
        this.items = items;
    }

    public Alegra_CurrencyVO getCurrency() {
        return currency;
    }

    public void setCurrency(Alegra_CurrencyVO currency) {
        this.currency = currency;
    }

    public int getSeller() {
        return seller;
    }

    public void setSeller(int seller) {
        this.seller = seller;
    }

    public ArrayList<Alegra_PaymentVO> getPayments() {
        return payments;
    }

    public void setPayments(ArrayList<Alegra_PaymentVO> payments) {
        this.payments = payments;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentForm() {
        return paymentForm;
    }

    public void setPaymentForm(String paymentForm) {
        this.paymentForm = paymentForm;
    }

    public Alegra_StampVO getStamp() {
        return stamp;
    }

    public void setStamp(Alegra_StampVO stamp) {
        this.stamp = stamp;
    }

    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
}
