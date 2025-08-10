/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.alegra;

/**
 *
 * @author Administrador
 */
public class Alegra_InventoryVO {

    private String unit;
    public static final String UNIT_SERVICE = "service";

    public Alegra_InventoryVO(String unit) {
        this.unit = unit;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
