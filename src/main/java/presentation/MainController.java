package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Class used to start the application, it generates the Controllers for the other interfaces
 * @author Daria Miu
 */
public class MainController {
    private InitialView initialView;
    private CustomerController customerController;
    private ProductController productController;
    private OrderController orderController;

    public MainController(){
        initialView = new InitialView();
        start();
    }

    /**
     * Method to activate the action listeners of the buttons for accessing one of the sections
     * Customer Order Product
     */
    private void start(){
        initialView.clientActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerController = new CustomerController();
            }
        });

        initialView.productActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productController = new ProductController();

            }
        });

        initialView.orderActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                orderController = new OrderController();
            }
        });
    }
}
