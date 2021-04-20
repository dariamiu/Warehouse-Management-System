package dao;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import databaseConnection.ConnectionFactory;

/**
 * Generic class with methods for communicating with the database through queries
 * @param <T> type of class
 * @author Daria Miu
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    }

    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM warehouse3.");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();
    }

    public List<T> findAll() {
        // TODO:
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM warehouse3." + type.getSimpleName());
        //System.out.println(query);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();
            return createObjects(resultSet);

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Field idField = type.getDeclaredFields()[0];
        idField.setAccessible(true);
        String query = createSelectQuery(idField.getName());
        List<T> list = new ArrayList<T>();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            list = createObjects(resultSet);
            if(list.size() == 0) return null;
            return list.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();

        try {
            while (resultSet.next()) {
                T instance = type.getDeclaredConstructor().newInstance();
                for (Field field : type.getDeclaredFields()) {
                    Object value = resultSet.getObject(field.getName());
                    System.out.println(field.getName());
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return list;
    }

    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO warehouse3." + type.getSimpleName() + " (");
       try{
           int i = 1;
           Field[] fields = type.getDeclaredFields();
           for (int j = 1; j < fields.length ; j++) {
               Field field = fields[j];
               field.setAccessible(true);
               i++;
                   query.append(field.getName());

               if( i < type.getDeclaredFields().length){
                   query.append(",");
               }
           }
           query.append(") VALUES (");
           i = 1;
           for (int j = 1; j < fields.length ; j++) {
               Field field = fields[j];
               field.setAccessible(true);
               i++;
               if(field.getType().isAssignableFrom(String.class)){
                   query.append("'" + field.get(t) + "'");
               }else{
                   query.append(field.get(t));
               }
               if( i < type.getDeclaredFields().length){
                   query.append(",");
               }
           }
           query.append(")");
       }catch (Exception e){
           e.printStackTrace();
       }
       //System.out.println(query);
       try{
           connection = ConnectionFactory.getConnection();
           statement = connection.prepareStatement(query.toString());
           statement.executeUpdate();
       }catch (Exception e){
          e.printStackTrace();
       }finally {
           ConnectionFactory.close(statement);
           ConnectionFactory.close(connection);
       }
        return t;
    }

    public T delete(T t){
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM warehouse3." + type.getSimpleName() + " WHERE ");

        try{
            Field field = type.getDeclaredFields()[0];
            field.setAccessible(true);
            query.append(field.getName() + "=" + field.get(t));
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(query);
       try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }
    public T update(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        StringBuilder query = new StringBuilder();
        query.append("UPDATE warehouse3." + type.getSimpleName() + " SET ");
        try{
            int i = 0;
            for (Field field : type.getDeclaredFields()) {
                field.setAccessible(true);
                query.append(field.getName() + "=");
                i++;
                //System.out.println(field.get(t));
                if(field.getType().isAssignableFrom(String.class)){
                    query.append("'" + field.get(t) + "'");
                }else{
                    query.append(field.get(t));
                }
                if( i < type.getDeclaredFields().length){
                    query.append(",");
                }
            }

            Field field = type.getDeclaredFields()[0];
            field.setAccessible(true);
            query.append(" WHERE " + field.getName() + "=" + field.get(t));
        }catch (Exception e){
            e.printStackTrace();
        }
        //System.out.println(query);
        try{
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query.toString());
            statement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

}
