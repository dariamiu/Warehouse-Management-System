package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Class implements the initial view of the GUI
 * @author Daria Miu
 */
public class InitialView extends JFrame {


    private JButton client;

    private JButton product;

    private JButton order;

    public InitialView(){
        this.setTitle("WAREHOUSE");
        this.setSize(400,400);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);

        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        initializeForm(panel);

        panel.setBackground(new Color(199,201,255));
        this.setContentPane(panel);
        this.setVisible(true);

    }
    private void initializeForm(JPanel panel){
        client = new JButton("CLIENTS");
        client.setBounds(100,50,200,20);

        product = new JButton("PRODUCTS");
        product.setBounds(100,100,200,20);

        order = new JButton("ORDERS");
        order.setBounds(100,150,200,20);


        panel.add(client);
        panel.add(product);
        panel.add(order);

    }

    public void clientActionListener(final ActionListener actionListener){
        client.addActionListener(actionListener);
    }

    public void productActionListener(final ActionListener actionListener){
        product.addActionListener(actionListener);
    }

    public void orderActionListener(final ActionListener actionListener){
        order.addActionListener(actionListener);
    }
}
