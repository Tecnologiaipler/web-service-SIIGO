/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice_alegra.entities.vo.siigo.DocumentInfo.RelatedUsers;

/**
 *
 * @author Brayan Bernal
 * @since 10 Julio 2025 10:31 PM
 */
public class Siigo_RelatedUsers {
    
    private int sellerId;  // Optional, numeric
    private int collectorId;  // Optional, numeric

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public int getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(int collectorId) {
        this.collectorId = collectorId;
    }
    
    
    
}
