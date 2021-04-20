package model;
/**
 * Class implements the table product from the database
 * @author Daria Miu
 */
public class Product {

    private int id_product;
    private String name;
    private float unit_price;
    private String product_description;
    private int stock;

    public Product(){

    }

    public Product(int id_product, String name,float unit_price,String product_description, int stock ) {
        this.id_product = id_product;
        this.name = name;
        this.unit_price = unit_price;
        this.product_description = product_description;
        this.stock = stock;

    }

    public Product(String name, float unit_price, String product_description, int stock) {
        this.name = name;
        this.unit_price = unit_price;
        this.product_description = product_description;
        this.stock = stock;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public float getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(float unit_price) {
        this.unit_price = unit_price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
