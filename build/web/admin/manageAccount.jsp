<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Accounts</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-4">
            <h1 class="text-center">Manage Accounts</h1>
            <form action="account" method="get" class="d-flex justify-content-center mb-3">
                <input type="text" name="keyword" class="form-control w-25 me-2" placeholder="Search by username">
                <input type="hidden" name="service" value="searchAccount">
                <button type="submit" class="btn btn-primary">Search</button>
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
            <div class="text-center mt-3">
                <a href="account?service=addAccountForm" class="btn btn-success">Add New Account</a>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
