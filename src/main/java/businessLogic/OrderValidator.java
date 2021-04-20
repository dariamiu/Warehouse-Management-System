package businessLogic;
/**
 * Class contains methods to validate an Order
 * @author Daria Miu
 *
 */
public class OrderValidator {
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
            throw new RuntimeException("field must be an integer");
        }
        return number;
    }

    /**
     * Method checks is a client was selected for an order, throws exception if not
     * @param s the string from the id field in the add order panel
     * @param s1 the string from the JComboBox of client names from the add order panel
     */
    public void validateCreate(String s, String s1){
        if(s.isEmpty() && s1.isEmpty()){
            throw new RuntimeException("no client selected");
        }
    }

}
