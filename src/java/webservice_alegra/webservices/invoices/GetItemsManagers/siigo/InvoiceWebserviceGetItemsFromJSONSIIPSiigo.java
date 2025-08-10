/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.InvoiceWebserviceGetItemsFromJSONSIIPSiigo
 */
package webservice_alegra.webservices.invoices.GetItemsManagers.siigo;

import java.util.ArrayList;
import java.util.List;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import webservice_alegra.entities.vo.siigo.Siigo_Contact;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address.City.Siigo_City;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Address.Siigo_Address;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Phone.Siigo_Phone;
import webservice_alegra.entities.vo.siigo.DocumentInfo.ClientInfo.Siigo_Customer;
import webservice_alegra.entities.vo.siigo.DocumentInfo.GlobalDiscount.Siigo_GlobalDiscount;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Item.Siigo_Item;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Mail.Siigo_Mail;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Payments.Siigo_Payment;
import webservice_alegra.entities.vo.siigo.DocumentInfo.RelatedUsers.Siigo_RelatedUsers;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Siigo_DocumentInfo;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Stamp.Siigo_Stamp;
import webservice_alegra.util.WebServiceAlegraUtilitarioM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import webservice_alegra.entities.vo.siigo.DocumentInfo.Document.Document;
import webservice_alegra.entities.vo.siigo.ValoresInternosUsuarioVO;



/**
 * Clase encargada de convertir JSON proveniente de SIIP2 a objetos compatibles con
 * el Webservice de SIIGO para facturación.
 * 
 * @author Brayan Bernal
 * @since 09 Julio 2025
 */
public class InvoiceWebserviceGetItemsFromJSONSIIPSiigo {
    
    // SIIGO BUILT-IN METHODS
    public final String SIIGO_METHOD_CASH = "cash";
    public final String SIIGO_METHOD_CHECK = "check";
    public final String SIIGO_METHOD_CREDIT_CARD = "credit-card";
    public final String SIIGO_METHOD_DEBIT_CARD = "debit-card";
    public final String SIIGO_METHOD_DEPOSIT = "deposit";
    public final String SIIGO_METHOD_TRANSFER = "transfer";
    public final String SIIGO_METHOD_TRANSFER_PAYU = "transfer-payu";
    public final String SIIGO_METHOD_TRANSFER_ADDI = "transfer-addi";
    public final String SIIGO_TRANSFER_SUFI = "transfer-sufi";
    public final String SIIGO_TRANSFER_BOLD= "transfer-bold";
    public final String SIIGO_TRANSFER_GOU= "transfer-gou";

    /**
     * Extrae valores internos específicos del usuario desde el JSON de entrada.
     * 
     * @param json JSONObject que contiene la información del contacto.
     * @return ValoresInternosUsuarioVO con datos internos (nro consecutivo, historia usuario).
     * @throws Exception si falta alguna clave esperada en JSON.
     */
    
