package webservice_alegra.entities.vo.alegra;

/**
 * @deprecated 
 * @author Yohan Romero
 */
public class Alegra_PaymentVO {

    private String date;
    private String account;
    private double amount;
    private String paymentMethod;
    private String anotations;
    private String observations;
    private Alegra_CurrencyVO currency;
    // ALEGRA BUILT-IN METHODS
    public static final String METHOD_CASH = "cash";
    public static final String METHOD_CHECK = "check";
    public static final String METHOD_CREDIT_CARD = "credit-card";
    public static final String METHOD_DEBIT_CARD = "debit-card";
    public static final String METHOD_DEPOSIT = "deposit";
    public static final String METHOD_TRANSFER = "transfer";
    // PERSONALIZED SIIP METHODS
    public static final String METHOD_TRANSFER_PAYU = "transfer-payu";
    public static final String METHOD_TRANSFER_ADDI = "transfer-addi";
    public static final String METHOD_TRANSFER_SUFI = "transfer-sufi";

    public Alegra_PaymentVO(String date, String account, double amount, String paymentMethod, String anotations, String observations, Alegra_CurrencyVO currency) {
        this.date = date;
        this.account = account;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.anotations = anotations;
        this.observations = observations;
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAnotations() {
        return anotations;
    }

    public void setAnotations(String anotations) {
        this.anotations = anotations;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Alegra_CurrencyVO getCurrency() {
        return currency;
    }

    public void setCurrency(Alegra_CurrencyVO currency) {
        this.currency = currency;
    }
    
    public String getPaymentMethodSpanishName() {

        if (paymentMethod.equals(METHOD_CASH)) {
            return "Efectivo";
        } else if (paymentMethod.equals(METHOD_CHECK)) {
            return "Cheque";
        } else if (paymentMethod.equals(METHOD_CREDIT_CARD)) {
            return "Tarjeta Crédito";
        } else if (paymentMethod.equals(METHOD_DEBIT_CARD)) {
            return "Tarjeta Débito";
        } else if (paymentMethod.equals(METHOD_DEPOSIT)) {
            return "Consignación";
        } else if (paymentMethod.equals(METHOD_TRANSFER)) {
            return "Transferencia";
        } else if (paymentMethod.equals(METHOD_TRANSFER_PAYU)) {
            return "Internet PayU";
        } else if (paymentMethod.equals(METHOD_TRANSFER_ADDI)) {
            return "Crédito ADDI";
        } else if (paymentMethod.equals(METHOD_TRANSFER_SUFI)) {
            return "Crédito SUFI";
        }

        return null;
    }

    public void correctPersonalizedPaymentMethod() {
        // CONVERT PERSONALIZED METHOD TO ALEGRA BUILT-IN  METHOD
        if (paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_PAYU)
                || paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_ADDI)
                || paymentMethod.equals(Alegra_PaymentVO.METHOD_TRANSFER_SUFI)) {
            paymentMethod = Alegra_PaymentVO.METHOD_TRANSFER;
        }
    }
}
