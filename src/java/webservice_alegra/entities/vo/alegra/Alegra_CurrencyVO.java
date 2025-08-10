package webservice_alegra.entities.vo.alegra;

/**
 * @deprecated
 * @author Yohan Romero
 */
public class Alegra_CurrencyVO {

    private String code;
    private double exchangeRate;
    public static final String USD_CURRENCY = "USD";
    public static final String COP_CURRENCY = "COP";  
 
    public Alegra_CurrencyVO(String code, double exchangeRate) {
        this.code = code;
        this.exchangeRate = exchangeRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

}
