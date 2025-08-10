package webservice_alegra.entities.vo.alegra;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author Yohan Romero
 */
public class WooCommerceOrderVO {

    private int id;
    private String status;
    private String currency;
    @SerializedName("discount_total")
    private String discountTotal;
    private String total;
    private WooCommerceBillingVO billing;
    @SerializedName("payment_method")
    private String paymentMethod;
    @SerializedName("payment_method_title")
    private String paymentMethodTitle;
    @SerializedName("date_paid")
    private String datePaid;
    @SerializedName("meta_data")
    private ArrayList<WooCommerceMetaDataVO> metaData;
    @SerializedName("line_items")
    private ArrayList<WooCommerceLineItemVO> lineItems;
    @SerializedName("coupon_lines")
    private ArrayList<WooCommerceCouponLineVO> couponLines;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(String discountTotal) {
        this.discountTotal = discountTotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public WooCommerceBillingVO getBilling() {
        return billing;
    }

    public void setBilling(WooCommerceBillingVO billing) {
        this.billing = billing;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(String datePaid) {
        this.datePaid = datePaid;
    }

    public ArrayList<WooCommerceMetaDataVO> getMetaData() {
        return metaData;
    }

    public void setMetaData(ArrayList<WooCommerceMetaDataVO> metaData) {
        this.metaData = metaData;
    }

    public ArrayList<WooCommerceLineItemVO> getLineItems() {
        return lineItems;
    }

    public void setLineItems(ArrayList<WooCommerceLineItemVO> lineItems) {
        this.lineItems = lineItems;
    }

    public ArrayList<WooCommerceCouponLineVO> getCouponLines() {
        return couponLines;
    }

    public void setCouponLines(ArrayList<WooCommerceCouponLineVO> couponLines) {
        this.couponLines = couponLines;
    }

}
