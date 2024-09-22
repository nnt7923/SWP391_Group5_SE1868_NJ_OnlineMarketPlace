<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Account</title>
</head>
<body>
    <h1>Add New Account</h1>
    <form action="account?service=addAccount" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" required><br>

        <label for="password">Password:</label>
        <input type="password" name="password" required><br>

        <label for="email">Email:</label>
        <input type="email" name="email" required><br>

        <label for="phone">Phone:</label>
        <input type="text" name="phone" required><br>

        <label for="address">Address:</label>
        <input type="text" name="address" required><br>

        <label for="roleID">Role ID:</label>
        <input type="number" name="roleID" required><br>

        <label for="status">Status:</label>
        <input type="text" name="status" required><br>

        <button type="submit">Add Account</button>
        <a href="account?service=listAll">Cancel</a>
    </form>
</body>
</html>
