package presentation;

import model.Product;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 * Class implements the aspect of the Product frame
 * @author Daria Miu
 */
public class ProductView extends AppFrame<Product> {

    private JButton view = new JButton("view");
    private JButton add = new JButton("modify");
    private JPanel buttonsPanel = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel viewProducts = new JPanel();
    private JPanel addProducts = new JPanel();
    private JPanel wp = new JPanel();
    private JButton searchButton = new JButton("search");
    private JButton viewAll = new JButton("view all");
    private List<Product> products = new ArrayList<>();
    private JScrollPane scrollPane;
    private JTextField searchText = new JTextField();

    private JTextField nameText = new JTextField();
    private JTextField descriptionText = new JTextField();
    private JTextField priceText = new JTextField();
    private JTextField stockText = new JTextField();
    private JTextField idText = new JTextField();

    private JButton create = new JButton("create");
    private JButton update = new JButton("update");
    private JButton delete = new JButton("delete");
    private JButton show = new JButton("show");

    public ProductView() {

        this.setSize(600, 600);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        actionListeners();
        constructAdd();
        constructView();
        initialize();
        this.setContentPane(panel);
        this.setVisible(true);

    }
    private void initializeButtons(JPanel buttonsPanel){

        view.setBounds(10,10,200,30);
        add.setBounds(220,10,200,30);

        buttonsPanel.setBackground(new Color(136,139,233));

        buttonsPanel.add(view);
        buttonsPanel.add(add);
    }
    /**
     * Method sets an initial view of the panel
     * @param panel the initial panel when the window opens
     */
    private void initializeWp(JPanel panel){
        wp.setLayout(null);
        JLabel welcome = new JLabel("Products Management");
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
                panel.remove(addProducts);
                panel.add(viewProducts, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(wp);
                panel.remove(viewProducts);
                panel.add(addProducts, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });
    }
    /**
     * Method implements the view of the panel in which the products can be seen in a table
     */
    private void constructView(){
        viewProducts.setLayout(null);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(50,130,490,300);
        viewProducts.add(scrollPane);
        searchText.setBounds(239,50,120,30);
        searchButton.setBounds(390,50,80,30);
        viewAll.setBounds(267,460,100,30);
        viewProducts.add(searchButton);
        viewProducts.add(searchText);
        viewProducts.add(viewAll);

        JLabel idField = new JLabel("introduce id");
        idField.setBounds(150,50,100,30);
        viewProducts.add(idField);

    }
    /**
     * Method implements the view of the panel in which products can be modified
     */
    private void constructAdd(){
        addProducts.setLayout(null);
        JLabel id = new JLabel("id");
        JLabel name = new JLabel("name");
        JLabel description = new JLabel("description");
        JLabel price = new JLabel("price");
        JLabel stock = new JLabel("stock");


        id.setBounds(150,80,60,30);
        idText.setBounds(225,80,80,30);

        name.setBounds(150,120,60,30);
        nameText.setBounds(225,120,200,30);

        description.setBounds(150, 160, 70,30);
        descriptionText.setBounds(225, 160, 200, 30);

        price.setBounds(150, 200, 60,30);
        priceText.setBounds(225, 200, 200, 30);

        stock.setBounds(150, 240, 60, 30);
        stockText.setBounds(225, 240, 200, 30);


        create.setBounds(150,340,80,30);
        update.setBounds(250,340,80,30 );
        delete.setBounds(350, 340,80,30);
        show.setBounds(330,80,80,30);

        addProducts.add(create);
        addProducts.add(update);
        addProducts.add(delete);
        addProducts.add(show);

        addProducts.add(id);
        addProducts.add(name);
        addProducts.add(description);
        addProducts.add(price);
        addProducts.add(stock);

        addProducts.add(idText);
        addProducts.add(nameText);
        addProducts.add(descriptionText);
        addProducts.add(priceText);
        addProducts.add(stockText);

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
     * @param products the products to be displayed in th table
     */
    public void setProducts(List<Product> products) {
        this.products = products;
        JTable table = createTable(products);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(180);
        tcm.getColumn(2).setPreferredWidth(70);
        tcm.getColumn(3).setPreferredWidth(200);
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
    public void showActionListener(final ActionListener actionListener){
        show.addActionListener(actionListener);
    }
    public void createActionListener(final ActionListener actionListener){
        create.addActionListener(actionListener);
    }
    public void updateActionListener(final ActionListener actionListener){
        update.addActionListener(actionListener);
    }
    public void deleteActionListener(final ActionListener actionListener){
        delete.addActionListener(actionListener);
    }

    public String getSearchText(){
        return searchText.getText();
    }

    public String getNameText() {
        return nameText.getText();
    }

    public void setNameText(String s) {
        this.nameText.setText(s);
    }

    public String getDescriptionText() { return descriptionText.getText(); }

    public void setDescriptionText(String s) {
        this.descriptionText.setText(s);
    }

    public String getPriceText() {
        return priceText.getText();
    }

    public void setPriceText(String s) {
        this.priceText.setText(s);
    }

    public String getStockText() {
        return stockText.getText();
    }

    public void setStockText(String s) {
        this.stockText.setText(s);
    }

    public String getIdText() {
        return idText.getText();
    }

    public void setIdText(String s) {
        this.idText.setText(s);
    }

}
