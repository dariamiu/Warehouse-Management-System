package businessLogic;


import dao.ProductDAO;

import model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the logic behind the Product object
 * @author Daria Miu
 *
 */
public class ProductBLL {
    private ProductDAO productDAO = new ProductDAO();
    private ProductValidator productValidator = new ProductValidator();

    /**
     * method finds all customers
     * @return all the customers in a list
     */
    public List<Product> findAll(){
        List<Product> products = new ArrayList<>();
        products = productDAO.findAll();
        return products;
    }
    /**
     * method finds customer with specified id
     * @param id of customer to find
     * @return the found customer
     */
    public Product findById(int id){
        Product product = productDAO.findById(id);
        if(product == null) {
            throw new RuntimeException("invalid id");
        }
        return product;
    }
    /**
     * inserts into the database table
     * @param product the product to be inserted in the table
     */
    public void insert(Product product){
        productValidator.validateCreate(product);
        productDAO.insert(product);
    }

    /**
     * method deletes customer from database table
     * @param product the product to delete from database table
     */
    public void delete(Product product){
        productDAO.delete(product);
    }

    /**
     * method updates customer data
     * @param product to update data
     */
    public void update(Product product){
        productValidator.validateCreate(product);
        productDAO.update(product);
    }

    /**
     * Method fins the product with the given name
     * @param name to search for
     * @return id of customer
     */
    public int findIdByName(String name){
        return productDAO.findIdByName(name);
    }

    /**
     * Method searches for the stock of a product (given by its id)
     * @param id id of the product to find
     * @return the stock of that product
     */
    public int findStockById(int id){
        if(productDAO.findStockById(id) == 0){
            throw new RuntimeException("Out of stock!");
        }
        return productDAO.findStockById(id);
    }

    /**
     * Method finds the name of a product given its id
     * @param id id of product
     * @return the name coresponding to the id
     */
    public String findNameById(int id){
        return productDAO.findNameById(id);
    }
}
