package webservice_alegra.entities.vo.alegra;

/**
 *
 * @author Yohan Romero
 */
public class WooCommerceCouponLineVO {

    private int id;
    private String code;
    private String discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

}
