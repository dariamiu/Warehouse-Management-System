package presentation;

import businessLogic.CustomerBLL;
import businessLogic.CustomerValidator;
import model.Customer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that controls the CustomerView
 * @author Daria Miu
 */
public class CustomerController {

    private CustomerView customerView;
    private CustomerBLL customerBLL;
    private CustomerValidator customerValidator;

    public CustomerController(){
        customerView = new CustomerView();
        customerBLL = new CustomerBLL();
        customerValidator = new CustomerValidator();
        initializeActionListeners();
    }

    /**
     * Method to initialize all the action listeners for the buttons from the CustomerView.
     * Method retrieves the data from the text fields and JComboBoxes in the interface and sends the data to the BLL to be processed.
     */
    private void initializeActionListeners(){
    customerView.viewAllActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        customerView.setCustomers(customerBLL.findAll());
    }
    });
    customerView.searchActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Customer> customer =  new ArrayList<>();
            try {
                customer.add(customerBLL.findById(customerBLL.findIdByName(customerView.getSearchText())));
                customerView.setCustomers(customer);
            }catch (RuntimeException exception){
                customerView.displayErrorMessage(exception);
            }

        }
    });

    customerView.showActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            Customer customer = new Customer();
            try{
                int id = customerValidator.validateId(customerView.getIdText());
                customer = customerBLL.findById(id);
                customerView.setNameText(customer.getName());
                customerView.setPhoneText(customer.getPhone_number());
                customerView.setEmailText(customer.getEmail_address());
                customerView.setCityText(customer.getCity());
                customerView.setCountryText(customer.getCountry());
            }catch (RuntimeException exception){
                customerView.displayErrorMessage(exception);
            }
        }
    });
    customerView.createActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = new Customer();
            customer.setName(customerView.getNameText());
            customer.setPhone_number(customerView.getPhoneText());
            customer.setEmail_address(customerView.getEmailText());
            customer.setCity(customerView.getCityText());
            customer.setCountry(customerView.getCountryText());
            try{
                customerBLL.insert(customer);
                customerView.displayInformationMessage("Client was successfully added");
            }catch (Exception e1){
               customerView.displayErrorMessage(e1);
            }
        }
    });

    customerView.updateActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = new Customer();
            int id;
            try {
                id = customerValidator.validateId(customerView.getIdText());
                customerBLL.findById(id);
                customer.setId_customer(id);
                customer.setName(customerView.getNameText());
                customer.setPhone_number(customerView.getPhoneText());
                customer.setEmail_address(customerView.getEmailText());
                customer.setCity(customerView.getCityText());
                customer.setCountry(customerView.getCountryText());
            }catch (Exception exception){
                customerView.displayErrorMessage(exception);
            }
            try{
                customerBLL.update(customer);
                customerView.displayInformationMessage("Client was successfully updated");
            }catch (Exception e1){
                customerView.displayErrorMessage(e1);
            }
        }
    });

    customerView.deleteActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Customer customer = new Customer();
            int id;
            try {
                id = customerValidator.validateId(customerView.getIdText());
                customer.setId_customer(id);
                customerBLL.delete(customer);
                customerView.displayInformationMessage("Client was successfully deleted");
            }catch (RuntimeException exception){
                customerView.displayErrorMessage(exception);
            }
        }
    });
    }
}
