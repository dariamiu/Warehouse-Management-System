package presentation;

import dao.AbstractDAO;
import model.Customer;
import model.Order;
import model.Product;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Class implements the aspect of the Order frame
 * @author Daria Miu
 */
public class OrderView extends AppFrame<Order> {

    private JButton view = new JButton("view");
    private JButton add = new JButton("add");
    private JButton modify = new JButton("modify");
    private JPanel buttonsPanel = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel viewOrders = new JPanel();
    private JPanel addOrders = new JPanel();
    private JPanel modifyOrder = new JPanel();
    private JPanel wp = new JPanel();

    private JButton searchButton = new JButton("search");
    private JButton viewAll = new JButton("view all");
    private List<Order> orders = new ArrayList<>();
    private JScrollPane scrollPane;
    private JTextField searchText = new JTextField();

    private JComboBox cb;
    private JComboBox cbNames;
    private JComboBox cbStatus;
    private JTextField quantityText = new JTextField();
    private JTextField customerIdText = new JTextField();
    private JTextField customerNameText = new JTextField();


    private JButton viewProducts = new JButton("show");
    private JButton ok = new JButton("ok");
    private JButton addToOrder = new JButton("add");
    private JButton viewBill = new JButton("view bill");
    private JButton finalizeOrder = new JButton("place order");
    private JButton viewCustomersName = new JButton("show");

    private JButton deleteButton = new JButton("delete");
    private JButton modifyStatus = new JButton("modify");

    private JTextField orderIdText = new JTextField();
    private JTextField orderId2Text = new JTextField();

    public OrderView() {

        this.setSize(600, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        actionListeners();
        constructAdd();
        constructView();
        constructModify();
        initialize();
        this.setContentPane(panel);
        this.setVisible(true);

    }
    private void initializeButtons(JPanel buttonsPanel){

        view.setBounds(10,10,200,30);
        add.setBounds(220,10,200,30);
        modify.setBounds(420,10,200,30);

        buttonsPanel.setBackground(new Color(136,139,233));

        buttonsPanel.add(view);
        buttonsPanel.add(add);
        buttonsPanel.add(modify);
    }
    /**
     * Method sets an initial view of the panel
     * @param panel the initial panel when the window opens
     */
    private void initializeWp(JPanel panel){
        wp.setLayout(null);
        JLabel welcome = new JLabel("Orders Management");
        welcome.setFont(new Font("Lucida Console", Font.PLAIN, 30));
        wp.setBackground(new Color(199,201,255));
        welcome.setBounds(130,160,400,30);
        wp.add(welcome);
    }
    /**
     * Method initializes the action listeners of the button that have nothing to do with the app logic(they don't need the
     * controller to initialize them)
     */
    private void actionListeners(){
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(wp);
                panel.remove(addOrders);
                panel.remove(modifyOrder);
                panel.add(viewOrders, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(wp);
                panel.remove(viewOrders);
                panel.remove(modifyOrder);
                panel.add(addOrders, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });
        modify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(wp);
                panel.remove(viewOrders);
                panel.remove(addOrders);
                panel.add(modifyOrder, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });
    }
    /**
     * Method implements the view of the panel in which the orders can be seen in a table
     */
    private void constructView(){
        viewOrders.setLayout(null);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(50,130,490,300);

        searchText.setBounds(239,50,120,30);
        searchButton.setBounds(390,50,80,30);
        viewAll.setBounds(267,460,100,30);
        viewOrders.add(searchButton);
        viewOrders.add(searchText);
        viewOrders.add(viewAll);
        viewOrders.add(scrollPane);

        JLabel idField = new JLabel("introduce id");
        idField.setBounds(150,50,100,30);
        viewOrders.add(idField);
    }
    /**
     * Method implements the view of the panel in which orders can be added
     */
    private void constructAdd(){
        addOrders.setLayout(null);
        JLabel quantity = new JLabel("quantity");
        JLabel customerId = new JLabel("customer id");
        JLabel customerName = new JLabel("customer name");
        JLabel or = new JLabel("or");
        JLabel products = new JLabel("products");

        customerId.setBounds(55,50,100,30);
        customerIdText.setBounds(55,80,80,30);

        or.setBounds(185,80,20,30);

        customerName.setBounds(250,50,100,30);
        String[] init = {};
        cbNames =new JComboBox(init);
        cbNames.setBounds(250,80,200,30);
        cbNames.setEditable(true);
        AutoCompleteDecorator.decorate(cbNames);
        viewCustomersName.setBounds(450,80,80,30);


        products.setBounds(55,200,100,30);
        cb =new JComboBox(init);
        cb.setBounds(55, 230,200,30);
        cb.setEditable(true);
        AutoCompleteDecorator.decorate(cb);
        viewProducts.setBounds(255,230,80,30);

        quantity.setBounds(370,200,100,30);
        quantityText.setBounds(370,230,80,30);

        addToOrder.setBounds(450,230,80,30);

        finalizeOrder.setBounds(200,400,180,30);

        ok.setBounds(270,150,50,30);
        addOrders.add(cb);
        addOrders.add(cbNames);

        addOrders.add(customerId);
        addOrders.add(customerIdText);
        addOrders.add(or);
        addOrders.add(customerName);
        addOrders.add(customerNameText);
        addOrders.add(quantity);
        addOrders.add(quantityText);
        addOrders.add(products);

        addOrders.add(ok);
        addOrders.add(addToOrder);
        addOrders.add(finalizeOrder);
        addOrders.add(viewProducts);
        addOrders.add(viewCustomersName);


    }
    /**
     * Method implements the view of the panel in which orders can be modified
     */
    public void constructModify(){
        modifyOrder.setLayout(null);
        JLabel delete = new JLabel("delete order");
        JLabel modifyOrderStatus = new JLabel("modify order status");
        JLabel orderId = new JLabel("order id");
        JLabel orderId2 = new JLabel("order id");
        JLabel status = new JLabel("status");

        String[] stats = {"Completed","Delivered","Received by customer"};
        cbStatus = new JComboBox(stats);

        delete.setBounds(50,70,100,30);
        orderId.setBounds(50,110,100,30);

        modifyOrderStatus.setBounds(50,180,150,30);
        orderId2.setBounds(50,220,100,30);
        status.setBounds(50,260,100,30);

        orderIdText.setBounds(140,110,50,30);
        orderId2Text.setBounds(140,220,50,30);
        cbStatus.setBounds(140,260,150,30);

        deleteButton.setBounds(200,110,80,30);
        modifyStatus.setBounds(300,260,80,30);

        modifyOrder.add(delete);
        modifyOrder.add(modifyOrderStatus);
        modifyOrder.add(orderId);
        modifyOrder.add(orderId2);
        modifyOrder.add(status);

        modifyOrder.add(deleteButton);
        modifyOrder.add(modifyStatus);

        modifyOrder.add(cbStatus);

        modifyOrder.add(orderIdText);
        modifyOrder.add(orderId2Text);


    }
    /**
     * Method initializes the initial view of the window
     */
    private void initialize(){
        initializeButtons(buttonsPanel);
        initializeWp(wp);
        panel.setLayout(new BorderLayout());
        panel.add(buttonsPanel,BorderLayout.NORTH);
        panel.add(wp,BorderLayout.CENTER);
    }