    public ValoresInternosUsuarioVO getValoresInternosUsuarioVO(JSONObject json) throws Exception {
        ValoresInternosUsuarioVO valoresInternosUsuarioVO = new ValoresInternosUsuarioVO();
        JSONObject jsonContact = json.getJSONObject("contact");
        String numero_consecutivo_interno = jsonContact.getString("numero-consecutivo-interno");
        String numero_historia_usuario = jsonContact.getString("id-alumno");
        valoresInternosUsuarioVO.setNumero_consecutivo_interno(numero_consecutivo_interno);
        valoresInternosUsuarioVO.setNumero_historia_usuario(numero_historia_usuario);
        return valoresInternosUsuarioVO;
    }

    
    /**
     * Construye un objeto Siigo_Customer a partir de JSON recibido.
     * Se parametrizan campos específicos de identificación y teléfono según formatos SIIGO.
     * 
     * @param json JSONObject con los datos de contacto.
     * @return Siigo_Customer mapeado.
     * @throws Exception si falta alguna clave en JSON.
     */
    
    
    public Siigo_Customer getSiigo_CustomerFromJson(JSONObject json) throws Exception {
               
        Siigo_Customer siigo_customer = new Siigo_Customer();
        JSONObject jsonContact = json.getJSONObject("contact");

        String identificationType = jsonContact.getString("identification-type");
        String identificationNumber = jsonContact.getString("identification-number");
        String name = jsonContact.getString("name");
        String lastName = jsonContact.getString("last-name");
        String phone = jsonContact.getString("phone");
        String address = jsonContact.getString("address");
        String city = jsonContact.getString("city");
        String department = jsonContact.getString("department");
        String country = jsonContact.getString("country");
         
        
        siigo_customer.setType("Customer"); // Customer, Supplier, Other
        switch (identificationType) {
            case "CC":  // Cédula de Ciudadanía
                siigo_customer.setPerson_type("Person");
                siigo_customer.setIdType("13");
                break;

            case "CE":  // Cédula de Extranjería
                siigo_customer.setPerson_type("Person");
                siigo_customer.setIdType("22");
                break;

            case "PP":  // Pasaporte
                siigo_customer.setPerson_type("Person");
                siigo_customer.setIdType("41");
                break;

            case "TI":  // Tarjeta de Identidad
                siigo_customer.setPerson_type("Person");
                siigo_customer.setIdType("12");
                break;

            case "NIT": // Número de Identificación Tributaria
                siigo_customer.setPerson_type("Company");
                siigo_customer.setIdType("31");
                break;

            default:    // Si no coincide con ningún tipo conocido, se usa CC por defecto
                siigo_customer.setPerson_type("Person");
                siigo_customer.setIdType("13");
                break;
        }
        siigo_customer.setIdentification(identificationNumber);
        List<String> name_ = new ArrayList<>();
        name_.add(name);
        name_.add(lastName);
        siigo_customer.setName(name_);
        siigo_customer.setCommercialName(name);
        siigo_customer.setBranchOffice(0);
        siigo_customer.setActive(true);
        siigo_customer.setVatResponsible(true);
        
        Siigo_Address siigo_Address = new Siigo_Address();
        siigo_Address.setAddress(address);
        Siigo_City siigo_city = new Siigo_City();
        siigo_city.setCountry_code("Co"); // PENDIENTE PARAMETRIZAR 
        siigo_city.setState_code("11"); // PENDIENTE PARAMETRIZAR
        siigo_city.setCity_code("11001"); // PENDIENTE PARAMETRIZAR
        siigo_Address.setPostal_code("110911"); // PENDIENTE PARAMETRIZAR
        siigo_Address.setCity(siigo_city);
        
        siigo_customer.setAddress(siigo_Address);
        
        
        List<Siigo_Phone> phones = new ArrayList<>();
        Siigo_Phone siigo_phone = new Siigo_Phone();
        if (phone != null) {
            // Elimina espacios, guiones, paréntesis, etc., para validar solo los dígitos
            String digitsOnly = phone.replaceAll("\\D", ""); // Solo números

            if (digitsOnly.length() > 0 && digitsOnly.length() <= 10) {
                siigo_phone.setNumber(digitsOnly);
            } else {
                siigo_phone.setNumber("0"); // Por defecto si es muy largo o vacío
            }
        } else {
            siigo_phone.setNumber("0"); // Por defecto si es null
        }
        siigo_phone.setIndicative("57");
        phones.add(siigo_phone);
        siigo_customer.setPhones(phones);
        
        List<Siigo_Contact> contacts = new ArrayList<>();
        Siigo_Contact contact = new Siigo_Contact();
        contact.setFirst_name(name);
        contact.setLast_name(lastName);
        contacts.add(contact);
        siigo_customer.setContacts(contacts);
        
        siigo_customer.setComments("");
        Siigo_RelatedUsers siigo_RelatedUsers = new Siigo_RelatedUsers();
        siigo_RelatedUsers.setSellerId(282);
        return siigo_customer;
        
    }
    
    /**
     * Obtiene la fecha actual con formato 'yyyy-MM-dd' para las facturas.
     * 
     * @return String con la fecha actual formateada.
     */
    public String getCurrentDateFactura() {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        
        // Define the date format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Format and return the date
        return currentDate.format(formatter);
    }
    
