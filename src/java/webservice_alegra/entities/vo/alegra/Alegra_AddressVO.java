package webservice_alegra.entities.vo.alegra;

/**
 * @deprecated
 * @author Yohan Romero
 */
public class Alegra_AddressVO {

    private String address;
    private String city;
    private String department;
    private String country;

    public Alegra_AddressVO(String address, String city, String department, String country) {
        this.address = address;
        this.city = city;
        this.department = department;
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
