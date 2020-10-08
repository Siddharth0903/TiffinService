<%-- 
    Document   : CustomerForm
    Created on : Oct 7, 2020, 10:03:42 AM
    Author     :  SPhonedharth Patel
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Customer Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
                  integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>


            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="https://www.javaguPhonees.net" class="navbar-brand"> Tiffin Service
                        </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Customers</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${customer != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${customer == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${customer != null}">
                                    Edit Customer
                                </c:if>
                                <c:if test="${customer == null}">
                                    Add New Customer
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${customer != null}">
                            <input type="hidden" name="CPhone" value="<c:out value='${customer.Phone}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Customer Name</label> 
                            <input type="text" value="<c:out value='${customer.Name}' />" class="form-control" name="Name" required="required">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>Customer Address</label> <input type="text" value="<c:out value='${customer.Address}' />" class="form-control" name="Address" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Customer Phone</label> <input type="text" value="<c:out value='${customer.Phone}' />" class="form-control" name="Phone" required="required">
                        </fieldset>
                        
                         <fieldset class="form-group">
                            <label>Customer Email</label> <input type="text" value="<c:out value='${customer.Email}' />" class="form-control" name="Email" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Tiffin Type</label> <input type="text" value="<c:out value='${customer.Type}' />" class="form-control" name="Type" required="required">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

        </html>