    /**
     * Construye el objeto principal Siigo_DocumentInfo que representa la factura
     * con todos sus componentes (cliente, items, pagos, descuentos, etc).
     * 
     * @param json JSON con toda la información recibida desde SIIP2.
     * @return Siigo_DocumentInfo completo para envío a SIIGO.
     * @throws Exception si falta información requerida o error en formato.
     */
    public Siigo_DocumentInfo getSiigo_DocumentInfoFromJson(JSONObject json) throws Exception {
        
        WebServiceAlegraUtilitarioM util = new WebServiceAlegraUtilitarioM();
        util.generarRegistroAuditoriaInformacionAlegra("JSON Entrante proveniente de WebserviceAlegra", json.toString());
    
        ValoresInternosUsuarioVO valoresInternosUsuarioVO = this.getValoresInternosUsuarioVO(json);
        
        
        Siigo_DocumentInfo siigo_DocumentInfo = new Siigo_DocumentInfo();
        Document document = new Document();
        document.setId(new Integer(27545));
        
        siigo_DocumentInfo.setSeller(new Long(282));
        siigo_DocumentInfo.setDocument(document);
        siigo_DocumentInfo.setDate(getCurrentDateFactura()); 
        //siigo_DocumentInfo.setNumber(new Long(1)); // PENDIENTE FIJAR NUMERO
        Siigo_Customer siigo_Customer = getSiigo_CustomerFromJson(json);
        siigo_DocumentInfo.setCustomer(siigo_Customer);
        Siigo_Stamp siigo_Stamp = new Siigo_Stamp();
        siigo_Stamp.setSend(true);
        siigo_DocumentInfo.setStamp(siigo_Stamp);
        Siigo_Mail siigo_Mail = new Siigo_Mail();
        siigo_Mail.setSend(true);
        siigo_DocumentInfo.setMail(siigo_Mail);
        siigo_DocumentInfo.setObservations("Factura de Venta generada desde Sistema de IPLER SIIP2 a SIIGO - [nroconsecutivo] "+valoresInternosUsuarioVO.getNumero_consecutivo_interno()+" [nrohistoria] "+valoresInternosUsuarioVO.getNumero_historia_usuario()); //
        List<Long> l_retentions = new ArrayList<>();
        l_retentions.add(new Long(0));
        siigo_DocumentInfo.setAdvancePayment(new Double(0));
        
        List<Siigo_Item> l_Siigo_Item = getSiigodItemsFromJsonSIIP2ToWebServiceAlegra(json);
        long totalSinDescuento = 0;
        for(Siigo_Item s:l_Siigo_Item){
            totalSinDescuento = totalSinDescuento+s.getPrice();
        }
        siigo_DocumentInfo.setItems(l_Siigo_Item); 
        List<String> additionalFields = new ArrayList<>(); 
        List<Siigo_Payment> l_Siigo_Payment = getPaymentsFromJsonSIIP2ToWebserviceAlegra(json);
        
        long totalPagos = 0;
        for(Siigo_Payment s5:l_Siigo_Payment){
            totalPagos = totalPagos + s5.getValue().longValue();
        }
        
        siigo_DocumentInfo.setPayments(l_Siigo_Payment); // TERMINADO
        List<Siigo_GlobalDiscount> l_discounts = getDiscountsFromWebServiceAlegraToSIIGOJson(json,totalSinDescuento);
        
        double totalDescuento = 0;
        for(Siigo_GlobalDiscount s3:l_discounts){
            totalDescuento = totalDescuento+s3.getValue();
        }
        double totalConDescuentos = totalSinDescuento-totalDescuento;
        
        double diferencia=0;
        if (totalConDescuentos!=totalPagos){
            diferencia = totalConDescuentos-totalPagos;
            if (diferencia<0) diferencia=diferencia*(-1);
        }
        
        for(Siigo_GlobalDiscount s3:l_discounts){
            if (totalConDescuentos>totalPagos){
                s3.setValue(Double.valueOf(s3.getValue().doubleValue()+diferencia));
            }else{
                s3.setValue(Double.valueOf(s3.getValue().doubleValue()-diferencia));
            }
            break;
        }
        
        siigo_DocumentInfo.setGlobal_discounts(l_discounts); // TERMINADO
        return siigo_DocumentInfo;
    }
    
