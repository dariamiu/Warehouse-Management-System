package businessLogic;

import dao.CustomerDAO;
import model.Customer;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the logic behind the Customer object
 * @author Daria Miu
 *
 */
public class CustomerBLL {

    private CustomerDAO customerDAO = new CustomerDAO();
    private CustomerValidator customerValidator = new CustomerValidator();

    /**
     * method finds all customers
     * @return all the customers in a list
     */
    public List<Customer> findAll(){
        List<Customer> customers = new ArrayList<>();
        customers = customerDAO.findAll();
        return customers;
    }

    /**
     * method finds customer with specified id
     * @param id of customer to find
     * @return the found customer
     */
    public Customer findById(int id){
        Customer customer = customerDAO.findById(id);
        if(customer == null) {
            throw new RuntimeException("invalid id");
        }
       return customer;
    }

    /**
     * method finds id of customer with specified name
     * @param name of customer to find
     * @return the found customer id
     */
    public int findIdByName(String name){
        if(customerDAO.findIdByName(name) == 0){
            throw new RuntimeException("Wrong name");
        }
       return customerDAO.findIdByName(name);
    }

    /**
     * inserts into the database table
     * @param customer the customer to be inserted in the table
     */
    public void insert(Customer customer){
        customerValidator.validateCreate(customer);
        customerDAO.insert(customer);
    }

    /**
     * method deletes customer from database table
     * @param customer the customer to delete from database table
     */
    public void delete(Customer customer){
       customerDAO.delete(customer);
    }

    /**
     * method updates customer data
     * @param customer to update data
     */
    public void update(Customer customer){
        customerValidator.validateCreate(customer);
        customerDAO.update(customer);
    }
}
