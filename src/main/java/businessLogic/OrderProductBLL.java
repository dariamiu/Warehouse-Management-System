package businessLogic;

import dao.OrderProductDAO;
import model.OrderProduct;

import java.util.List;
/**
 * Class implements the logic behind the OrderProduct object
 * @author Daria Miu
 *
 */
public class OrderProductBLL {

    private OrderProductDAO orderProductDAO = new OrderProductDAO();
    //private OrderProductValidator orderProductValidator = new OrderProductValidator();

    public List<OrderProduct> findAll(){
        List<OrderProduct> orderProductList;
        orderProductList = orderProductDAO.findAll();
        return orderProductList;
    }

    public OrderProduct findById(int id){
        OrderProduct orderProduct = orderProductDAO.findById(id);
        if(orderProduct == null){
            throw new RuntimeException("invalid id");
        }
        return orderProduct;
    }

    /**
     * inserts into the database table
     * @param orderProduct the orderProduct to be inserted in the table
     */
    public void insert(OrderProduct orderProduct){
        orderProductDAO.insert(orderProduct);
    }

    /**
     * method deletes customer from database table
     * @param orderProduct the orderProduct to delete from database table
     */
    public void delete(OrderProduct orderProduct){
        orderProductDAO.delete(orderProduct);
    }

    /**
     * method updates customer data
     * @param orderProduct to update data
     */
    public void update(OrderProduct orderProduct){
        orderProductDAO.update(orderProduct);
    }

    /**
     * method finds the products from an order given its id
     * @param id id of order
     * @return list of products on that order
     */
    public List<OrderProduct> findByOrderId(int id){
        return orderProductDAO.findByOrderId(id);
    }

    /**
     * method used when adding new products to an order to check if the product that the user wants to add is already on
     * the list of products.
     * @param id_order the id of the order
     * @param id_product the id of the product
     * @return the OrderProduct if any is found, else null
     */
    public OrderProduct findByOrderIdAndProductId(int id_order, int id_product){
        OrderProduct orderProduct= orderProductDAO.findByOrderIdAnProductId(id_order,id_product);
        return orderProduct;
    }
}