    /**
     * Calcula descuentos globales a partir del JSON y total sin descuento.
     * @param json JSON con array "discounts".
     * @param totalSinDescuento Total calculado sin aplicar descuentos.
     * @return Lista de objetos Siigo_GlobalDiscount con valores calculados.
     * @throws Exception en caso de error en el formato JSON.
     */
    public List<Siigo_GlobalDiscount> getDiscountsFromWebServiceAlegraToSIIGOJson(JSONObject json,long totalSinDescuento) throws Exception {
        JSONArray jsonDiscounts = json.getJSONArray("discounts");

        List<Siigo_GlobalDiscount> l_discounts = new ArrayList<>();

        for (int i = 0; i < jsonDiscounts.length(); i++) {
            JSONObject jsonDiscount = jsonDiscounts.getJSONObject(i);
            int type = jsonDiscount.getInt("type");
            double amount = jsonDiscount.getDouble("amount");
            String description = jsonDiscount.getString("description");

            Siigo_GlobalDiscount siigo_GlobalDiscount = new Siigo_GlobalDiscount();
            siigo_GlobalDiscount.setId(new Long(5));
            //siigo_GlobalDiscount.setPercentage(Double.valueOf(amount));  // PENDIENTE POR REVISIAR
            double valorConDescuento = (long) (totalSinDescuento*(amount/100));
            siigo_GlobalDiscount.setValue(Double.valueOf(valorConDescuento));
           
            l_discounts.add(siigo_GlobalDiscount);
        }

        return l_discounts;
    }

    
    
    /**
     * Convierte el array "items" del JSON en lista de objetos Siigo_Item.
     * 
     * @param json JSON con array "items".
     * @return Lista de Siigo_Item para la factura.
     * @throws Exception en caso de error.
     */

    public List<Siigo_Item> getSiigodItemsFromJsonSIIP2ToWebServiceAlegra(JSONObject json) throws Exception {
        JSONArray jsonItems = json.getJSONArray("items");

        List<Siigo_Item> items = new ArrayList<Siigo_Item>();

        for (int i = 0; i < jsonItems.length(); i++) {
            JSONObject jsonItem = jsonItems.getJSONObject(i);
            String reference = jsonItem.getString("reference");
            String name = jsonItem.getString("name");
            String description = jsonItem.getString("description");
            int quantity = jsonItem.getInt("quantity");
            long value = jsonItem.getLong("value");

            Siigo_Item siigo_item = new Siigo_Item();
            siigo_item.setCode(reference);
            siigo_item.setDescription(name+"  "+description);
            siigo_item.setQuantity(Long.valueOf(quantity));
            siigo_item.setPrice(Long.valueOf(value));
            
            items.add(siigo_item);
           
        }
        return items;
    }
    
