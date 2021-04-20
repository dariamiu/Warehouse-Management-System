package businessLogic;


import model.Customer;
import org.junit.platform.commons.util.StringUtils;

/**
 * Class contains methods to validate a Customer
 * @author Daria Miu
 *
 */
public class CustomerValidator {

    /**
     * Method to check is the input is integer
     * @param s the string which needs to be checked if ot contains an integer
     * @return the parsed string
     */
    public int validateId(String s){
        int number;
        try{
            number = Integer.parseInt(s);
        }catch (NumberFormatException e){
            throw new RuntimeException("id must be an integer");
        }
        return number;

    }

    /**
     * Method checks if all the mandatory fields are completed sao that a new entity can be created/updated in the
     * database table
     * @param customer customer to validate
     */
    public void validateCreate(Customer customer){
        if (customer == null) {
            throw new RuntimeException("Client must not be null!");
        }else if(StringUtils.isBlank(customer.getName())){
            throw new RuntimeException("Name field is empty");
        }else if(StringUtils.isBlank(customer.getPhone_number())){
            throw new RuntimeException("Phone number field is empty");
        }else if(StringUtils.isBlank(customer.getCity())){
            throw new RuntimeException("City field is empty");
        }else if(StringUtils.isBlank(customer.getCountry())){
            throw new RuntimeException("Country field is empty");
        }else if(StringUtils.isBlank(customer.getEmail_address())){
            throw new RuntimeException("Email field is empty");
        }
    }
}
