package app;

import presentation.MainController;


import java.sql.SQLException;


import java.util.logging.Logger;

/**
 * The main class of the app, starts the app
 * @author Daria Miu
 */
public class Application {
    protected static final Logger LOGGER = Logger.getLogger(Application.class.getName());

    public static void main(String[] args) throws SQLException {

        new MainController();


    }
}