    /**
     * Convierte array "payments" del JSON en lista de Siigo_Payment,
     * aplicando mapeo de método de pago a ID específico SIIGO.
     * 
     * @param json JSON con array "payments".
     * @return Lista de pagos en formato SIIGO.
     * @throws Exception en caso de error.
     */
    public List<Siigo_Payment> getPaymentsFromJsonSIIP2ToWebserviceAlegra(JSONObject json) throws Exception {
        JSONArray jsonPayments = json.getJSONArray("payments");

        List<Siigo_Payment> payments = new ArrayList<Siigo_Payment>();
        
        for (int i = 0; i < jsonPayments.length(); i++) {
            JSONObject jsonPayment = jsonPayments.getJSONObject(i);
            String date = jsonPayment.getString("date");
            long amount = jsonPayment.getLong("amount");
            String paymentMethod = jsonPayment.getString("method");
            String anotations = jsonPayment.getString("anotations");
            JSONObject observations = jsonPayment.getJSONObject("observations");
            
            //String bankAccount = getBankAccount(paymentMethod, observations);

            Siigo_Payment siigo_Payment = new Siigo_Payment();
            
                
            // Parametrizacion de formas de pago.  Se usa las de alegra para envitar tocar el codigo existente
            // en SIIP2 al maximo
            // GET https://api.siigo.com/v1/payment-types?document_type=FV
            if (paymentMethod.equals(this.SIIGO_METHOD_CASH)){
                siigo_Payment.setId(new Long(2512)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_CHECK)){
                siigo_Payment.setId(new Long(7862)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_CREDIT_CARD)){
                siigo_Payment.setId(new Long(2513)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_DEBIT_CARD)){
                siigo_Payment.setId(new Long(2514)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_DEPOSIT)){
                siigo_Payment.setId(new Long(7862)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_TRANSFER)){
                siigo_Payment.setId(new Long(7862)); 
            }else if (paymentMethod.equals(this.SIIGO_METHOD_TRANSFER_PAYU)){
                siigo_Payment.setId(new Long(7859));
            }else if (paymentMethod.equals(this.SIIGO_METHOD_TRANSFER_ADDI)){
                siigo_Payment.setId(new Long(10671));
            }else if (paymentMethod.equals(this.SIIGO_TRANSFER_SUFI)){
                siigo_Payment.setId(new Long(7862)); 
            }else if (paymentMethod.equals(this.SIIGO_TRANSFER_BOLD)){
                siigo_Payment.setId(new Long(10670)); 
            }else if (paymentMethod.equals(this.SIIGO_TRANSFER_GOU)){
                siigo_Payment.setId(new Long(8467)); 
            }
            
            siigo_Payment.setDue_date(date);
            siigo_Payment.setValue(Double.valueOf(Math.floor(Double.valueOf(amount).doubleValue())));
            
            payments.add(siigo_Payment);
            
            
        }

        return payments;
    }
    
    /**
     * 
     * @param paymentMethod
     * @param observations
     * @return 
     */
/*
    public String getBankAccount(String paymentMethod, JSONObject observations) {
        String bankAccount;
        String code;
        try {
            code = observations.getString("code");
        } catch (Exception ex) {
            code = "";
        }

        if (paymentMethod.equals(Alegra_PaymentVO.METHOD_CASH)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_CAJA;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_CHECK)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_CAJA;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_CREDIT_CARD)) {
            if (code.equals("VISA") || code.equals("MasterCard") || code.equals("Colsubsidio")
                    || code.equals("American Express") || code.equals("Exito Tuya") || code.equals("Falabella")) {
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
            } else if (code.equals("Diners Club")) {
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_DAVIVIENDA;
            } else {
                //bankAccount = Alegra_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
            }
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_DEBIT_CARD)) {
            if (code.equals("VISA") || code.equals("MasterCard") || code.equals("Colsubsidio")
                    || code.equals("American Express") || code.equals("Exito Tuya") || code.equals("Falabella")) {
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
            } else if (code.equals("Diners Club")) {
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_DAVIVIENDA;
            } else {
                bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
            }
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_DEPOSIT)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_BANCO_DE_BOGOTA;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_PAYU)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_PAYU;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_ADDI)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_ADDI;
        } else if (paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_SUFI)) {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_SUFI;
        } else {
            bankAccount = Siigo_BankAccountVO.BANK_ACCOUNT_CAJA;
        }

        return bankAccount;
    }
 */   
        /**
     * 
     * @param json
     * @return
     * @throws Exception 
     */
    
    /*
    
    public  getSellerFromJson(JSONObject json) throws Exception {
        JSONObject jsonSeller = json.getJSONObject("seller");

        String name = jsonSeller.getString("name");
        String identification = jsonSeller.getString("identification");
        String observations = jsonSeller.getString("observations");

        Alegra_SellerVO seller = new Alegra_SellerVO();
        seller.setName(name);
        seller.setIdentification(identification);
        seller.setObservations(observations);

        return seller;
    }
    */


    
    
}
