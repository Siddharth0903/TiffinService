/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.SQLException;

import DAO.DAOConnection;
import Model.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author  Siddharth Patel
 */
@WebServlet("/")
public class CustomerServlet extends HttpServlet {

   private static final long serialVersionUID = 1;
     DAOConnection DAOConnection;

    public void init() {
       DAOConnection DAOConnection = new DAOConnection();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertCustomer(request, response);
                    break;
                case "/delete":
                    deleteCustomer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCustomer(request, response);
                    break;
                default:
                    listCustomer(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException, NullPointerException{
        List <Customer> listCustomer = DAOConnection.selectAllCustomers();
        request.setAttribute("listCustomer", listCustomer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        long Phone = Long.parseLong(request.getParameter("Phone"));
        Customer existingCustomer = DAOConnection.selectCustomer(Phone);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
        request.setAttribute("customer", existingCustomer);
        dispatcher.forward(request, response);

    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String Name = request.getParameter("Name");
        String Address = request.getParameter("Address");
        long Phone =  Long.parseLong(request.getParameter("Phone"));
        String Email = request.getParameter("Email");
        String Type = request.getParameter("Type");

        Customer newCustomer = new Customer(Name, Address, Phone, Email, Type);
        DAOConnection.insertCustomer(newCustomer);
        response.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String Name = request.getParameter("Name");
        String Address = request.getParameter("Address");
        long Phone =  Long.parseLong(request.getParameter("Phone"));
        String Email = request.getParameter("Email");
        String Type = request.getParameter("Type");
                
        Customer newCustomer = new Customer(Name, Address, Phone, Email, Type);
        DAOConnection.updateCustomer(newCustomer);
        response.sendRedirect("list");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("Phone"));
        DAOConnection.deleteCustomer(id);
        response.sendRedirect("list");

    }
}
