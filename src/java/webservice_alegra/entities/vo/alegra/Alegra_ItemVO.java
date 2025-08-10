package webservice_alegra.entities.vo.alegra;

import webservice_alegra.util.Utils;

/**
 * @deprecated
 * @author Yohan Romero
 */
public class Alegra_ItemVO {

    private int id;
    private String reference;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private double discount;
    private Alegra_InventoryVO inventory;
    private String type = TYPE_SIMPLE;
    public static final String TYPE_SIMPLE = "simple";
    public static final String TYPE_KIT = "kit";

    public Alegra_ItemVO() {
    }

    public Alegra_ItemVO(String reference, String name, String description, int quantity, long price, Alegra_InventoryVO inventory) {
        this.reference = reference;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.inventory = inventory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Alegra_InventoryVO getInventory() {
        return inventory;
    }

    public void setInventory(Alegra_InventoryVO inventory) {
        this.inventory = inventory;
    }

    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
}
