package dao;

import databaseConnection.ConnectionFactory;
import model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
/**
 * Class with methods for communicating with the database through queries related to Customer(extends AbstractDAO)
 * @author Daria Miu
 */
public class CustomerDAO extends AbstractDAO<Customer> {

    /**
     * Method fins the customer with the given name
     * @param name to search for
     * @return id of customer
     */
    public int findIdByName(String name){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("name");
        List<Customer> list = new ArrayList<>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return 0;
            return list.get(0).getId_customer();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:findIdByName " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }
}
