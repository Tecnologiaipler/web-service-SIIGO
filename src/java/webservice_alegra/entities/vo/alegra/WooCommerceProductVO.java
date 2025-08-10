package webservice_alegra.entities.vo.alegra;

import java.util.ArrayList;

/**
 *
 * @author Yohan Romero
 */
public class WooCommerceProductVO {

    private int id;
    private String name;
    private ArrayList<WooCommerceProductAttributeVO> attributes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<WooCommerceProductAttributeVO> getAttributes() {
        return attributes;
    }

    public void setAttributes(ArrayList<WooCommerceProductAttributeVO> attributes) {
        this.attributes = attributes;
    }

}
