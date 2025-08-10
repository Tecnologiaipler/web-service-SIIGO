package webservice_alegra.entities.vo.alegra;

import webservice_alegra.util.Utils;

/**
 * @deprecated
 * @author Yohan Romero
 */
public class Alegra_ContactVO {

    private int id;
    private String name;          // Legal Entity
    private Alegra_NameVO nameObject;    // Person Entity
    private Alegra_IdentificationVO identificationObject;
    private String phonePrimary;
    private String email;
    private boolean ignoreRepeated = false;
    private Alegra_AddressVO address;
    private String[] type = {TYPE_CLIENT};
    private String kindOfPerson;
    private String regime;
    public static final String KIND_LEGAL = "LEGAL_ENTITY";
    public static final String KIND_PERSON = "PERSON_ENTITY";
    public static final String REGIME_SIMPLIFIED = "SIMPLIFIED_REGIME";
    public static final String TYPE_CLIENT = "client";
    public static final String TYPE_PROVIDER = "provider";

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

    public Alegra_NameVO getNameObject() {
        return nameObject;
    }

    public void setNameObject(Alegra_NameVO nameObject) {
        this.nameObject = nameObject;
    }

    public Alegra_IdentificationVO getIdentificationObject() {
        return identificationObject;
    }

    public void setIdentificationObject(Alegra_IdentificationVO identificationObject) {
        this.identificationObject = identificationObject;
    }

    public String getPhonePrimary() {
        return phonePrimary;
    }

    public void setPhonePrimary(String phonePrimary) {
        this.phonePrimary = phonePrimary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIgnoreRepeated() {
        return ignoreRepeated;
    }

    public void setIgnoreRepeated(boolean ignoreRepeated) {
        this.ignoreRepeated = ignoreRepeated;
    }

    public Alegra_AddressVO getAddress() {
        return address;
    }

    public void setAddress(Alegra_AddressVO address) {
        this.address = address;
    }

    public String[] getType() {
        return type;
    }

    public void setType(String[] type) {
        this.type = type;
    }

    public String getKindOfPerson() {
        return kindOfPerson;
    }

    public void setKindOfPerson(String kindOfPerson) {
        this.kindOfPerson = kindOfPerson;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String toJson() {
        return Utils.convertObjectToJSON(this);
    }
}
