<%-- 
    Document   : dashboard
    Created on : Sep 19, 2024, 4:41:59 PM
    Author     : phuvu
--%>

<%@ page import="model.Account" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    Account account = (Account) session.getAttribute("account");
    if (account == null || account.getRoleId() != 1) {
        response.sendRedirect("../login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    </head>
    <body>

        <div class="container-fluid">
            <h1 class="my-4 text-center">Admin Dashboard</h1>

            <!-- Navigation menu -->
            <nav class="navbar navbar-expand-lg navbar-light bg-light mb-4">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="account?action=list">Qu?n lý Tài kho?n</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="product?action=list">Qu?n lý S?n ph?m</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="category?action=list">Qu?n lý Danh m?c</a>
                    </li>
                </ul>
            </nav>

            <!-- Dashboard Content -->
            <div class="row">
                <!-- Product Section -->
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h3>Qu?n lý S?n ph?m</h3>
                        </div>
                        <div class="card-body">
                            <p>Hi?n t?i có <strong>${productCount}</strong> s?n ph?m.</p>
                            <a href="product?action=list" class="btn btn-primary">Xem s?n ph?m</a>
                            <a href="product?action=add" class="btn btn-success">Thêm s?n ph?m</a>
                        </div>
                    </div>
                </div>

                <!-- Category Section -->
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h3>Qu?n lý Danh m?c</h3>
                        </div>
                        <div class="card-body">
                            <p>Hi?n t?i có <strong>${categoryCount}</strong> danh m?c.</p>
                            <a href="category?action=list" class="btn btn-primary">Xem danh m?c</a>
                            <a href="category?action=add" class="btn btn-success">Thêm danh m?c</a>
                        </div>
                    </div>
                </div>

                <!-- Account Section -->
                <div class="col-lg-4">
                    <div class="card mb-4">
                        <div class="card-header">
                            <h3>Qu?n lý Tài kho?n</h3>
                        </div>
                        <div class="card-body">
                            <p>Hi?n t?i có <strong>${accountCount}</strong> tài kho?n.</p>
                            <a href="account?action=list" class="btn btn-primary">Xem tài kho?n</a>
                            <a href="account?action=add" class="btn btn-success">Thêm tài kho?n</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>



