package dao;

import databaseConnection.ConnectionFactory;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

/**
 * Class with methods for communicating with the database through queries related to Product(extends AbstractDAO)
 * @author Daria Miu
 */
public class ProductDAO extends AbstractDAO<Product> {
    /**
     * Method fins the product with the given name
     * @param name to search for
     * @return id of customer
     */
    public int findIdByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        List<Product> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return 0;
            return list.get(0).getId_product();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findIdByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Method searches for the stock of a product (given by its id)
     * @param id id of the product to find
     * @return the stock of that product
     */
    public int findStockById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id_product");
        List<Product> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return 0;
            return list.get(0).getStock();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findIdByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Method finds the name of a product given its id
     * @param id id of product
     * @return the name coresponding to the id
     */
    public String findNameById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id_product");
        List<Product> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return null;
            return list.get(0).getName();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findIdByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
}
