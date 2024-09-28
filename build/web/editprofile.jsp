<%-- 
    Document   : editprofile
    Created on : Sep 28, 2024, 7:25:34 AM
    Author     : phuvu
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Profile</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h2>Edit Profile</h2>
            <form action="updateprofile" method="POST">
                <div class="form-group">
                    <label for="username">Full Name</label>
                    <input type="text" class="form-control" id="username" name="username" value="${account.username}" required>
                </div>
                <div class="form-group mt-3">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" id="email" name="email" value="${account.email}" required>
                </div>
                <div class="form-group mt-3">
                    <label for="phone">Phone Number</label>
                    <input type="text" class="form-control" id="phone" name="phone" value="${account.phone}" required>
                </div>
                <div class="form-group mt-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${account.address}" required>
                </div>
                <button type="submit" class="btn btn-primary mt-4">Save Changes</button>
            </form>
        </div>
    </body>
</html>
