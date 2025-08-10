/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo.GlobalDiscount;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class Siigo_GlobalDiscount {
    private Long id;
    private Double percentage;
    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }
    
    
}
