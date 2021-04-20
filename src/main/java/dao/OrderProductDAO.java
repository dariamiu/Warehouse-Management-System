package dao;

import databaseConnection.ConnectionFactory;
import model.Order;
import model.OrderProduct;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
/**
 * Class with methods for communicating with the database through queries related to OrderProduct(extends AbstractDAO)
 * @author Daria Miu
 */
public class OrderProductDAO extends AbstractDAO<OrderProduct>{

    /**
     * Method inserts orderproduct in database table
     * For the other tables, when inserting a new entity the id is generated automatically in the database so
     * the general insert() function from AbstractDao starts introducing data only from the second field of the table.
     * For this table, the primary key consists of the id_product and id_order together, so that is why it also needs
     * the first field to be inserted by the app, not by the database.
     * @param t orderproduct to insert
     * @return the orderproduct inserted
     */
    public OrderProduct insert(OrderProduct t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO warehouse3." + t.getClass().getSimpleName() + " (");
        try{
            int i = 0;
            Field[] fields = t.getClass().getDeclaredFields();
            for (int j = 0; j < fields.length ; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                i++;
                query.append(field.getName());

                if( i < t.getClass().getDeclaredFields().length){
                    query.append(",");
                }
            }
            query.append(") VALUES (");
            i = 0;
            for (int j = 0; j < fields.length ; j++) {
                Field field = fields[j];
                field.setAccessible(true);
                i++;
                //System.out.println(field.get(t));
                if(field.getType().isAssignableFrom(String.class)){
                    query.append("'" + field.get(t) + "'");
                }else{
                    query.append(field.get(t));
                }
                if( i < t.getClass().getDeclaredFields().length){
                    query.append(",");
                }
            }
            query.append(")");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Method to find the products on a given order
     * @param id id to be found
     * @return products on that id order
     */
    public List<OrderProduct> findByOrderId(int id) {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        Field idOrderField = OrderProduct.class.getDeclaredFields()[0];
        query.append("SELECT * FROM warehouse3.orderproduct WHERE " + idOrderField.getName() + "=" + id);
        System.out.println(query);
        List<OrderProduct> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return null;
            return list;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findByIdOrder " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Method used when adding new products to an order to check if the product that the user wants to add is already on
     * the list of products.
     * @param id_order the id of the order
     * @param id_product the id of the product
     * @return the OrderProduct if any is found, else null
     */
    public OrderProduct findByOrderIdAnProductId(int id_order, int id_product) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        Field idOrderField = OrderProduct.class.getDeclaredFields()[0];
        Field idProductField = OrderProduct.class.getDeclaredFields()[1];
        query.append("SELECT * FROM warehouse3.orderproduct WHERE " +  idOrderField.getName() + "=" + id_order + " AND " + idProductField.getName() + "=" + id_product);
        System.out.println(query);
        List<OrderProduct> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return null;
            return list.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findByIdOrder " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }


}
