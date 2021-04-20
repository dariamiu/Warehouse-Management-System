package businessLogic;


import dao.OrderDAO;

import model.Order;


import java.util.ArrayList;
import java.util.List;
/**
 * Class implements the logic behind the Order object
 * @author Daria Miu
 *
 */
public class OrderBLL {
    private OrderDAO orderDAO = new OrderDAO();
    private OrderValidator orderValidator = new OrderValidator();

    /**
     * method finds all orders
     * @return all the orders in a list
     */
    public List<Order> findAll(){
        List<Order> orders= new ArrayList<>();
        orders = orderDAO.findAll();
        return orders;
    }
    /**
     * method finds order with specified id
     * @param id of order to find
     * @return the found order
     */
    public Order findById(int id){
        Order order = orderDAO.findById(id);
        if(order == null) {
            throw new RuntimeException("invalid id");
        }
        return order;
    }
    /**
     * inserts into the database table
     * @param order the order to be inserted in the table
     */
    public void insert(Order order){
       // orderValidator.validateCreate(order);
        orderDAO.insert(order);
    }

    /**
     * method deletes customer from database table
     * @param order the order to delete from database table
     */
    public void delete(Order order){
        orderDAO.delete(order);
    }


    /**
     * method updates customer data
     * @param order to update data
     */
    public void update(Order order){
        //orderValidator.validateCreate(order);
        orderDAO.update(order);
    }
}
