package presentation;

import businessLogic.ProductBLL;
import businessLogic.ProductValidator;
import model.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that controls the ProductView
 * @author Daria Miu
 */
public class ProductController {
    private ProductView productView;
    private ProductBLL productBLL;
    private ProductValidator productValidator;

    public ProductController(){
        productBLL = new ProductBLL();
        productView = new ProductView();
        productValidator = new ProductValidator();
        initializeActionListeners();
    }
    /**
     * Method to initialize all the action listeners for the buttons from the ProductView.
     * Method retrieves the data from the text fields and JComboBoxes in the interface and sends the data to the BLL to be processed.
     */
    private void initializeActionListeners(){
        productView.viewAllActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productView.setProducts(productBLL.findAll());
            }
        });
        productView.searchActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Product> products =  new ArrayList<>();
                try {
                    int id = productValidator.validateInt(productView.getSearchText());
                    products.add(productBLL.findById(id));
                    productView.setProducts(products);
                }catch (RuntimeException exception){
                    productView.displayErrorMessage(exception);
                }

            }
        });

        productView.showActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Product product = new Product();
                try{
                    int id = productValidator.validateInt(productView.getIdText());
                    product = productBLL.findById(id);
                    productView.setNameText(product.getName());
                    productView.setDescriptionText(product.getProduct_description());
                    productView.setPriceText(String.valueOf(product.getUnit_price()));
                    productView.setStockText(String.valueOf(product.getStock()));
                }catch (RuntimeException exception){
                    productView.displayErrorMessage(exception);
                }
            }
        });
        productView.createActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();
                product.setName(productView.getNameText());
                product.setProduct_description(productView.getDescriptionText());
                try{
                   product.setUnit_price(productValidator.validateFloat(productView.getPriceText()));
                   product.setStock(productValidator.validateInt(productView.getStockText()));
                }catch (Exception exception){
                    productView.displayErrorMessage(exception);
                }
                try{
                    productBLL.insert(product);
                    productView.displayInformationMessage("Product was successfully added");
                }catch (Exception e1){
                    productView.displayErrorMessage(e1);
                }
            }
        });

        productView.updateActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Product product = new Product();
                int id;
                try {
                    id = productValidator.validateInt(productView.getIdText());
                    productBLL.findById(id);
                    product.setName(productView.getNameText());
                    product.setProduct_description(productView.getDescriptionText());
                    product.setUnit_price(productValidator.validateFloat(productView.getPriceText()));
                    product.setStock(productValidator.validateInt(productView.getStockText()));

                }catch (Exception exception){
                    productView.displayErrorMessage(exception);
                }
                try{
                    productBLL.update(product);
                    productView.displayInformationMessage("Product was successfully updated");
                }catch (Exception e1){
                    productView.displayErrorMessage(e1);
                }
            }
        });

        productView.deleteActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               Product product = new Product();
                int id;
                try {
                    id = productValidator.validateInt(productView.getIdText());
                    product.setId_product(id);
                    productBLL.delete(product);
                    productView.displayInformationMessage("Product was successfully deleted");
                }catch (RuntimeException exception){
                    productView.displayErrorMessage(exception);
                }
            }
        });
    }
}
