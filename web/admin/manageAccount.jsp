<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Accounts</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="path/to/your/styles.css">
    </head>
    <body class="container mt-5">
        <h1 class="mb-4">Manage Accounts</h1>
        
        <form action="account" method="get" class="row g-3 mb-4">
            <div class="col-auto">
                <input type="text" name="keyword" class="form-control" placeholder="Search by username">
            </div>
            <input type="hidden" name="service" value="searchAccount">
            <div class="col-auto">
                <button type="submit" class="btn btn-primary">Search</button>
            </div>
        </form>
        
        <table class="table table-bordered table-striped">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Address</th>
                    <th>Role ID</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
                    if (accounts != null && !accounts.isEmpty()) {
                        for (Account account : accounts) {
                %>
                <tr>
                    <td><%= account.getAccountId()%></td>
                    <td><%= account.getUsername()%></td>
                    <td><%= account.getEmail()%></td>
                    <td><%= account.getPhone()%></td>
                    <td><%= account.getAddress()%></td>
                    <td><%= account.getRoleId()%></td>
                    <td><%= account.getStatus()%></td>
                    <td>
                        <a href="account?service=updateAccountForm&accountId=<%= account.getAccountId()%>" class="btn btn-warning btn-sm">Edit</a>
                        <a href="account?service=deleteAccount&accountId=<%= account.getAccountId()%>" class="btn btn-danger btn-sm" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8" class="text-center">No accounts found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        
        <a href="account?service=addAccountForm" class="btn btn-success mt-3">Add New Account</a>

        <!-- Bootstrap JS Bundle -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
