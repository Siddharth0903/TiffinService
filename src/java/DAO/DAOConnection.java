/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Model.Customer;

/**
 *
 * @author  Siddharth Patel
 * conn1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/Java3", "root", "991538414");
 */
public class DAOConnection {
    
    
    private String jdbcURL = "jdbc:mysql://localhost:3306/java3?useSSL=false";
    private String jdbcCustomername = "root";
    private String jdbcPassword = "991538414";

    private static final String INSERT_USERS_SQL = "INSERT INTO customers" + "  (Name, Address, Phone, Email, Type) VALUES " +
        " (?, ?, ?, ?, ?);";

    private static final String SELECT_USER_BY_ID = "select Name, Address, Phone, Email, Type from customers where Phone=?";
    private static final String SELECT_ALL_USERS = "select * from customers";
    private static final String DELETE_USERS_SQL = "delete from customers where Phone = ?;";
    private static final String UPDATE_USERS_SQL = "update customers set Name = ?,Address =?,Email= ?, Type =? where Phone = ?;";

    public DAOConnection() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcCustomername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertCustomer(Customer cust) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, cust.getName());
            preparedStatement.setString(2, cust.getAddress());
            preparedStatement.setLong(3, cust.getPhone());
            preparedStatement.setString(4, cust.getEmail());
            preparedStatement.setString(5, cust.getType());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
        catch (NullPointerException e) {
            
        }
    }

    public Customer selectCustomer(long Phone) {
        Customer cust = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setLong(3, Phone);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                String Email = rs.getString("Email");
                String Type= rs.getString("Type");
                cust = new Customer(Name, Address,Phone, Email,Type);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return cust;
    }

    public List < Customer > selectAllCustomers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Customer > custs = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String Name = rs.getString("Name");
                String Address = rs.getString("Address");
                long Phone = rs.getLong("Phone");
                String Email = rs.getString("Email");
                String Type= rs.getString("Type");
                custs.add(new Customer(Name, Address,Phone, Email,Type));

                
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return custs;
    }

    public boolean deleteCustomer(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateCustomer(Customer cust) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
            
            statement.setString(1, cust.getName());
            statement.setString(2, cust.getAddress());
            statement.setLong(3, cust.getPhone());
            statement.setString(4, cust.getEmail());
            statement.setString(5, cust.getType());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
    
}
