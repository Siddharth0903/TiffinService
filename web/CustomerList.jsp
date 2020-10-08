<%-- 
    Document   : CustomerList
    Created on : Oct 7, 2020, 10:03:52 AM
    Author     :  Siddharth Patel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Tiffin Service Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguides.net" class="navbar-brand"> Tiffin Service Website </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Customers</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">List of Customers</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Customer</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                
                                <th>Name</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Email</th>
                                <th>Type</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="customer" items="${listCustomer}">

                                <tr>
                                    <td>
                                        <c:out value="${customer.Name}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.Address}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.Phone}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.Eamil}" />
                                    </td>
                                    <td>
                                        <c:out value="${customer.Type}" />
                                    </td>
                                    <td><a href="edit?Phone=<c:out value='${customer.Phone}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?Phone=<c:out value='${customer.Phone}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>