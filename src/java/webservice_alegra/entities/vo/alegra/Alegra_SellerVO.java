package webservice_alegra.entities.vo.alegra;

import webservice_alegra.util.Utils;

/**
 * @deprecated 
 * @author Yohan Romero
 */
public class Alegra_SellerVO {

    private int id;
    private String name;
    private String identification;
    private String observations;
    private String status;
    public static final String STATUS_ACTIVE = "active";
    public static final String STATUS_INACTIVE = "inactive";
    public static final int ID_IPLER_VIRTUAL = 11;

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

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
}
