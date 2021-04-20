package model;
/**
 * Class implements the table order from the database
 * @author Daria Miu
 */
public class Order {
    private int id_order;
    private int id_customer;
    private String order_status;
    private float totalPrice;

    public Order(){

    }

    public Order(int id_order, int id_customer, String order_status, float totalPrice) {
        this.id_order = id_order;
        this.id_customer = id_customer;
        this.order_status = order_status;
        this.totalPrice = totalPrice;
    }

    public Order(int id_customer, String order_status, float totalPrice) {
        this.id_customer = id_customer;
        this.order_status = order_status;
        this.totalPrice = totalPrice;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getStatus_of_order() {
        return order_status;
    }

    public void setStatus_of_order(String status_of_order) {
        this.order_status = status_of_order;
    }

    public int getId_customer() {
        return id_customer;
    }

    public void setId_customer(int id_customer) {
        this.id_customer = id_customer;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setOrder_status(String order_status) {
        this.order_status = order_status;
    }

    public String getOrder_status() {
        return order_status;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
