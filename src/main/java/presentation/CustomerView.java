package presentation;

import model.Customer;

import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the aspect of the Customer frame
 * @author Daria Miu
 */
public class CustomerView extends AppFrame<Customer>{

    private JButton view = new JButton("view");
    private JButton add = new JButton("modify");
    private JPanel buttonsPanel = new JPanel();
    private JPanel panel = new JPanel();
    private JPanel viewClients = new JPanel();
    private JPanel addClient = new JPanel();
    private JPanel wp = new JPanel();
    private JButton searchButton = new JButton("search");
    private JButton viewAll = new JButton("view all");
    private List<Customer> customers = new ArrayList<>();
    private JScrollPane scrollPane;
    private JTextField searchText = new JTextField();

    private JTextField nameText = new JTextField();
    private JTextField phoneText = new JTextField();
    private JTextField emailText = new JTextField();
    private JTextField cityText = new JTextField();
    private JTextField countryText = new JTextField();
    private JTextField idText = new JTextField();

    private JButton create = new JButton("create");
    private JButton update = new JButton("update");
    private JButton delete = new JButton("delete");
    private JButton show = new JButton("show");

    public CustomerView() {

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
        JLabel welcome = new JLabel("Clients Management");
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
                panel.remove(addClient);
                panel.add(viewClients, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });

        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(wp);
                panel.remove(viewClients);
                panel.add(addClient, BorderLayout.CENTER);
                validate();
                repaint();
            }
        });
    }

    /**
     * Method implements the view of the panel in which the clients can be seen in a table
     */
    private void constructView(){
        viewClients.setLayout(null);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(50,130,490,300);

        viewClients.add(scrollPane);
        searchText.setBounds(239,50,120,30);
        searchButton.setBounds(390,50,80,30);
        viewAll.setBounds(267,460,100,30);
        viewClients.add(searchButton);
        viewClients.add(searchText);
        viewClients.add(viewAll);

        JLabel nameLabel = new JLabel("introduce name");
        nameLabel.setBounds(120,50,100,30);
        viewClients.add(nameLabel);
    }

    /**
     * Method implements the view of the panel in which customers can be modified
     */
    private void constructAdd(){
        addClient.setLayout(null);
        JLabel id = new JLabel("id");
        JLabel name = new JLabel("name");
        JLabel phone = new JLabel("phone nr");
        JLabel mail = new JLabel("email");
        JLabel city = new JLabel("city");
        JLabel country = new JLabel("country");

        id.setBounds(150,80,60,30);
        idText.setBounds(225,80,80,30);

        name.setBounds(150,120,60,30);
        nameText.setBounds(225,120,200,30);

        phone.setBounds(150, 160, 60,30);
        phoneText.setBounds(225, 160, 200, 30);

        mail.setBounds(150, 200, 60,30);
        emailText.setBounds(225, 200, 200, 30);

        city.setBounds(150, 240, 60, 30);
        cityText.setBounds(225, 240, 200, 30);

        country.setBounds(150, 280, 60,30);
        countryText.setBounds(225, 280, 200, 30);

        create.setBounds(150,360,80,30);
        update.setBounds(250,360,80,30 );
        delete.setBounds(350, 360,80,30);
        show.setBounds(330,80,80,30);

        addClient.add(create);
        addClient.add(update);
        addClient.add(delete);
        addClient.add(show);

        addClient.add(id);
        addClient.add(name);
        addClient.add(phone);
        addClient.add(mail);
        addClient.add(city);
        addClient.add(country);

        addClient.add(idText);
        addClient.add(nameText);
        addClient.add(phoneText);
        addClient.add(emailText);
        addClient.add(cityText);
        addClient.add(countryText);

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
     * @param customers the customers to be displayed in th table
     */
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
        JTable table = createTable(customers);
        TableColumnModel tcm = table.getColumnModel();
        tcm.getColumn(0).setPreferredWidth(30);
        tcm.getColumn(1).setPreferredWidth(150);
        tcm.getColumn(2).setPreferredWidth(100);
        tcm.getColumn(3).setPreferredWidth(180);
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

    public String getPhoneText() {
        return phoneText.getText();
    }

    public void setPhoneText(String s) {
        this.phoneText.setText(s);
    }

    public String getEmailText() {
        return emailText.getText();
    }

    public void setEmailText(String s) {
        this.emailText.setText(s);
    }

    public String getCityText() {
        return cityText.getText();
    }

    public void setCityText(String s) {
        this.cityText.setText(s);
    }

    public String getCountryText() {
        return countryText.getText();
    }

    public void setCountryText(String s) {
        this.countryText.setText(s);
    }

    public String getIdText() {
        return idText.getText();
    }

    public void setIdText(String s) {
        this.idText.setText(s);
    }
}
