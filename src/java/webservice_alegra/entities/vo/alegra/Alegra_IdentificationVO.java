package webservice_alegra.entities.vo.alegra;

/**
 *
 * @author Yohan Romero
 */
public class Alegra_IdentificationVO {

    private String type;
    private String number;
    public static final String TYPE_RC = "RC";    // Registro civil
    public static final String TYPE_TI = "TI";    // Tarjeta de identidad
    public static final String TYPE_CC = "CC";    // Cédula de ciudadanía
    public static final String TYPE_TE = "TE";    // Tarjeta de extranjería
    public static final String TYPE_CE = "CE";    // Cédula de extranjería
    public static final String TYPE_NIT = "NIT";  // Número de identificación tributaria
    public static final String TYPE_PP = "PP";    // Pasaporte
    public static final String TYPE_DIE = "DIE";  // Documento de identificación extranjero

    public Alegra_IdentificationVO(String type, String number) {
        this.type = type;
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