    public void viewAllActionListener(final ActionListener actionListener){
        viewAll.addActionListener(actionListener);
    }

    /**
     * Method calls the createTable() method from the AppFrame class to create the table, then sets the sizes
     * of the columns of the table
     * @param orders the orders to be displayed in th table
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
        JTable table = createTable(orders);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(50);
        tcm.getColumn(1).setPreferredWidth(50);
        tcm.getColumn(2).setPreferredWidth(200);
        table.setColumnModel(tcm);
        scrollPane.getViewport().add(table);
    }
    /**
     * Method for adding the action listener to button search, will be used in the controller
     * @param actionListener the action given to the button search
     * (the same idea for all the buttons)
     */
    public void searchActionListener(final ActionListener actionListener){
        searchButton.addActionListener(actionListener);
    }
    public String getSearchText(){
        return searchText.getText();
    }

    public void viewProductsActionListener(final ActionListener actionListener){
        viewProducts.addActionListener(actionListener);
    }

    public void setComboBoxProducts(List<Product> products){
        cb.removeAllItems();
        int i = 0;
        for(Product product : products){
            cb.insertItemAt(product.getName(),i);
            i++;
        }
    }
    public void setComboBoxCustomers(List<Customer> customers){
        cbNames.removeAllItems();
        int i = 0;
        for(Customer customer : customers){
            cbNames.insertItemAt(customer.getName(),i);
            i++;
        }
    }

    public String getIdCustomerText(){
        return customerIdText.getText();
    }

    public String getQuantityText(){
        return quantityText.getText();
    }

    public void okActionListener(ActionListener actionListener){
        ok.addActionListener(actionListener);
    }

    public void viewCustomersActionListener(ActionListener actionListener){
        viewCustomersName.addActionListener(actionListener);

    }

    public void addToOrderActionListener(ActionListener actionListener){
        addToOrder.addActionListener(actionListener);
    }

    public void finalizeOrderActionListener(ActionListener actionListener){
        finalizeOrder.addActionListener(actionListener);
    }

    public String getSelectedProduct(){
        String product = ""+ cb.getItemAt(cb.getSelectedIndex());
        return product;
    }

    public String getSelectedName(){
        String name = ""+ cbNames.getItemAt(cbNames.getSelectedIndex());
        return name;
    }
    public String getSelectedStatus(){
        String name = ""+ cbStatus.getItemAt(cbStatus.getSelectedIndex());
        return name;
    }

    public void deleteButtonActionListener(ActionListener actionListener){
        deleteButton.addActionListener(actionListener);
    }
    public void modifyStatusButtonActionListener(ActionListener actionListener){
        modifyStatus.addActionListener(actionListener);
    }

    public String getIdOrder(){
        return orderIdText.getText();
    }
    public String getIdOrder2(){
        return orderId2Text.getText();
    }

}
