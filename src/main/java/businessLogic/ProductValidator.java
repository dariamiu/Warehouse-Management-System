package businessLogic;

import model.Product;
import org.junit.platform.commons.util.StringUtils;
/**
 * Class contains methods to validate a Product
 * @author Daria Miu
 *
 */
public class ProductValidator {

    /**
     * Method to check is the input is integer
     * @param s the string which needs to be checked if ot contains an integer
     * @return the parsed string
     */
    public int validateInt(String s){
        int number;
        try{
            number = Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new RuntimeException("field must be an integer");
        }
        return number;

    }
    /**
     * Method to check is the input is float
     * @param s the string which needs to be checked if ot contains an float
     * @return the parsed string
     */
    public float validateFloat(String s){
        float number;
        try{
            number = Float.parseFloat(s);
        }catch (NumberFormatException e){
            throw new RuntimeException("price must be a float number");
        }
        return number;

    }
    /**
     * Method checks if all the mandatory fields are completed sao that a new entity can be created/updated in the
     * database table
     * @param product product to validate
     */
    public void validateCreate(Product product){
        if (product == null) {
            throw new RuntimeException("Product must not be null!");
        }else if(StringUtils.isBlank(product.getName())){
            throw new RuntimeException("Name field is empty");
        }else if(StringUtils.isBlank(String.valueOf(product.getUnit_price()))){
            throw new RuntimeException("Price field is empty");
        }
    }

    /**
     * Checks is the user can add the product to the order (if it is in stock)
     * @param stock the stock of some product
     * @param quantity the quantity wanted by a customer
     */
    public void stockValidator(int stock, int quantity){
        if(stock < quantity) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Not enough stock, only ");
            stringBuilder.append(stock + " available");
            throw new RuntimeException(stringBuilder.toString());
        }
    }
}
