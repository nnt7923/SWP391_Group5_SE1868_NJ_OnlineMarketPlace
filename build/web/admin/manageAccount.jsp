<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Account" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Manage Accounts</title>
        <link rel="stylesheet" href="path/to/your/styles.css">
    </head>
    <body>
        <h1>Manage Accounts</h1>
        <form action="account" method="get">
            <input type="text" name="keyword" placeholder="Search by username">
            <input type="hidden" name="service" value="searchAccount">
            <button type="submit">Search</button>
        </form>
        <table border="1">
            <thead>
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
                        <a href="account?service=updateAccountForm&accountId=<%= account.getAccountId()%>">Edit</a>
                        <a href="account?service=deleteAccount&accountId=<%= account.getAccountId()%>" onclick="return confirm('Are you sure?')">Delete</a>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8">No accounts found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>

        </table>
        <a href="account?service=addAccountForm">Add New Account</a>
    </body>
</html>
