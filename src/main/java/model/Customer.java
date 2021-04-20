package model;
/**
 * Class implements the table customer from the database
 * @author Daria Miu
 */
public class Customer {

    private int id_customer;
    private String name;
    private String phone_number;
    private String email_address;
    private String city;
    private String country;

    public Customer(){

    }
    public Customer(int id_customer, String name, String phone_number, String email_address, String city, String country) {
        this.id_customer = id_customer;
        this.name = name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.city = city;
        this.country = country;
    }

    public Customer(String name, String phone_number, String email_address, String city, String country) {
        this.name = name;
        this.phone_number = phone_number;
        this.email_address = email_address;
        this.city = city;
        this.country = country;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
