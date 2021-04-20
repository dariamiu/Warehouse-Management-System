package model;
/**
 * Class implements the table orderproduct from the database
 * @author Daria Miu
 */
public class OrderProduct {

    private int id_order;
    private int id_product;
    private int quantity;

    public OrderProduct(int id_order, int id_product, int quantity) {
        this.id_order = id_order;
        this.id_product = id_product;
        this.quantity = quantity;
    }

    public OrderProduct(){

    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
