package webservice_alegra.entities.vo.alegra;

/**
 * @deprecated
 * @author Yohan Romero
 */
public class Alegra_DiscountVO {

    private int type;
    private double amount;
    private String description;
    public static final int TYPE_VALUE = 1;
    public static final int TYPE_PERCENTAGE = 2;

    public Alegra_DiscountVO(int type, double amount, String description) {
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
