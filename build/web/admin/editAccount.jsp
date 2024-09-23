<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
<%
    Account account = (Account) request.getAttribute("account");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Account</title>
</head>
<body>
    <h1>Edit Account</h1>
    <form action="account?service=updateAccount" method="post">
        <input type="hidden" name="accountId" value="<%= account.getAccountId() %>">

        <label for="username">Username:</label>
        <input type="text" name="username" value="<%= account.getUsername() %>" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" value="<%= account.getPassword() %>" required><br>

        <label for="email">Email:</label>
        <input type="email" name="email" value="<%= account.getEmail() %>" required><br>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" value="<%= account.getPhone() %>" required><br>

        <label for="address">Address:</label>
        <input type="text" name="address" value="<%= account.getAddress() %>" required><br>

        <label for="roleID">Role ID:</label>
        <input type="number" name="roleId" value="<%= account.getRoleId()%>" required><br>

        <label for="status">Status:</label>
        <input type="text" name="status" value="<%= account.getStatus() %>" required><br>

        <button type="submit">Update Account</button>
        <a href="account?service=listAll">Cancel</a>
    </form>
</body>
</html>
