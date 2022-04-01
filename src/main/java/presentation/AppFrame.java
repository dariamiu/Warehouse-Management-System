package presentation;

import org.junit.platform.commons.util.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Generic class implements methods common to all the frames of the GUI
 * @param <T> type of class
 * @author Daria Miu
 */
public class AppFrame<T> extends JFrame {
    /**
     * Method to display the errors as messages to the user when the validation of the input fails
     * @param exception message about the error
     */
    public void displayErrorMessage(Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Method to display information message
     * @param message message to display
     */
    public void displayInformationMessage(String message) {
        if (!StringUtils.isBlank(message)) {
            JOptionPane.showMessageDialog(this, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Method that receives a list of objects and generates the header of the table by extracting through reflection
     * the object properties and then populates the table with the values of the elements from the list.
     * Sets the fields names as column names, then adds on each row one element from the list
     * @param objects list of objects
     * @return table for that list of objects
     */
    public JTable createTable(List<T> objects) {

        int size = objects.get(0).getClass().getDeclaredFields().length;
        String columnNames[] = new String[size];
        int i =0;
        for(Field field : objects.get(0).getClass().getDeclaredFields()){
            field.setAccessible(true);
            columnNames[i] = field.getName();
            i++;
        }

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        for (Object object : objects) {
            String[] result = new String[size];
            int j = 0;
            for (Field field: object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    result[j] = field.get(object).toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                j++;
            }
            model.addRow(result);
        }

        JTable table = new JTable( model );
        return table;
    }
}
